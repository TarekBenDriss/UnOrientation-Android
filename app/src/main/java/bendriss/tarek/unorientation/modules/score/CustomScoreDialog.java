package bendriss.tarek.unorientation.modules.score;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import bendriss.tarek.unorientation.R;
import bendriss.tarek.unorientation.modules.singup.ActionsDelegate;
import bendriss.tarek.unorientation.modules.singup.SignupFragment;


public class CustomScoreDialog {

    private Context context;
    private ActionsDelegate delegate;




    public void showDialog(Activity activity,ScoreFragment fragment,String sts){


        context = activity;
        //delegate = (ActionsDelegate) context;
        delegate = (ActionsDelegate) fragment;

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.score_popup);


        int width = (int)(context.getResources().getDisplayMetrics().widthPixels*0.80);
        int height = (int)(context.getResources().getDisplayMetrics().heightPixels*0.50);

        dialog.getWindow().setLayout(width, FrameLayout.LayoutParams.WRAP_CONTENT);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);


        View view = dialog.getWindow().getDecorView();
        view.setBackgroundResource(android.R.color.transparent);


        TextView okay = dialog.findViewById(R.id.ok);
        TextView score = dialog.findViewById(R.id.score);


        score.setText(sts);


        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

        //double dose=getDoseDoubleFromString(dose1.getText().toString())+getDoseDoubleFromString(dose2.getText().toString());

    }









    public CustomScoreDialog() {
    }


    public CustomScoreDialog(Activity activity, ScoreFragment fragment) {


        context = activity;
        delegate = (ActionsDelegate) fragment;

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.score_popup);

        int width = (int)(context.getResources().getDisplayMetrics().widthPixels*0.80);
        int height = (int)(context.getResources().getDisplayMetrics().heightPixels*0.50);

        dialog.getWindow().setLayout(width, FrameLayout.LayoutParams.WRAP_CONTENT);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);


        View view = dialog.getWindow().getDecorView();
        view.setBackgroundResource(android.R.color.transparent);
        TextView okay = dialog.findViewById(R.id.ok);
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }


}







