package bendriss.tarek.unorientation.widgets;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import bendriss.tarek.unorientation.util.EndlessScroll;


/**
 * this class represents a custom recycler view
 */
public class AppCompatRecyclerView extends RecyclerView {

    private RefreshListener refreshListener;

    public AppCompatRecyclerView(Context context) {
        super(context);
    }

    public AppCompatRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AppCompatRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void enableEndlessScroll() {
        addOnScrollListener(new EndlessScroll(((LinearLayoutManager) getLayoutManager())) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (refreshListener != null) {
                    refreshListener.onLoadMore(page, totalItemsCount);
                }
            }
        });
    }

    public interface RefreshListener {

        void onPullToRefresh();

        void onLoadMore(int page, int totalItemsCount);
    }
}
