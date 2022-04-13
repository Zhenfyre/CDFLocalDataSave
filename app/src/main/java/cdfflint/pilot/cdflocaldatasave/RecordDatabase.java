package cdfflint.pilot.cdflocaldatasave;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {SQLRecord.class}, version = 5, exportSchema = false)
public abstract class RecordDatabase extends RoomDatabase {

    private static RecordDatabase instance;

    public abstract  RecordDao recordDao();

    public static synchronized RecordDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    RecordDatabase.class, "record_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private  RecordDao recordDao;

        private PopulateDbAsyncTask(RecordDatabase db) {
            recordDao = db.recordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            recordDao.insert(new SQLRecord("01/01/2000","12:00pm",
                    0,"0", "Temp", "Use",
                    "Color","Smell","Taste", "Rotten Egg",
                    "Sediment","Feathery", "Bacteria",
                    "Hardness","Chlorine","Alkalinity",
                    "Copper","Iron","pH","Pesticides",
                    "Lead","Nitrite", "Nitrate", "Latitude",
                     "Longitude", "Locality", "ZipCode", "Address"));
            recordDao.insert(new SQLRecord("01/02/2000","12:00pm",
                    0,"0", "Temp", "Use",
                    "Color","Smell","Taste", "Rotten Egg",
                    "Sediment","Feathery", "Bacteria",
                    "Hardness","Chlorine","Alkalinity",
                    "Copper","Iron","pH","Pesticides",
                    "Lead","Nitrite", "Nitrate", "Latitude",
                    "Longitude", "Locality", "ZipCode", "Address"));
            recordDao.insert(new SQLRecord("01/03/2000","12:00pm",
                    0,"0", "Temp", "Use",
                    "Color","Smell","Taste", "Rotten Egg",
                    "Sediment","Feathery", "Bacteria",
                    "Hardness","Chlorine","Alkalinity",
                    "Copper","Iron","pH","Pesticides",
                    "Lead","Nitrite", "Nitrate", "Latitude",
                    "Longitude", "Locality", "ZipCode", "Address"));
            return null;
        }
    }
}
