package bendriss.tarek.unorientation.modules.quiz;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import javax.inject.Inject;

import bendriss.tarek.unorientation.App;
import bendriss.tarek.unorientation.base.BaseViewModel;
import bendriss.tarek.unorientation.data.source.local.entity.UserProfile;
import bendriss.tarek.unorientation.data.source.remote.params.LoginParams;
import bendriss.tarek.unorientation.data.source.remote.params.QuizParam;
import bendriss.tarek.unorientation.data.source.remote.response.LoginResponse;
import bendriss.tarek.unorientation.data.source.remote.response.QuizResponse;
import bendriss.tarek.unorientation.data.source.remote.response.SignupResponse;
import bendriss.tarek.unorientation.data.source.repository.QuizRepository;
import bendriss.tarek.unorientation.data.source.repository.UserRepository;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 this class represents the login view model
 */
public class QuizViewModel extends BaseViewModel {

    @Inject
    QuizRepository mRepository;

    // constructor
    public QuizViewModel(@NonNull Application application) {
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





    public Single<QuizResponse> getQuiz(String name) {
        return mRepository.getQuiz(name)
                //
                .subscribeOn(Schedulers.computation())
                //
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<QuizResponse> getQuizV2(QuizParam quizParam) {
        return mRepository.getQuizV2(quizParam)
                //
                .subscribeOn(Schedulers.computation())
                //
                .observeOn(AndroidSchedulers.mainThread());
    }


}
