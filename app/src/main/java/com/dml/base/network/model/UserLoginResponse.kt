package com.dml.base.network.model

import com.google.gson.annotations.SerializedName

class UserLoginResponse {
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