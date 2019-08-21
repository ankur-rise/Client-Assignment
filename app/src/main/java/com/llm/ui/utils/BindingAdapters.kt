package com.llm.ui.utils

import androidx.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView

@BindingAdapter("app:uri")
fun setImageUri(view: SimpleDraweeView, uri:String?) {

    view.setImageURI(uri?:"")

}