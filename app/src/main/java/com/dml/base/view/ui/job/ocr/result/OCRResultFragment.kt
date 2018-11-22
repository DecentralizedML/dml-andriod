package com.dml.base.view.ui.job.ocr.result

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import com.dml.base.R
import com.dml.base.base.BaseFragment
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import kotlinx.android.synthetic.main.fragment_ocr_result.*
import java.io.File


class OCRResultFragment : BaseFragment(), OCRResultContract.View {

    companion object {
        fun newInstance(bundle: Bundle?): BaseFragment {
            val fragment = OCRResultFragment()
            if (bundle != null)
                fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var presenter: OCRResultContract.Presenter

    override fun setLayoutId(): Int {
        return R.layout.fragment_ocr_result
    }

    override fun connectViews() {
    }

    override fun setPresenter(presenter: OCRResultContract.Presenter) {
        this.presenter = presenter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val imageFile = File(arguments?.getString("image_path"))
        if (imageFile.exists()) {
            val bitmap = BitmapFactory.decodeFile(imageFile.absolutePath)
            val image = FirebaseVisionImage.fromBitmap(bitmap)
//            val metadata = FirebaseVisionImageMetadata.Builder()
//                    .setWidth(480)   // 480x360 is typically sufficient for
//                    .setHeight(360)  // image recognition
//                    .setFormat(FirebaseVisionImageMetadata.IMAGE_FORMAT_NV21)
//                    .setRotation(rotation)
//                    .build()

            val textRecognizer = FirebaseVision.getInstance()
                    .onDeviceTextRecognizer

            textRecognizer.processImage(image)
                    .addOnSuccessListener {
                        // Task completed successfully
                        // ...
                        resultTextView?.text = it.text
                        Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        // Task failed with an exception
                        // ...
                        Toast.makeText(context, "fail ${it.toString()}", Toast.LENGTH_SHORT).show()
                    }
        }
    }
}