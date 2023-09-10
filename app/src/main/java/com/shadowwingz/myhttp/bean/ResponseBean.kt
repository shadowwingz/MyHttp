package com.shadowwingz.myhttp.bean

import com.google.gson.annotations.SerializedName

data class ResponseBean(
    @SerializedName("error_code")
    val errorCode: Int,
    @SerializedName("resultcode")
    val resultCode: String
)
