package com.shadowwingz.myhttp.listener

interface IDataListener<T> {

    fun onSuccess(t: T)

    fun onFailure()
}