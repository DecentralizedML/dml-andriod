package com.dml.base.network.model

import java.io.Serializable

class TransactionResponse : Serializable {
    var id = ""
    var name = "Fashion Items Scanner"
    var date = "10/1/18"
    //    @SerializedName("reward")
    var amount = "0.00024 ETH"
    var fiatAmount = "0.00024 ETH"
}