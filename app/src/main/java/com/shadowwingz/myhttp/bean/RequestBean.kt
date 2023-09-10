package com.shadowwingz.myhttp.bean

import com.google.gson.annotations.SerializedName

data class RequestBean(
    @SerializedName("province_id")
    val provinceId: String,
    @SerializedName("key")
    val key: String
)
