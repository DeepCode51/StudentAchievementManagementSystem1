package chengji.bean;


//成绩表

public class Chengji {

    private int id;//主键
	
	private String fenshu;//分数
	
	private int kcid;//关联课程id  
	
	private Kc  kc;//关联课程实体
	
	private int userid;//关联学生id 
	
	private User user;//关联学生实体 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFenshu() {
		return fenshu;
	}

	public void setFenshu(String fenshu) {
		this.fenshu = fenshu;
	}

	public int getKcid() {
		return kcid;
	}

	public void setKcid(int kcid) {
		this.kcid = kcid;
	}

	public Kc getKc() {
		return kc;
	}

	public void setKc(Kc kc) {
		this.kc = kc;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

	


	
	
}
