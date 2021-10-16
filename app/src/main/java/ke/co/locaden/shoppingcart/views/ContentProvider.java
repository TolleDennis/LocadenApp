package ke.co.locaden.shoppingcart.views;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.os.Bundle;
import ke.co.locaden.R;
import ke.co.locaden.databinding.ActivityContentProviderBinding;


public class ContentProvider extends AppCompatActivity {
    private ActivityContentProviderBinding binding;

    private static final String TAG = "ContentProviderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_content_provider);
        setUp();
    }

    private void setUp() {
        try {
            binding.carAdd.setOnClickListener(view -> {
                if (validate()) {
                    onClickAddName();
                }
            });
            binding.carRetrieve.setOnClickListener(view -> onClickRetrieve());
        } catch (Exception e) {
            Log.e(TAG, "setUp: ", e);
        }
    }

    private boolean validate() {
        boolean valid = true;
        try {
            if (binding.carName.getText().toString().matches("")) {
                binding.carsInStock.setError("Cannot be empty");
                valid = false;
            }

            if (binding.carsInStock.getText().toString().matches("")) {
                binding.carsInStock.setError("Cannot be empty");
                valid = false;
            }

        } catch (Exception e) {
            Log.e(TAG, "validate: ", e);
        }

        return valid;
    }

    public void onClickAddName() {
        try {
            // Add a new student record
            ContentValues values = new ContentValues();
            values.put(AvaillableCarsProvider.CAR_NAME,
                    binding.carName.getText().toString());

            values.put(AvaillableCarsProvider.CARS_IN_STOCK,
                    binding.carsInStock.getText().toString());

            Uri uri = getContentResolver().insert(
                    AvaillableCarsProvider.CONTENT_URI, values);

            Toast.makeText(getBaseContext(),
                    uri.toString(), Toast.LENGTH_LONG).show();

            // reset the input fields
            binding.carName.setText("");
            binding.carsInStock.setText("");

        } catch (Exception e) {
            Log.e(TAG, "onClickAddName: ", e);
        }

    }
    public void onClickRetrieve() {
        try {
            // Retrieve teams records
            String URL = "content://epl";

            Uri teams = Uri.parse(URL);
            Cursor c = managedQuery(teams, null, null,
                    null, "team_name");

            if (c.moveToFirst()) {
                do {
                    Toast.makeText(this,
                            c.getString(c.getColumnIndex(AvaillableCarsProvider._ID)) +
                                    ".  NAME: " + c.getString(c.getColumnIndex(AvaillableCarsProvider.CAR_NAME)) +
                                    " POINTS: " + c.getString(c.getColumnIndex(AvaillableCarsProvider.CARS_IN_STOCK)),
                            Toast.LENGTH_SHORT).show();
                } while (c.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "onClickRetrieve: ", e);
        }
    }

}