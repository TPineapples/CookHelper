package ad.nerdsqu.cookhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ScrollView;

/**
 * Created by Aaron on 11/27/2016.
 */

public class AddRecipeActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        ScrollView list = (ScrollView)findViewById(R.id.scrollList);
        list.setEnabled(false);
    }
}
