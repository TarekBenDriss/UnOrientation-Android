package bendriss.tarek.unorientation.data.source.local.database;



import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import bendriss.tarek.unorientation.data.source.local.converter.Converters;
import bendriss.tarek.unorientation.data.source.local.dao.UserProfileDAO;
import bendriss.tarek.unorientation.data.source.local.entity.UserProfile;
import bendriss.tarek.unorientation.util.ApplicationUtils;


/**
 * this class configures the room the room database
 */
@Database(entities = {UserProfile.class}, version = AppDatabase.VERSION, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    static final int VERSION = 8;
    static final String NAME = ApplicationUtils.NAME + ".db";

    //public abstract UserPOKDao getUserPOKDao();
    public abstract UserProfileDAO getUserProfileDao();

}