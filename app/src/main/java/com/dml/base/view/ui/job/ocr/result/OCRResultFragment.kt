package com.dml.base.view.ui.job.ocr.result

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.exifinterface.media.ExifInterface
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
        presenter = OCRResultPresenter(this)

        val imageFile = File(arguments?.getString("image_path"))
        if (imageFile.exists()) {
            var bitmap = BitmapFactory.decodeFile(imageFile.absolutePath)
            bitmap = rotateImageIfRequired(bitmap, Uri.fromFile(imageFile))

            val image = FirebaseVisionImage.fromBitmap(bitmap)

            //local
//            val textRecognizer = FirebaseVision.getInstance()
//                    .onDeviceTextRecognizer

            //cloud
            val textRecognizer = FirebaseVision.getInstance()
                    .cloudTextRecognizer

            textRecognizer.processImage(image)
                    .addOnSuccessListener {
                        resultTextView?.text = it.text
                        Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Log.e("textRecognizer", it.toString())
                        Toast.makeText(context, "fail ${it.toString()}", Toast.LENGTH_SHORT).show()
                    }

            resultImageView?.setImageBitmap(bitmap)
        }
    }

    private fun rotateImageIfRequired(bitmap: Bitmap, uri: Uri): Bitmap {
        val exifInterface = ExifInterface(uri.path!!)
        val orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)

        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(bitmap, 90)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(bitmap, 180)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(bitmap, 270)
            else -> bitmap
        }
    }

    private fun rotateImage(bitmap: Bitmap, degree: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        val rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        bitmap.recycle()
        return rotatedBitmap
    }
}