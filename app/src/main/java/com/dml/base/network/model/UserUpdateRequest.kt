package com.dml.base.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserUpdateRequest : Serializable {
    var user = User()

    inner class User : Serializable {
        @SerializedName("country")
        var country = ""
        @SerializedName("date_of_birth")
        var dateOfBirth = ""
        @SerializedName("education_level")
        var educationLevel = ""
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