package com.shadowwingz.myhttp.core

import com.google.gson.Gson
import com.shadowwingz.myhttp.listener.internel.IHttpListener
import com.shadowwingz.myhttp.listener.internel.IHttpRequest
import java.io.UnsupportedEncodingException

class HttpTask<T>(
    url: String,
    private val httpRequest: IHttpRequest,
    httpListener: IHttpListener,
    requestData: T,
) : Runnable {

    init {
        httpRequest.setUrl(url)
        httpRequest.setListener(httpListener)

        val dataStr: String = Gson().toJson(requestData)
        try {
            httpRequest.setParams(dataStr.toByteArray())
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
    }

    override fun run() {
        httpRequest.execute()
    }
}