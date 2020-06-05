package bendriss.tarek.unorientation.modules.quiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;


import java.util.ArrayList;
import java.util.List;

import bendriss.tarek.unorientation.R;
import bendriss.tarek.unorientation.base.BaseAdapter;
import bendriss.tarek.unorientation.base.BaseDiffCallback;
import bendriss.tarek.unorientation.base.BaseViewHolder;
import bendriss.tarek.unorientation.data.source.remote.response.QuizResponse;
import bendriss.tarek.unorientation.databinding.ItemQuizBinding;
import bendriss.tarek.unorientation.util.Logger;


/**
this class is the leaves' adapter used to initiate the recycler view
 */
public class QuizAdapter extends BaseAdapter<String, QuizAdapter.ViewHolder> {

    private List<String> list;
    public QuizAdapter() {
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
    public QuizAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quiz, parent, false);
        return new QuizAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuizAdapter.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        String item = items.get(position);
        Logger.e("LIST",item);

        holder.bind(item);
    }

    @Override
    public void change(List<String> items) {
        list = new ArrayList<>();
        list=items;
        change(new QuizAdapter.NewsDiffCallback(this.items, items));
    }


    public void updateList(List<String> newItems)
    {
        this.items.clear();
        this.items.addAll(newItems);
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends BaseViewHolder {
        private final Context context;
        private final ItemQuizBinding binding;

        public ViewHolder(View view) {
            super(view);
            this.context = view.getContext();
            this.binding = DataBindingUtil.bind(view);
        }

        public void bind(String item) {
            if (binding.getQuiz() != null) {
                //binding.getQuiz().setAnswer(item);
                binding.setQuiz(new QuizItem(context,item,getPosition()+1));
            } else {
                binding.setQuiz(new QuizItem(context,item,getPosition()+1));
            }
            binding.executePendingBindings();
        }
    }

    public class NewsDiffCallback extends BaseDiffCallback<String> {
        public NewsDiffCallback(List<String> oldList, List<String> newList) {
            super(oldList, newList);
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            // add a unique ID property on Movie and expose a getId() method
            return oldList.get(oldItemPosition)==newList.get(newItemPosition);
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            String oldAnswer = oldList.get(oldItemPosition);
            String newAnswer = newList.get(newItemPosition);
            return oldAnswer.equals(newAnswer);
        }
    }

}
