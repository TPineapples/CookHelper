package ad.nerdsqu.cookhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Aaron on 11/27/2016.
 */

public class AddRecipeActivity extends AppCompatActivity {

    private EditText ingredient;
    private EditText quantity;
    private EditText name;
    private EditText cookTime;
    private EditText prepTime;
    private TextView directions;
    private Spinner unit;
    private Spinner category;
    private Spinner type;
    private Button addRecipe;
    private ArrayList<String> ingredients = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);


        name = (EditText)findViewById(R.id.etRecipeName);
        cookTime = (EditText)findViewById(R.id.etCookTime);
        prepTime = (EditText)findViewById(R.id.etPrepTime);

        ingredient = (EditText)findViewById(R.id.etIngredient);
        quantity = (EditText)findViewById(R.id.etQuantity);
        directions = (TextView)findViewById(R.id.tvSteps);

        unit = (Spinner)findViewById(R.id.unitSpinner);
        category = (Spinner)findViewById(R.id.categorySpinner);
        type = (Spinner)findViewById(R.id.typeSpinner);

        addRecipe = (Button)findViewById(R.id.bAddRecipe);



        /*A test to see if the activity is being passed an existing recipe to edit.
         *TRUE -> load all fields with recipe's info, change title and button text to
         *        reflect behaviour (eg "Add recipe" -> "Update recipe")*/

        if (getIntent().hasExtra("RECIPE_NAME")) {
            Recipe recipe = MainActivity.helper.getRecipeFromName
                    (getIntent().getStringExtra("RECIPE_NAME"));


            addRecipe.setText(R.string.updateRecipe);
            TextView title = (TextView)findViewById(R.id.tvAddRecipeTitle);
            title.setText(R.string.updateRecipe);

            for (String ingred : recipe.getIngredients()) {
                ingredients.add(ingred);
            }

            String[] categoryArray = getResources().getStringArray(R.array.food_category);
            String[] typeArray = getResources().getStringArray(R.array.food_type);

            int categoryIndex = Arrays.asList(categoryArray).indexOf(recipe.getCategory());
            int typeIndex = Arrays.asList(typeArray).indexOf(recipe.getRecipe_Type());

            category.setSelection(categoryIndex);
            type.setSelection(typeIndex);

            directions.setText(recipe.getDirections());

            name.setText(recipe.getRecipeName());

            String[] prepSplit = recipe.getPreparation_Time().split(" ");
            prepTime.setText(prepSplit[0]);

            String[] cookSplit = recipe.getCook_Time().split(" ");
            cookTime.setText(cookSplit[0]);


        }

        final String origName = name.getText().toString();

        //check for clicks on directions TextView to open a separate text input activity
        directions.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent getDirections = new Intent(AddRecipeActivity.this, EnterTextActivity.class);
                getDirections.putExtra("CURRENT_TEXT", directions.getText().toString());
                startActivityForResult(getDirections, 1);
            }
        });



        Button addIngredient = (Button) findViewById(R.id.bAddIngredient);
        addIngredient.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ingredients.add(ingredient.getText().toString() + ":" +
                        quantity.getText().toString()+ " " + unit.getSelectedItem().toString());
                ingredient.setText("");
                quantity.setText("");
                unit.setSelection(0);
                Toast.makeText(AddRecipeActivity.this, "Added Ingredient", Toast.LENGTH_SHORT).show();
            }
        });


        Button removeIngredients = (Button)findViewById(R.id.bRemoveIngredients);
        removeIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent deleteIngredients = new Intent(AddRecipeActivity.this, IngredientListActivity.class);
                String[] ingredArray = new String[ingredients.size()];
                ingredArray = ingredients.toArray(ingredArray);
                deleteIngredients.putExtra("INGREDIENT_LIST", ingredArray);
                startActivityForResult(deleteIngredients, 2);

            }
        });


        addRecipe = (Button) findViewById(R.id.bAddRecipe);
        addRecipe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //helper used to check for nulls and replace with empty string
                StringHelper help = new StringHelper();
                Recipe recipe;

                String rCategory = help.getSpinnerString(category);
                String rType = help.getSpinnerString(type);
                String rName = help.getEditTextString(name);
                String rDirections = help.getTextViewString(directions);

                String rCookTime = cookTime.getText().toString() + " minutes";
                String rPrepTime = prepTime.getText().toString() + " minutes";

                String[] ingredArray = new String[ingredients.size()];
                ingredArray = ingredients.toArray(ingredArray);

                recipe = new Recipe(rName, ingredArray, rPrepTime, rCookTime,
                                                        rCategory, rType, rDirections);

                //another check to see if it is altering a recipe or creating a new one
                //TRUE -> editing
                if (getIntent().hasExtra("RECIPE_NAME")) {


                    if (!origName.equals(rName)) {
                        MainActivity.helper.deleteRecipeFromName(origName);
                    } else {
                        MainActivity.helper.deleteRecipeFromName(recipe.getRecipeName());
                    }


                    MainActivity.helper.addRecipe(recipe);

                    //notify user of change and send back to the main screen
                    Toast.makeText(AddRecipeActivity.this, "Recipe Updated", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AddRecipeActivity.this, UserAreaActivity.class);
                    startActivity(i);

                } else {
                    MainActivity.helper.addRecipe(recipe);
                    Toast.makeText(AddRecipeActivity.this, "Successfully Added Recipe", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //returning from EnterText activity
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                directions.setText(data.getStringExtra("DIRECTIONS"));
            }
        } else {
            //returning from IngredientList activity, updating ingredients to reflect removed ingredients
            if (resultCode == RESULT_OK) {
                String[] updatedIngr = data.getStringArrayExtra("INGREDIENT_LIST");
                ingredients.clear();
                for (String ing : updatedIngr) {
                    ingredients.add(ing);

                }
            }
        }
    }
}





