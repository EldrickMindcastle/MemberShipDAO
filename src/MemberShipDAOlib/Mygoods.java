package MemberShipDAOlib;
import java.util.ArrayList;
public class Mygoods {
	public String member_acc;
    public ArrayList<Product> Product;
    
	public Mygoods(String member_acc, ArrayList<Product> mygoods) {
		super();
		this.member_acc = member_acc;
		Product = mygoods;
	}
    	
}
