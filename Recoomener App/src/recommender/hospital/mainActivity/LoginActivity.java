package recommender.hospital.mainActivity;

import java.util.HashMap;

import recommender.hospital.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity{

Button btnLogin;
	
	EditText txtUsername, txtPassword;
	 TextView tv_signup;
	// User Session Manager Class
	UserSessionManager session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	session = new UserSessionManager(getApplicationContext());
    	 // get user data from session
        HashMap<String, String> user = session.getUserDetails();
        // get email
        String email = user.get(UserSessionManager.KEY_EMAIL);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); 
        
        // User Session Manager
        session = new UserSessionManager(getApplicationContext());                
        
        // get Email, Password input text
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword); 
        
        Toast.makeText(getApplicationContext(), 
        		"User Login Status: " + session.isUserLoggedIn(), 
        		Toast.LENGTH_LONG).show();
        
       /* if(email==null)
        {
        	Intent i2 = new Intent(getApplicationContext(), SignUpActivity.class);
        	startActivity(i2);
        }*/
        // User Login button
        btnLogin = (Button) findViewById(R.id.btnLogin);
        final SharedPreferences pref=getSharedPreferences("userdetail", MODE_PRIVATE);
        //SignUP Text click event
        btnLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
		
					// Get username, password from EditText
					String username = txtUsername.getText().toString();
					String password = txtPassword.getText().toString();
					
					// Validate if username, password is filled				
					if(username.trim().length() > 0 && password.trim().length() > 0){
						
						// For testing puspose username, password is checked with static data
						// username = admin
						// password = admin
						
						if(username.equals("admin") && password.equals("admin")){
							
							// Creating user login session
							// Statically storing name="Android Example"
							// and email="androidexample84@gmail.com"
							session.createUserLoginSession(username);
							
							// Starting MainActivity
							Intent i2 = new Intent(getApplicationContext(), MainActivity.class);
							i2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							
							// Add new Flag to start new Activity
							i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivity(i2);
							
							finish();
							
						}
						else{
							
							// username / password doesn't match
							Toast.makeText(getApplicationContext(), "Username/Password is incorrect", Toast.LENGTH_LONG).show();
							
						}				
					}
					else{
						
						// user didn't entered username or password
						Toast.makeText(getApplicationContext(), "Please enter username and password", Toast.LENGTH_LONG).show();
						
					}
					
				
				}
				
			
		});
        // Login button click event
        
    }   

}
