package com.shadowwingz.myhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.shadowwingz.myhttp.bean.RequestBean
import com.shadowwingz.myhttp.bean.ResponseBean
import com.shadowwingz.myhttp.listener.IDataListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = "https://v.juhe.cn/historyWeather/citys"
        val params = RequestBean(
            provinceId = "2",
            key = "bb52107206585ab074f5e59a8c73875b"
        )
        MyHttp.sendJsonRequest(
            url = url,
            params = params,
            response = ResponseBean::class.java,
            dataListener = object : IDataListener<ResponseBean> {
                override fun onSuccess(t: ResponseBean) {
                    Log.d("shadowwingz", "onSuccess: $t")
                }

                override fun onFailure() {
                }
            }
        )
    }
}