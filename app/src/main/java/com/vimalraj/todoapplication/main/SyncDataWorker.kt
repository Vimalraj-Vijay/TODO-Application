package com.vimalraj.todoapplication.main

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class SyncDataWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        for (i: Int in 0..100) {
            println("Uploading $i")
        }
        return Result.success()
    }

}