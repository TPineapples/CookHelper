package ad.nerdsqu.cookhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RecipeManageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_manage);

        Button edit = (Button)findViewById(R.id.bEditRecipe);
        Button delete = (Button)findViewById(R.id.bDelReicpe);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edit = new Intent(RecipeManageActivity.this, AddRecipeActivity.class);
                edit.putExtra("RECIPE_NAME", getIntent().getStringExtra("RECIPE_NAME"));
                startActivity(edit);
            }
        });
        
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent delete = new Intent(RecipeManageActivity.this, UserAreaActivity.class);
                MainActivity.helper.deleteRecipeFromName(getIntent().getStringExtra("RECIPE_NAME"));
                Toast.makeText(RecipeManageActivity.this, "Recipe Deleted", Toast.LENGTH_SHORT).show();
                startActivity(delete);
            }
        });

    }
}
