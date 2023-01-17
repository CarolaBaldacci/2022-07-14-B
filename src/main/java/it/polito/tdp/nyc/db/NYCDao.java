package it.polito.tdp.nyc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polito.tdp.nyc.model.Adiacenza;
import it.polito.tdp.nyc.model.Hotspot;
import it.polito.tdp.nyc.model.Zona;

public class NYCDao {
	
	public List<Hotspot> getAllHotspot(){
		String sql = "SELECT * FROM nyc_wifi_hotspot_locations";
		List<Hotspot> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Hotspot(res.getInt("OBJECTID"), res.getString("Borough"),
						res.getString("Type"), res.getString("Provider"), res.getString("Name"),
						res.getString("Location"),res.getDouble("Latitude"),res.getDouble("Longitude"),
						res.getString("Location_T"),res.getString("City"),res.getString("SSID"),
						res.getString("SourceID"),res.getInt("BoroCode"),res.getString("BoroName"),
						res.getString("NTACode"), res.getString("NTAName"), res.getInt("Postcode")));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
	public List<String> getAllBorghi(){
		String sql = "SELECT DISTINCT Borough FROM nyc_wifi_hotspot_locations";
		List<String> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getString("Borough"));
			}
			Collections.sort(result);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
	public List<Zona> getZonaBorgo(String b){
		String sql = "SELECT DISTINCT NTAName"
				+ " FROM nyc_wifi_hotspot_locations"
				+ " WHERE Borough=?"
				+ " GROUP BY NTAName";
		List<Zona> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, b);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Zona(res.getString("NTAName")));				   
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
	}
	
	public List<Adiacenza> getArchi(String b){
		String sql = "SELECT DISTINCT n1.NTAName, n2.NTAName,COUNT(DISTINCT n1.SSID) AS pesoN1, COUNT(DISTINCT n2.SSID) AS pesoN2"
				+ " FROM nyc_wifi_hotspot_locations n1,  nyc_wifi_hotspot_locations n2"
				+ " WHERE n1.Borough= ? AND n1.Borough=n2.Borough"
				+ " GROUP BY n1.NTACode, n2.NTACode";
		List<Adiacenza> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, b);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				int pesoN1=res.getInt("pesoN1");
				int pesoN2=res.getInt("pesoN2");
				int peso =(pesoN1+pesoN2);
				if(pesoN1<pesoN2) {
					Zona z1=new Zona(res.getString("n1.NTAName"));
					Zona z2=new Zona(res.getString("n2.NTAName"));
					result.add(new Adiacenza(z1, z2,peso));
				}
				
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
	}
	
	
}
