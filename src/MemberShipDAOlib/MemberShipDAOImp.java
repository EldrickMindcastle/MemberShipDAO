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

public class MemberShipDAOImp implements MemberShipDAO {
	final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	final String CONN_STRING = "jdbc:mysql://localhost:3306/mydb?" + "user=root&password=mysql2015";

	@Override
	public void add(Member member) {
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(CONN_STRING);
			PreparedStatement pstmt = conn.prepareStatement(
					"Insert into customer_acc (mail,password,first_name,last_name,tmp_pw ) values (?,?,?,?,?)");
			pstmt.setString(1, member.member_acc);
			pstmt.setString(2, member.member_pw);
			pstmt.setString(3, member.first_name);
			pstmt.setString(4, member.last_name);
			pstmt.setInt(5, member.tmp_pw);

			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException ex) {
			Logger.getLogger(MemberShipDAOImp.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(MemberShipDAOImp.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void update(Member member) {
		// TODO Auto-generated method stub
	}

	@Override
	public Member FindByAcc(String member_acc) {
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(CONN_STRING);
			PreparedStatement pstmt = conn.prepareStatement("Select * from customer_acc where mail = ?");
			pstmt.setString(1, member_acc);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Member s = new Member(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getString(7));
				return s;
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
	public boolean Login(String member_acc, String member_pw) {
		boolean Ans = false;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(CONN_STRING);
			PreparedStatement pstmt = conn.prepareStatement("Select password from customer_acc where mail = ?");
			pstmt.setString(1, member_acc);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
					if( rs.getString(1).equals(member_pw)){
						Ans = true;
						}else{
						Ans = false ;	
						}
				return Ans;
			} else {
				return false;
			}
		} catch (SQLException ex) {
			Logger.getLogger(MemberShipDAOImp.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(MemberShipDAOImp.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	@Override
	public void resetpw(String member_acc) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean reg_confirm(String member_acc,Integer tmp_pw) {
		boolean Ans = false;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(CONN_STRING);
			PreparedStatement pstmt = conn.prepareStatement("Select * from customer_acc where mail = ?");
			pstmt.setString(1, member_acc);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Member s = new Member(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getString(7));
					if(s.tmp_pw == tmp_pw){
						Ans = true;
						}else{
						Ans = false ;	
						}
				return Ans;
			} else {
				return false;
			}
		} catch (SQLException ex) {
			Logger.getLogger(MemberShipDAOImp.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(MemberShipDAOImp.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	

}
