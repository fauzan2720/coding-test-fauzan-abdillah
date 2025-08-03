package com.example.codingtest_fauzanabdillah.core.util


import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.example.codingtest_fauzanabdillah.databinding.LayoutDialogLoadingBinding

object LoadingDialogUtil {
    private var dialog: AlertDialog? = null

    fun show(context: Context) {
        if (dialog?.isShowing == true) return

        val binding = LayoutDialogLoadingBinding.inflate(LayoutInflater.from(context))
        dialog = AlertDialog.Builder(context)
            .setView(binding.root)
            .setCancelable(false)
            .create()
        dialog?.show()
    }

    fun hide() {
        dialog?.dismiss()
        dialog = null
    }
}
