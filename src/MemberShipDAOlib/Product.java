package MemberShipDAOlib;

public class Product {
	public int id ;
	public String name;
	public double price;
	public String description ;
	public String last_update;
	public int category_id;
	public String customer_mail;
	
	public Product(int id, String name, double price, String description,String last_update, int category_id,String customer_mail) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.last_update = last_update;
		this.category_id = category_id;
		this.customer_mail = customer_mail;
	}	
}
