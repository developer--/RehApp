package geolab.myo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userProfileImage = (CircleImageView) findViewById(R.id.profile_image);
        userProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create and show fragment
                LoginFragment loginFragment = new LoginFragment();
                loginFragment.show(getSupportFragmentManager(),"login-fragment");
            }
        });
    }

    // for user profile image
    private CircleImageView userProfileImage;


    @SuppressLint("ValidFragment")
    public class LoginFragment extends android.support.v4.app.DialogFragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState){

            View view = inflater.inflate(R.layout.login_dialog_fragment,null);

            final EditText userName = (EditText) view.findViewById(R.id.userName);
            final EditText password = (EditText) view.findViewById(R.id.password);

            Button loginBtn = (Button) view.findViewById(R.id.loginBtn);


            //on login button click
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(userName.getText().toString() == "123" && password.getText().toString() == "123"){
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
            });


            return view;
        }

    }
}
