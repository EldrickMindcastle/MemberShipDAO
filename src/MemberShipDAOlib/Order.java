package MemberShipDAOlib;

import java.util.ArrayList;

public class Order {
    public int id;
    public int customer_id;
    public ArrayList<OrderDetail> Detail;
    
	public Order(int id, int customer_id, ArrayList<OrderDetail> detail) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		Detail = detail;
	}    
}