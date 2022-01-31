package cdfflint.pilot.cdflocaldatasave;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class RecordViewModel extends AndroidViewModel {
    private RecordRepository repository;
    private LiveData<List<SQLRecord>> allRecords;

    public RecordViewModel(@NonNull Application application) {
        super(application);
        repository = new RecordRepository(application);
        allRecords = repository.getAllRecords();
    }

    public void insert(SQLRecord record){
        repository.insert(record);
    }

    public void update(SQLRecord record){
        repository.update(record);
    }

    public void delete(SQLRecord record) {
        repository.delete(record);
    }

    public void deleteAllRecords(){
        repository.deleteAllRecords();
    }

    public LiveData<List<SQLRecord>> getAllRecords() {
        return allRecords;
    }
}
