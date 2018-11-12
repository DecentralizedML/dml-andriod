package com.dml.base.network.model

import java.io.Serializable

class WalletTypeResponse : Serializable {
    var id = ""
    var name = "Decentralized Machine Learning"
    //    @SerializedName("reward")
    var amount = "500.00"
    var fiatAmount = "6.45"
}