package recommender.hospital.datasets;

import java.util.*;


/*
 * 
 * This class contains the data of all users.
 * the fields are user_id, User_disease List
 * 
 * 
 * */
public class UserData {
	
	private int user_Id;
	private List<String> disease_List=new LinkedList<String>();
	/*Constructor*/
	public UserData(int u_id, List<String> d_list)
	{
		this.user_Id=u_id;
		this.disease_List=d_list;
	}
	
	/*Get user Id*/
	public int getuser_Id()
	{
		return this.user_Id;
	}
	
	/*Get list of diseases*/
	public List<String> getdisease_List ()
	{
		return this.disease_List;
	}
	/*Set user Id*/
	public void setuser_Id(int u_id)
	{
		this.user_Id=u_id;
	}
	/*Set diseaseList*/
	public void setdisease_List(List<String> d_list)
	{
		this.disease_List=d_list;
	}
	
}
