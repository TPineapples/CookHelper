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
    private ArrayList<String> ingredients = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        //TODO
        name = (EditText)findViewById(R.id.etRecipeName);
        cookTime = (EditText)findViewById(R.id.etCookTime);
        prepTime = (EditText)findViewById(R.id.etPrepTime);

        ingredient = (EditText)findViewById(R.id.etIngredient);
        quantity = (EditText)findViewById(R.id.etQuantity);
        directions = (TextView)findViewById(R.id.tvSteps);



        directions.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent getDirections = new Intent(AddRecipeActivity.this, EnterTextActivity.class);
                getDirections.putExtra("CURRENT_TEXT", directions.getText().toString());
                startActivityForResult(getDirections, 1);
            }
        });

        unit = (Spinner)findViewById(R.id.unitSpinner);
        category = (Spinner)findViewById(R.id.categorySpinner);
        type = (Spinner)findViewById(R.id.typeSpinnerAdd);

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



        Button addRecipe = (Button) findViewById(R.id.bAddRecipe);
        addRecipe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
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

                MainActivity.helper.addRecipe(recipe);
            }
        });


    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                directions.setText(data.getStringExtra("DIRECTIONS"));
            }
        }
    }
}





