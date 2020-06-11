package bendriss.tarek.unorientation.modules.jobstats;


import android.app.Application;

import androidx.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import bendriss.tarek.unorientation.App;
import bendriss.tarek.unorientation.base.BaseViewModel;
import bendriss.tarek.unorientation.data.source.remote.params.QuizParam;
import bendriss.tarek.unorientation.data.source.remote.params.ScrapParam;
import bendriss.tarek.unorientation.data.source.remote.response.HistoriqueResponse;
import bendriss.tarek.unorientation.data.source.remote.response.QuizResponse;
import bendriss.tarek.unorientation.data.source.remote.response.ScrapResponse;
import bendriss.tarek.unorientation.data.source.repository.QuizRepository;
import bendriss.tarek.unorientation.data.source.repository.ScrapRepository;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 this class represents the login view model
 */
public class ScrapViewModel extends BaseViewModel {

    @Inject
    ScrapRepository mRepository;

    // constructor
    public ScrapViewModel(@NonNull Application application) {
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





    public Single<ScrapResponse> getScrap(ScrapParam param) {
        return mRepository.getScrap(param)
                //
                .subscribeOn(Schedulers.computation())
                //
                .observeOn(AndroidSchedulers.mainThread());
    }



}
