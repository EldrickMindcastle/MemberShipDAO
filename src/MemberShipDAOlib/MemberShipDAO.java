package MemberShipDAOlib;


public interface MemberShipDAO {
	public void add(Member member);
	public void update(Member member);
	public Member FindByAcc(String member_acc);
	public boolean Login(String member_acc,String member_pw) ;
	public void resetpw(String member_acc);
	public boolean reg_confirm(String member_acc,Integer tmp_pw);
}
