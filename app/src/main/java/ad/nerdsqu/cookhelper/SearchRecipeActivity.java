package ad.nerdsqu.cookhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by Aaron on 11/27/2016.
 */

public class SearchRecipeActivity extends AppCompatActivity {

    private ArrayList<String> ingredients = new ArrayList<String>();
    private EditText ingredient;
    private EditText name;
    private Spinner category;
    private Spinner type;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_recipe);

        //TODO check if multiple delimiters is OK
        Button addIngredient = (Button) findViewById(R.id.bAddIngredient);
        addIngredient.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ingredients.add(ingredient.getText().toString());
            }
        });

        Button search = (Button)findViewById(R.id.bSearchForRecipe);
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String[] s = {}; // only used to denote type of array for ingredients.toArray()

                //TODO change to new activity with serach results
                if (ingredients.size() > 1 ) {
                    MainActivity.helper.searchByMultipleIngredients(ingredients.toArray(s));

                } else {
                    MainActivity.helper.searchBySingleIngredient(ingredients.get(0));
                }
            }
        });

    }
}
