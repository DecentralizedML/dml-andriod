package com.dml.base.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserUpdateResponse : Serializable {

    @SerializedName("data")
    lateinit var data: Data

    inner class Data {
        @SerializedName("attributes")
        var attributes: Attributes? = null
        @SerializedName("id")
        var id = ""
        @SerializedName("type")
        var type = ""
    }

    inner class Attributes {
        @SerializedName("country")
        var country = ""
        @SerializedName("date_of_birth")
        var dateOfBirth = ""
        @SerializedName("education_level")
        var educationLevel = ""
        @SerializedName("email")
        var email = ""
        @SerializedName("first_name")
        var firstName = ""
        @SerializedName("gender")
        var gender = ""
        @SerializedName("last_name")
        var lastName = ""
        @SerializedName("profile_image")
        var profileImage = ""
        @SerializedName("wallet_address")
        var walletAddress = ""
    }
}