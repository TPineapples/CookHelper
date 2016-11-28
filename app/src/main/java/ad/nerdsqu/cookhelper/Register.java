package ad.nerdsqu.cookhelper;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        helper = MainActivity.helper;

        Button registerUser = (Button) findViewById(R.id.buttonSubmit);
        final EditText username = (EditText) findViewById(R.id.fieldUsername);
        final EditText password = (EditText) findViewById(R.id.fieldPassword);
        final EditText passwordConfirm = (EditText) findViewById(R.id.fieldPasswordConfirmation);

        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user, pass, pass2;
                user = username.getText().toString();
                pass = password.getText().toString();
                pass2 = passwordConfirm.getText().toString();

                if(user.equals("") || pass.equals("") || pass2.equals("")) {
                    //No username or password entered --Maybe requires more safeguards?
                    Toast.makeText(Register.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                    LinearLayout everything = (LinearLayout) findViewById(R.id.viewContent);
                    everything.startAnimation(shake);
                } else {
                    if(!pass.equals(pass2)){
                        Toast.makeText(Register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                        Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                        LinearLayout everything = (LinearLayout) findViewById(R.id.viewContent);
                        everything.startAnimation(shake);
                    } else {
                        Login newUser = new Login(user, pass);
                        if(!helper.addLogin(newUser)){
                            Toast.makeText(Register.this, "Username already exists", Toast.LENGTH_SHORT).show();
                            Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                            LinearLayout everything = (LinearLayout) findViewById(R.id.viewContent);
                            everything.startAnimation(shake);
                        } else {
                            Toast.makeText(Register.this, "Successfully added " + user, Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }

            }
        });
    }
}
