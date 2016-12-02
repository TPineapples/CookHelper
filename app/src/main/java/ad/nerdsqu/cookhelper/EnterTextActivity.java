package ad.nerdsqu.cookhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Selection;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/*A simple text input activity that receives existing text as a StringExtra and returns updated
 text to previous activity
 */
public class EnterTextActivity extends AppCompatActivity {
    private EditText directions;
    @Override



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_text);

        Button set = (Button)findViewById(R.id.bConfirmSetText);

        directions = (EditText)findViewById(R.id.etEnterDirections) ;
        directions.setText(getIntent().getStringExtra("CURRENT_TEXT"));

        //move cursor to end of text
        Selection.setSelection(directions.getText(), directions.getText().length());

        //return text
        set.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent back = new Intent();
                back.putExtra("DIRECTIONS", directions.getText().toString());
                setResult(RESULT_OK, back);
                finish();
            }
        });

    }
}
