package ad.nerdsqu.cookhelper;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


/**
 * Created by Aaron on 11/27/2016.
 */

public class RecipeActivity extends AppCompatActivity {
        private Recipe recipe;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_recipe);

            TextView prep = (TextView)findViewById(R.id.tvPrepTime);
            TextView cook = (TextView)findViewById(R.id.tvCookTime);
            TextView total = (TextView)findViewById(R.id.tvTotalTime);
            TextView category = (TextView)findViewById(R.id.tvFoodCategory);
            TextView type = (TextView)findViewById(R.id.tvFoodType);
            TextView title = (TextView)findViewById(R.id.tvRecipeTitle);


            //setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);

            ListView ingredientListView = (ListView)findViewById(R.id.lvIngredientsList);

            String recipeName = getIntent().getStringExtra("RECIPE_NAME");
            recipe = MainActivity.helper.getRecipeFromName(recipeName);

            title.setText(recipeName);

            //calculate total time by splitting the string (<xx> minutes)
            //to extract the leading int <xx>
            String[] splitCook, splitPrep;
            splitCook = recipe.getCookTime().split(" ");
            splitPrep = recipe.getPrepTime().split(" ");
            int totalTime = Integer.parseInt(splitCook[0]) +
                    Integer.parseInt(splitPrep[0]);

            prep.setText(recipe.getPrepTime());
            cook.setText(recipe.getCookTime());
            total.setText(totalTime + " minutes");
            category.setText(recipe.getCategory());
            type.setText(recipe.getRecipeType());


            String[] unformattedIngredients = recipe.getIngredients();
            String[] ingredients = new String[unformattedIngredients.length];
            String[] split;
            for (int i = 0; i < recipe.getIngredients().length; i++) {
                if (unformattedIngredients[i].contains(":")) {
                    split = unformattedIngredients[i].split(":");
                    ingredients[i] = split[1] + " " + split[0];
                } else {
                    ingredients[i] = unformattedIngredients[i];
                }

            }



            ArrayAdapter<String> ingredientAdapter =
                    new ArrayAdapter<String>(this, R.layout.list_ingredient_template, ingredients);

            ingredientListView.setAdapter(ingredientAdapter);


            TextView directions = (TextView)findViewById(R.id.tvDirections);
            directions.setVerticalScrollBarEnabled(true);
            directions.setMovementMethod(ScrollingMovementMethod.getInstance());

            directions.setText(recipe.getDirections());

            TextView directionLabel = (TextView)findViewById(R.id.tvDirectionsLabel);
            directionLabel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(RecipeActivity.this, ViewDirectionsActivity.class);
                    i.putExtra("DIRECTIONS", recipe.getDirections());
                    startActivity(i);
                }
            });
        }

}
