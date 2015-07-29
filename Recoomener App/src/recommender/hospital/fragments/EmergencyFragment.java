package recommender.hospital.fragments;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import recommender.hospital.R;
import recommender.hospital.adapters.EmergencyListAdapter;
import recommender.hospital.adapters.RecListAdapter;
import recommender.hospital.constants.ConstantValues;
import recommender.hospital.datasets.HospitalData;
import recommender.hospital.datasets.HospitalGenerator;
import recommender.hospital.datasets.RatingDataset;
import recommender.hospital.datasets.RatingGenerator;
import recommender.hospital.locationfinder.DistanceCalc;
import recommender.hospital.locationfinder.GPSTracker;
import recommender.hospital.locationfinder.GeocodingLocation;
import recommender.hospital.recommendationengine.RecommendationEngine;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class EmergencyFragment extends Fragment {
	public Context getContext()
	{
		return this.getActivity().getApplicationContext();
		
	}
	GPSTracker gpstrack=new GPSTracker(this.getActivity());
	GeocodingLocation locationAddress = new GeocodingLocation();
	private HashMap<Integer,HospitalData> hospital_ret_data=new HashMap<Integer,HospitalData>();
	HashMap <Integer,Double> hospital_short_dist=new HashMap<Integer,Double>();
	private HashMap<Integer,RatingDataset> rating_ret_data=new HashMap<Integer,RatingDataset>();
	private HashMap<String,Float> hospital_names_list=new HashMap<String,Float>();
	public EmergencyFragment(){}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_emergency, container, false);
        ListView lst=(ListView)rootView.findViewById(R.id.list_emergency);
        gpstrack.getLocation();
        HospitalGenerator hg=new HospitalGenerator();
 		hg.hospitaldataread(ConstantValues.hospitaldata_File);
 		hospital_ret_data=hg.getHospitalDataHashmap();
 		Iterator<Map.Entry<Integer, HospitalData>> it = hospital_ret_data.entrySet().iterator();
 		Log.d("size",(Integer.toString(hospital_ret_data.size())));
		while (it.hasNext())
		{
			Entry<Integer, HospitalData> entry = it.next();
			Log.d("hospital key",entry.getKey().toString());
			String name=hospital_ret_data.get(entry.getKey()).gethospital_Name().toString();
			locationAddress.getLatLongFromAddress(name,this.getActivity());
			double dist=DistanceCalc.distFrom(Math.toRadians(gpstrack.getLatitude()),Math.toRadians(gpstrack.getLongitude()),Math.toRadians(GeocodingLocation.lat),Math.toRadians(GeocodingLocation.longt));
    		/*check to get top 3 hospitals based on distance from user current location*/
			hospital_ret_data.get(entry.getKey()).sethospital_distance(dist);
			hospital_short_dist.put(entry.getKey(), dist);
			
		}
		List<Entry<Integer, Double>> greatest = RecommendationEngine.findGreatest(hospital_short_dist, 3);
		hospital_short_dist.clear();
		for (Entry<Integer, Double> entry1 : greatest)
        {
			hospital_short_dist.put(entry1.getKey(), entry1.getValue());
        }
		Iterator<Entry<Integer, Double>> it2 = hospital_short_dist.entrySet().iterator();
		while (it2.hasNext())
		{
			Entry<Integer, Double> entry = it2.next();
			String name=hospital_ret_data.get(entry.getKey()).gethospital_Name().toString();
			hospital_names_list.put(name, 0f);
			
		}
		Log.d("short distan",hospital_names_list.toString());
        // RecListAdapter adapter=new RecListAdapter(hospital_names_list);
         EmergencyListAdapter adapter=new EmergencyListAdapter(hospital_names_list);
         lst.setAdapter(adapter);
         /*Click Listener to get the values of hospital and launch maps*/
         lst.setOnItemClickListener(new OnItemClickListener() {
             public void onItemClick(AdapterView<?> parent, View view,
                     int position, long id) {
          	   
                     // selected item
                    String selected = ((TextView) view.findViewById(R.id.txtKey)).getText().toString();
                    locationAddress.getLatLongFromAddress(selected,getContext());
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,          	
                  		  Uri.parse("http://maps.google.com/maps?f=d&daddr="+selected));
         			startActivity(intent);
                                                           
                 }
               });
        return rootView;
    }
}
