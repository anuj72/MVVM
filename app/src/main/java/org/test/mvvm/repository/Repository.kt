package org.test.mvvm.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import org.test.mvvm.api.MyRetrofitBuilder
import org.test.mvvm.models.User

object Repository {
    var job:CompletableJob?=null
    fun getUser(userId:String):LiveData<User>{
        job= Job()
        return object :LiveData<User>(){
            override fun onActive() {
                super.onActive()
                job?.let {
                    theJob->
                    CoroutineScope(IO+theJob).launch {
                        val user=MyRetrofitBuilder.apiService.getUser(userId)
                        withContext(Main){
                            value=user
                            theJob.complete()
                        }
                    }
                }

            }
        }
    }
    fun cancelJobs(){
        job?.cancel()
    }
}