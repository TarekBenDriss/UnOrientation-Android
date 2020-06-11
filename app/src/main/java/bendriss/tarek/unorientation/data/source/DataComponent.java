package bendriss.tarek.unorientation.data.source;



import javax.inject.Singleton;

import bendriss.tarek.unorientation.AppModule;
import bendriss.tarek.unorientation.data.source.local.database.StorageModule;
import bendriss.tarek.unorientation.data.source.remote.service.NetworkModule;
import bendriss.tarek.unorientation.data.source.remote.service.RequestInterceptor;
import bendriss.tarek.unorientation.modules.jobstats.ScrapViewModel;
import bendriss.tarek.unorientation.modules.login.LoginFragment;
import bendriss.tarek.unorientation.modules.login.LoginViewModel;
import bendriss.tarek.unorientation.modules.quiz.QuizFragment;
import bendriss.tarek.unorientation.modules.quiz.QuizViewModel;
import bendriss.tarek.unorientation.modules.singup.SignupFragment;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, StorageModule.class, NetworkModule.class})
public interface DataComponent {

    void inject(LoginViewModel loginViewModel);
    void inject(RequestInterceptor resquestInterceptor);
    void inject(LoginFragment loginFragment);
    void inject(SignupFragment signupFragment);
    void inject(QuizFragment quizFragment);
    void inject(QuizViewModel quizViewModel);
    void inject(ScrapViewModel scrapViewModel);
}
