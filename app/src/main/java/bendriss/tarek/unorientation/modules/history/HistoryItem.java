package bendriss.tarek.unorientation.modules.history;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;


public class HistoryItem extends BaseObservable {

    private final Context context;
    private History history;

    public Context getContext() {
        return context;
    }

    public HistoryItem(Context context, History history) {
        this.context = context;
        this.history = history;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public boolean onLongClick(View view) {
        return true;
    }

    public void onClick(View view) {
    }

}
