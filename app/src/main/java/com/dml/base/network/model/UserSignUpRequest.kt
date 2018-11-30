package com.dml.base.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserSignUpRequest : Serializable {
    var user = User()

    inner class User : Serializable {
        @SerializedName("email")
        var email = ""
        @SerializedName("password")
        var password = ""
        @SerializedName("password_confirmation")
        var passwordConfirmation = ""
    }
}