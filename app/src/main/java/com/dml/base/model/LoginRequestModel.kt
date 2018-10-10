package com.dml.base.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class LoginRequestModel : Serializable {
    var email = ""
    var password = ""
}