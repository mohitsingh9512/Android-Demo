package com.example.test1.ui.activity

import android.os.Bundle
import com.example.test1.ui.base.BaseActivity

class DetailActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
    }
}