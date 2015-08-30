package MemberShipDAOlib;


public class Member {
		public int member_id;
		public String member_acc;
		public String member_pw;
		public String first_name;
		public String last_name;
		public int tmp_pw;
		public String del_date;
	
		public Member(int member_id, String member_acc, String member_pw, String first_name, String last_name,
				int tmp_pw, String del_date) {
			super();
			this.member_id = member_id;
			this.member_acc = member_acc;
			this.member_pw = member_pw;
			this.first_name = first_name;
			this.last_name = last_name;
			this.tmp_pw = tmp_pw;
			this.del_date = del_date;
		}

	
}