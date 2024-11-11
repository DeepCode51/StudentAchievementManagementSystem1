package chengji.bean;


//用户

public class User {

    private int id;//主键
	
	private String username;//用户名 
	
	private String truename;//姓名
	
	private String sex;//性别  男 女
	
	private String password;//密码
	
	private String phone;//手机号码
	
	private String role;//用户角色  管理员  学生用户

	private int banjiid;//关联班级id 

	private Banji banji;//关联班级实体

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getKcid() {
		return banjiid;
	}

	public void setKcid(int banjiid) {
		this.banjiid = banjiid;
	}

	public int getBanjiid() {
		return banjiid;
	}

	public void setBanjiid(int banjiid) {
		this.banjiid = banjiid;
	}

	public Banji getBanji() {
		return banji;
	}

	public void setBanji(Banji banji) {
		this.banji = banji;
	}

	
	
	
	
	




	
	
}
