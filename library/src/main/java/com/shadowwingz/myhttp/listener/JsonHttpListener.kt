package com.shadowwingz.myhttp.listener

import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import com.shadowwingz.myhttp.listener.internel.IHttpListener
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class JsonHttpListener<T>(
    private val response: Class<T>,
    private val dataListener: IDataListener<T>
) : IHttpListener {

    private val handler: Handler = Handler(Looper.getMainLooper())

    override fun onSuccess(inputStream: InputStream) {
        val content = getContent(inputStream)
        val responseObject: T = Gson().fromJson(content, response)
        handler.post {
            dataListener.onSuccess(responseObject)
        }
    }

    override fun onFailure() {

    }

    private fun getContent(inputStream: InputStream): String {
        val bufferReader = BufferedReader(InputStreamReader(inputStream))
        val sb = StringBuilder()
        try {
            var line: String?
            while ((bufferReader.readLine().also { line = it }) != null) {
                sb.append("$line\n")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                inputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return sb.toString().replace("\n", "")
    }
}