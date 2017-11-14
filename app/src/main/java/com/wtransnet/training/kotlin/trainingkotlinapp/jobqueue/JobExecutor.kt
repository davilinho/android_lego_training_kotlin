package com.wtransnet.training.kotlin.trainingkotlinapp.jobqueue

import android.content.Context
import com.birbit.android.jobqueue.Job
import com.birbit.android.jobqueue.Params
import com.birbit.android.jobqueue.RetryConstraint
import com.birbit.android.jobqueue.network.NetworkUtil
import com.birbit.android.jobqueue.network.NetworkUtilImpl
import com.wtransnet.training.kotlin.trainingkotlinapp.result.Result
import com.wtransnet.training.kotlin.trainingkotlinapp.useCase.IUseCase

/**
* Created by davidmartin on 3/11/17.
*/
private val tag = JobExecutor::class.java.canonicalName

class JobExecutor<T1, T2>(private val context: Context, private val request: T1?, private val useCase: IUseCase<T1, T2>,
                          private val offlineCallback: () -> Unit, private val onlineCallback: (Result<T2>) -> Unit,
                          priority: Executor.Priority):
        Job(Params(priority.ordinal).requireNetwork().singleInstanceBy(tag).addTags(tag)) {

    override fun onAdded() {
        if (NetworkUtilImpl(context).getNetworkStatus(context) == NetworkUtil.DISCONNECTED) {
            offlineCallback()
        }
    }

    override fun onRun() {
        useCase.execute(request).subscribe({
            response -> onlineCallback(response)
        })
    }

    override fun shouldReRunOnThrowable(throwable: Throwable, runCount: Int, maxRunCount: Int): RetryConstraint {
        return RetryConstraint.createExponentialBackoff(runCount, 1000)
    }

    override fun onCancel(cancelReason: Int, throwable: Throwable?) {
        println("[Cancel reason $cancelReason] - Cause: ${throwable?.cause}")
    }
}
