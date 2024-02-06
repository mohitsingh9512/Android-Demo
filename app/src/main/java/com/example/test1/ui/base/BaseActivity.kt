package com.example.test1.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.test1.MyApplication

open class BaseActivity : AppCompatActivity() {

    private val appComponent
        get() = (application as MyApplication).appComponent

    private val activityComponent by lazy {
        appComponent.activityComponent()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    val injector get() = activityComponent
}