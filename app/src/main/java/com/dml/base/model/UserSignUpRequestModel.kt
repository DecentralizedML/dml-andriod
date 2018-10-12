package com.dml.base.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserSignUpRequestModel : Serializable {
    var user = User()

    inner class User : Serializable {
        @SerializedName("email")
        var email = ""
        @SerializedName("password")
        var password = ""
        @SerializedName("password_confirmation")
        var passwordConfirmation = ""
        @SerializedName("first_name")
        var firstName = ""
        @SerializedName("last_name")
        var lastName = ""
    }
}