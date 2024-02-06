package com.example.test1.ui.activity

import android.os.Bundle
import com.example.test1.R
import com.example.test1.ui.base.BaseActivity
import com.example.test1.ui.fragment.MainFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.content_frame,
                    MainFragment.newInstance(Bundle())
                )
                .commit()
        }
    }
}