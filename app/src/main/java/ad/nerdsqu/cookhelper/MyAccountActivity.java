package ad.nerdsqu.cookhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MyAccountActivity extends AppCompatActivity {

    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        TextView name = (TextView)findViewById(R.id.current_user_name);

        user = getIntent().getStringExtra("USER");
        name.setText(user);

        //change password
        Button changePassword = (Button)findViewById(R.id.confirm_password_change);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText old_password_text = (EditText)findViewById(R.id.old_password);
                EditText new_password1_text = (EditText)findViewById(R.id.new_password1);
                EditText new_password2_text = (EditText)findViewById(R.id.new_password2);

                String old_pass = old_password_text.getText().toString();
                String new_pass1 = new_password1_text.getText().toString();
                String new_pass2 = new_password2_text.getText().toString();

                //check if old password is correct and new passwords patch
                if (MainActivity.helper.IsValidLogin(user, old_pass)) {
                    if (new_pass1.equals(new_pass2)) {
                        MainActivity.helper.setPassword(new_pass1,user);
                        Toast.makeText(MyAccountActivity.this, "Success!", Toast.LENGTH_SHORT).show();

                        old_password_text.setText("");
                        new_password1_text.setText("");
                        new_password2_text.setText("");
                    } else {
                        Toast.makeText(MyAccountActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MyAccountActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                    old_password_text.setText("");
                }
            }
        });
    }
}
