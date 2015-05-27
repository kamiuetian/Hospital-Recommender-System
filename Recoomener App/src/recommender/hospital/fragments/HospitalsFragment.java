package recommender.hospital.fragments;

import recommender.hospital.R;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import recommender.hospital.constants.ConstantValues;
import recommender.hospital.datasets.HospitalData;
import recommender.hospital.datasets.HospitalGenerator;
import recommender.hospital.locationfinder.GeocodingLocation;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HospitalsFragment extends Fragment {
	GeocodingLocation locationAddress = new GeocodingLocation();
	private String[] list_hospital_items;
	private List<String> l_h =new LinkedList<String>();
	public HospitalsFragment(){
		HospitalGenerator hg=new HospitalGenerator();
        hg.hospitaldataread(ConstantValues.hospitaldata_File);
        HashMap<Integer, HospitalData> hospitalmapper=hg.getHospitalDataHashmap();
        Iterator<Map.Entry<Integer, HospitalData>> it_rating = hospitalmapper.entrySet().iterator();
    	while (it_rating.hasNext())
    		{
    		 Map.Entry<Integer, HospitalData> entry = it_rating.next();
    		 l_h.add(entry.getValue().gethospital_Name().toString());
    		}
	}
	public Context getContext()
	{
		return this.getActivity().getApplicationContext();
		
	}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_hospitals, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.list_hospitals);
         list_hospital_items=l_h.toArray(new String[l_h.size()]);
         ArrayAdapter <String> adpt= new ArrayAdapter <String>(getActivity().getApplicationContext(), R.layout.hospital_list_items,list_hospital_items);
       listView.setAdapter(adpt);
       /*Click Listener to get the values of hospital and launch maps*/
       listView.setOnItemClickListener(new OnItemClickListener() {
           public void onItemClick(AdapterView<?> parent, View view,
                   int position, long id) {
        	   
                   // selected item
                  String selected = ((TextView) view.findViewById(R.id.hospital)).getText().toString();
                  locationAddress.getLatLongFromAddress(selected,getContext());
                  Intent intent = new Intent(android.content.Intent.ACTION_VIEW,          	
                		  Uri.parse("http://maps.google.com/maps?f=d&daddr="+selected));
       			startActivity(intent);
               }
             });
         return rootView;
    }
	
}
