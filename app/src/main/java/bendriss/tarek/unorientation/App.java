package bendriss.tarek.unorientation;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;


import java.util.Locale;

import bendriss.tarek.unorientation.data.source.DaggerDataComponent;
import bendriss.tarek.unorientation.data.source.DataComponent;
import bendriss.tarek.unorientation.data.source.local.database.StorageModule;
import bendriss.tarek.unorientation.data.source.remote.service.NetworkModule;
import bendriss.tarek.unorientation.data.source.remote.service.ServiceEndpoint;
import bendriss.tarek.unorientation.util.LocalHelper;
import bendriss.tarek.unorientation.widgets.LoadingDialog;

public class App extends MultiDexApplication {

    private static DataComponent dataComponent;
    public static Application app;
    private static LoadingDialog loader;
    public static Context context;
    public static String token,agence;
    private static App instance;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }



    public static DataComponent getDataComponent() {
        return dataComponent;
    }

    public static Context getContext(){
        return instance;
    }

    public static App getInstance() {
        return instance;
    }

    public static boolean isDialogShowed() {
        return loader.isAdded();
    }


    public static void hideLoader() {
        LoadingDialog.dismiss(loader);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        loader = LoadingDialog.newInstance();
        initAppComponent();
        app=this;
        instance = this;

        LocalHelper.setLocale(this, Locale.getDefault().getDisplayLanguage());
    }

    public static void showLoader(Context context) {
        LoadingDialog.show(context, loader);
    }


    private void initAppComponent() {

        dataComponent = DaggerDataComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(ServiceEndpoint.BASE_URL))
                .storageModule(new StorageModule())
                .build();


    }

    public static void initAppComponent2(String s) {

        dataComponent = DaggerDataComponent.builder()
                .appModule(new AppModule(app))
                .networkModule(new NetworkModule(s))
                //.networkModule(new NetworkModule(NetworkConstants.BASE_URL))
                .storageModule(new StorageModule())
                .build();


    }



    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
