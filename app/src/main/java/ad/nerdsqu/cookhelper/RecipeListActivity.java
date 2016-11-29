package ad.nerdsqu.cookhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class RecipeListActivity extends AppCompatActivity {
    private String[] recipes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        ListView listview = (ListView)findViewById(R.id.lvSavedList);

        recipes = getIntent().getStringArrayExtra("RECIPE_LIST");

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, R.layout.list_item_template, recipes);


        listview.setAdapter(itemsAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(RecipeListActivity.this, RecipeActivity.class);
                //If you wanna send any data to nextActicity.class you can use
                i.putExtra("RECIPE_NAME", recipes[position]);

                startActivity(i);
            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent i = new Intent(RecipeListActivity.this, RecipeManageActivity.class);
                i.putExtra("RECIPE_NAME", recipes[position]);
                startActivity(i);

                return true;
            }
        });
    }



}
