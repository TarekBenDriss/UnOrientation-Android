package bendriss.tarek.unorientation.data.source.remote;


import javax.inject.Inject;
import javax.inject.Singleton;

import bendriss.tarek.unorientation.data.source.local.entity.UserProfile;
import bendriss.tarek.unorientation.data.source.remote.params.LoginParams;
import bendriss.tarek.unorientation.data.source.remote.params.ScoreParam;
import bendriss.tarek.unorientation.data.source.remote.response.LoginResponse;
import bendriss.tarek.unorientation.data.source.remote.response.ObjectResponse;
import bendriss.tarek.unorientation.data.source.remote.response.SignupResponse;
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
    public Single<LoginResponse> signin(LoginParams lp) {
        return serviceEndpoint.login(lp.getUsername(),lp.getPassword());
    }


    public Single<ObjectResponse> getScore(ScoreParam lp) {
        return serviceEndpoint.getScore(lp);
    }

    public Single<SignupResponse> signup(UserProfile userProfile) {
        return serviceEndpoint.signup(userProfile);
    }

}
