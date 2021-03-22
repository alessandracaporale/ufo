package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.List;

public class TestDB {

	public static void main(String[] args) {
		
		String jdbcURLS="jdbc:mysql://localhost/ufo_sightings?user=root&password=root";
		
		try {
			Connection conn = DriverManager.getConnection(jdbcURLS);
			
			Statement st=conn.createStatement();
			
			String sql = "SELECT DISTINCT shape\r\n"
					+ "FROM sighting\r\n"
					+ "WHERE SHAPE<>''\r\n"
					+ "ORDER BY shape ASC";
			
			ResultSet res = st.executeQuery(sql);
			
			List<String> formeUFO =new ArrayList<>();
			while(res.next()) {
				String forma = res.getString("shape");
				formeUFO.add(forma);
			}
			st.close();
			
			System.out.println(formeUFO);
			
			String sql2="SELECT COUNT(*) FROM sighting WHWEW shape= ? ";
			String shapeScelta = "circle";
			
			PreparedStatement st2=conn.prepareStatement(sql2);
			st2.setString(1, shapeScelta);
			ResultSet res2=st2.executeQuery();
			res2.first();
			int count =res2.getInt("cnt");
			st2.close();
			
			System.out.println("UFO di forma "+shapeScelta+" sono: "+count);
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	
	
	
	
	}

}
