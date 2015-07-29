package recommender.hospital.fragments;

import recommender.hospital.R;
import recommender.hospital.mainActivity.UserSessionManager;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProfileFragment extends Fragment{
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
		 View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
		 //UserSessionManager mgr=new UserSessionManager(this.getActivity().getApplicationContext());
		 //mgr.logoutUser();
		 return rootView;
	 }
}
