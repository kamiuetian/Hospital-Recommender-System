package recommender.hospital.constants;

/*
 * 
 * This file contains all the constant values used in this project
 *  
 * 
 * */
public class ConstantValues {
	
	/*Dataset Directory Path*/
	//public static String path="/assets/";
	/*Dataset Files names*/
	public static String userdata_File="testUsers.csv";
	public static String hospitaldata_File="hospitals.csv";
	public static String ratingdata_File="hospitalratings.csv";
	 
	/*Weights for the rating factors*/
	public static float w_d_c=0.25f; //doctors communication
	public static float w_n_c=0.20f; //nurses communication
	public static float w_s_c=0.15f; //staff communication
	public static float p_p_c=0.14f;  //Patients Pain Control
	public static float m_e_p=0.1f; //medicine explanation to patients
	public static float i_r_h=0.1f; //instruction during recovery at home
	public static float p_r_c=0.04f; //Patient's room cleaning 
	public static float s_r_c=0.02f; // Room surrounding cleaning
	
	/*values for constant strings used to represent patients response
	 * Never=4
	 * Sometimes=6
	 * Usually=8
	 * often=10
	 * yes=10
	 * no=0
	 * */
	public static int never=4;
	public static int sometimes=6;
	public static int usually=8;
	public static int often=10;
	public static int yes=10;
	public static int no=10;
	
}
