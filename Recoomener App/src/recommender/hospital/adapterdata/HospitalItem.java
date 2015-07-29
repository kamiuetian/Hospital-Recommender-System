package recommender.hospital.adapterdata;

public class HospitalItem {
	private String title;
	private int icon;
	private int id;
	private String phone;
	private String email;
	private String address;
	public HospitalItem(){}

	public HospitalItem(String title, int icon,int id,String phone,String email, String address){
		this.title = title;
		this.icon = icon;
		this.id=id;
		this.phone=phone;
		this.email=email;
		this.address=address;
	}
	
	
	public String getTitle(){
		return this.title;
	}
	
	public int getIcon(){
		return this.icon;
	}
	
	public int getId()
	{
		return this.id;
	}
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setIcon(int icon){
		this.icon = icon;
	}
	
	public void setId(int id)
	{
		this.id=id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
