package bendriss.tarek.unorientation.modules.jobstats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import java.util.List;

import bendriss.tarek.unorientation.R;
import bendriss.tarek.unorientation.base.BaseAdapter;
import bendriss.tarek.unorientation.base.BaseDiffCallback;
import bendriss.tarek.unorientation.base.BaseViewHolder;
import bendriss.tarek.unorientation.databinding.ItemHistoryBinding;
import bendriss.tarek.unorientation.databinding.ItemJobBinding;
import bendriss.tarek.unorientation.modules.history.History;
import bendriss.tarek.unorientation.modules.history.HistoryItem;


public class JobsAdapter extends BaseAdapter<Job, JobsAdapter.ViewHolder> {

    public JobsAdapter() {
        super();
    }

    /**
     *
     * First method to avoid items repeat
     *
     * */

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     *
     * Second method to avoid items repeat
     *
     * */

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public JobsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job, parent, false);
        return new JobsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JobsAdapter.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        Job item = items.get(position);
        holder.bind(item);
    }

    @Override
    public void change(List<Job> items) {
        change(new JobsAdapter.NewsDiffCallback(this.items, items));
    }

    public class ViewHolder extends BaseViewHolder {
        private final Context context;
        private final ItemJobBinding binding;

        public ViewHolder(View view) {
            super(view);
            this.context = view.getContext();
            this.binding = DataBindingUtil.bind(view);
        }

        public void bind(Job item) {
            if (binding.getJob() != null) {
                binding.getJob().setJob(item);
            } else {
                binding.setJob(new JobItem(context,item));
            }
            binding.executePendingBindings();
        }
    }

    public class NewsDiffCallback extends BaseDiffCallback<Job> {
        public NewsDiffCallback(List<Job> oldList, List<Job> newList) {
            super(oldList, newList);
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            // add a unique ID property on Movie and expose a getId() method
            Job oldJob = oldList.get(oldItemPosition);
            Job newJob = newList.get(newItemPosition);
            String s1 = oldJob.getCompany()+oldJob.getLocation()+oldJob.getName();
            String s2 = newJob.getCompany()+newJob.getLocation()+newJob.getName();
            return s1.equals(s2);
            //return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            Job oldJob = oldList.get(oldItemPosition);
            Job newJob = newList.get(newItemPosition);
            String s1 = oldJob.getCompany()+oldJob.getLocation()+oldJob.getName();
            String s2 = newJob.getCompany()+newJob.getLocation()+newJob.getName();
            return s1.equals(s2);
        }
    }

}
