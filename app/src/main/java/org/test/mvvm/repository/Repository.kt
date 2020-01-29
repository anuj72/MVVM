package org.test.mvvm.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import org.test.mvvm.api.MyRetrofitBuilder
import org.test.mvvm.models.User
import retrofit2.HttpException
import java.lang.Exception

object Repository {
    var job: CompletableJob? = null
    fun getUser(userId: String): LiveData<User> {
        job = Job()
        return object : LiveData<User>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        try {
                            val user = MyRetrofitBuilder.apiService.getUser(userId)

                            withContext(Main) {
                                value = user
                                theJob.complete()
                            }
                        }catch (e:HttpException){
                            Log.v("Exception",e.toString())
                        }

                    }
                }

            }
        }
    }

    fun cancelJobs() {
        job?.cancel()
    }
}