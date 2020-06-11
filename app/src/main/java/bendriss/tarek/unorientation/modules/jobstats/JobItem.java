package bendriss.tarek.unorientation.modules.jobstats;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;



public class JobItem extends BaseObservable {

    private final Context context;
    private Job job;

    public Context getContext() {
        return context;
    }

    public JobItem(Context context, Job job) {
        this.context = context;
        this.job = job;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public boolean onLongClick(View view) {
        return true;
    }

    public void onClick(View view) {
    }

}
