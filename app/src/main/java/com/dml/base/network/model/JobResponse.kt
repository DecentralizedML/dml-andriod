package com.dml.base.network.model

import java.io.Serializable

class JobResponse : Serializable {
    var id = ""
    var name = "Fashion Items Scanner"
    var date = "10/1/18"
    //    @SerializedName("reward")
    var reward = "0.00024 ETH"
}