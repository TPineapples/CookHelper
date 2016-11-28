package ad.nerdsqu.cookhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Aaron on 11/28/2016.
 */

public class ViewDirectionsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_directions);

        TextView directions = (TextView) findViewById(R.id.tvDirectionsFullscreen);
        directions.setText(getIntent().getStringExtra("DIRECTIONS"));
    }
}
