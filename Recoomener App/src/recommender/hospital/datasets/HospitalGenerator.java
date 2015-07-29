package recommender.hospital.datasets;
/*
 * 
 * This class reads the data from the hospitals file 
 * and creates the object of HospitalData class
 * 
 * 
 * */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

public class HospitalGenerator {
	private static Context context;
	public HospitalData hosp_data; 
    public static void setContext(Context mcontext) {
        if (context == null)
            context = mcontext;
    }
	HashMap <Integer,HospitalData> hospitalmapper = new HashMap<Integer, HospitalData> ();
	public void hospitaldataread(String file)
	{
		String data="";
		try
		{
			
		System.out.println(file);
		InputStream in=context.getAssets().open(file);
		BufferedReader br=new BufferedReader(new InputStreamReader(in, "UTF-8"));
		/*
		 * 
		 * Split the rating files based on "," token
		new FileReader(file) * Values[0]=hospital_Id
		 * Values[1]=hospital_Name
		 * Values[2]=hospital address
		 * Values[3]= hospital phone
		 * values[4]= hospital website
		 * values[5]= ospital email
		 * 
		 * */
		while((data=br.readLine())!=null)
		{
			//boolean srvc;
			String [] values= data.split(",");
			/*call getdepartments to get the list of hospitals*/
			/*if(values[3].trim().equalsIgnoreCase("yes"))
				{srvc=true;}
			else
				{srvc=false;}*/
			List<String> departments=getdepartments(values[6]);
			//hospitalmapper.put(Integer.parseInt(values[0]),new HospitalData(Integer.parseInt(values[0]), values[1].trim(),srvc, departments));			
		
			hospitalmapper.put(Integer.parseInt(values[0]),new HospitalData(Integer.parseInt(values[0]), values[1].trim(), 
								values[2].trim(), values[3].trim(), values[4].trim(), values[5].trim(),departments));
		}
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public List<String> getdepartments(String dpt_val) {
		List <String> dpts=new LinkedList<String> ();
		String [] dpt_single=dpt_val.split("-");
		for(int i=0;i<dpt_single.length;i++)
		{
			dpts.add(dpt_single[i]);
		}
		return dpts;
	}
	
	/*
	 * Returm th hashmap created 
	 * called from SimilarUsers
	 * */
	
	public HashMap <Integer,HospitalData> getHospitalDataHashmap()
	{
		return hospitalmapper;
		
	}
}
