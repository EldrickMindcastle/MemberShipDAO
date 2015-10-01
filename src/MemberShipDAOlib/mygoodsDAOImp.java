package MemberShipDAOlib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mygoodsDAOImp implements mygoodsDAO {
	final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	final String CONN_STRING = "jdbc:mysql://localhost:3306/mydb?" + "user=root&password=mysql2015";
	@Override
	public ArrayList<Product> Findmygoods(String member_acc) {
		
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(CONN_STRING);
			Statement stmt = conn.createStatement();
			ArrayList<Product> mygoods = new ArrayList<Product>();
			PreparedStatement pstmt = conn.prepareStatement("Select * from product where customer_mail = ?");
			pstmt.setString(1, member_acc);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				mygoods.add(new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4),rs.getString(5), rs.getInt(6), rs.getString(7)));
			}
			rs.close();
            stmt.close();
            conn.close();
			return mygoods;

		} catch (SQLException ex) {
			Logger.getLogger(MemberShipDAOImp.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(MemberShipDAOImp.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	}
