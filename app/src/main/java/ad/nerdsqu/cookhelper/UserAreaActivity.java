package ad.nerdsqu.cookhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Aaron on 11/27/2016.
 */

public class UserAreaActivity extends AppCompatActivity {
    private Button searchForRecipe;
    private Button addARecipe;
    private Button mySavedRecipes;
    private Button myAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        searchForRecipe = (Button) findViewById(R.id.bSearchForRecipe);
        addARecipe = (Button) findViewById(R.id.bAddARecipe);
        mySavedRecipes = (Button) findViewById(R.id.bMySavedRecipes);
        myAccount = (Button) findViewById(R.id.bMyAccount);

        searchForRecipe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(UserAreaActivity.this, SearchRecipeActivity.class);
                startActivity(i);
            }

        });
        addARecipe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(UserAreaActivity.this, AddRecipeActivity.class);
                startActivity(i);
            }

        });
        mySavedRecipes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(UserAreaActivity.this, RecipeListActivity.class);

                ArrayList<Recipe> recipes = MainActivity.helper.getAllRecipes();
                String[] recipeArray = new String[recipes.size()];
                for (int i = 0; i < recipes.size(); i++) {
                    recipeArray[i] = recipes.get(i).getRecipeName();
                }
                intent.putExtra("RECIPE_LIST", recipeArray);
                startActivity(intent);
            }

        });
        myAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(UserAreaActivity.this, MyAccountActivity.class);
                String user = getIntent().getStringExtra(("USER"));
                i.putExtra("USER", user);
                startActivity(i);
            }

        });

    }




}