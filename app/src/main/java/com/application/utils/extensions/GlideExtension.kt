package com.application.utils.extensions

import android.net.Uri
import android.widget.ImageView
import com.application.models.UserModel
import com.bumptech.glide.Glide

fun ImageView.setImage(user: UserModel) {
    Glide.with(this)
        .load(user.photo)
        .into(this)
}

fun ImageView.setImageUriGlide(userPhoto: Uri) {
    Glide.with(this)
        .load(userPhoto)
        .into(this)
}