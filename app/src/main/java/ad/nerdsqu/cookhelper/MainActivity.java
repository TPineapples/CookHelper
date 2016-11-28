package ad.nerdsqu.cookhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public static DatabaseHelper helper;
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
        TextView buttonHelp = (TextView) findViewById(R.id.linkHelp);

        SpannableString helpstyling = new SpannableString(getString(R.string.HelpLink));
        helpstyling.setSpan(new UnderlineSpan(), 0, helpstyling.length(), 0);
        buttonHelp.setText(helpstyling);

        buttonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, HelpLoginActivity.class);
                startActivity(i);
            }
        });

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
//        String RecipeName = "Buffalo Chicken Wings";
//
//        String[] Ingredients = {"Flour:1/2:cup", "Oil for deep frying","salt:1/2:tbsp", "paprika:1/4:tbsp",
//
//                "HotSauce:1/4cup"};
//
//        String Cook_Time = "15 minutes";
//
//        String Preparation_Time = "15 minutes";
//
//        String Category = "American";
//
//        String Recipe_Type = "Dinner";
//
//        String Directions = "In a small bowl mix together the flour, paprika, cayenne pepper and salt. Place chicken wings in a large nonporous glass dish or bowl and sprinkle flour mixture over them until they are evenly coated. Cover dish or bowl and refrigerate for 60 to 90 minutes."
//
//                + "Heat oil in a deep fryer to 375 degrees F (190 degrees C). The oil should be just enough to cover wings entirely, an inch or so deep. Combine the butter, hot sauce, pepper and garlic powder in a small saucepan over low heat. Stir together and heat until butter is melted and mixture is well blended. "
//
//                + "Remove from heat and reserve for serving Fry coated wings in hot oil for 10 to 15 minutes, or until parts of wings begin to turn brown. Remove from heat, place wings in serving bowl, add hot sauce mixture and stir together. Serve.";
//
//        Recipe recipe = new Recipe(RecipeName, Ingredients, Cook_Time, Preparation_Time, Category, Recipe_Type, Directions);

//        helper.addRecipe(recipe);
        newAccount.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this, Register.class);
                startActivity(i);
            }
        });

    }

}
