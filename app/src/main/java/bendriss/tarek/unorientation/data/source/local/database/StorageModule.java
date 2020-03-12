package bendriss.tarek.unorientation.data.source.local.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * this class configures the module of storage
 */
@Module
public class StorageModule {

    @Provides
    @Singleton
    public SharedPreference provideSharedPreferences(@NonNull Context context, @NonNull ObjectMapper objectMapper) {
        return new SharedPreference(context, objectMapper);
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@NonNull Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, AppDatabase.NAME)
              // hedha e zeyed
                .fallbackToDestructiveMigration()
              // youfa hné
                .build();
    }
}
