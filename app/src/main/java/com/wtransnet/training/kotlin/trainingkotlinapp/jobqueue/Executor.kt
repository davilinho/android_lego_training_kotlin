package com.wtransnet.training.kotlin.trainingkotlinapp.jobqueue

import android.content.Context
import com.birbit.android.jobqueue.JobManager
import com.birbit.android.jobqueue.config.Configuration
import com.birbit.android.jobqueue.log.CustomLogger
import com.wtransnet.training.kotlin.trainingkotlinapp.log.Logger
import com.wtransnet.training.kotlin.trainingkotlinapp.useCase.IUseCase

/**
* Created by davidmartin on 6/11/17.
*/
@Suppress("UNUSED_PARAMETER")
class Executor<T1, T2>(private val context: Context, private val priority: Priority): IExecutor<T1, T2> {

    enum class Priority(value: Int) {
        LOW(0), MID(10), HIGH(100)
    }

    override fun addJobInBackground(request: T1?, useCase: IUseCase<T1, T2>, offlineCallback: () -> Unit,
                                    onlineCallback: (T2) -> Unit) {
        getJobManager().addJobInBackground(JobExecutor(context, request, useCase, offlineCallback, onlineCallback,
                priority))
    }

    private fun getJobManager(): JobManager {
        return JobManager(Configuration.Builder(context)
                .minConsumerCount(1)
                .maxConsumerCount(3)
                .loadFactor(3)
                .consumerKeepAlive(120)
                .customLogger(object : CustomLogger {
                    override fun isDebugEnabled(): Boolean {
                        return true
                    }
                    override fun d(text: String, vararg args: Any) {
                        Logger.instance.debug<JobManager>(String.format(text, *args))
                    }
                    override fun e(t: Throwable, text: String, vararg args: Any) {
                        Logger.instance.error<JobManager>(String.format(text, *args), t)
                    }
                    override fun e(text: String, vararg args: Any) {
                        Logger.instance.error<JobManager>(String.format(text, *args))
                    }
                    override fun v(text: String, vararg args: Any) {
                        Logger.instance.info<JobManager>(String.format(text, *args))
                    }
                }).build())
    }
}

interface IExecutor<T1, T2> {
    fun addJobInBackground(request: T1?, useCase: IUseCase<T1, T2>, offlineCallback: () -> Unit,
                           onlineCallback: (T2) -> Unit)
}