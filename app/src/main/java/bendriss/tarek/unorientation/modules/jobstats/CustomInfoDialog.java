package bendriss.tarek.unorientation.modules.jobstats;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import bendriss.tarek.unorientation.R;



public class CustomInfoDialog {

    private Context context;
    private Button dose1,dose2,dose12,dose22,dose13,dose23;
    private LayoutInflater mInflater;
    private PopupWindow mDropdown = null;
    private TextView monInrTxt,doseTxt,doseTxt2,doseTxt3,addInrTxt,va,ab;

    public void showDialog(Activity activity,String sts){
        /*
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
*/


        context = activity;

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        if(sts.equals("tn"))
            dialog.setContentView(R.layout.info_tn);
        else
            dialog.setContentView(R.layout.info_wrld);

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
/*
        ImageView dropImg = dialog.findViewById(R.id.dropImg);
        TextView inrValue = dialog.findViewById(R.id.inrValue);
        LinearLayout valider = dialog.findViewById(R.id.btnOk);
        LinearLayout annuler = dialog.findViewById(R.id.btn_dialog);
        dose1 = dialog.findViewById(R.id.dose1);
        dose2 = dialog.findViewById(R.id.dose2);
        dose12 = dialog.findViewById(R.id.dose12);
        dose22 = dialog.findViewById(R.id.dose22);
        dose13 = dialog.findViewById(R.id.dose13);
        dose23 = dialog.findViewById(R.id.dose23);
        monInrTxt = dialog.findViewById(R.id.monInrTxt);
        doseTxt = dialog.findViewById(R.id.doseTxt);
        doseTxt2 = dialog.findViewById(R.id.doseTxt2);
        doseTxt3 = dialog.findViewById(R.id.doseTxt3);
        addInrTxt = dialog.findViewById(R.id.addInrTxt);
        va = dialog.findViewById(R.id.va);
        ab = dialog.findViewById(R.id.ab);
        inrValue.setText(s+"");


        double s1=Constants.DOSE1STATIC;
        double s2=Constants.DOSE2STATIC;
        double s3=Constants.DOSE3STATIC;

        if(s1<=1)
        {
            dose1.setText(getStringDoseFromDouble(s1));
            dose2.setText(getStringDoseFromDouble(0));
        }
        else
        {
            dose1.setText(getStringDoseFromDouble(1));
            dose2.setText(getStringDoseFromDouble(s1-1));
        }

        if(s2<=1)
        {
            dose12.setText(getStringDoseFromDouble(s2));
            dose22.setText(getStringDoseFromDouble(0));
        }
        else
        {
            dose12.setText(getStringDoseFromDouble(1));
            dose22.setText(getStringDoseFromDouble(s2-1));
        }

        if(s3<=1)
        {
            dose13.setText(getStringDoseFromDouble(s3));
            dose23.setText(getStringDoseFromDouble(0));
        }
        else
        {
            dose13.setText(getStringDoseFromDouble(1));
            dose23.setText(getStringDoseFromDouble(s3-1));
        }



        if(s<1.8)
        {
            dropImg.setImageResource(R.drawable.ic_drop_orange);

        }
        else if(1.8<=s && s<=3)
        {
            dropImg.setImageResource(R.drawable.ic_drop_green);
        }
        else if(s>3 && s<=5)
        {
            dropImg.setImageResource(R.drawable.ic_drop_orange);

        }
        else if(s>5)
        {
            dropImg.setImageResource(R.drawable.ic_red_drop);
        }



        valider.setOnClickListener(v ->
        {
            double valeurDose = getDoseDoubleFromString(dose1.getText().toString())+getDoseDoubleFromString(dose2.getText().toString());
            double valeurDose2 = getDoseDoubleFromString(dose12.getText().toString())+getDoseDoubleFromString(dose22.getText().toString());
            double valeurDose3 = getDoseDoubleFromString(dose13.getText().toString())+getDoseDoubleFromString(dose23.getText().toString());
            EventBus.getDefault().post(new ItemMenuClickEvent(Constants.ADD_DOSE,valeurDose+"",valeurDose2+"",valeurDose3+""));
            dialog.dismiss();
        });
        annuler.setOnClickListener(v -> {


            dialog.dismiss();

        });



        dose1.setOnClickListener(views -> initiatePopupWindowDose1(1));
        dose2.setOnClickListener(views -> initiatePopupWindowDose2(1));

        dose12.setOnClickListener(views -> initiatePopupWindowDose1(2));
        dose22.setOnClickListener(views -> initiatePopupWindowDose2(2));

        dose13.setOnClickListener(views -> initiatePopupWindowDose1(3));
        dose23.setOnClickListener(views -> initiatePopupWindowDose2(3));
*/


/*
        if(MedecinDashboardActivity.language.equals(Constants.ARABE))
        {
            dose1.setTypeface(Typeface.createFromAsset(context.getAssets(), "cairo_regular.ttf"));
            dose2.setTypeface(Typeface.createFromAsset(context.getAssets(), "cairo_regular.ttf"));
            dose12.setTypeface(Typeface.createFromAsset(context.getAssets(), "cairo_regular.ttf"));
            dose22.setTypeface(Typeface.createFromAsset(context.getAssets(), "cairo_regular.ttf"));
            dose13.setTypeface(Typeface.createFromAsset(context.getAssets(), "cairo_regular.ttf"));
            dose23.setTypeface(Typeface.createFromAsset(context.getAssets(), "cairo_regular.ttf"));
            addInrTxt.setTypeface(Typeface.createFromAsset(context.getAssets(), "cairo_regular.ttf"));
            va.setTypeface(Typeface.createFromAsset(context.getAssets(), "cairo_regular.ttf"));
            monInrTxt.setTypeface(Typeface.createFromAsset(context.getAssets(), "cairo_regular.ttf"));
            doseTxt.setTypeface(Typeface.createFromAsset(context.getAssets(), "cairo_regular.ttf"));
            doseTxt2.setTypeface(Typeface.createFromAsset(context.getAssets(), "cairo_regular.ttf"));
            doseTxt3.setTypeface(Typeface.createFromAsset(context.getAssets(), "cairo_regular.ttf"));
            ab.setTypeface(Typeface.createFromAsset(context.getAssets(), "cairo_regular.ttf"));
            /*
            monInrTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
            doseTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
            doseTxt2.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
            doseTxt3.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
           */
/*
        }
        else
        {
            dose1.setTypeface(Typeface.createFromAsset(context.getAssets(), "roboto_regular.otf"));
            dose2.setTypeface(Typeface.createFromAsset(context.getAssets(), "roboto_regular.otf"));
            dose12.setTypeface(Typeface.createFromAsset(context.getAssets(), "roboto_regular.otf"));
            dose22.setTypeface(Typeface.createFromAsset(context.getAssets(), "roboto_regular.otf"));
            dose13.setTypeface(Typeface.createFromAsset(context.getAssets(), "roboto_regular.otf"));
            dose23.setTypeface(Typeface.createFromAsset(context.getAssets(), "roboto_regular.otf"));
            addInrTxt.setTypeface(Typeface.createFromAsset(context.getAssets(), "roboto_regular.otf"));
            va.setTypeface(Typeface.createFromAsset(context.getAssets(), "roboto_regular.otf"));
            monInrTxt.setTypeface(Typeface.createFromAsset(context.getAssets(), "roboto_regular.otf"));
            doseTxt.setTypeface(Typeface.createFromAsset(context.getAssets(), "roboto_regular.otf"));
            doseTxt2.setTypeface(Typeface.createFromAsset(context.getAssets(), "roboto_regular.otf"));
            doseTxt3.setTypeface(Typeface.createFromAsset(context.getAssets(), "roboto_regular.otf"));
            ab.setTypeface(Typeface.createFromAsset(context.getAssets(), "roboto_regular.otf"));

        }
*/
        dialog.show();

        //double dose=getDoseDoubleFromString(dose1.getText().toString())+getDoseDoubleFromString(dose2.getText().toString());

    }









