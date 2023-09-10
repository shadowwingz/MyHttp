package com.shadowwingz.myhttp.request

import com.shadowwingz.myhttp.listener.internel.IHttpListener
import com.shadowwingz.myhttp.listener.internel.IHttpRequest
import java.io.BufferedOutputStream
import java.net.HttpURLConnection
import java.net.URL

class JsonHttpRequest : IHttpRequest {

    private lateinit var _url: String
    private lateinit var _params: ByteArray
    private lateinit var _httpListener: IHttpListener

    override fun setUrl(url: String) {
        _url = url
    }

    override fun setParams(params: ByteArray) {
        _params = params
    }

    override fun execute() {
        val url: URL
        var httpUrlConnection: HttpURLConnection? = null
        try {
            url = URL(_url)
            httpUrlConnection = url.openConnection() as HttpURLConnection
            httpUrlConnection.apply {
                connectTimeout = 6000
                useCaches = false
                instanceFollowRedirects = true
                readTimeout = 3000
                doInput = true
                doOutput = true
                requestMethod = "POST"
                setRequestProperty("Content-Type", "application/json;charset=UTF-8")
                connect()
            }
            val out = httpUrlConnection.outputStream
            val bos = BufferedOutputStream(out)
            bos.write(_params)
            bos.flush()
            out.close()
            bos.close()
            if (httpUrlConnection.responseCode == HttpURLConnection.HTTP_OK) {
                _httpListener.onSuccess(httpUrlConnection.inputStream)
            } else {
                throw RuntimeException("请求失败")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("请求失败")
        } finally {
            httpUrlConnection?.disconnect()
        }
    }

    override fun setListener(httpListener: IHttpListener) {
        _httpListener = httpListener
    }
}