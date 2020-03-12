package bendriss.tarek.unorientation.modules.login;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import javax.inject.Inject;

import bendriss.tarek.unorientation.App;
import bendriss.tarek.unorientation.base.BaseViewModel;
import bendriss.tarek.unorientation.data.source.remote.params.LoginParams;
import bendriss.tarek.unorientation.data.source.remote.response.LoginResponse;
import bendriss.tarek.unorientation.data.source.repository.UserRepository;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 this class represents the login view model
 */
public class LoginViewModel extends BaseViewModel {

    @Inject
    UserRepository mRepository;

    // constructor
    public LoginViewModel(@NonNull Application application) {
        super(application);
        App.getDataComponent().inject(this);
    }

    /**
     this function allows to do the sign in
     */
    /*
    public Single<LoginPokResponse> signinPok(LoginParams lp) {
        return mRepository.signinPok(lp)
                //
                .subscribeOn(Schedulers.computation())
                //
                .observeOn(AndroidSchedulers.mainThread());
    }
*/
    /**
     this function verify the existance of the agency code using codeAgence as parameter
     */
    /*
    public Single<VerifAgenceResponse> verifCodeAgence(String codeAgence) {
        return mRepository.verifCodeAgence(codeAgence)
                //
                .subscribeOn(Schedulers.computation())
                //
                .observeOn(AndroidSchedulers.mainThread());
    }
*/

    public String getConnectedUserToken() {
        return null;

        //return mRepository.getConnectedUserToken();
    }

    /**
     this function deletes the user in case of logout
     */
    public void delete() {
       // mRepository.delete();
    }

    /**
     this function returns the connected user token
     */
    public LiveData<String> getConnectedUserToken2() {
      return null;
      //  return mRepository.getConnectedUserToken2();
    }

    /**
     this function returns the connected user profile informations
     */
    /*
    public LiveData<UserProfile> getConnectedUserProfile() {
        return mRepository.getConnectedUserProfile();
    }
*/

    public Single<LoginResponse> signinPok(LoginParams lp) {
        return mRepository.login(lp)
                //
                .subscribeOn(Schedulers.computation())
                //
                .observeOn(AndroidSchedulers.mainThread());
    }
}
