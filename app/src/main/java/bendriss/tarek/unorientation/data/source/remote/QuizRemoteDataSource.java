package bendriss.tarek.unorientation.data.source.remote;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import bendriss.tarek.unorientation.data.source.local.entity.UserProfile;
import bendriss.tarek.unorientation.data.source.remote.params.LoginParams;
import bendriss.tarek.unorientation.data.source.remote.params.QuizParam;
import bendriss.tarek.unorientation.data.source.remote.response.HistoriqueResponse;
import bendriss.tarek.unorientation.data.source.remote.response.LoginResponse;
import bendriss.tarek.unorientation.data.source.remote.response.QuizResponse;
import bendriss.tarek.unorientation.data.source.remote.response.SignupResponse;
import bendriss.tarek.unorientation.data.source.remote.service.ServiceEndpoint;
import io.reactivex.Single;

@Singleton
public class QuizRemoteDataSource {


    private final ServiceEndpoint serviceEndpoint;

    @Inject
    public QuizRemoteDataSource(ServiceEndpoint serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
    }


    public Single<QuizResponse> getQuiz(String name) {
        return serviceEndpoint.getQuiz(name);
    }

    public Single<QuizResponse> getQuiz2(QuizParam quizParam) {
        return serviceEndpoint.getQuizV2(quizParam);
    }

    public Single<List<HistoriqueResponse>> getHistorique(int userId) {
        return serviceEndpoint.getHistorique(userId);
    }


}
