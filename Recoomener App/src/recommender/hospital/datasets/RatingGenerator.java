package recommender.hospital.datasets;
/*
 * 
 * This class reads the data from hospitalRating file. Generate weighted ratings of each hospital.
 * and creates an object of the RatingDataset class.
 * 
 * */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;

import recommender.hospital.constants.ConstantValues;

import android.content.Context;
import android.util.Log;


public class RatingGenerator
{
	private static Context context;

    public static void setContext(Context mcontext) {
        if (context == null)
            context = mcontext;
    }
	int key=1;
	HashMap <Integer,RatingDataset> ratingMapper=new HashMap <Integer,RatingDataset>();
	String data="";
	public void RatingdataRead(String file)
	{
		
		try
		{
		
		InputStream in=context.getAssets().open(file);
		BufferedReader br=new BufferedReader(new InputStreamReader(in, "UTF-8"));
		/*
		 * 
		 * Split the rating files based on "," token
		 * factors[0]=userId,factors[1]=hospital Id,factors[2]=doctor communication
		 * factors[3]=nurses communication,factors[4]=staff communication,factors[5]= patient pain control
		 * factors[6]=medicine explanation ,factors[7]=information during recovery,
		 * factors[8]=rooms and bathrooms cleaning
		 * factors[9]=surrounding environment cleaning
		 * 
		 * */
		while((data=br.readLine())!=null)
		{
			String [] factors= data.split(",");
			ratingMapper.put(key,new RatingDataset(Integer.parseInt(factors[0]),Integer.parseInt(factors[1]),CalculateRating(factors)));
			key++;
		}
		}catch(IOException e)
		{
			e.printStackTrace();
			
		}
	}
	
	/*Rating Calculator method :
	 * Calculates the wighted rating for each hospital*/
	public float CalculateRating(String factors[])
	{
		float weighted_rating=0;
		weighted_rating+=valuegetter(factors[2])*ConstantValues.w_d_c;	//doctor communication
		weighted_rating+=valuegetter(factors[3])*ConstantValues.w_n_c;	//nurses communication
		weighted_rating+=valuegetter(factors[4])*ConstantValues.w_s_c;	//staff communication
		weighted_rating+=valuegetter(factors[5])*ConstantValues.p_p_c;	//patient's pain control
		weighted_rating+=valuegetter(factors[6])*ConstantValues.m_e_p;	//medicine explanation
		weighted_rating+=valuegetter(factors[7])*ConstantValues.i_r_h;	//instruction during recovery
		weighted_rating+=valuegetter(factors[8])*ConstantValues.p_r_c;	//room cleaning
		weighted_rating+=valuegetter(factors[9])*ConstantValues.s_r_c; 	//surrounding cleaning
		return weighted_rating;
	}
	
	/*Constant Value Finder 
	 * returns the equivalent integer value for string passed as parameter*/
	public int valuegetter(String factor)
	{
		int factor_int_value=0;
		factor.trim();
		if(factor.equalsIgnoreCase("never"))
		{factor_int_value=4;}
		if(factor.equalsIgnoreCase("sometimes"))
		{factor_int_value=6;}
		if(factor.equalsIgnoreCase("usually"))
		{factor_int_value=8;}
		if(factor.equalsIgnoreCase("often")||factor.equalsIgnoreCase("yes"))
		{factor_int_value=10;}
		
			return factor_int_value;
	}
	
	/*
	 * Returm th hashmap created 
	 * called from SimilarUsers
	 * */
	
	public HashMap <Integer,RatingDataset> getRatingDatasetHashmap()
	{
		return ratingMapper;
		
	}
	public void sizeret()
	{
		System.out.println("size= "+ratingMapper.size());
	}
	
}


