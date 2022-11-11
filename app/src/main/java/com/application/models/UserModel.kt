package com.application.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    var id: Int,
    val photo: String,
    val username: String,
    val career: String,
    val email: String,
    val phone: String,
    val homeAddress: String,
    val dataBirth: String
) : Parcelable