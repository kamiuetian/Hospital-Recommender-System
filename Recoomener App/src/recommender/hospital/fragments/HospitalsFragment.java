package recommender.hospital.fragments;

import recommender.hospital.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import recommender.hospital.adapterdata.HospitalItem;
import recommender.hospital.adapterdata.NavDrawerItem;
import recommender.hospital.adapters.HospitalListAdapter;
import recommender.hospital.adapters.NavDrawerListAdapter;
import recommender.hospital.constants.ConstantValues;
import recommender.hospital.datasets.HospitalData;
import recommender.hospital.datasets.HospitalGenerator;
import recommender.hospital.locationfinder.GeocodingLocation;
import recommender.hospital.mainActivity.HospitalDetails;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
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
	private List<String> l_h =new LinkedList<String>();
	/*hospital List Items*/
	private String[] hospitalListTitles;
	private TypedArray hospitalListIcons;
	HospitalItem hi;
	 HashMap<Integer, HospitalData> hospitalmapper;
	private ArrayList<HospitalItem> hospitalItems;
	private HospitalListAdapter adapter;
	public HospitalsFragment(){
		HospitalGenerator hg=new HospitalGenerator();
        hg.hospitaldataread(ConstantValues.hospitaldata_File);
        hospitalmapper=hg.getHospitalDataHashmap();
        
	}
	public Context getContext()
	{
		return this.getActivity().getApplicationContext();
		
	}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_hospitals, container, false);
        final ListView listView = (ListView) rootView.findViewById(R.id.list_hospitals);
        
       //hospitalListTitles=l_h.toArray(new String[l_h.size()]);
        hospitalListIcons=getResources().obtainTypedArray(R.array.pics_hospitals);
        hospitalItems = new ArrayList<HospitalItem>();
        Iterator<Map.Entry<Integer, HospitalData>> it_rating = hospitalmapper.entrySet().iterator();
    	while (it_rating.hasNext())
    		{
    		 Map.Entry<Integer, HospitalData> entry = it_rating.next();
    		 hospitalItems.add(new HospitalItem(entry.getValue().gethospital_Name(),hospitalListIcons.getResourceId(entry.getKey()-1, -1),
    				 			entry.getKey(),entry.getValue().gethospital_phone(),entry.getValue().gethospital_email(),
    				 			entry.getValue().gethospital_Name()));
    		// l_h.add(entry.getValue().gethospital_Name().toString());
    		}
        adapter=new HospitalListAdapter(getContext(), hospitalItems);
        listView.setAdapter(adapter);
       /*Click Listener to get the values of hospital and launch maps*/
       listView.setOnItemClickListener(new OnItemClickListener() {
           public void onItemClick(AdapterView<?> parent, View view,
                   int position, long id) {
        	   hi=(HospitalItem) listView.getItemAtPosition(position);
                  
        	   Intent intent=new Intent(getContext(),HospitalDetails.class);
        	  intent.putExtra("h_name",hi.getTitle() );
        	  intent.putExtra("r_id",hi.getIcon() );
        	  intent.putExtra("h_email", hi.getEmail());
        	  intent.putExtra("h_address", hi.getAddress());
        	  intent.putExtra("h_phone", hi.getPhone());
       			startActivity(intent);
               }
             });
         return rootView;
    }
	
}
