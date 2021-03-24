package com.example.converter.ui


import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.converter.R
import com.example.converter.databinding.ActivityMainBinding
import com.example.converter.model.IImage
import com.example.converter.presenter.ConvertPresenter
import com.example.converter.view.ConverterView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), ConverterView {
    companion object {
        private val PICK_IMAGE = 2
    }

    private var ui: ActivityMainBinding? = null
    private val context: Context? = null
    val cvtpresenter: ConvertPresenter by moxyPresenter {

        ConvertPresenter(AndroidSchedulers.mainThread(), Converter(context))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui?.root)
        ui?.button?.setOnClickListener {
            cvtpresenter.click()
        }
    }

    override fun pickImage() { // выбор изображения из ресурсов телефона
        val intent = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }

        startActivityForResult(Intent.createChooser(intent, "Выберите изображение"), PICK_IMAGE)
    }

    var dialog: Dialog? = null

    override fun showInProgress() {
        context?.let {
            dialog?.setTitle(R.string.progress)
            dialog?.show()
        }
    }

    override fun hideInProgress() {
        dialog?.dismiss()
    }

    var dialog_s: Dialog? = null
    override fun showSuccess() {
        context?.let {
            dialog_s?.setTitle(R.string.success)
            dialog_s?.show()
        }
    }

    var dialog_error: Dialog? = null
    override fun showError() {
        context?.let {
            dialog_error?.setTitle(R.string.error)
            dialog_error?.show()
        }
    }


    fun onResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == PICK_IMAGE) {
            if (resultCode == RESULT_OK) {
                data.data?.let { uri ->
                    val byt = context?.contentResolver?.openInputStream(uri)?.buffered()
                        ?.use { it.readBytes() }
                    byt?.let {
                        cvtpresenter.selected(IImage(byt))
                    }
                }

            }
        }

    }
}