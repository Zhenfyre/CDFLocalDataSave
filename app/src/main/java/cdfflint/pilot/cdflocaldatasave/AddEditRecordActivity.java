package cdfflint.pilot.cdflocaldatasave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditRecordActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_ID";
    public static final String EXTRA_TABLET =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_TABLET";
    public static final String EXTRA_COLLECTION_DATE =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_COLLECTION_DATE";
    public static final String EXTRA_COLLECTION_TIME =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_COLLECTION_TIME";
    public static final String EXTRA_TIME_RUNNING =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_TIME_RUNNING";
    public static final String EXTRA_WATER_TEMP =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_WATER_TEMP";

    private NumberPicker numberPickerTablet;
    private EditText editTextDate;
    private EditText editTextTimeCollected;
    private EditText editTextTimeRunning;
    private EditText editTextTemp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        editTextDate = findViewById(R.id.collectionDate);
        editTextTimeCollected = findViewById(R.id.time_of_day);
        editTextTimeRunning = findViewById(R.id.time_running_water);
        editTextTemp = findViewById(R.id.temp_achieved);
        numberPickerTablet = findViewById(R.id.number_picker_tablet);

        numberPickerTablet.setMinValue(0);
        numberPickerTablet.setMaxValue(32);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Record");
            editTextDate.setText(intent.getStringExtra(EXTRA_COLLECTION_DATE));
            editTextTimeCollected.setText(intent.getStringExtra(EXTRA_COLLECTION_TIME));
            editTextTimeRunning.setText(intent.getStringExtra(EXTRA_TIME_RUNNING));
            editTextTemp.setText(intent.getStringExtra(EXTRA_WATER_TEMP));
            numberPickerTablet.setValue(intent.getIntExtra(EXTRA_TABLET, 0));
        } else {
            setTitle("Add Record");
        }
    }

    private void saveRecord(){
        int tabletNumber = numberPickerTablet.getValue();
        String collectionDate = editTextDate.getText().toString();
        String collectionTime = editTextTimeCollected.getText().toString();
        String timeRunning = editTextTimeRunning.getText().toString();
        String waterTemp = editTextTemp.getText().toString();

        if (collectionDate.trim().isEmpty() || collectionTime.trim().isEmpty() || tabletNumber == 0) {
            Toast.makeText(this, "Please enter tablet number, date and time of collection",
                    Toast.LENGTH_LONG).show();
                    return;
        }

        Intent data = new Intent();
            data.putExtra(EXTRA_TABLET, tabletNumber);
            data.putExtra(EXTRA_COLLECTION_DATE, collectionDate);
            data.putExtra(EXTRA_COLLECTION_TIME, collectionTime);
            data.putExtra(EXTRA_TIME_RUNNING, timeRunning);
            data.putExtra(EXTRA_WATER_TEMP, waterTemp);

            int id = getIntent().getIntExtra(EXTRA_ID, -1);
            if (id != -1) {
                data.putExtra(EXTRA_ID, id);
            }

            setResult(RESULT_OK, data);
            finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_record_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_record:
                saveRecord();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}