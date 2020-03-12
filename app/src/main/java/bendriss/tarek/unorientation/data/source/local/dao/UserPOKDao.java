package bendriss.tarek.unorientation.data.source.local.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import java.util.List;

import bendriss.tarek.unorientation.data.source.local.entity.UserProfile;

/**
 * this interface represents the userpok's dao
 */
@Dao
public interface UserPOKDao {

    /**
     * this function adds a user to the database
     * @param userPOK
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserProfile userPOK);

    /**
     * this function adds a list of users to the database
     * @param userProfiles
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<UserProfile> userProfiles);

    /**
     * this function deletes a user from the database
     */
    @Query("DELETE FROM userProfile")
    void delete();

    /**
     * this function returns all the users from the database
     * @return
     */
    @Query("SELECT * FROM userProfile")
    LiveData<List<UserProfile>> findAll();

    /**
     * this function returns the connected user's token
     * @return
     */
    /*
    @Query("SELECT token FROM userPOK ORDER BY id DESC LIMIT 1")
    String getConnectedUserPOKToken();
    */
    /**
     * this function returns the connected user's token
     * @return
     */
    /*
    @Query("SELECT token FROM userPOK ORDER BY id DESC LIMIT 1")
    LiveData<String> getConnectedUserPOKToken2();
    */
}