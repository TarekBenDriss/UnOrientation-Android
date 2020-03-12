package bendriss.tarek.unorientation.data.source.local.dao;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import java.util.List;

import bendriss.tarek.unorientation.data.source.local.entity.UserProfile;


/**
 * this class represents the user profil's dao
 */
@Dao
public interface UserProfileDAO {

    /**
     * this function adds a user profile to the database
     * @param userProfile
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserProfile userProfile);

    /**
     * this function adds a list of userprofile to the database
     * @param userProfiles
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<UserProfile> userProfiles);

    /**
     * this function deletes a userprofile from the database
     */
    @Query("DELETE FROM userprofile")
    void delete();

    /**
     * this function returns all the userprofiles
     * @return
     */
    @Query("SELECT * FROM userprofile")
    LiveData<UserProfile> findAll();



    /**
     * this function returns the connected user's token
     * @return
     */
    @Query("SELECT token FROM userprofile ORDER BY id DESC LIMIT 1")
    String getConnectedUserPOKToken();

    /**
     * this function returns the connected user's token
     * @return
     */
    @Query("SELECT token FROM userprofile ORDER BY id DESC LIMIT 1")
    LiveData<String> getConnectedUserPOKToken2();

}