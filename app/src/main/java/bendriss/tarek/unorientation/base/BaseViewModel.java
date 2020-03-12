package bendriss.tarek.unorientation.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import io.reactivex.disposables.CompositeDisposable;

/**
 * this class represents a custom view model
 */
public class BaseViewModel extends AndroidViewModel {

    protected final CompositeDisposable mDisposable;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        mDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.clear();
        }
    }
}

