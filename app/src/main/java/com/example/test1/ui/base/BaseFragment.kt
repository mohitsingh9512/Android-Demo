package com.example.test1.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    protected val injector get() = (requireActivity() as BaseActivity).injector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}