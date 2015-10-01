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

public class OrderDAOImpl implements OrderDAO {
	final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	final String CONN_STRING = "jdbc:mysql://localhost:3306/mydb?" + "user=root&password=mysql2015";

	@Override
	public void add(Order o) {
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(CONN_STRING);
			conn.setAutoCommit(false);
			double amount = 0;
			PreparedStatement pstmt = conn.prepareStatement(
					"Insert into customer_order (customer_id , amount,confirmation_number) values (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, o.customer_id);
			pstmt.setDouble(2, amount);
			pstmt.setInt(3, 15);

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			int auto_id = rs.getInt(1);
			rs.close();
			for (OrderDetail od : o.Detail) {
				double pp = 0;
				PreparedStatement pstmt2 = conn.prepareStatement(
						"Insert into order_detail (customer_order_id, product_id,quantity) values (?, ?,?)");
				pstmt2.setInt(1, auto_id);
				pstmt2.setInt(2, od.product_id);
				pstmt2.setInt(3, od.quantity);
				pstmt2.executeUpdate();
				PreparedStatement pstmt3 = conn.prepareStatement("Select price from product where id = ?");
				pstmt3.setInt(1, od.product_id);
				ResultSet rs3 = pstmt3.executeQuery();
				pp = rs3.getDouble(1);
				amount = amount + pp;
			}

			PreparedStatement pstmt4 = conn.prepareStatement("Update customer_order Set amount = ? Where id = ?");
			pstmt4.setDouble(1, amount);
			pstmt4.setInt(2, auto_id);
			pstmt4.executeUpdate();

			conn.commit();
			conn.setAutoCommit(true);
			conn.close();

		} catch (ClassNotFoundException ex) {
			Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public Product FindByPID(int PID) {
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(CONN_STRING);
			PreparedStatement pstmt = conn.prepareStatement("Select * from product where id = ?");
			pstmt.setInt(1, PID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Product P = new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4),
						rs.getString(5), rs.getInt(6), rs.getString(7));
				return P;
			} else {
				return null;
			}

		} catch (SQLException ex) {
			Logger.getLogger(MemberShipDAOImp.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(MemberShipDAOImp.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	@Override
	public ArrayList<Product> FindByCID(int CID){
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(CONN_STRING);
			Statement stmt = conn.createStatement();
			ArrayList<Product> goods = new ArrayList<Product>();
			PreparedStatement pstmt = conn.prepareStatement("Select * from product where category_id = ?");
			pstmt.setInt(1, CID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				goods.add(new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4),	rs.getString(5), rs.getInt(6), rs.getString(7)));
			}
			rs.close();
            stmt.close();
            conn.close();
			return goods;

		} catch (SQLException ex) {
			Logger.getLogger(MemberShipDAOImp.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(MemberShipDAOImp.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

}
