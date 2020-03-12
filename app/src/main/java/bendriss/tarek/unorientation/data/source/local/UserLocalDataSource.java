package bendriss.tarek.unorientation.data.source.local;



import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;


import javax.inject.Inject;
import javax.inject.Singleton;

import bendriss.tarek.unorientation.data.source.local.database.AppDatabase;
import bendriss.tarek.unorientation.data.source.local.entity.UserProfile;

/**
 * this class represents the user's local datasource
 */
@Singleton
public class UserLocalDataSource {

    private final AppDatabase database;

    @Inject
    public UserLocalDataSource(@NonNull AppDatabase database) {
        this.database = database;
    }

    /**
     * this function adds the connected user to the database
     * @param user
     */
    public void addConnectedUser(UserProfile user) {
        database.getUserProfileDao().insert(user);
    }

    /**
     * this function deletes the connected user from the database
     */
    public void delete() {
        database.getUserProfileDao().delete();
    }

    /**
     * this function returns the connected user's token
     * @return
     */
    public String getConnectedUserToken() {
        return database.getUserProfileDao().getConnectedUserPOKToken();
    }

    /**
     * this function returns the connected user's token
     * @return
     */
    public LiveData<String> getConnectedUserToken2() {
        return database.getUserProfileDao().getConnectedUserPOKToken2();
    }

    /**
     * this function adds the connected user's profile to the database
     * @param user
     */
    public void addConnectedUserProfile(UserProfile user) {
        database.getUserProfileDao().insert(user);
    }

    /**
     * this function deletes the user profile
     */
    public void deleteUserProfile() {
        database.getUserProfileDao().delete();
    }

    /**
     * this function returns the connected user profile
     * @return
     */
    public LiveData<UserProfile> getConnectedUserProfile() {
        return database.getUserProfileDao().findAll();
    }


}
