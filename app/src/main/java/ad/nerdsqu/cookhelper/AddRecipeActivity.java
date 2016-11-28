package ad.nerdsqu.cookhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;

/**
 * Created by Aaron on 11/27/2016.
 */

public class AddRecipeActivity extends AppCompatActivity {

    private EditText ingredient;
    private EditText quantity;
    private EditText name;
    private EditText directions;
    private EditText cookTime;
    private EditText prepTime;
    private Spinner unit;
    private Spinner category;
    private Spinner type;
    private ArrayList<String> ingredients = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        //TODO
        //name = (EditText)findViewById(R.id.etRecipeName);
        //cookTime = (EditText)findViewById(R.id.etCookTime);
        //prepTime = (EditText)findViewById(R.id.etPrepTime);

        ingredient = (EditText)findViewById(R.id.etIngredient);
        quantity = (EditText)findViewById(R.id.etQuantity);
        directions = (EditText)findViewById(R.id.etAddDirections);


        unit = (Spinner)findViewById(R.id.unitSpinner);
        category = (Spinner)findViewById(R.id.categorySpinner);
        type = (Spinner)findViewById(R.id.typeSpinner);

        //TODO check if multiple delimiters is OK
        Button addIngredient = (Button) findViewById(R.id.bAddIngredient);
        addIngredient.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ingredients.add(ingredient.getText().toString() + ":" +
                    quantity.getText().toString()+ ":" + unit.getSelectedItem().toString());
            }
        });



        Button addRecipe = (Button) findViewById(R.id.bAddRecipe);
        addIngredient.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String[] s = {}; // only used to denote type of array for ingredients.toArray()
                Recipe recipe;
                String rType = type.getSelectedItem().toString();
                String rCategory = category.getSelectedItem().toString();
                String rName = name.getText().toString();
                String rDirections = directions.getText().toString();
                String rCookTime = cookTime.getText().toString() + " minutes";
                String rPrepTime = prepTime.getText().toString() + " minutes";
                recipe = new Recipe(rName, ingredients.toArray(s), rPrepTime, rCookTime,
                                                        rCategory, rType, rDirections);

                MainActivity.helper.addRecipe(recipe);
            }
        });


    }
}





