package com.dml.base.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SignUpModel : Serializable {
    @SerializedName("wallet_address")
    var walletAddress = ""
    @SerializedName("last_name")
    var lastName = ""
    var jwt = ""
    var id = ""
    @SerializedName("first_name")
    var firstName = ""
    var email = ""
}