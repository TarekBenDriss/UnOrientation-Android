package bendriss.tarek.unorientation.data.source.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import bendriss.tarek.unorientation.data.source.local.QuizLocalDataSource;
import bendriss.tarek.unorientation.data.source.local.UserLocalDataSource;
import bendriss.tarek.unorientation.data.source.local.entity.UserProfile;
import bendriss.tarek.unorientation.data.source.remote.QuizRemoteDataSource;
import bendriss.tarek.unorientation.data.source.remote.UserRemoteDataSource;
import bendriss.tarek.unorientation.data.source.remote.params.LoginParams;
import bendriss.tarek.unorientation.data.source.remote.params.QuizParam;
import bendriss.tarek.unorientation.data.source.remote.response.HistoriqueResponse;
import bendriss.tarek.unorientation.data.source.remote.response.LoginResponse;
import bendriss.tarek.unorientation.data.source.remote.response.QuizResponse;
import bendriss.tarek.unorientation.util.Logger;
import io.reactivex.Single;

@Singleton
public class QuizRepository {

    private final QuizLocalDataSource quizLocalDataSource;
    private final QuizRemoteDataSource quizRemoteDataSource;

    @Inject
    QuizRepository(@NonNull QuizLocalDataSource quizLocalDataSource, @NonNull QuizRemoteDataSource quizRemoteDataSource) {
        this.quizLocalDataSource = quizLocalDataSource;
        this.quizRemoteDataSource = quizRemoteDataSource;
    }


    public Single<QuizResponse> getQuiz(String name) {
        return quizRemoteDataSource.getQuiz(name)
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

    public Single<QuizResponse> getQuizV2(QuizParam quizParam) {
        return quizRemoteDataSource.getQuiz2(quizParam)
                // oon Succes do in other thread
                .doOnSuccess(response -> {
                   // Logger.e("QUIZRESPONSE",response.toString());
                });
    }

    public Single<List<HistoriqueResponse>> getHistorique(int userId) {
        return quizRemoteDataSource.getHistorique(userId)
                // oon Succes do in other thread
                .doOnSuccess(response -> {
                   // Logger.e("QUIZRESPONSE",response.toString());
                });
    }

}
