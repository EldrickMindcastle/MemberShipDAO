package MemberShipDAOlib;
import java.util.ArrayList;
public interface OrderDAO {
    public void add(Order o);
    public Product FindByPID(int PID);
    public  ArrayList<Product> FindByCID(int CID);
}