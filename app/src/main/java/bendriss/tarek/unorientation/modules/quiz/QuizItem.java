package bendriss.tarek.unorientation.modules.quiz;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;


import org.greenrobot.eventbus.EventBus;

import bendriss.tarek.unorientation.data.source.remote.response.QuizResponse;
import bendriss.tarek.unorientation.util.ItemClickEvent;

/**
this class represents the leave as an item to the recycler view
 */
public class QuizItem extends BaseObservable {

    private final Context context;
    private QuizResponse quiz;
    private String answer;
    private int position;

    public Context getContext() {
        return context;
    }

    public QuizItem(Context context, QuizResponse quiz,int position) {
        this.context = context;
        this.quiz = quiz;
        this.position = position;
    }

    public QuizItem(Context context, String answer) {
        this.context = context;
        this.answer = answer;
    }


    public QuizItem(Context context, String answer,int position) {
        this.context = context;
        this.answer = answer;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public QuizResponse getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizResponse quiz) {
        this.quiz = quiz;
    }

    public boolean onLongClick(View view) {
        return true;
    }

    public void onClick(View view) {
        ItemClickEvent item = new ItemClickEvent();
        item.setDate(position+"");
        item.setHeure(answer);
        item.setQuiz(quiz);
        EventBus.getDefault().post(item);
    }

}
