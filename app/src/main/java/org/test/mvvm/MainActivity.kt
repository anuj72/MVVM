package org.test.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {


    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("DEBUG:${ExampleSingleton.SingletonUser.hashCode()}")
        viewModel=ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.user.observe(this, Observer {user->
            println("DEBUG:${user}")
        })
        viewModel.setUserId("1")
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJobs()
    }
}
