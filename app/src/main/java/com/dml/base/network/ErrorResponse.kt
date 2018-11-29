package com.dml.base.network

import com.google.gson.annotations.SerializedName

class ErrorResponse {
    @SerializedName("errors")
    lateinit var errors: Errors

    inner class Errors {
    }
}