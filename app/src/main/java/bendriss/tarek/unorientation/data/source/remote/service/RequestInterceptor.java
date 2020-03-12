package bendriss.tarek.unorientation.data.source.remote.service;




import androidx.annotation.NonNull;

import java.io.IOException;

import javax.inject.Inject;

import bendriss.tarek.unorientation.App;
import bendriss.tarek.unorientation.data.source.local.UserLocalDataSource;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * this class represents and configures the request interceptor's module
 */
public class RequestInterceptor implements Interceptor {

    /*Params*/
    private static final String PAGE = "page";
    private static final String API_KEY = "api_key";
    private static final String LANGUAGE = "language";
    /* Headers */
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String AUTHORIZATION = "Authorization";
    private static final String ACCEPT = "accept";
    private static final String ACCEPT_ENCODING = "accept-encoding";
    private static final String ACCEPT_LANGUAGE = "accept-language";
    private static final String USER_AGENT = "user-agent";
    /* Values */
    private static final String CONTENT_JSON = "application/json";
    private static final String ENCODING_GZIP = "gzip, deflate";
    private static final String LANGUAGE_EN = "en-US,en;q=0.8";
    private static final String AGENT_THE_MOVIE_DB = "TheMovieDB";
    private static final String LANGUAGE_FR = "fr-FR";
    private static final String KEY = "dcc77f08dc635793aec422e0436bbdca";

    @Inject
    UserLocalDataSource userLocalDataSource;


    RequestInterceptor() {
        App.getDataComponent().inject(this);
    }


    public static Request provideRequest(@NonNull Request original, @NonNull Headers headers, @NonNull HttpUrl httpUrl) {
        Request.Builder requestBuilder = original.newBuilder()
                .headers(headers)
                //.url(httpUrl)
                .method(original.method(), original.body());
        return requestBuilder.build();
    }

    public static Headers provideHeaders(String token) {
        Headers.Builder headersBuilder = new Headers.Builder();
        headersBuilder.add(AUTHORIZATION, token);
        headersBuilder.add(ACCEPT, CONTENT_JSON);
        headersBuilder.add(CONTENT_TYPE, CONTENT_JSON);
        //headersBuilder.add(ACCEPT_ENCODING, ENCODING_GZIP);
        headersBuilder.add(ACCEPT_LANGUAGE, LANGUAGE_FR);
        headersBuilder.add(USER_AGENT, AGENT_THE_MOVIE_DB);
        return headersBuilder.build();
    }



    public static HttpUrl provideHttpUrl(@NonNull Request original) {
        HttpUrl.Builder httpUrlBuilder = original.url().newBuilder()
                .addQueryParameter(API_KEY, KEY)
                .addQueryParameter(LANGUAGE, LANGUAGE_FR);
        return httpUrlBuilder.build();
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        String token = "";
        if(userLocalDataSource.getConnectedUserToken() != null)
        {
            token = "Bearer " + userLocalDataSource.getConnectedUserToken();
            //token = "Bearer " + App.token;
        }
            //token = "Bearer " + userLocalDataSource.getConnectedUserToken();
        else
        {
            //token = "Bearer " + App.token;
            //Logger.i("token1"," mouch mriguel");
        }



        //Logger.i("token1",token);


        final Request request = chain.request();
        final Headers headers = provideHeaders(token);
        final HttpUrl httpUrl = provideHttpUrl(request);
        final Request newRequest = provideRequest(request, headers, httpUrl);
        return chain.proceed(newRequest);
    }
}
