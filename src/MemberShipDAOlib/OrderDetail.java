package MemberShipDAOlib;
public class OrderDetail {
    public int customer_order_id;
    public int product_id;
    public int quantity;
    
	public OrderDetail(int customer_order_id, int product_id, int quantity) {
		super();
		this.customer_order_id = customer_order_id;
		this.product_id = product_id;
		this.quantity = quantity;
	}
    
}