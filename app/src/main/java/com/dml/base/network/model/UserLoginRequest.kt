package com.dml.base.network.model

import java.io.Serializable

class UserLoginRequest : Serializable {
    var email = ""
    var password = ""
}