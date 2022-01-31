package cdfflint.pilot.cdflocaldatasave;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface RecordDao {

    @Insert
    void insert (SQLRecord record);

    @Update
    void update(SQLRecord record);

    @Delete
    void delete(SQLRecord record);

    @Query("DELETE FROM record_table")
    void deleteAllRecords();

    @Query("SELECT * FROM record_table ORDER BY collectionDate DESC")
    LiveData<List<SQLRecord>> getAllRecords();

}
