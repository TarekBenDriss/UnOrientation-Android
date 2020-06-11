package bendriss.tarek.unorientation.data.source.remote;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import bendriss.tarek.unorientation.data.source.remote.params.QuizParam;
import bendriss.tarek.unorientation.data.source.remote.params.ScrapParam;
import bendriss.tarek.unorientation.data.source.remote.response.HistoriqueResponse;
import bendriss.tarek.unorientation.data.source.remote.response.QuizResponse;
import bendriss.tarek.unorientation.data.source.remote.response.ScrapResponse;
import bendriss.tarek.unorientation.data.source.remote.service.ServiceEndpoint;
import io.reactivex.Single;

@Singleton
public class ScrapRemoteDataSource {


    private final ServiceEndpoint serviceEndpoint;

    @Inject
    public ScrapRemoteDataSource(ServiceEndpoint serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
    }


    public Single<ScrapResponse> getScrap(ScrapParam param) {
        return serviceEndpoint.getScrap(param);
    }




}
