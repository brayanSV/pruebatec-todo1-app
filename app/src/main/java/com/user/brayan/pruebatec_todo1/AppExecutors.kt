package com.user.brayan.pruebatec_todo1

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class AppExecutors(val diskIo: Executor,val networkIo: Executor,val mainThread: Executor) {
    @Inject
    constructor(): this(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3), MainThreadExecutor())

    fun diskIO(): Executor = diskIo

    fun networkIO(): Executor = networkIo

    fun mainThread(): Executor = mainThread

    private class MainThreadExecutor: Executor {
        val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}