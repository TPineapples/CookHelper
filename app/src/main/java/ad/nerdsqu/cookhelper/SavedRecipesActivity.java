package ad.nerdsqu.cookhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SavedRecipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_recipes);
        final ListView listview = (ListView)findViewById(R.id.saved_list);
        final ArrayList<String> values = new ArrayList<String>();
        ArrayList<Recipe> recipes = MainActivity.helper.getAllRecipes();

        for (Recipe recipe : recipes) {
            values.add(recipe.RecipeName);
        }

        for (int i = 0; i<4; i++) {
            values.add("Test"+i);
        }


        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, R.layout.list_item_template, values);

        listview.setAdapter(itemsAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(SavedRecipesActivity.this, RecipeActivity.class);
                //If you wanna send any data to nextActicity.class you can use
                i.putExtra("RECIPE_NAME", values.get(position));

                startActivity(i);
            }
        });
    }



}
