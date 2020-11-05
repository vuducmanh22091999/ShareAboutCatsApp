package com.example.shareaboutcatsapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    abstract fun getLayoutID() : Int
    abstract fun doViewCreated()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutID(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doViewCreated()
    }

    fun addFragment(fragment: Fragment, id: Int) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).addFragment(fragment, id)
        }
    }

    fun replaceFragment(fragment: Fragment, id: Int) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).replaceFragment(fragment, id)
        }
    }
}