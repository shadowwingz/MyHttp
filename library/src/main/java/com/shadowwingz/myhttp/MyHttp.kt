package com.shadowwingz.myhttp

import com.shadowwingz.myhttp.core.HttpTask
import com.shadowwingz.myhttp.core.ThreadManager
import com.shadowwingz.myhttp.listener.IDataListener
import com.shadowwingz.myhttp.listener.JsonHttpListener
import com.shadowwingz.myhttp.request.JsonHttpRequest

object MyHttp {

    fun <P, R> sendJsonRequest(
        url: String,
        params: P,
        response: Class<R>,
        dataListener: IDataListener<R>
    ) {
        val httpRequest = JsonHttpRequest()
        val httpListener = JsonHttpListener(response, dataListener)
        val httpTask = HttpTask(
            url = url,
            httpRequest = httpRequest,
            httpListener = httpListener,
            requestData = params
        )
        ThreadManager.getInstance().addTask(httpTask)
    }
}