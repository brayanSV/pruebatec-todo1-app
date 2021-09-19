package com.user.brayan.pruebatec_todo1

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

open class AppExecutors(
    val diskIO: Executor, //Usadas para realizar peticiones en la base local
    val networkIO: Executor, //Usadas para realizar peticiones a internet
    val mainThread: Executor //Funcion que corre en el hilo principal
) {
    @Inject
    constructor(): this(
        Executors.newSingleThreadExecutor(),
        Executors.newFixedThreadPool(3),
        MainThreadExecutor()
    )

    private class MainThreadExecutor: Executor {
        val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }

    fun diskIO(): Executor {
        return diskIO
    }

    fun networkIO(): Executor {
        return networkIO
    }

    fun mainThread(): Executor {
        return mainThread
    }
}