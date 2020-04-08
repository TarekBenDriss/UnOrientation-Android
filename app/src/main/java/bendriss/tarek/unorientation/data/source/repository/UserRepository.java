package bendriss.tarek.unorientation.data.source.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import javax.inject.Inject;
import javax.inject.Singleton;

import bendriss.tarek.unorientation.data.source.local.UserLocalDataSource;
import bendriss.tarek.unorientation.data.source.local.entity.UserProfile;
import bendriss.tarek.unorientation.data.source.remote.UserRemoteDataSource;
import bendriss.tarek.unorientation.data.source.remote.params.LoginParams;
import bendriss.tarek.unorientation.data.source.remote.response.LoginResponse;
import bendriss.tarek.unorientation.data.source.remote.response.SignupResponse;
import bendriss.tarek.unorientation.util.Logger;
import io.reactivex.Single;

/**
 * this class represents the user's repository
 */
@Singleton
public class UserRepository {

    private final UserLocalDataSource userLocalDataSource;
    private final UserRemoteDataSource userRemoteDataSource;

    @Inject
    UserRepository(@NonNull UserLocalDataSource userLocalDataSource, @NonNull UserRemoteDataSource userRemoteDataSource) {
        this.userLocalDataSource = userLocalDataSource;
        this.userRemoteDataSource = userRemoteDataSource;
    }

    /**
     * this function allows signing in
     * @param lp
     * @return
     */
    public Single<LoginResponse> login(LoginParams lp) {
        return userRemoteDataSource.signin(lp)
                // oon Succes do in other thread
                .doOnSuccess(response -> {

                    if (response != null && response.getToken() != null) {
                        UserProfile user = new UserProfile();

                        user.setToken(response.getToken());
                        //user.setUserKey(response.getUserKey());
                        userLocalDataSource.delete();
                        userLocalDataSource.deleteUserProfile();
                        userLocalDataSource.addConnectedUser(user);
                    }
                });
    }



    public Single<SignupResponse> signup(UserProfile userProfile) {
        return userRemoteDataSource.signup(userProfile)
                // oon Succes do in other thread
                .doOnSuccess(response -> {

                    Logger.e("SignupResponse",userProfile.toString());
                });
    }



    /**
     * this function returns the user's token
     * @return
     */
    public String getConnectedUserToken() {
        return userLocalDataSource.getConnectedUserToken();
    }

    /**
     * this function deletes the connected user
     */
    public void delete() {
        userLocalDataSource.delete();
        userLocalDataSource.deleteUserProfile();
    }

    /**
     * this function returns the current user's token
     * @return
     */
    public LiveData<String> getConnectedUserToken2() {
        return userLocalDataSource.getConnectedUserToken2();
    }

    /**
     * this function returns the current user's profile•
     * @return
     */
    public LiveData<UserProfile> getConnectedUserProfile() {
        return userLocalDataSource.getConnectedUserProfile();
    }









}
