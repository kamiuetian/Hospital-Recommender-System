package recommender.hospital.mainActivity;

import recommender.hospital.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends Activity {
	String username,pass,cnfrmpass;
	public Context getContext()
	{
		return this.getApplicationContext();
		
	}
	 public void onCreate(Bundle savedInstanceState) {
		 
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_signup); 
	        EditText email=(EditText) findViewById(R.id.email);
	        EditText password= (EditText) findViewById(R.id.password);
	        Button btn_signup=(Button) findViewById(R.id.btn_signup);
	        EditText cnfrmpassword= (EditText) findViewById(R.id.cnfrmpassword);
	        username=email.getText().toString();
	        pass=password.getText().toString();
	        cnfrmpass=cnfrmpassword.getText().toString();
	        btn_signup.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(username==" " ||pass==" " || cnfrmpass==" ")
			        {
			        	Toast.makeText(getContext(), "Missing field information",Toast.LENGTH_LONG).show();
			        }
			        else if(!pass.equals(cnfrmpass))
			        {
			        	Toast.makeText(getContext(), "Password MisMatch",Toast.LENGTH_LONG).show();
			        }
			        else 
			        {
			        	UserSessionManager mgr=new UserSessionManager(getContext());
			        	
			        	Log.d("Ëmail",username);
			        	mgr.createUserLoginSession(username);
			        	/*SharedPreferences.Editor edit=getSharedPreferences("userdetail", MODE_PRIVATE).edit();
			        	edit.putString("email", username);
			        	edit.putString("password", pass);
			        	edit.commit();
			        	/*Set Status as Login*/
			        	/*SharedPreferences.Editor edit_login=getSharedPreferences("LoginStatus", MODE_PRIVATE).edit();
			        	edit_login.putBoolean("IsUserLoggedIn", true);
			        	edit_login.commit();*/
			        	Intent i = new Intent(getApplicationContext(), MainActivity.class);
						startActivity(i);
			        }

				}
			});
	        	 }
}
