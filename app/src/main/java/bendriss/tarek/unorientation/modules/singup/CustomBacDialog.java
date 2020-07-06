package bendriss.tarek.unorientation.modules.singup;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import bendriss.tarek.unorientation.R;
import bendriss.tarek.unorientation.modules.score.ScoreFragment;


public class CustomBacDialog {

    private Context context;
    private ActionsDelegate delegate;

    public void showDialog(Activity activity,SignupFragment fragment,String sts){


        context = activity;
        //delegate = (ActionsDelegate) context;
        delegate = (ActionsDelegate) fragment;

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
            dialog.setContentView(R.layout.bac_popup);


        int width = (int)(context.getResources().getDisplayMetrics().widthPixels*0.80);
        int height = (int)(context.getResources().getDisplayMetrics().heightPixels*0.50);

        dialog.getWindow().setLayout(width, FrameLayout.LayoutParams.WRAP_CONTENT);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);


        View view = dialog.getWindow().getDecorView();
        view.setBackgroundResource(android.R.color.transparent);


        TextView okay = dialog.findViewById(R.id.ok);
        TextView info = dialog.findViewById(R.id.info);
        TextView eco = dialog.findViewById(R.id.eco);
        TextView sc = dialog.findViewById(R.id.science);
        TextView math = dialog.findViewById(R.id.math);
        TextView lettre = dialog.findViewById(R.id.lettres);
        TextView sport = dialog.findViewById(R.id.sport);
        TextView tech = dialog.findViewById(R.id.tech);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.choisirBac("Informatique");
                dialog.dismiss();
            }
        });
                eco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.choisirBac("Economie");
                dialog.dismiss();
            }
        });
                sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.choisirBac("Sciences");
                dialog.dismiss();
            }
        });
                math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.choisirBac("Maths");
                dialog.dismiss();
            }
        });
                lettre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.choisirBac("Lettres");
                dialog.dismiss();
            }
        });
                sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.choisirBac("Sport");
                dialog.dismiss();
            }
        });
                tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.choisirBac("Techniques");
                dialog.dismiss();
            }
        });


        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

        //double dose=getDoseDoubleFromString(dose1.getText().toString())+getDoseDoubleFromString(dose2.getText().toString());

    }



    public void showDialog(Activity activity,ScoreFragment fragment,String sts){


        context = activity;
        //delegate = (ActionsDelegate) context;
        delegate = (ActionsDelegate) fragment;

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.bac_popup);


        int width = (int)(context.getResources().getDisplayMetrics().widthPixels*0.80);
        int height = (int)(context.getResources().getDisplayMetrics().heightPixels*0.50);

        dialog.getWindow().setLayout(width, FrameLayout.LayoutParams.WRAP_CONTENT);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);


        View view = dialog.getWindow().getDecorView();
        view.setBackgroundResource(android.R.color.transparent);


        TextView okay = dialog.findViewById(R.id.ok);
        TextView info = dialog.findViewById(R.id.info);
        TextView eco = dialog.findViewById(R.id.eco);
        TextView sc = dialog.findViewById(R.id.science);
        TextView math = dialog.findViewById(R.id.math);
        TextView lettre = dialog.findViewById(R.id.lettres);
        TextView sport = dialog.findViewById(R.id.sport);
        TextView tech = dialog.findViewById(R.id.tech);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.choisirBac("Informatique");
                dialog.dismiss();
            }
        });
        eco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.choisirBac("Economie");
                dialog.dismiss();
            }
        });
        sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.choisirBac("Sciences");
                dialog.dismiss();
            }
        });
        math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.choisirBac("Maths");
                dialog.dismiss();
            }
        });
        lettre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.choisirBac("Lettres");
                dialog.dismiss();
            }
        });
        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.choisirBac("Sport");
                dialog.dismiss();
            }
        });
        tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.choisirBac("Techniques");
                dialog.dismiss();
            }
        });


        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

        //double dose=getDoseDoubleFromString(dose1.getText().toString())+getDoseDoubleFromString(dose2.getText().toString());

    }









    public CustomBacDialog() {
    }

    public CustomBacDialog(Activity activity,SignupFragment fragment) {


        context = activity;
        delegate = (ActionsDelegate) fragment;

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.bac_popup);

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


    public CustomBacDialog(Activity activity, ScoreFragment fragment) {


        context = activity;
        delegate = (ActionsDelegate) fragment;

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.bac_popup);

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







