package ad.nerdsqu.cookhelper;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Aaron on 11/29/2016.
 *
 * Simple class to get strings from components while avoiding null references
 */

public class StringHelper {

    public String getEditTextString(EditText text) {
        return !(text.getText() == null) ? text.getText().toString() : "";
    }

    public String getSpinnerString(Spinner spin) {
        return !(spin.getSelectedItem() == null) ? spin.getSelectedItem().toString() : "";
    }

    public String getTextViewString(TextView text) {
        return !(text.getText() == null) ? text.getText().toString() : "";
    }
}
