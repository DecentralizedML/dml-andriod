package com.dml.base.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserLoginRequest : Serializable {
    @SerializedName("email")
    var email = ""
    @SerializedName("password")
    var password = ""
}