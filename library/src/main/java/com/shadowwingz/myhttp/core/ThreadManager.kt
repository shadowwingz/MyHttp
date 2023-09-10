package com.shadowwingz.myhttp.core

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class ThreadManager {

    companion object {

        @Volatile
        private var INSTANCE: ThreadManager? = null

        fun getInstance(): ThreadManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ThreadManager().also {
                    INSTANCE = it
                }
            }
        }
    }

    private val runnable: Runnable = Runnable {
        while (true) {
            try {
                threadPoolExecutor.execute(queue.take())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private val queue = LinkedBlockingQueue<Runnable>()

    private val threadPoolExecutor: ThreadPoolExecutor = ThreadPoolExecutor(
        3,
        10,
        10,
        TimeUnit.SECONDS,
        ArrayBlockingQueue(4)
    ) { r, _ ->
        queue.add(r)
    }

    init {
        threadPoolExecutor.execute(runnable)
    }

    fun addTask(runnable: Runnable) {
        queue.add(runnable)
    }
}