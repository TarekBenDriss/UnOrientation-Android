package bendriss.tarek.unorientation.modules.history;

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


public class HistoryAdapter extends BaseAdapter<History, HistoryAdapter.ViewHolder> {

    public HistoryAdapter() {
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
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new HistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        History item = items.get(position);
        holder.bind(item);
    }

    @Override
    public void change(List<History> items) {
        change(new HistoryAdapter.NewsDiffCallback(this.items, items));
    }

    public class ViewHolder extends BaseViewHolder {
        private final Context context;
        private final ItemHistoryBinding binding;

        public ViewHolder(View view) {
            super(view);
            this.context = view.getContext();
            this.binding = DataBindingUtil.bind(view);
        }

        public void bind(History item) {
            if (binding.getHistory() != null) {
                binding.getHistory().setHistory(item);
            } else {
                binding.setHistory(new HistoryItem(context,item));
            }
            binding.executePendingBindings();
        }
    }

    public class NewsDiffCallback extends BaseDiffCallback<History> {
        public NewsDiffCallback(List<History> oldList, List<History> newList) {
            super(oldList, newList);
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            // add a unique ID property on Movie and expose a getId() method
            return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            History oldNews = oldList.get(oldItemPosition);
            History newNews = newList.get(newItemPosition);
            return oldNews.getId() == newNews.getId();
        }
    }

}
