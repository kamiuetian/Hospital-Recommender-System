package recommender.hospital.datasets;
import java.util.*;

/*
 * This class contains all the hospitals 
 * Fields are hopital id,hospital_Name list of hospital departments.
 * 
 * */
public class HospitalData {
	private int hospital_Id;
	private String hospital_Name;
	private double hospital_distance;
	private boolean service_emergency;
	private List<String> department_List =new LinkedList<String>();
	/*Constructor*/
	public HospitalData(int h_id,String h_name,boolean srvc_emrg, List<String> dpt_list)
	{
		this.hospital_Id=h_id;
		this.hospital_Name=h_name;
		this.department_List=dpt_list;
		this.service_emergency=srvc_emrg;
	}
	/*get the Hospital Id*/
	public int gethospital_Id()
	{
		return this.hospital_Id;
	}
	
	/*get the hospital Name*/
	public String gethospital_Name()
	{
		return this.hospital_Name;
	}
	/*get the Hospital distance*/
	public double gethospital_distance()
	{
		return this.hospital_distance;
	}
	/*Get emergency service status*/
	public boolean gethospital_srvc()
	{
		return this.service_emergency;
	}
	/*Get List Of departments in a hospital*/
	public List<String> getdepartment_List()
	{
		return this.department_List;
	}
	
	/*Set hospital Id*/
	public void sethospital_Id(int h_id)
	{
		this.hospital_Id=h_id;
	}
	/*Set hospital Name*/
	public void sethospitalName(String h_nmae)
	{
		this.hospital_Name=h_nmae;
	}
	/*Set emergency service status*/
	public void sethospital_srvc(boolean srvc)
	{
		this.service_emergency=srvc;
	}
	/*Set hospital distance*/
	public void sethospital_distance(double h_dist)
	{
		this.hospital_distance=h_dist;
	}
	/*Set departmentList*/
	public void setdisease_List(List<String> d_list)
	{
		this.department_List=d_list;
	}
}
