package ad.nerdsqu.cookhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class IngredientListActivity extends AppCompatActivity {
    private ArrayList<String> ingredients;
    private String[] ingredArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        ListView listview = (ListView)findViewById(R.id.lvSavedList);


        ingredArray = getIntent().getStringArrayExtra("INGREDIENT_LIST");
        String[] split;

        //add array to arrayList changing formatting from <ingredient>:<amount>
        //to <amount> <ingredient>
        for (String ingr : ingredArray) {
            split = ingr.split(":");
            ingredients.add(split[1] + " " + split[0]);
        }


        final ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, R.layout.list_item_template, ingredients);

        listview.setAdapter(itemsAdapter);


        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                ingredients.remove(position);
                ingredArray[position] = "";
                itemsAdapter.notifyDataSetChanged();
                return false;
            }
        });

        Button confirm = (Button)findViewById(R.id.bConfirmRemove);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent();

                ArrayList<String> temp = new ArrayList<>();

                for (String ingred : ingredArray) {
                    if (!ingred.equals(""))  temp.add(ingred);
                }

                String[] updatedIngr = new String[temp.size()];
                updatedIngr = temp.toArray(updatedIngr);

                back.putExtra("INGREDIENT_LIST", updatedIngr);
                setResult(RESULT_OK, back);
                finish();
            }
        });



    }
}
