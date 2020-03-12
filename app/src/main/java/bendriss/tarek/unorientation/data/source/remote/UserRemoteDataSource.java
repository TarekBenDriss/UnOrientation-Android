package bendriss.tarek.unorientation.data.source.remote;


import javax.inject.Inject;
import javax.inject.Singleton;

import bendriss.tarek.unorientation.data.source.remote.params.LoginParams;
import bendriss.tarek.unorientation.data.source.remote.response.LoginResponse;
import bendriss.tarek.unorientation.data.source.remote.service.ServiceEndpoint;
import io.reactivex.Single;

/**
 * this class represents the user's remote datasource
 */
@Singleton
public class UserRemoteDataSource {


    private final ServiceEndpoint serviceEndpoint;

    @Inject
    public UserRemoteDataSource(ServiceEndpoint serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
    }

    /**
     * this function allows singing in
     * @param lp
     * @return
     */
    public Single<LoginResponse> signinPok(LoginParams lp) {
        return serviceEndpoint.login(lp);
    }

}
