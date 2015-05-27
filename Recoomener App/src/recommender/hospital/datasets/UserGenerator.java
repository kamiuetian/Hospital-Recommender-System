package recommender.hospital.datasets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;

public class UserGenerator {
	private static Context context;

    public static void setContext(Context mcontext) {
        if (context == null)
            context = mcontext;
    }
	HashMap <Integer,UserData> mapper = new HashMap<Integer, UserData> ();
	public void userdataread(String file)
	{
		
		String data="";
		try
		{
		System.out.println(file);
		InputStream in=context.getAssets().open(file);
		BufferedReader br=new BufferedReader(new InputStreamReader(in, "UTF-8"));
		/*
		 * 
		 * Split the Users file based on "," token
		 * Values[0]=User_Id
		 * Values[1]=List_Diseases
		 * 
		 * */
		while((data=br.readLine())!=null)
		{
			String [] values= data.split(",");
			/*call getdiseases to get the list of diseases*/
			List<String> disease_list=getdiseases(values[1]);
			mapper.put(Integer.parseInt(values[0]),new UserData(Integer.parseInt(values[0]), disease_list));
						
		}
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	/*
	 * 
	 * Returns List of diseases.
	 * called from userdataread function()
	 * */
	public List<String> getdiseases(String dpt_val) {
		List <String> dis=new LinkedList<String> ();
		String [] disease_single=dpt_val.split("-");
		for(int i=0;i<disease_single.length;i++)
		{
			dis.add(disease_single[i]);
		}
		return dis;
	}
	
	/*
	 * Returm th hashmap created 
	 * called from SimilarUsers
	 * */
	
	public HashMap <Integer,UserData> getUserDataHashmap()
	{
		return mapper;
		
	}

}
