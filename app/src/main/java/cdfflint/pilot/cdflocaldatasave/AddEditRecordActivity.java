package cdfflint.pilot.cdflocaldatasave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
    public static final String EXTRA_LATITUDE =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_LATITUDE";
    public static final String EXTRA_LONGITUDE =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_LONGITUDE";
    public static final String EXTRA_LOCALITY =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_LOCALITY";
    public static final String EXTRA_ZIPCODE =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_ZIPCODE";
    public static final String EXTRA_ADDRESS =
            "cdfflint.pilot.cdflocaldatasave.EXTRA_ADDRESS";

    private NumberPicker numberPickerTablet;
    private EditText editTextDate;
    private EditText editTextTimeCollected;
    private EditText editTextTimeRunning;

    private RadioGroup tempGroup;
    private RadioButton tempButton;
    private String tempStringRecord;
    private int tempRadioId = -1;

    private EditText editTextNormalUse;
    private EditText editTextWaterColor;
    private EditText editTextWaterSmell;
    private EditText editTextWaterTaste;

    private String rottenEggStringRecord;
    private CheckBox rottenEggCheckbox;

    private String sedimentPresentStringRecord;
    private CheckBox sedimentPresentCheckbox;

    private String sedimentFeatheryStringRecord;
    private CheckBox sedimentFeatheryCheckbox;

    private String bacteriaStringRecord;
    private CheckBox bacteriaCheckbox;

    private EditText editTextHardnessPpm;
    private EditText editTextChlorinePpm;
    private EditText editTextAlkalinityPpm;
    private EditText editTextCopperPpm;
    private EditText editTextIronPpm;
    private EditText editTextPhValue;

    private String pesticideStringRecord;
    private CheckBox pesticideCheckbox;

    private String leadStringRecord;
    private CheckBox leadCheckbox;

    private EditText editTextNitriteResult;
    private EditText editTextNitrateResult;
    private EditText editTextLatitude;
    private EditText editTextLongitude;
    private EditText editTextLocality;
    private EditText editTextZipCode;
    private EditText editTextAddress;

    private String latitude;
    private String longitude;
    private String locality;
    private String zipCode;
    private String address;

    Button addLocationButton, defaultLocationButton;
    TextView textView1, textView2, textView3, textView4, textView5;
    LinearLayout linearLayout1, linearLayout2, linearLayout3;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        addLocationButton = findViewById(R.id.current_location_button);
        defaultLocationButton = findViewById(R.id.refuse_location_button);
        editTextLatitude = findViewById(R.id.edit_text1);
        editTextLongitude = findViewById(R.id.edit_text2);
        editTextLocality = findViewById(R.id.edit_text3);
        editTextZipCode = findViewById(R.id.edit_text4);
        editTextAddress = findViewById(R.id.edit_text5);
        linearLayout1 = findViewById(R.id.linear_layout1);
        linearLayout2 = findViewById(R.id.linear_layout2);
        linearLayout3 = findViewById(R.id.linear_layout3);
        textView1 = findViewById(R.id.text_view1);
        textView2 = findViewById(R.id.text_view2);
        textView3 = findViewById(R.id.text_view3);
        textView4 = findViewById(R.id.text_view4);
        textView5 = findViewById(R.id.text_view5);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        addLocationButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (ActivityCompat.checkSelfPermission(AddEditRecordActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                    editTextLatitude.setVisibility(View.VISIBLE);
                    editTextLongitude.setVisibility(View.VISIBLE);
                    editTextLocality.setVisibility(View.VISIBLE);
                    editTextZipCode.setVisibility(View.VISIBLE);
                    editTextAddress.setVisibility(View.VISIBLE);
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout3.setVisibility(View.VISIBLE);
                    textView1.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView3.setVisibility(View.VISIBLE);
                    textView4.setVisibility(View.VISIBLE);
                    textView5.setVisibility(View.VISIBLE);

                } else {
                    ActivityCompat.requestPermissions(AddEditRecordActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });

        defaultLocationButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                    editTextLatitude.setVisibility(View.VISIBLE);
                    editTextLongitude.setVisibility(View.VISIBLE);
                    editTextLocality.setVisibility(View.VISIBLE);
                    editTextZipCode.setVisibility(View.VISIBLE);
                    editTextAddress.setVisibility(View.VISIBLE);
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout3.setVisibility(View.VISIBLE);
                    textView1.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView3.setVisibility(View.VISIBLE);
                    textView4.setVisibility(View.VISIBLE);
                    textView5.setVisibility(View.VISIBLE);

                    Location location = new Location("defaultlocation");
                    location.setLatitude(42.73300);
                    location.setLongitude(-84.47820);

                editTextLatitude.setText(Html.fromHtml(String.valueOf(location.getLatitude())));
                editTextLongitude.setText(Html.fromHtml(String.valueOf(location.getLongitude())));
                editTextLocality.setText("East Lansing");
                editTextZipCode.setText("48824");
                editTextAddress.setText("509 E. Circle Drive, East Lansing, MI 48824, USA");
            }
        });

        editTextDate = findViewById(R.id.collectionDate);
        editTextTimeCollected = findViewById(R.id.time_of_day);
        editTextTimeRunning = findViewById(R.id.time_running_water);

        tempGroup = findViewById(R.id.temp_achieved_radio_group);

        numberPickerTablet = findViewById(R.id.number_picker_tablet);
        editTextNormalUse = findViewById(R.id.normal_use);
        editTextWaterColor = findViewById(R.id.water_color);
        editTextWaterSmell = findViewById(R.id.water_smell);
        editTextWaterTaste = findViewById(R.id.water_taste);

        rottenEggCheckbox = findViewById(R.id.rotten_egg_checkbox);
        sedimentPresentCheckbox = findViewById(R.id.sediment_present_checkbox);
        sedimentFeatheryCheckbox = findViewById(R.id.sediment_feathery_checkbox);
        bacteriaCheckbox = findViewById(R.id.bacteria_result_checkbox);

        editTextHardnessPpm = findViewById(R.id.hardness_ppm);
        editTextChlorinePpm = findViewById(R.id.chlorine_ppm);
        editTextAlkalinityPpm = findViewById(R.id.alkalinity_ppm);
        editTextCopperPpm = findViewById(R.id.copper_ppm);
        editTextIronPpm = findViewById(R.id.iron_ppm);
        editTextPhValue = findViewById(R.id.ph_value);

        pesticideCheckbox = findViewById(R.id.positive_pesticide_checkbox);
        leadCheckbox = findViewById(R.id.positive_lead_checkbox);

        editTextNitriteResult = findViewById(R.id.nitrite_result);
        editTextNitrateResult = findViewById(R.id.nitrate_result);

        TabletNumbers.initNumbers();
        numberPickerTablet.setMinValue(0);
        numberPickerTablet.setMaxValue(TabletNumbers.getTabletArrayList().size()-1);
        numberPickerTablet.setDisplayedValues(TabletNumbers.numberList());

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Record");
            editTextDate.setText(intent.getStringExtra(EXTRA_COLLECTION_DATE));
            editTextTimeCollected.setText(intent.getStringExtra(EXTRA_COLLECTION_TIME));
            editTextTimeRunning.setText(intent.getStringExtra(EXTRA_TIME_RUNNING));

            tempStringRecord = intent.getStringExtra(EXTRA_WATER_TEMP);
            switch (tempStringRecord) {
                case "Cold":
                    tempGroup.check(R.id.cold_radio_button);
                    break;
                case "Cool":
                    tempGroup.check(R.id.cool_radio_button);
                    break;
                case "Tepid":
                    tempGroup.check(R.id.tepid_radio_button);
                    break;
                case "Hot":
                    tempGroup.check(R.id.hot_radio_button);
                    break;
                case "Scalding":
                    tempGroup.check(R.id.scalding_radio_button);
                    break;
            }

            numberPickerTablet.setValue(intent.getIntExtra(EXTRA_TABLET, 0));
            editTextNormalUse.setText(intent.getStringExtra(EXTRA_NORMAL_USE));
            editTextWaterColor.setText(intent.getStringExtra(EXTRA_WATER_COLOR));
            editTextWaterSmell.setText(intent.getStringExtra(EXTRA_WATER_SMELL));
            editTextWaterTaste.setText(intent.getStringExtra(EXTRA_WATER_TASTE));

            rottenEggStringRecord = intent.getStringExtra(EXTRA_ROTTEN_EGG);
            switch (rottenEggStringRecord) {
                case "Yes":
                    rottenEggCheckbox.setChecked(true);
                    break;
                default:
                    rottenEggCheckbox.setChecked(false);
                    break;
            }

            sedimentPresentStringRecord = intent.getStringExtra(EXTRA_SEDIMENT_PRESENT);
            switch (sedimentPresentStringRecord) {
                case "Yes":
                    sedimentPresentCheckbox.setChecked(true);
                    break;
                default:
                    sedimentPresentCheckbox.setChecked(false);
                    break;
            }

            sedimentFeatheryStringRecord = intent.getStringExtra(EXTRA_SEDIMENT_FEATHERY);
            switch (sedimentFeatheryStringRecord) {
                case "Yes":
                    sedimentFeatheryCheckbox.setChecked(true);
                    break;
                default:
                    sedimentFeatheryCheckbox.setChecked(false);
                    break;
            }

            bacteriaStringRecord = intent.getStringExtra(EXTRA_BACTERIA_RESULT);
            switch (bacteriaStringRecord) {
                case "Positive":
                    bacteriaCheckbox.setChecked(true);
                    break;
                default:
                    bacteriaCheckbox.setChecked(false);
                    break;
            }

            editTextHardnessPpm.setText(intent.getStringExtra(EXTRA_HARDNESS_PPM));
            editTextChlorinePpm.setText(intent.getStringExtra(EXTRA_CHLORINE_PPM));
            editTextAlkalinityPpm.setText(intent.getStringExtra(EXTRA_ALKALINITY_PPM));
            editTextCopperPpm.setText(intent.getStringExtra(EXTRA_COPPER_PPM));
            editTextIronPpm.setText(intent.getStringExtra(EXTRA_IRON_PPM));
            editTextPhValue.setText(intent.getStringExtra(EXTRA_PH_VALUE));

            pesticideStringRecord = intent.getStringExtra(EXTRA_PESTICIDE_RESULT);
            switch (pesticideStringRecord) {
                case "Positive":
                    pesticideCheckbox.setChecked(true);
                    break;
                default:
                    pesticideCheckbox.setChecked(false);
                    break;
            }

            leadStringRecord = intent.getStringExtra(EXTRA_LEAD_RESULT);
            switch (leadStringRecord) {
                case "Positive":
                    leadCheckbox.setChecked(true);
                    break;
                default:
                    leadCheckbox.setChecked(false);
                    break;
            }
            editTextNitriteResult.setText(intent.getStringExtra(EXTRA_NITRITE_RESULT));
            editTextNitrateResult.setText(intent.getStringExtra(EXTRA_NITRATE_RESULT));
            editTextLatitude.setText(intent.getStringExtra(EXTRA_LATITUDE));
            editTextLongitude.setText(intent.getStringExtra(EXTRA_LONGITUDE));
            editTextLocality.setText(intent.getStringExtra(EXTRA_LOCALITY));
            editTextZipCode.setText(intent.getStringExtra(EXTRA_ZIPCODE));
            editTextAddress.setText(intent.getStringExtra(EXTRA_ADDRESS));

            editTextLatitude.setVisibility(View.VISIBLE);
            editTextLongitude.setVisibility(View.VISIBLE);
            editTextLocality.setVisibility(View.VISIBLE);
            editTextZipCode.setVisibility(View.VISIBLE);
            editTextAddress.setVisibility(View.VISIBLE);
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            linearLayout3.setVisibility(View.VISIBLE);
            textView1.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
            textView4.setVisibility(View.VISIBLE);
            textView5.setVisibility(View.VISIBLE);

        } else {
            setTitle("Add Record");
        }
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(AddEditRecordActivity.this,
                                Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1);
                        editTextLatitude.setText(Html.fromHtml(String.valueOf(addresses.get(0).getLatitude())));
                        editTextLongitude.setText(Html.fromHtml(String.valueOf(addresses.get(0).getLongitude())));
                        editTextLocality.setText(Html.fromHtml(addresses.get(0).getLocality()));
                        editTextZipCode.setText(Html.fromHtml(addresses.get(0).getPostalCode()));
                        editTextAddress.setText(Html.fromHtml(addresses.get(0).getAddressLine(0)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void saveRecord() {
        int tabletNumber = numberPickerTablet.getValue();
        String collectionDate = editTextDate.getText().toString();
        String collectionTime = editTextTimeCollected.getText().toString();
        String timeRunning = editTextTimeRunning.getText().toString();

        tempRadioId = tempGroup.getCheckedRadioButtonId();
        tempButton = findViewById(tempRadioId);
        tempStringRecord = tempButton.getText().toString();

        String normalUse = editTextNormalUse.getText().toString();
        String waterColor = editTextWaterColor.getText().toString();
        String waterSmell = editTextWaterSmell.getText().toString();
        String waterTaste = editTextWaterTaste.getText().toString();

        boolean rottenEggIsChecked = rottenEggCheckbox.isChecked();
        if (rottenEggIsChecked) {
            rottenEggStringRecord = "Yes";
        } else {
            rottenEggStringRecord = "Missing";
        }

        boolean sedimentPresentIsChecked= sedimentPresentCheckbox.isChecked();
        if (sedimentPresentIsChecked) {
            sedimentPresentStringRecord = "Yes";
        } else {
            sedimentPresentStringRecord = "Missing";
        }

        boolean sedimentFeatheryIsChecked= sedimentFeatheryCheckbox.isChecked();
        if (sedimentFeatheryIsChecked) {
            sedimentFeatheryStringRecord = "Yes";
        } else {
            sedimentFeatheryStringRecord = "Missing";
        }

        boolean bacteriaIsChecked = bacteriaCheckbox.isChecked();
        if (bacteriaIsChecked) {
            bacteriaStringRecord = "Positive";
        } else {
            bacteriaStringRecord = "Missing";
        }

        String hardnessPpm = editTextHardnessPpm.getText().toString();
        String chlorinePpm = editTextChlorinePpm.getText().toString();
        String alkalinityPpm = editTextAlkalinityPpm.getText().toString();
        String copperPpm = editTextCopperPpm.getText().toString();
        String ironPpm = editTextIronPpm.getText().toString();
        String phValue = editTextPhValue.getText().toString();

        boolean pesticideIsChecked = pesticideCheckbox.isChecked();
        if (pesticideIsChecked) {
            pesticideStringRecord = "Positive";
        } else {
            pesticideStringRecord = "Missing";
        }

        boolean leadIsChecked = leadCheckbox.isChecked();
        if (leadIsChecked) {
            leadStringRecord = "Positive";
        } else {
            leadStringRecord = "Missing";
        }

        String nitriteResult = editTextNitriteResult.getText().toString();
        String nitrateResult = editTextNitrateResult.getText().toString();
        latitude = editTextLatitude.getText().toString();
        longitude = editTextLongitude.getText().toString();
        locality = editTextLocality.getText().toString();
        zipCode = editTextZipCode.getText().toString();
        address = editTextAddress.getText().toString();

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
        data.putExtra(EXTRA_WATER_TEMP, tempStringRecord);
        data.putExtra(EXTRA_NORMAL_USE, normalUse);
        data.putExtra(EXTRA_WATER_COLOR, waterColor);
        data.putExtra(EXTRA_WATER_SMELL, waterSmell);
        data.putExtra(EXTRA_WATER_TASTE, waterTaste);
        data.putExtra(EXTRA_ROTTEN_EGG, rottenEggStringRecord);
        data.putExtra(EXTRA_SEDIMENT_PRESENT, sedimentPresentStringRecord);
        data.putExtra(EXTRA_SEDIMENT_FEATHERY, sedimentFeatheryStringRecord);
        data.putExtra(EXTRA_BACTERIA_RESULT, bacteriaStringRecord);
        data.putExtra(EXTRA_HARDNESS_PPM, hardnessPpm);
        data.putExtra(EXTRA_CHLORINE_PPM, chlorinePpm);
        data.putExtra(EXTRA_ALKALINITY_PPM, alkalinityPpm);
        data.putExtra(EXTRA_COPPER_PPM, copperPpm);
        data.putExtra(EXTRA_IRON_PPM, ironPpm);
        data.putExtra(EXTRA_PH_VALUE, phValue);
        data.putExtra(EXTRA_PESTICIDE_RESULT, pesticideStringRecord);
        data.putExtra(EXTRA_LEAD_RESULT, leadStringRecord);
        data.putExtra(EXTRA_NITRITE_RESULT, nitriteResult);
        data.putExtra(EXTRA_NITRATE_RESULT, nitrateResult);
        data.putExtra(EXTRA_LATITUDE, latitude);
        data.putExtra(EXTRA_LONGITUDE, longitude);
        data.putExtra(EXTRA_LOCALITY, locality);
        data.putExtra(EXTRA_ZIPCODE, zipCode);
        data.putExtra(EXTRA_ADDRESS, address);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:<" + latitude  + ">,<" + longitude + ">?q=<" + latitude  + ">,<" + longitude + ">(" + address + ")"));
        startActivity(intent);

        addItemToSheet();
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

        tempRadioId = tempGroup.getCheckedRadioButtonId();
        tempButton = findViewById(tempRadioId);
        final String tempSend = tempButton.getText().toString().trim();

        final String normalUseSend = editTextNormalUse.getText().toString().trim();
        final String waterColorSend = editTextWaterColor.getText().toString().trim();
        final String waterSmellSend = editTextWaterSmell.getText().toString().trim();
        final String waterTasteSend = editTextWaterTaste.getText().toString().trim();

        boolean rottenEggIsChecked = rottenEggCheckbox.isChecked();
        if (rottenEggIsChecked) {
            rottenEggStringRecord = "Yes";
        } else {
            rottenEggStringRecord = "Missing";
        }
        final String rottenEggSend = rottenEggStringRecord.trim();

        boolean sedimentPresentIsChecked = sedimentPresentCheckbox.isChecked();
        if (sedimentPresentIsChecked) {
            sedimentPresentStringRecord = "Yes";
        } else {
            sedimentPresentStringRecord = "Missing";
        }
        final String sedimentPresentSend = sedimentPresentStringRecord.trim();

        boolean sedimentFeatheryIsChecked = sedimentFeatheryCheckbox.isChecked();
        if (sedimentFeatheryIsChecked) {
            sedimentFeatheryStringRecord = "Yes";
        } else {
            sedimentFeatheryStringRecord = "Missing";
        }
        final String sedimentFeatherySend = sedimentFeatheryStringRecord.trim();

        boolean bacteriaIsChecked = bacteriaCheckbox.isChecked();
        if (bacteriaIsChecked) {
            bacteriaStringRecord = "Positive";
        } else {
            bacteriaStringRecord = "Missing";
        }
        final String bacteriaResultSend = bacteriaStringRecord.trim();

        final String hardnessPpmSend = editTextHardnessPpm.getText().toString().trim();
        final String chlorinePpmSend = editTextChlorinePpm.getText().toString().trim();
        final String alkalinityPpmSend = editTextAlkalinityPpm.getText().toString().trim();
        final String copperPpmSend = editTextCopperPpm.getText().toString().trim();
        final String ironPpmSend = editTextIronPpm.getText().toString().trim();
        final String phValueSend = editTextPhValue.getText().toString().trim();

        boolean pesticideIsChecked = pesticideCheckbox.isChecked();
        if (pesticideIsChecked) {
            pesticideStringRecord = "Positive";
        } else {
            pesticideStringRecord = "Missing";
        }
        final String pesticideResultSend = pesticideStringRecord.trim();

        boolean leadIsChecked = leadCheckbox.isChecked();
        if (leadIsChecked) {
            leadStringRecord = "Positive";
        } else {
            leadStringRecord = "Missing";
        }
        final String leadResultSend = leadStringRecord.trim();

        final String nitriteResultSend = editTextNitriteResult.getText().toString().trim();
        final String nitrateResultSend = editTextNitrateResult.getText().toString().trim();
        final String latitudeSend = editTextLatitude.getText().toString().trim();
        final String longitudeSend = editTextLongitude.getText().toString().trim();
        final String localitySend = editTextLocality.getText().toString().trim();
        final String zipCodeSend = editTextZipCode.getText().toString().trim();
        final String addressSend = editTextAddress.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://script.google.com/macros/s/AKfycbwYJqShj4JPKEltoi0vLpegVbF-RZU4fYhHcl_3mWNSw65JEnsxHY6s_YW2pY7rc-S4Lg/exec",
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
                paramsMap.put("waterTemp",tempSend);
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
                paramsMap.put("latitude",latitudeSend);
                paramsMap.put("longitude",longitudeSend);
                paramsMap.put("locality",localitySend);
                paramsMap.put("zipCode",zipCodeSend);
                paramsMap.put("address",addressSend);

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