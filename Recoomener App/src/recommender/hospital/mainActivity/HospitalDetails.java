package recommender.hospital.mainActivity;

import recommender.hospital.R;
import recommender.hospital.locationfinder.GeocodingLocation;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HospitalDetails extends Activity{
	GeocodingLocation locationAddress = new GeocodingLocation();
	public Context getContext()
	{
		return this.getApplicationContext();
		
	}
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_details); 
	        TextView name=(TextView) findViewById(R.id.txt_name);
	        Button phone=(Button) findViewById(R.id.btn_phone);
	        Button navigate=(Button) findViewById(R.id.btn_navigate);
	        TextView email=(TextView) findViewById(R.id.txt_email);
	        TextView url=(TextView) findViewById(R.id.txt_url);
	        ImageView img=(ImageView) findViewById(R.id.img_hospital);
	        Intent intent=getIntent();
	        String hospital_name=intent.getStringExtra("h_name");
	        int resource_id=intent.getExtras().getInt("r_id");
	        final String hospital_phone=intent.getStringExtra("h_phone");
	        String hospital_email=intent.getStringExtra("h_email");
	        final String hospital_address=intent.getStringExtra("h_address");
	        name.setText(hospital_name);
	        phone.setText(hospital_phone);
	        email.setText(hospital_email);
	        url.setText(hospital_address);
	        img.setBackgroundResource(resource_id);
	        phone.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(hospital_phone.equalsIgnoreCase("NIL"))
	            	{
	            	}
	            	else
	            	{
	            	 Intent callIntent = new Intent(Intent.ACTION_CALL);
	            	    callIntent.setData(Uri.parse("tel:"+hospital_phone));
	            	    startActivity(callIntent);
	            	}
	            }
	        });
	        //Navigation
	        navigate.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	locationAddress.getLatLongFromAddress(hospital_address,getContext());
	                  Intent intent = new Intent(android.content.Intent.ACTION_VIEW,          	
	                		  Uri.parse("http://maps.google.com/maps?f=d&daddr="+hospital_address));
	                  startActivity(intent);
	            }
	        });
	 }
}