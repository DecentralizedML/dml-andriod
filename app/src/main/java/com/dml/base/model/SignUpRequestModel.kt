package com.dml.base.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SignUpRequestModel : Serializable {
    @SerializedName("user[email]")
    var email = ""
    @SerializedName("user[password]")
    var password = ""
    @SerializedName("user[password_confirmation]")
    var passwordConfirmation = ""
    @SerializedName("user[first_name]")
    var firstName = ""
    @SerializedName("user[last_name]")
    var lastName = ""
}