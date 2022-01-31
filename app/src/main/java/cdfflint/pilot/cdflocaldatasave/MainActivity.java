package cdfflint.pilot.cdflocaldatasave;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;

    private RecordViewModel recordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    recordViewModel.delete(adapter.getRecordAt(viewHolder.getAdapterPosition()));
                    Toast.makeText(MainActivity.this, "Record deleted", Toast.LENGTH_SHORT).show();
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
                startActivityForResult(intent, EDIT_NOTE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK){
            String collectionDate = data.getStringExtra(AddEditRecordActivity.EXTRA_COLLECTION_DATE);
            String collectionTime = data.getStringExtra(AddEditRecordActivity.EXTRA_COLLECTION_TIME);
            int tabletNumber = data.getIntExtra(AddEditRecordActivity.EXTRA_TABLET, 0);
            String timeRunning = data.getStringExtra(AddEditRecordActivity.EXTRA_TIME_RUNNING);
            String waterTemp = data.getStringExtra(AddEditRecordActivity.EXTRA_WATER_TEMP);

            SQLRecord record = new SQLRecord(collectionDate, collectionTime, tabletNumber,
                timeRunning, waterTemp);
            recordViewModel.insert(record);

            Toast.makeText(this, "Record saved", Toast.LENGTH_SHORT).show();
          }

        else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK){
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

            SQLRecord record = new SQLRecord(collectionDate, collectionTime, tabletNumber, timeRunning, waterTemp);
            record.setId(id);
            recordViewModel.update(record);

            Toast.makeText(this, "Record updated", Toast.LENGTH_SHORT).show();
          }

        else {
            Toast.makeText(this, "Record not saved", Toast.LENGTH_SHORT).show();
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
        switch (item.getItemId()){
            case R.id.delete_all_records:
                recordViewModel.deleteAllRecords();
                Toast.makeText(this, "All records deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}