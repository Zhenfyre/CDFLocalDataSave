package cdfflint.pilot.cdflocaldatasave;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class RecordRepository {
    private RecordDao recordDao;
    private LiveData<List<SQLRecord>> allRecords;

    public RecordRepository(Application application) {
        RecordDatabase database = RecordDatabase.getInstance(application);
        recordDao = database.recordDao();
        allRecords = recordDao.getAllRecords();
    }

    public void insert(SQLRecord record){
        new InsertRecordAsyncTask(recordDao).execute(record);
    }

    public void update(SQLRecord record){
        new UpdateRecordAsyncTask(recordDao).execute(record);
    }

    public void delete(SQLRecord record){
        new DeleteRecordAsyncTask(recordDao).execute(record);
    }

    public void deleteAllRecords(){
        new DeleteAllRecordsAsyncTask(recordDao).execute();
    }

    public LiveData<List<SQLRecord>> getAllRecords() {
        return allRecords;
    }

    private static class InsertRecordAsyncTask extends AsyncTask<SQLRecord, Void, Void> {
        private RecordDao recordDao;

        private InsertRecordAsyncTask(RecordDao recordDao) {
            this.recordDao = recordDao;
        }

        @Override
        protected Void doInBackground(SQLRecord... records) {
            recordDao.insert(records[0]);
            return null;
        }
    }

    private static class UpdateRecordAsyncTask extends AsyncTask<SQLRecord, Void, Void> {
        private RecordDao recordDao;

        private UpdateRecordAsyncTask(RecordDao recordDao) {
            this.recordDao = recordDao;
        }

        @Override
        protected Void doInBackground(SQLRecord... records) {
            recordDao.update(records[0]);
            return null;
        }
    }
    private static class DeleteRecordAsyncTask extends AsyncTask<SQLRecord, Void, Void> {
        private RecordDao recordDao;

        private DeleteRecordAsyncTask(RecordDao recordDao) {
            this.recordDao = recordDao;
        }

        @Override
        protected Void doInBackground(SQLRecord... records) {
            recordDao.delete(records[0]);
            return null;
        }
    }
    private static class DeleteAllRecordsAsyncTask extends AsyncTask<Void, Void, Void> {
        private RecordDao recordDao;

        private DeleteAllRecordsAsyncTask(RecordDao recordDao) {
            this.recordDao = recordDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            recordDao.deleteAllRecords();
            return null;
        }
    }
}
