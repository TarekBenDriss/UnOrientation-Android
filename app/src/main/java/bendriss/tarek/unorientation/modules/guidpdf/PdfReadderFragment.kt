package bendriss.tarek.unorientation.modules.guidpdf

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import bendriss.tarek.unorientation.R
import bendriss.tarek.unorientation.databinding.FragmentPdfReadderBinding
import bendriss.tarek.unorientation.databinding.FragmentQuizBinding
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.util.FitPolicy


class PdfReadderFragment : Fragment() {

    private var mBinding: FragmentPdfReadderBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_pdf_readder, container, false)

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pdf_readder, container, false)

        val guideUri: Uri = Uri.parse("http://www.orientation.tn/orient/pdf/guide_2019.pdf")
        val pdfView: PDFView = mBinding?.pdfView!!
        pdfView.fromAsset("guide_2019.pdf")
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .enableAnnotationRendering(true) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .spacing(0)
                .pageFitPolicy(FitPolicy.BOTH)
                .load()


        return mBinding?.root
    }


}