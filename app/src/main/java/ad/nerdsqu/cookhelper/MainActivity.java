package ad.nerdsqu.cookhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new DatabaseHelper(this);
        System.out.println("x000: Attempting default login registration");
        Login temp = new Login();
        helper.addLogin(temp);
        System.out.println(helper.IsValidLogin("Default", "Password1"));

        final EditText username = (EditText) findViewById(R.id.fieldUsername);
        final EditText password = (EditText) findViewById(R.id.fieldPassword);
        Button newAccount = (Button) findViewById(R.id.buttonRegister);
        Button submitLogin = (Button) findViewById(R.id.buttonLogin);

        submitLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String user, pass;
                user = username.getText().toString();
                pass = password.getText().toString();
                System.out.println("x222: " + user + ", " + pass);
                if(user.equals("") || pass.equals("")) {
                    //No username or password entered --Maybe requires more safeguards?
                    Toast.makeText(MainActivity.this, "Please enter both fields", Toast.LENGTH_SHORT).show();
                    Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                    LinearLayout everything = (LinearLayout) findViewById(R.id.viewContent);
                    everything.startAnimation(shake);
                } else {
                    if (helper.IsValidLogin(user, pass)) {
                        Toast.makeText(MainActivity.this, "Welcome Back " + user, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, UserAreaActivity.class);
                        i.putExtra("USER", user);

                        startActivity(i);
                    } else {
                        Toast.makeText(MainActivity.this, "Wrong username or password. Please try again", Toast.LENGTH_SHORT).show();
                        Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                        LinearLayout everything = (LinearLayout) findViewById(R.id.viewContent);
                        everything.startAnimation(shake);

                    }
                }
            }
        });

        newAccount.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this, Register.class);
                startActivityForResult(i, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1) {
            if (resultCode == 666) {
                System.out.println("x111");
                Login newUser = data.getParcelableExtra("USERINFO");
                System.out.println("NEWUSER: " + newUser);
                helper.addLogin(newUser);
            }
        }
    }
}
