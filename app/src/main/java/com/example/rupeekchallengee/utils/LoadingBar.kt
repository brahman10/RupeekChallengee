package com.example.rupeekchallengee.utils

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.LinearLayout
import com.example.rupeekchallengee.R


class LoadingBar(var context: Context) {
    var dialog: Dialog
    var rel_loader: LinearLayout
    fun show() {
        dialog.show()
    }

    fun dismiss() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }

    val isShowing: Boolean
        get() = dialog.isShowing

    init {
        dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.dialog_rounded_corner);
        dialog.setContentView(R.layout.loading_layout)
        dialog.setCanceledOnTouchOutside(false)
        rel_loader = dialog.findViewById(R.id.rel_loader)
    }
}