package recommender.hospital.fragments;

import recommender.hospital.R;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EmergencyFragment extends Fragment {
	
	public EmergencyFragment(){}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_emergency, container, false);
        String uri = "geo:"+ 33.356894 + "," + 72.145786 +"&q=hospitals";
        startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));
        return rootView;
    }
}
