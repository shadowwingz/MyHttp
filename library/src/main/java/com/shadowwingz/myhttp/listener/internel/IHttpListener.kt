package com.shadowwingz.myhttp.listener.internel

import java.io.InputStream

interface IHttpListener {

    fun onSuccess(inputStream: InputStream)

    fun onFailure()
}