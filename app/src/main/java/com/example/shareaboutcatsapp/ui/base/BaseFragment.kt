package com.example.shareaboutcatsapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shareaboutcatsapp.ui.main.MainActivity

abstract class BaseFragment : Fragment() {
    abstract fun getLayoutID(): Int
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

    fun addFragment(fragment: Fragment, id: Int, start: Int, end: Int, popStart: Int, popEnd: Int) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).addFragment(fragment, id, start, end, popStart, popEnd)
        }
    }

    fun replaceFragment(fragment: Fragment, id: Int) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).replaceFragment(fragment, id)
        }
    }

    fun showLoading() {
        if (activity is MainActivity) {
            (activity as MainActivity).showLoading()
        }
    }

    fun hideLoading() {
        if (activity is MainActivity) {
            (activity as MainActivity).hideLoading()
        }
    }

    fun hideKeyboard() {
        if (activity is MainActivity) {
            (activity as MainActivity).hideKeyboard()
        }
    }

    fun initMyRoomDB() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).initMyRoomDB()
        }
    }
}