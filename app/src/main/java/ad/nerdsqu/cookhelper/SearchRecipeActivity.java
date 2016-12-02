package ad.nerdsqu.cookhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Aaron on 11/27/2016.
 */

public class SearchRecipeActivity extends AppCompatActivity {

    private ArrayList<String> ingredients = new ArrayList<String>();
    private EditText ingredientIn;
    private EditText name;
    private Spinner category;
    private Spinner type;
    private StringHelper help = new StringHelper();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_recipe);

        ingredientIn = (EditText)findViewById(R.id.etAddIngredient);
        name = (EditText)findViewById(R.id.etSearchRecipeName);
        category = (Spinner)findViewById(R.id.spinCategory);
        type = (Spinner)findViewById(R.id.spinType);



        Button addIngredient = (Button) findViewById(R.id.bAddSearchIngredient);
        addIngredient.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ingredients.add(help.getEditTextString(ingredientIn));
                Toast.makeText(SearchRecipeActivity.this, "Ingredient Added", Toast.LENGTH_SHORT).show();
                ingredientIn.setText("");
            }
        });


        Button search = (Button)findViewById(R.id.bSearchForRecipe);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rName = help.getEditTextString(name);

                String rCategory = help.getSpinnerString(category);
                String rType = help.getSpinnerString(type);

                String[] rIngredients = new String[ingredients.size()];
                rIngredients = ingredients.toArray(rIngredients);

                ArrayList<Recipe> searchResult = new ArrayList<>();

                boolean ingExists = !(rIngredients.length == 0);
                boolean typeExists = !(rType.equals("")||rType.equals("Any"));
                boolean categExists = !(rCategory.equals("")||rCategory.equals("All"));

                Intent intent = new Intent(SearchRecipeActivity.this, RecipeListActivity.class);

                if (!rName.equals("")) {
                    searchResult = MainActivity.helper.searchByName(rName);
                } else {

                    if (categExists && typeExists && ingExists) {
                        searchResult = MainActivity.helper.GeneralSearch
                                (rCategory, rType, rIngredients);

                    } else if (ingExists && typeExists) {
                        searchResult = MainActivity.helper.searchByIngredientAndType
                                (rIngredients, rType);

                    } else if (ingExists && categExists) {
                        searchResult = MainActivity.helper.searchByIngredientAndCategory
                                (rIngredients, rCategory);

                    } else if (categExists && typeExists) {
                        searchResult = MainActivity.helper.searchByCategoryAndType
                                (rCategory, rType);

                    } else if (ingExists) {
                        searchResult = MainActivity.helper.searchByMultipleIngredients
                                (rIngredients);

                    } else if (typeExists) {
                        searchResult = MainActivity.helper.searchByType
                                (rType);

                    } else if (categExists) {
                        searchResult = MainActivity.helper.searchByCategory
                                (rCategory);

                    } else {
                        Toast.makeText(SearchRecipeActivity.this, "Input Required", Toast.LENGTH_SHORT).show();
                    }
                }
                String[] resultArray = new String[searchResult.size()];
                for (int i = 0; i < searchResult.size(); i++) {
                    resultArray[i] = searchResult.get(i).getRecipeName();
                }
                intent.putExtra("RECIPE_LIST", resultArray);
                startActivity(intent);
            }

        });



    }


}
