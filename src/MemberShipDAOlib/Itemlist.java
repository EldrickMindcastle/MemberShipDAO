package MemberShipDAOlib;

import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.RETURN;

public class Itemlist {
	public ArrayList<String> product_id ;
	
	
	public  Itemlist() {
		ArrayList<String> A = new ArrayList<String>();
		this.product_id= A;
	}

	public ArrayList<String> getlist(){
		return product_id;		
	}
	
}