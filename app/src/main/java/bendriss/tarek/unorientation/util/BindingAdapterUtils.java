package bendriss.tarek.unorientation.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * this function represents binding adapter utils
 */
public class BindingAdapterUtils {


    @BindingAdapter("changeText")
    public static void setImageUrl(TextView view, String txt) {
        Log.e("TEXT","1 "+txt);
        txt = txt.toLowerCase();
        Log.e("TEXT","2 "+txt);
        switch (txt) {
            case "pleinair":
                view.setText("Plein air");
                break;
            case "mathphyinf":
                view.setText("Math, physique, informatique");
                break;
            case "prepaintegré":
                view.setText("Préparatoir integré");
                break;
            case "chimiebiologie":
                view.setText("Chimie et biologie");
                break;
            case "biologiegeologie":
                view.setText("Biologie et geologie");
                break;
            case "scphyinf":
                view.setText("Science, physique, informatique");
                break;
            case "appliquéegestion":
                view.setText("Informatique appliquée à la gestion");
                break;
            case "statenvironnement":
                view.setText("Statistiques d'environnement");
                break;
            case "nouvellestecho":
                view.setText("Nouvelles technologies");
                break;
            case "physiquechimie":
                view.setText("Physique et chimie");
                break;
            case "ingindus":
                view.setText("Ingénierie industrielle");
                break;
            case "ingnrj":
                view.setText("Ingénierie d'énergie");
                break;
            case "scdelaterre":
                view.setText("Sciences de la terre");
                break;
            case "scexactes":
                view.setText("Sciences exactes");
                break;
            case "scinformatique":
                view.setText("Sciences informatiques");
                break;
            case "schumaines":
                view.setText("Sciences humaines");
                break;
            case "philo":
                view.setText("Philosophies");
                break;
            case "islam":
                view.setText("Islam");
                break;
            case "patrimoineislm":
                view.setText("Patrimoine islmique");
                break;
            case "scislamique":
                view.setText("Sciences islamiques");
                break;
            case "scdechariaa":
                view.setText("Sciences de chariaa");
                break;
            case "documentationarch":
                view.setText("Documentation et archive");
                break;
            case "patrimoineculturel":
                view.setText("Patrimoine culturel");
                break;
            case "scd'education":
                view.setText("Sciences de l'education");
                break;
            case "educationspecialisé":
                view.setText("Education specialisée");
                break;
            case "education/enseignement":
                view.setText("Education / enseignement");
                break;
            case "politiquejuridique":
                view.setText("Politique et juridique");
                break;
            case "9anoun":
                view.setText("Droit");
                break;
            case "9anounejtime3i":
                view.setText("Droit social");
                break;
            case "natureetagriculture":
                view.setText("Nature et agriculture");
                break;
            case "scdelavie":
                view.setText("Sciences de la vie");
                break;
            case "phy/loisir":
                view.setText("Physique et loisirs");
                break;
            case "nrml":
                view.setText("Normal");
                break;
            case "artclassique":
                view.setText("Art classique");
                break;
            case "cinemaa/v":
                view.setText("Cinema et audio/visuel");
                break;
            case "artmoderne":
                view.setText("Art moderne");
                break;
            case "sonetnvltechno":
                view.setText("Son et nouvelles technonologies");
                break;
            case "ar":
                view.setText("Arabe");
                break;
            case "fr":
                view.setText("Francais");
                break;
            case "eng":
                view.setText("Anglais");
                break;
            case "icharat":
                view.setText("Langage des signes");
                break;
            case "sceco":
                view.setText("Sciences economiques");
                break;
            case "compta":
                view.setText("Comptabilité");
                break;
            case "sante":
                view.setText("Santé");
                break;
            case "santeetsecurité":
                view.setText("Sante et securité");
                break;
            case "tadrim9adam739":
                view.setText("Soins des pieds");
                break;
            case "dentaire":
                view.setText("Medecine dentaire");
                break;
            case "ta8dhya":
                view.setText("Nutrition");
                break;
            case "smur":
                view.setText("Service mobile d'urgence et de réanimation");
                break;
            case "smurenfant":
                view.setText("Service mobile d'urgence et de réanimation enfants");
                break;
            case "3ilejtabi3i":
                view.setText("Traitement naturel");
                break;
            case "3ilej3amal":
                view.setText("Ergothérapie");
                break;
            case "prothesedent":
                view.setText("Prothèse dentaire");
                break;
            case "ecoute":
                view.setText("Appareils d'écoute");
                break;
            case "not9":
                view.setText("Prononciation");
                break;
            case "tawlid":
                view.setText("Sage-femme");
                break;
            case "lunetet739":
                view.setText("Lunettes et optique");
                break;
            case "jira7et3dham":
                view.setText("Chirurgie orthopédique");
                break;
            case "appareil oper":
                view.setText("Équipement de théâtre chirurgical");
                break;
            case "tabnijwin3ach":
                view.setText("Anesthésie et réanimation");
            default:
                txt = txt.substring(0,1).toUpperCase() + txt.substring(1).toLowerCase();
                view.setText(txt);
        }

        //view.setText("xxxxxx");
    }

/*
    @BindingAdapter("urlImg")
    public static void setImage(ImageView view, String url) {
        Context context = App.app.getApplicationContext();
        if(!Constants.IMG_PATIENT.equals("00000") && Constants.IMG_PATIENT.substring(0,4).equals("http"))
        GlideApp.with(context).load(Constants.IMG_PATIENT).placeholder(R.drawable.ic_doctor_avk).into(view);
        else
            GlideApp.with(context).load(R.drawable.ic_doctor_avk).into(view);
    }

    @BindingAdapter("imgVisibility2")
    public static void imgVisibility2(ImageView view, List<Document> list) {
        if(list.size()==0)
            view.setVisibility(View.VISIBLE);
        else
            view.setVisibility(View.INVISIBLE);
    }

    @BindingAdapter("imgPatient")
    public static void setImagePatient(CircleImageView view, String url) {
        Context context = App.app.getApplicationContext();
        //Log.e("imgPatientTAG",url);
        if(url!=null && !url.equals("00000") && !url.equals("") && url.substring(0,4).equals("http"))
        {
            GlideApp.with(context).load(url).placeholder(R.drawable.ic_doctor_avk).into(view);
            view.setBorderWidth(1);
        }
        else
        {
            GlideApp.with(context).load(R.drawable.ic_doctor_avk).into(view);
            view.setBorderWidth(0);
        }

    }


    @BindingAdapter("imgNews")
    public static void setImageNews(ImageView view, List<Image> image) {
        Context context = App.app.getApplicationContext();
        String url;
        //Log.e("imgPatientTAG",url);
        if(image!=null && image.size()>0)
        {
            //if(image.get(0)!=null) {
                //url = "https://demo.pokmy.net/api/documents/"+image.get(0).get_id()+"/resize/1/100/100/true?access_token="+App.token;
                url = "https://" + App.agence + ".pokmy.net/api/documents/" + image.get(0).get_id() + "/resize/1/100/100/true?access_token=" + App.token;
                GlideApp.with(context).applyDefaultRequestOptions(new RequestOptions().disallowHardwareConfig())
                        .load(url).apply(RequestOptions.circleCropTransform()).placeholder(R.drawable.empty).into(view);
            //}
        }
    }



    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("onLoadMore")
    public static void setRecyclerViewEndlessScroll(RecyclerView recyclerView, EndlessScroll endlessScroll) {
        endlessScroll = new EndlessScroll((LinearLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
            }
        };
        recyclerView.addOnScrollListener(endlessScroll);
    }

    @BindingAdapter("backgroundMsg")
    public static void setBackgroundImg(LinearLayout layout, String s) {
            if(s.equals("patient"))
            {
                layout.setBackgroundResource(R.drawable.shape_outgoing_bubble);
            }
            else
                layout.setBackgroundResource(R.drawable.shape_incoming_bubble);
        Log.e("backgroundTAG",s);


    }

    @BindingAdapter("backgroundMedecin")
    public static void backgroundMedecin(LinearLayout layout, String s) {
        if(s.equals("patient"))
        {
            layout.setBackgroundResource(R.drawable.shape_incoming_bubble_medecin);
        }
        else
            layout.setBackgroundResource(R.drawable.shape_outgoing_bubbule_medecin);
        Log.e("messageTAG",s);

    }


    @BindingAdapter("visibilityMedecin")
    public static void setVisibleMedecin(ImageView layout, String s) {
        if(s.equals("patient"))
        {
            layout.setVisibility(View.GONE);
        }
        else
            layout.setVisibility(View.VISIBLE);
    }

    @BindingAdapter("visibilityNomMedecin")
    public static void setVisibleNomMedecin(TextView layout, String s) {
        if(s.equals("patient"))
        {
            layout.setVisibility(View.GONE);
        }
        else
            layout.setVisibility(View.VISIBLE);

    }


    @BindingAdapter("visibilityPatient")
    public static void setVisiblePatient(ImageView layout, String s) {
        if(!s.equals("patient"))
        {
            layout.setVisibility(View.GONE);
        }
        else
            layout.setVisibility(View.VISIBLE);
    }

    @BindingAdapter("visibilityNomPatient")
    public static void setVisibleNomPatient(TextView layout, String s) {
        if(!s.equals("patient"))
        {
            layout.setVisibility(View.GONE);
        }
        else
        {
            layout.setVisibility(View.VISIBLE);
            layout.setText(Constants.NOM_PATIENT);
        }

    }

    @BindingAdapter("imgVisibility")
    public static void imgVisibility(ImageView layout, String s) {
        if(s != null && !s.equals(""))
        {
            layout.setVisibility(View.VISIBLE);
            //RequestBuilder<Bitmap> requestBuilder = GlideApp.with(layout.getContext()).asBitmap();
            //requestBuilder.load(s).into(layout);
        }
        else
            layout.setVisibility(View.GONE);

    }

    @SuppressLint("ResourceAsColor")
    @BindingAdapter("txtVisibility")
    public static void txtVisibility(TextView layout, String s) {
        Context context = App.app.getApplicationContext();

        if(s.equals("Est ce que je peux utiliser ce médicament ?"))
        {
            layout.setText(R.string.demmand_medecine);
            layout.setTextColor(context.getResources().getColor(R.color.gris_chat));
        }
        else if(s.equals("Mon analyse"))
        {
            layout.setTextColor(context.getResources().getColor(R.color.gris_chat));
        }
        else
        {
            layout.setTextColor(context.getResources().getColor(R.color.white));
            layout.setVisibility(View.VISIBLE);
        }

    }


    @SuppressLint("ResourceAsColor")
    @BindingAdapter("txtColorChat")
    public static void txtColorChat(TextView layout, String s) {
        Context context = App.app.getApplicationContext();


        if(s.equals("patient"))
        {
            //layout.setText(R.string.demmand_medecine);
            layout.setTextColor(context.getResources().getColor(R.color.gris_chat));
        }
        else {
            layout.setTextColor(context.getResources().getColor(R.color.white));
            //layout.setVisibility(View.VISIBLE);
        }

    }



    @SuppressLint("ResourceAsColor")
    @BindingAdapter("txtVisibilityMedecin")
    public static void txtVisibilityMedecin(TextView layout, String s) {
        Context context = App.app.getApplicationContext();


        if(s.equals("Est ce que je peux utiliser ce médicament ?"))
        {
            layout.setText(R.string.demmand_medecine);
            layout.setTextColor(context.getResources().getColor(R.color.gris_chat));


        }
        else if(s.equals("Mon analyse"))
        {
            layout.setTextColor(context.getResources().getColor(R.color.gris_chat));
        }
        else
        {
            layout.setTextColor(context.getResources().getColor(R.color.white));
            layout.setVisibility(View.VISIBLE);
        }

    }


    @BindingAdapter("quizzVisibility")
    public static void quizzVisibility(ConstraintLayout layout, String s) {
        if(s.equals(""))
        {
            layout.setVisibility(View.GONE);
        }
        else
            layout.setVisibility(View.VISIBLE);

    }

    @SuppressLint("ResourceAsColor")
    @BindingAdapter("inrColor")
    public static void inrColor(TextView layout, double d) {
        Log.e("colorTAG",d+"");

        Context context = App.app.getApplicationContext();
        if(d<1.8)
        {
            layout.setTextColor(context.getResources().getColor(R.color.orange_drop));
        }
        else if(1.8<=d && d<=3)
        {
            layout.setTextColor(context.getResources().getColor(R.color.green_drop));
        }
        else if(d>3 && d<=5)
        {
            layout.setTextColor(context.getResources().getColor(R.color.orange_drop));
        }
        else if(d>5)
        {
            layout.setTextColor(context.getResources().getColor(R.color.red_drop));
        }

    }

    @SuppressLint("ResourceAsColor")
    @BindingAdapter("layoutColor")
    public static void layoutColor(LinearLayout layout, double d) {
        Log.e("colorTAG",d+"");

        Context context = App.app.getApplicationContext();
        if(d<1.8)
        {
            layout.setBackground(context.getResources().getDrawable(R.drawable.orange_shape));
        }
        else if(1.8<=d && d<=3)
        {
            layout.setBackground(context.getResources().getDrawable(R.drawable.green_shape));
        }
        else if(d>3 && d<=5)
        {
            layout.setBackground(context.getResources().getDrawable(R.drawable.orange_shape));
        }
        else if(d>5)
        {
            layout.setBackground(context.getResources().getDrawable(R.drawable.red_shape));
        }

    }

    @SuppressLint("ResourceAsColor")
    @BindingAdapter("ic_inr")
    public static void ic_inr(ImageView view, double d) {
        Log.e("colorTAG",d+"");

        Context context = App.app.getApplicationContext();
        if(d<1.8)
        {
            GlideApp.with(context).load(R.drawable.ic_inr).fitCenter().into(view);
        }
        else if(1.8<=d && d<=3)
        {
            GlideApp.with(context).load(R.drawable.ic_inr_2).fitCenter().into(view);
        }
        else if(d>3 && d<=5)
        {
            GlideApp.with(context).load(R.drawable.ic_inr).fitCenter().into(view);
        }
        else if(d>5)
        {
            GlideApp.with(context).load(R.drawable.ic_inr_1).fitCenter().into(view);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @BindingAdapter("dateChat")
    public static void dateChat(TextView layout, String s) {


        //Log.e("dateTag",s);
        Date dateMsg;
        try {
            String year = s.substring(0, 4);
            String month = s.substring(5, 7);
            String day = s.substring(8, 10);
            String hourMsg = s.substring(11, 16);

            layout.setText(year + "/" + month + "/" + day);

            Date date = new Date();


            String dayToday = (String) DateFormat.format("dd", date); // 20
            String monthToday = (String) DateFormat.format("MM", date); // 06
            String yearToday = (String) DateFormat.format("yyyy", date);

            //String minuteMsg          = (String) DateFormat.format("dd",   ); // 20


            if (day.equals(dayToday) && month.equals(monthToday) && year.equals(yearToday))
                layout.setText(hourMsg);
            else
                layout.setText(day + "/" + month + "/" + year + " " + hourMsg);
        }
        catch (StringIndexOutOfBoundsException e)
        {
            layout.setText(new Date().getHours()+":"+new Date().getMinutes());
        }


    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @BindingAdapter("textChat")
    public static void textChat(TextView layout, String s) {
        Context context = App.app.getApplicationContext();

        if(s.equals(Constants.PATIENT))
            layout.setText(context.getString(R.string.sent_you_photo));
        else
            layout.setText(context.getString(R.string.your_sent_response));


    }




    @RequiresApi(api = Build.VERSION_CODES.O)
    @BindingAdapter("nomPatientList")
    public static void nomPatientList(TextView layout, String s) {
                layout.setText(StringUtils.upperFirstChar(s));
        //layout.setText(id+"");
    }


    @BindingAdapter("nbrToTxt")
    public static void nbrToTxt(TextView view, int id) {
        if(id>0)
        view.setText(id+"");
    }


    @BindingAdapter("iconeNotif")
    public static void iconeNotif(ImageView view, int id) {
        //int s = Integer.parseInt(id);

        if(id==0)
            view.setVisibility(View.INVISIBLE);
        else
            view.setVisibility(View.VISIBLE);
    }


    @BindingAdapter("iconeMessage")
    public static void iconeMessage(ImageView view, String id) {
        //int s = Integer.parseInt(id);


        if(id.equals("true"))
        {
            Log.e("iconVisibilityTAG",id);
            view.setVisibility(View.VISIBLE);
        }
        else
        {
            Log.e("iconVisibilityTAG",id);
            view.setVisibility(View.INVISIBLE);
        }

    }

    @BindingAdapter("notifImg")
    public static void notifImg(ImageView view, String id) {
        //int s = Integer.parseInt(id);

        Context context = App.app.getApplicationContext();
        if(id.equals("Demande_Rdv"))
        {
            GlideApp.with(context).load(R.drawable.ic_rdv_1).fitCenter().into(view);
        }
        else
        {

            GlideApp.with(context).load(R.drawable.ic_inr_notif).fitCenter().into(view);
        }

    }
*/
}
