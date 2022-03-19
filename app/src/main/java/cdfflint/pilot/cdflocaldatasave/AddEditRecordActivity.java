package cdfflint.pilot.cdflocaldatasave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

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
    public static final String EXTRA_NORMAL_USE =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_NORMAL_USE";
    public static final String EXTRA_WATER_COLOR =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_WATER_COLOR";
    public static final String EXTRA_WATER_SMELL =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_WATER_SMELL";
    public static final String EXTRA_WATER_TASTE =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_WATER_TASTE";
    public static final String EXTRA_ROTTEN_EGG =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_ROTTEN_EGG";
    public static final String EXTRA_SEDIMENT_PRESENT =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_SEDIMENT_PRESENT";
    public static final String EXTRA_SEDIMENT_FEATHERY =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_SEDIMENT_FEATHERY";
    public static final String EXTRA_BACTERIA_RESULT =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_BACTERIA_RESULT";
    public static final String EXTRA_HARDNESS_PPM =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_HARDNESS_PPM";
    public static final String EXTRA_CHLORINE_PPM =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_CHLORINE_PPM";
    public static final String EXTRA_ALKALINITY_PPM =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_ALKALINITY_PPM";
    public static final String EXTRA_COPPER_PPM =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_COPPER_PPM";
    public static final String EXTRA_IRON_PPM =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_IRON_PPM";
    public static final String EXTRA_PH_VALUE =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_PH_VALUE";
    public static final String EXTRA_PESTICIDE_RESULT =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_PESTICIDE_RESULT";
    public static final String EXTRA_LEAD_RESULT =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_LEAD_RESULT";
    public static final String EXTRA_NITRITE_RESULT =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_NITRITE_RESULT";
    public static final String EXTRA_NITRATE_RESULT =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_NITRATE_RESULT";

    private NumberPicker numberPickerTablet;
    private EditText editTextDate;
    private EditText editTextTimeCollected;
    private EditText editTextTimeRunning;
    private EditText editTextTemp;
    private EditText editTextNormalUse;
    private EditText editTextWaterColor;
    private EditText editTextWaterSmell;
    private EditText editTextWaterTaste;
    private EditText editTextRottenEgg;
    private EditText editTextSedimentPresent;
    private EditText editTextSedimentFeathery;
    private EditText editTextBacteriaResult;
    private EditText editTextHardnessPpm;
    private EditText editTextChlorinePpm;
    private EditText editTextAlkalinityPpm;
    private EditText editTextCopperPpm;
    private EditText editTextIronPpm;
    private EditText editTextPhValue;
    private EditText editTextPesticideResult;
    private EditText editTextLeadResult;
    private EditText editTextNitriteResult;
    private EditText editTextNitrateResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        editTextDate = findViewById(R.id.collectionDate);
        editTextTimeCollected = findViewById(R.id.time_of_day);
        editTextTimeRunning = findViewById(R.id.time_running_water);
        editTextTemp = findViewById(R.id.temp_achieved);
        numberPickerTablet = findViewById(R.id.number_picker_tablet);
        editTextNormalUse = findViewById(R.id.normal_use);
        editTextWaterColor = findViewById(R.id.water_color);
        editTextWaterSmell = findViewById(R.id.water_smell);
        editTextWaterTaste = findViewById(R.id.water_taste);
        editTextRottenEgg = findViewById(R.id.rotten_egg);
        editTextSedimentPresent = findViewById(R.id.sediment_present);
        editTextSedimentFeathery = findViewById(R.id.sediment_feathery);
        editTextBacteriaResult = findViewById(R.id.bacteria_result);
        editTextHardnessPpm = findViewById(R.id.hardness_ppm);
        editTextChlorinePpm = findViewById(R.id.chlorine_ppm);
        editTextAlkalinityPpm = findViewById(R.id.alkalinity_ppm);
        editTextCopperPpm = findViewById(R.id.copper_ppm);
        editTextIronPpm = findViewById(R.id.iron_ppm);
        editTextPhValue = findViewById(R.id.ph_value);
        editTextPesticideResult = findViewById(R.id.pesticide_result);
        editTextLeadResult = findViewById(R.id.lead_result);
        editTextNitriteResult = findViewById(R.id.nitrite_result);
        editTextNitrateResult = findViewById(R.id.nitrate_result);

        numberPickerTablet.setMinValue(0);
        numberPickerTablet.setMaxValue(32);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Record");
            editTextDate.setText(intent.getStringExtra(EXTRA_COLLECTION_DATE));
            editTextTimeCollected.setText(intent.getStringExtra(EXTRA_COLLECTION_TIME));
            editTextTimeRunning.setText(intent.getStringExtra(EXTRA_TIME_RUNNING));
            editTextTemp.setText(intent.getStringExtra(EXTRA_WATER_TEMP));
            numberPickerTablet.setValue(intent.getIntExtra(EXTRA_TABLET, 0));
            editTextNormalUse.setText(intent.getStringExtra(EXTRA_NORMAL_USE));
            editTextWaterColor.setText(intent.getStringExtra(EXTRA_WATER_COLOR));
            editTextWaterSmell.setText(intent.getStringExtra(EXTRA_WATER_SMELL));
            editTextWaterTaste.setText(intent.getStringExtra(EXTRA_WATER_TASTE));
            editTextRottenEgg.setText(intent.getStringExtra(EXTRA_ROTTEN_EGG));
            editTextSedimentPresent.setText(intent.getStringExtra(EXTRA_SEDIMENT_PRESENT));
            editTextSedimentFeathery.setText(intent.getStringExtra(EXTRA_SEDIMENT_FEATHERY));
            editTextBacteriaResult.setText(intent.getStringExtra(EXTRA_BACTERIA_RESULT));
            editTextHardnessPpm.setText(intent.getStringExtra(EXTRA_HARDNESS_PPM));
            editTextChlorinePpm.setText(intent.getStringExtra(EXTRA_CHLORINE_PPM));
            editTextAlkalinityPpm.setText(intent.getStringExtra(EXTRA_ALKALINITY_PPM));
            editTextCopperPpm.setText(intent.getStringExtra(EXTRA_COPPER_PPM));
            editTextIronPpm.setText(intent.getStringExtra(EXTRA_IRON_PPM));
            editTextPhValue.setText(intent.getStringExtra(EXTRA_PH_VALUE));
            editTextPesticideResult.setText(intent.getStringExtra(EXTRA_PESTICIDE_RESULT));
            editTextLeadResult.setText(intent.getStringExtra(EXTRA_LEAD_RESULT));
            editTextNitriteResult.setText(intent.getStringExtra(EXTRA_NITRITE_RESULT));
            editTextNitrateResult.setText(intent.getStringExtra(EXTRA_NITRATE_RESULT));

        } else {
            setTitle("Add Record");
        }
    }

    private void saveRecord() {
        int tabletNumber = numberPickerTablet.getValue();
        String collectionDate = editTextDate.getText().toString();
        String collectionTime = editTextTimeCollected.getText().toString();
        String timeRunning = editTextTimeRunning.getText().toString();
        String waterTemp = editTextTemp.getText().toString();
        String normalUse = editTextNormalUse.getText().toString();
        String waterColor = editTextWaterColor.getText().toString();
        String waterSmell = editTextWaterSmell.getText().toString();
        String waterTaste = editTextWaterTaste.getText().toString();
        String rottenEgg = editTextRottenEgg.getText().toString();
        String sedimentPresent = editTextSedimentPresent.getText().toString();
        String sedimentFeathery = editTextSedimentFeathery.getText().toString();
        String bacteriaResult = editTextBacteriaResult.getText().toString();
        String hardnessPpm = editTextHardnessPpm.getText().toString();
        String chlorinePpm = editTextChlorinePpm.getText().toString();
        String alkalinityPpm = editTextAlkalinityPpm.getText().toString();
        String copperPpm = editTextCopperPpm.getText().toString();
        String ironPpm = editTextIronPpm.getText().toString();
        String phValue = editTextPhValue.getText().toString();
        String pesticideResult = editTextPesticideResult.getText().toString();
        String leadResult = editTextLeadResult.getText().toString();
        String nitriteResult = editTextNitriteResult.getText().toString();
        String nitrateResult = editTextNitrateResult.getText().toString();

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
        data.putExtra(EXTRA_NORMAL_USE, normalUse);
        data.putExtra(EXTRA_WATER_COLOR, waterColor);
        data.putExtra(EXTRA_WATER_SMELL, waterSmell);
        data.putExtra(EXTRA_WATER_TASTE, waterTaste);
        data.putExtra(EXTRA_ROTTEN_EGG, rottenEgg);
        data.putExtra(EXTRA_SEDIMENT_PRESENT, sedimentPresent);
        data.putExtra(EXTRA_SEDIMENT_FEATHERY, sedimentFeathery);
        data.putExtra(EXTRA_BACTERIA_RESULT, bacteriaResult);
        data.putExtra(EXTRA_HARDNESS_PPM, hardnessPpm);
        data.putExtra(EXTRA_CHLORINE_PPM, chlorinePpm);
        data.putExtra(EXTRA_ALKALINITY_PPM, alkalinityPpm);
        data.putExtra(EXTRA_COPPER_PPM, copperPpm);
        data.putExtra(EXTRA_IRON_PPM, ironPpm);
        data.putExtra(EXTRA_PH_VALUE, phValue);
        data.putExtra(EXTRA_PESTICIDE_RESULT, pesticideResult);
        data.putExtra(EXTRA_LEAD_RESULT, leadResult);
        data.putExtra(EXTRA_NITRITE_RESULT, nitriteResult);
        data.putExtra(EXTRA_NITRATE_RESULT, nitrateResult);

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
                addItemToSheet();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void   addItemToSheet() {
        int tabletNumber = numberPickerTablet.getValue();
        String tabletNumberGet = Integer.toString(tabletNumber);
        final String tabletNumberSend;
        if (tabletNumber < 10) {
            tabletNumberSend = "'00" + tabletNumberGet;
        } else if (tabletNumber < 100) {
            tabletNumberSend = "'0" + tabletNumberGet;
        } else tabletNumberSend =  tabletNumberGet;

        final String dateCollectedSend = editTextDate.getText().toString().trim();
        final String timeCollectedSend = editTextTimeCollected.getText().toString().trim();
        final String timeRunningSend = editTextTimeRunning.getText().toString().trim();
        final String waterTempSend = editTextTemp.getText().toString().trim();
        final String normalUseSend = editTextNormalUse.getText().toString().trim();
        final String waterColorSend = editTextWaterColor.getText().toString().trim();
        final String waterSmellSend = editTextWaterSmell.getText().toString().trim();
        final String waterTasteSend = editTextWaterTaste.getText().toString().trim();
        final String rottenEggSend = editTextRottenEgg.getText().toString().trim();
        final String sedimentPresentSend = editTextSedimentPresent.getText().toString().trim();
        final String sedimentFeatherySend = editTextSedimentFeathery.getText().toString().trim();
        final String bacteriaResultSend = editTextBacteriaResult.getText().toString().trim();
        final String hardnessPpmSend = editTextHardnessPpm.getText().toString().trim();
        final String chlorinePpmSend = editTextChlorinePpm.getText().toString().trim();
        final String alkalinityPpmSend = editTextAlkalinityPpm.getText().toString().trim();
        final String copperPpmSend = editTextCopperPpm.getText().toString().trim();
        final String ironPpmSend = editTextIronPpm.getText().toString().trim();
        final String phValueSend = editTextPhValue.getText().toString().trim();
        final String pesticideResultSend = editTextPesticideResult.getText().toString().trim();
        final String leadResultSend = editTextLeadResult.getText().toString().trim();
        final String nitriteResultSend = editTextNitriteResult.getText().toString().trim();
        final String nitrateResultSend = editTextNitrateResult.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://script.google.com/macros/s/AKfycbwVOKSgVxhTbl4FpkorCU73doMw6bgZn8Yb-f5eXweBsZgw-zDL9Y09-LZK7cq1TIF-OA/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> paramsMap = new HashMap<>();

                paramsMap.put("action","addItem");
                paramsMap.put("participantId",tabletNumberSend);
                paramsMap.put("dateCollected",dateCollectedSend);
                paramsMap.put("timeCollected",timeCollectedSend);
                paramsMap.put("timeRunning",timeRunningSend);
                paramsMap.put("waterTemp",waterTempSend);
                paramsMap.put("normalUse",normalUseSend);
                paramsMap.put("waterColor",waterColorSend);
                paramsMap.put("waterSmell",waterSmellSend);
                paramsMap.put("waterTaste",waterTasteSend);
                paramsMap.put("rottenEgg",rottenEggSend);
                paramsMap.put("sedimentPresent",sedimentPresentSend);
                paramsMap.put("sedimentFeathery",sedimentFeatherySend);
                paramsMap.put("bacteriaResult",bacteriaResultSend);
                paramsMap.put("hardnessPpm",hardnessPpmSend);
                paramsMap.put("chlorinePpm",chlorinePpmSend);
                paramsMap.put("alkalinityPpm",alkalinityPpmSend);
                paramsMap.put("copperPpm",copperPpmSend);
                paramsMap.put("ironPpm",ironPpmSend);
                paramsMap.put("phValue",phValueSend);
                paramsMap.put("pesticideResult",pesticideResultSend);
                paramsMap.put("leadResult",leadResultSend);
                paramsMap.put("nitriteResult",nitriteResultSend);
                paramsMap.put("nitrateResult",nitrateResultSend);

                return paramsMap;
            }
        };

        int socketTimeOut = 50000;

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);

    }

}