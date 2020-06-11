package bendriss.tarek.unorientation.data.source.repository;

import androidx.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import bendriss.tarek.unorientation.data.source.local.QuizLocalDataSource;
import bendriss.tarek.unorientation.data.source.local.ScrapLocalDataSource;
import bendriss.tarek.unorientation.data.source.remote.QuizRemoteDataSource;
import bendriss.tarek.unorientation.data.source.remote.ScrapRemoteDataSource;
import bendriss.tarek.unorientation.data.source.remote.params.QuizParam;
import bendriss.tarek.unorientation.data.source.remote.params.ScrapParam;
import bendriss.tarek.unorientation.data.source.remote.response.HistoriqueResponse;
import bendriss.tarek.unorientation.data.source.remote.response.QuizResponse;
import bendriss.tarek.unorientation.data.source.remote.response.ScrapResponse;
import io.reactivex.Single;

@Singleton
public class ScrapRepository {

    private final ScrapLocalDataSource scrapLocalDataSource;
    private final ScrapRemoteDataSource scrapRemoteDataSource;

    @Inject
    ScrapRepository(@NonNull ScrapLocalDataSource scrapLocalDataSource, @NonNull ScrapRemoteDataSource scrapRemoteDataSource) {
        this.scrapLocalDataSource = scrapLocalDataSource;
        this.scrapRemoteDataSource = scrapRemoteDataSource;
    }


    public Single<ScrapResponse> getScrap(ScrapParam param) {
        return scrapRemoteDataSource.getScrap(param)
                // oon Succes do in other thread
                .doOnSuccess(response -> {
                    /*
                    if (response != null && response.getToken() != null) {
                        UserProfile user = new UserProfile();

                        user.setToken(response.getToken());
                        //user.setUserKey(response.getUserKey());
                        userLocalDataSource.delete();
                        userLocalDataSource.deleteUserProfile();
                        userLocalDataSource.addConnectedUser(user);
                    }
                     */
                });
    }


}
