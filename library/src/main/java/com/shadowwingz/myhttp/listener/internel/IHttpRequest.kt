package com.shadowwingz.myhttp.listener.internel

interface IHttpRequest {

    fun setUrl(url: String)

    fun setParams(params: ByteArray)

    fun execute()

    fun setListener(httpListener: IHttpListener)
}