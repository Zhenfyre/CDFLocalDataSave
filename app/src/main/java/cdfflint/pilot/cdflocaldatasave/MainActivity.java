package cdfflint.pilot.cdflocaldatasave;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;

    private RecordViewModel recordViewModel;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout = findViewById(R.id.coordinator_layout);
        FloatingActionButton buttonAddRecord = findViewById(R.id.button_add_record);
        buttonAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEditRecordActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final RecordAdapter adapter = new RecordAdapter();
        recyclerView.setAdapter(adapter);

        recordViewModel = new ViewModelProvider(this).get(RecordViewModel.class);
        recordViewModel.getAllRecords().observe(this, new Observer<List<SQLRecord>>() {
            @Override
            public void onChanged(List<SQLRecord> records) {
                adapter.submitList(records);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Snackbar.make(coordinatorLayout, "Confirm Deletion?", Snackbar.LENGTH_LONG).setAction("Delete",
                        new View.OnClickListener(){
                            @Override
                            public void onClick(View view) {
                                recordViewModel.delete(adapter.getRecordAt(viewHolder.getAdapterPosition()));
                                Toast.makeText(MainActivity.this, "Record deleted", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new RecordAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SQLRecord record) {
                Intent intent = new Intent(MainActivity.this, AddEditRecordActivity.class);
                intent.putExtra(AddEditRecordActivity.EXTRA_ID, record.getId());
                intent.putExtra(AddEditRecordActivity.EXTRA_COLLECTION_DATE, record.getCollectionDate());
                intent.putExtra(AddEditRecordActivity.EXTRA_COLLECTION_TIME, record.getCollectionTime());
                intent.putExtra(AddEditRecordActivity.EXTRA_TABLET, record.getTabletNumber());
                intent.putExtra(AddEditRecordActivity.EXTRA_TIME_RUNNING, record.getTimeRunning());
                intent.putExtra(AddEditRecordActivity.EXTRA_WATER_TEMP, record.getWaterTemp());
                intent.putExtra(AddEditRecordActivity.EXTRA_NORMAL_USE, record.getNormalUse());
                intent.putExtra(AddEditRecordActivity.EXTRA_WATER_COLOR, record.getWaterColor());
                intent.putExtra(AddEditRecordActivity.EXTRA_WATER_SMELL, record.getWaterSmell());
                intent.putExtra(AddEditRecordActivity.EXTRA_WATER_TASTE, record.getWaterTaste());
                intent.putExtra(AddEditRecordActivity.EXTRA_ROTTEN_EGG, record.getRottenEgg());
                intent.putExtra(AddEditRecordActivity.EXTRA_SEDIMENT_PRESENT, record.getSedimentPresent());
                intent.putExtra(AddEditRecordActivity.EXTRA_SEDIMENT_FEATHERY, record.getSedimentFeathery());
                intent.putExtra(AddEditRecordActivity.EXTRA_BACTERIA_RESULT, record.getBacteriaResult());
                intent.putExtra(AddEditRecordActivity.EXTRA_HARDNESS_PPM, record.getHardnessPpm());
                intent.putExtra(AddEditRecordActivity.EXTRA_CHLORINE_PPM, record.getChlorinePpm());
                intent.putExtra(AddEditRecordActivity.EXTRA_ALKALINITY_PPM, record.getAlkalinityPpm());
                intent.putExtra(AddEditRecordActivity.EXTRA_COPPER_PPM, record.getCopperPpm());
                intent.putExtra(AddEditRecordActivity.EXTRA_IRON_PPM, record.getIronPpm());
                intent.putExtra(AddEditRecordActivity.EXTRA_PH_VALUE, record.getPhValue());
                intent.putExtra(AddEditRecordActivity.EXTRA_PESTICIDE_RESULT, record.getPesticideResult());
                intent.putExtra(AddEditRecordActivity.EXTRA_LEAD_RESULT, record.getLeadResult());
                intent.putExtra(AddEditRecordActivity.EXTRA_NITRITE_RESULT, record.getNitriteResult());
                intent.putExtra(AddEditRecordActivity.EXTRA_NITRATE_RESULT, record.getNitrateResult());
                intent.putExtra(AddEditRecordActivity.EXTRA_LATITUDE, record.getLatitude());
                intent.putExtra(AddEditRecordActivity.EXTRA_LONGITUDE, record.getLongitude());
                intent.putExtra(AddEditRecordActivity.EXTRA_LOCALITY, record.getLocality());
                intent.putExtra(AddEditRecordActivity.EXTRA_ZIPCODE, record.getZipCode());
                intent.putExtra(AddEditRecordActivity.EXTRA_ADDRESS, record.getAddress());

                startActivityForResult(intent, EDIT_NOTE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String collectionDate = data.getStringExtra(AddEditRecordActivity.EXTRA_COLLECTION_DATE);
            String collectionTime = data.getStringExtra(AddEditRecordActivity.EXTRA_COLLECTION_TIME);
            int tabletNumber = data.getIntExtra(AddEditRecordActivity.EXTRA_TABLET, 0);
            String timeRunning = data.getStringExtra(AddEditRecordActivity.EXTRA_TIME_RUNNING);
            String waterTemp = data.getStringExtra(AddEditRecordActivity.EXTRA_WATER_TEMP);
            String normalUse = data.getStringExtra(AddEditRecordActivity.EXTRA_NORMAL_USE);
            String waterColor = data.getStringExtra(AddEditRecordActivity.EXTRA_WATER_COLOR);
            String waterSmell = data.getStringExtra(AddEditRecordActivity.EXTRA_WATER_SMELL);
            String waterTaste = data.getStringExtra(AddEditRecordActivity.EXTRA_WATER_TASTE);
            String rottenEgg = data.getStringExtra(AddEditRecordActivity.EXTRA_ROTTEN_EGG);
            String sedimentPresent = data.getStringExtra(AddEditRecordActivity.EXTRA_SEDIMENT_PRESENT);
            String sedimentFeathery = data.getStringExtra(AddEditRecordActivity.EXTRA_SEDIMENT_FEATHERY);
            String bacteriaResult = data.getStringExtra(AddEditRecordActivity.EXTRA_BACTERIA_RESULT);
            String hardnessPpm = data.getStringExtra(AddEditRecordActivity.EXTRA_HARDNESS_PPM);
            String chlorinePpm = data.getStringExtra(AddEditRecordActivity.EXTRA_CHLORINE_PPM);
            String alkalinityPpm = data.getStringExtra(AddEditRecordActivity.EXTRA_ALKALINITY_PPM);
            String copperPpm = data.getStringExtra(AddEditRecordActivity.EXTRA_COPPER_PPM);
            String ironPpm = data.getStringExtra(AddEditRecordActivity.EXTRA_IRON_PPM);
            String phValue = data.getStringExtra(AddEditRecordActivity.EXTRA_PH_VALUE);
            String pesticideResult = data.getStringExtra(AddEditRecordActivity.EXTRA_PESTICIDE_RESULT);
            String leadResult = data.getStringExtra(AddEditRecordActivity.EXTRA_LEAD_RESULT);
            String nitriteResult = data.getStringExtra(AddEditRecordActivity.EXTRA_NITRITE_RESULT);
            String nitrateResult = data.getStringExtra(AddEditRecordActivity.EXTRA_NITRATE_RESULT);
            String latitude = data.getStringExtra(AddEditRecordActivity.EXTRA_LATITUDE);
            String longitude = data.getStringExtra(AddEditRecordActivity.EXTRA_LONGITUDE);
            String locality = data.getStringExtra(AddEditRecordActivity.EXTRA_LOCALITY);
            String zipCode = data.getStringExtra(AddEditRecordActivity.EXTRA_ZIPCODE);
            String address = data.getStringExtra(AddEditRecordActivity.EXTRA_ADDRESS);

            SQLRecord record = new SQLRecord(collectionDate, collectionTime, tabletNumber,
                    timeRunning, waterTemp, normalUse, waterColor, waterSmell, waterTaste,
                    rottenEgg, sedimentPresent, sedimentFeathery, bacteriaResult, hardnessPpm,
                    chlorinePpm, alkalinityPpm, copperPpm, ironPpm, phValue, pesticideResult,
                    leadResult, nitriteResult, nitrateResult, latitude, longitude, locality,
                    zipCode, address);
            recordViewModel.insert(record);

            Toast.makeText(this, "Record saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditRecordActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(this, "Record cannot be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String collectionDate = data.getStringExtra(AddEditRecordActivity.EXTRA_COLLECTION_DATE);
            String collectionTime = data.getStringExtra(AddEditRecordActivity.EXTRA_COLLECTION_TIME);
            int tabletNumber = data.getIntExtra(AddEditRecordActivity.EXTRA_TABLET, 0);
            String timeRunning = data.getStringExtra(AddEditRecordActivity.EXTRA_TIME_RUNNING);
            String waterTemp = data.getStringExtra(AddEditRecordActivity.EXTRA_WATER_TEMP);
            String normalUse = data.getStringExtra(AddEditRecordActivity.EXTRA_NORMAL_USE);
            String waterColor = data.getStringExtra(AddEditRecordActivity.EXTRA_WATER_COLOR);
            String waterSmell = data.getStringExtra(AddEditRecordActivity.EXTRA_WATER_SMELL);
            String waterTaste = data.getStringExtra(AddEditRecordActivity.EXTRA_WATER_TASTE);
            String rottenEgg = data.getStringExtra(AddEditRecordActivity.EXTRA_ROTTEN_EGG);
            String sedimentPresent = data.getStringExtra(AddEditRecordActivity.EXTRA_SEDIMENT_PRESENT);
            String sedimentFeathery = data.getStringExtra(AddEditRecordActivity.EXTRA_SEDIMENT_FEATHERY);
            String bacteriaResult = data.getStringExtra(AddEditRecordActivity.EXTRA_BACTERIA_RESULT);
            String hardnessPpm = data.getStringExtra(AddEditRecordActivity.EXTRA_HARDNESS_PPM);
            String chlorinePpm = data.getStringExtra(AddEditRecordActivity.EXTRA_CHLORINE_PPM);
            String alkalinityPpm = data.getStringExtra(AddEditRecordActivity.EXTRA_ALKALINITY_PPM);
            String copperPpm = data.getStringExtra(AddEditRecordActivity.EXTRA_COPPER_PPM);
            String ironPpm = data.getStringExtra(AddEditRecordActivity.EXTRA_IRON_PPM);
            String phValue = data.getStringExtra(AddEditRecordActivity.EXTRA_PH_VALUE);
            String pesticideResult = data.getStringExtra(AddEditRecordActivity.EXTRA_PESTICIDE_RESULT);
            String leadResult = data.getStringExtra(AddEditRecordActivity.EXTRA_LEAD_RESULT);
            String nitriteResult = data.getStringExtra(AddEditRecordActivity.EXTRA_NITRITE_RESULT);
            String nitrateResult = data.getStringExtra(AddEditRecordActivity.EXTRA_NITRATE_RESULT);
            String latitude = data.getStringExtra(AddEditRecordActivity.EXTRA_LATITUDE);
            String longitude = data.getStringExtra(AddEditRecordActivity.EXTRA_LONGITUDE);
            String locality = data.getStringExtra(AddEditRecordActivity.EXTRA_LOCALITY);
            String zipCode = data.getStringExtra(AddEditRecordActivity.EXTRA_ZIPCODE);
            String address = data.getStringExtra(AddEditRecordActivity.EXTRA_ADDRESS);

            SQLRecord record = new SQLRecord(collectionDate, collectionTime, tabletNumber,
                    timeRunning, waterTemp, normalUse, waterColor, waterSmell, waterTaste,
                    rottenEgg, sedimentPresent, sedimentFeathery, bacteriaResult, hardnessPpm,
                    chlorinePpm, alkalinityPpm, copperPpm, ironPpm, phValue, pesticideResult,
                    leadResult, nitriteResult, nitrateResult, latitude, longitude, locality,
                    zipCode, address);
            record.setId(id);
            recordViewModel.update(record);

            Toast.makeText(this, "Record updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Record closed without saving", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_records:
                recordViewModel.deleteAllRecords();
                Toast.makeText(this, "All records deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}