package bendriss.tarek.unorientation.data.source;



import javax.inject.Singleton;

import bendriss.tarek.unorientation.AppModule;
import bendriss.tarek.unorientation.data.source.local.database.StorageModule;
import bendriss.tarek.unorientation.data.source.remote.service.NetworkModule;
import bendriss.tarek.unorientation.data.source.remote.service.RequestInterceptor;
import bendriss.tarek.unorientation.modules.login.LoginActivity;
import bendriss.tarek.unorientation.modules.login.LoginViewModel;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, StorageModule.class, NetworkModule.class})
public interface DataComponent {

    void inject(LoginViewModel loginViewModel);
    void inject(LoginActivity loginActivity);
    void inject(RequestInterceptor resquestInterceptor);
    /*
    void inject(BaseGlideModule baseGlideModule);
    void inject(LoginActivity loginActivity);
    void inject(NewsViewModel newsViewModel);
    void inject(SalaryViewModel salaryViewModel);
    void inject(LeavesViewModel leavesViewModel);
    void inject(ExpensesViewModel expensesViewModel);
    void inject(MissionsViewModel missionsViewModel);
    void inject(BusinessManagerViewModel businessManagerViewModel);
    void inject(SplashScreenActivity splashScreenActivity);
    void inject(ProfileViewModel profileViewModel);
     */
}