    public CustomInfoDialog() {
    }

    public CustomInfoDialog(Activity activity) {
       // MedecinDashboardActivity medecinDashboardActivity = (MedecinDashboardActivity) activity;


        context = activity;
        /*
        s1=Constants.DOSE1STATIC;
        s2=Constants.DOSE2STATIC;
        s3=Constants.DOSE3STATIC;


         */
        //Toast.makeText(medecinDashboardActivity, Constants.DOSE1STATIC+"", Toast.LENGTH_SHORT).show();

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.info_tn);

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

/*
        ImageView dropImg = dialog.findViewById(R.id.dropImg);
        TextView inrValue = dialog.findViewById(R.id.inrValue);
        LinearLayout valider = dialog.findViewById(R.id.btnOk);
        LinearLayout annuler = dialog.findViewById(R.id.btn_dialog);

        dose1 = dialog.findViewById(R.id.dose1);
        dose2 = dialog.findViewById(R.id.dose2);
        dose12 = dialog.findViewById(R.id.dose12);
        dose22 = dialog.findViewById(R.id.dose22);
        dose13 = dialog.findViewById(R.id.dose13);
        dose23 = dialog.findViewById(R.id.dose23);

        if(s1<=1)
        {
            dose1.setText(getStringDoseFromDouble(s1));
            dose2.setText(getStringDoseFromDouble(0));
        }
        else
        {
            dose1.setText(getStringDoseFromDouble(1));
            dose2.setText(getStringDoseFromDouble(s1-1));
        }

        if(s2<=1)
        {
            dose12.setText(getStringDoseFromDouble(s2));
            dose22.setText(getStringDoseFromDouble(0));
        }
        else
        {
            dose12.setText(getStringDoseFromDouble(1));
            dose22.setText(getStringDoseFromDouble(s2-1));
        }

        if(s3<=1)
        {
            dose13.setText(getStringDoseFromDouble(s3));
            dose23.setText(getStringDoseFromDouble(0));
        }
        else
        {
            dose13.setText(getStringDoseFromDouble(1));
            dose23.setText(getStringDoseFromDouble(s3-1));
        }


        dose1.setText("sldjf");
        dose2.setText("0");

 */
    }

    /*
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Subscribe
    public void onEvent(ItemMenuClickEvent event) {

    }

     */

}







