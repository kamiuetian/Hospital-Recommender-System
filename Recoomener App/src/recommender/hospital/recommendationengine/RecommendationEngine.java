package recommender.hospital.recommendationengine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import recommender.hospital.constants.ConstantValues;
import recommender.hospital.datasets.HospitalData;
import recommender.hospital.datasets.HospitalGenerator;
import recommender.hospital.datasets.RatingDataset;
import recommender.hospital.datasets.RatingGenerator;
import recommender.hospital.datasets.UserData;
import recommender.hospital.datasets.UserGenerator;

public class RecommendationEngine {
	/*
	 * get file names for each dataset file
	 * */
	static String rating_file= ConstantValues.ratingdata_File;
	static String hospital_file=ConstantValues.hospitaldata_File;
	static String user_file=ConstantValues.userdata_File;
	static String your_recommended; 
	static List <Integer> patient_similar=new LinkedList<Integer>();
	HashMap <Integer, Float> recommended_hospital= new HashMap<Integer, Float> ();
	public HashMap<Integer,Float> Recommend(int u_id)
	{
		/*
		 * create objects of classes for each dataset
		 * */
		HashMap <Integer,Float> ret_values=new HashMap<Integer,Float>();
		boolean has_disease=false;
		UserGenerator ug=new UserGenerator();
		ug.userdataread(user_file);
		/*Get the hashmap of UserData
		 * 
		 *  Key= user_Id
		 *  Value= object of UserData class
		 *  */
		HashMap <Integer,UserData> mapper = ug.getUserDataHashmap();
		//System.out.println(mapper.toString());
		/*check if user already exists and disease is in profile*/
		if(mapper.containsKey(u_id))
			{
			/*
			 * Iterate through the HashMap of UserData
			 * 
			 * */
				Iterator<Map.Entry<Integer, UserData>> it = mapper.entrySet().iterator();
				while (it.hasNext())
				{
				    Map.Entry<Integer, UserData> entry = it.next();
				    int user_Id=entry.getKey();
				    /*Get the disease list for each user*/
				    List<String> d_list = entry.getValue().getdisease_List();
				    if(d_list.contains("cholera") && user_Id==u_id)
				    {
				    	has_disease=true;
				    ///System.out.println("Diseases: " + d_list.toString());
				    ret_values=rechospitalonSelfrating(user_Id);
				    }

				}
				
			}
		if(!(mapper.containsKey(u_id)) || !has_disease)
			{
			/*
			 * Iterate through userData to find list of users with same disease.
			 * 
			 * */
			System.out.println(!has_disease);
			Iterator<Map.Entry<Integer, UserData>> it = mapper.entrySet().iterator();
			while (it.hasNext())
			{
			    Map.Entry<Integer, UserData> entry = it.next();
			    int user_Id=entry.getKey();
			    /*Get the disease list for each user*/
			    List<String> d_list = entry.getValue().getdisease_List();
			    if(d_list.contains("GAD"))
			    {
			   // System.out.println("Diseases: " + d_list.toString());
			    patient_similar.add(user_Id);
			    
			    }

			}
			//.
			System.out.println(patient_similar.toString());
				ret_values=rechospitalonOtherrating(patient_similar);
				List<Entry<Integer, Float>> greatest = findGreatest(ret_values, 5);
				ret_values.clear();
				for (Entry<Integer, Float> entry1 : greatest)
		        {
		            ret_values.put(entry1.getKey(), entry1.getValue());
		        }
			}
		
		return ret_values;
		}
	/*
	 * recommend hospital based on user own rating of hospital
	 * 
	 * */
public HashMap<Integer,Float> rechospitalonSelfrating(int U_id)
{
	float hospital_rating = 0;
	float top_rated=0;
	int hospital_id = -1;
	/*
	 * Iterate through the HashMap of ratingDataset
	 * 
	 * */
	RatingGenerator rg=new RatingGenerator();
	rg.RatingdataRead(rating_file);
	HospitalGenerator hg=new HospitalGenerator();
	hg.hospitaldataread(hospital_file);
	HashMap<Integer, HospitalData> hospitalmapper=hg.getHospitalDataHashmap();
	System.out.println(hospitalmapper.size());
	 
	HashMap <Integer,RatingDataset> ratingmapper=rg.getRatingDatasetHashmap();
	Iterator<Map.Entry<Integer, RatingDataset>> it_rating = ratingmapper.entrySet().iterator();
	while (it_rating.hasNext())
		{
		    Map.Entry<Integer, RatingDataset> entry = it_rating.next();
		    int user_Id= entry.getValue().getuser_Id();
		    if(user_Id==U_id)
		    	{
				    hospital_id=entry.getValue().gethospital_Id();
				    //System.out.println("Hospital_ID ="+hospital_id);
				    hospital_rating=entry.getValue().gethospital_Rating();
				   // System.out.println("Hospital_rating ="+hospital_rating);
				    if(hospitalmapper.get(hospital_id).getdepartment_List().contains("ENT") && hospital_rating>top_rated)
				    {
				    	your_recommended=hospitalmapper.get(hospital_id).gethospital_Name().toString();
				    	System.out.println(your_recommended);
				    	top_rated=hospital_rating;
				    }
		    	}
		    

		}
	recommended_hospital.put(hospital_id, top_rated);
	return recommended_hospital;
	
	
}
/*
 * 
 * Recommend hospital based on the ratings of other users.
 * 
 * */

public HashMap <Integer,Float> rechospitalonOtherrating(List<Integer> lst)
	{
		
		RatingGenerator rg=new RatingGenerator();
		rg.RatingdataRead(rating_file);
		HashMap <Integer,RatingDataset> ratingmapper=rg.getRatingDatasetHashmap();
		Iterator<Map.Entry<Integer, RatingDataset>> it_rating = ratingmapper.entrySet().iterator();
		while (it_rating.hasNext())
		{
		    Map.Entry<Integer, RatingDataset> entry = it_rating.next();
		    int user_Id= entry.getValue().getuser_Id();
		    if(lst.contains(user_Id))
		    {
		    	if(recommended_hospital.containsKey(entry.getValue().gethospital_Id()))
		    	{
		    	float rating_old=recommended_hospital.get(entry.getValue().gethospital_Id());
		    	rating_old+=entry.getValue().gethospital_Rating();
		    	rating_old/=2;
		    	recommended_hospital.put(entry.getValue().gethospital_Id(), rating_old);
		    	}
		    	else
		    	{
		    		recommended_hospital.put(entry.getValue().gethospital_Id(), entry.getValue().gethospital_Rating());
		    	
		    	}
		    }
		}
		System.out.println(recommended_hospital.toString());
		return recommended_hospital;
		
	}

/*Gets top 5 hospitals
 * 
 * Return the 5 hospitals with top ratings
 * */
public static <K, V extends Comparable<? super V>> List<Entry<K, V>> 
findGreatest(Map<K, V> map, int n)
{
Comparator<? super Entry<K, V>> comparator = 
    new Comparator<Entry<K, V>>()
{
    public int compare(Entry<K, V> e0, Entry<K, V> e1)
    {
        V v0 = e0.getValue();
        V v1 = e1.getValue();
        return v0.compareTo(v1);
    }
};
PriorityQueue<Entry<K, V>> highest = 
    new PriorityQueue<Entry<K,V>>(n, comparator);
for (java.util.Map.Entry<K, V> entry : map.entrySet())
{
    highest.offer(entry);
    while (highest.size() > n)
    {
        highest.poll();
    }
}

List<Entry<K, V>> result = new ArrayList<Map.Entry<K,V>>();
while (highest.size() > 0)
{
    result.add(highest.poll());
}
return result;
}

}
