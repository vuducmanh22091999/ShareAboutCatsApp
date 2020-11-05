package com.example.shareaboutcatsapp.ui.main.breeds

import android.view.View
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_details_breeds.*

class DetailsBreedsFragment : BaseFragment(), View.OnClickListener {
    override fun getLayoutID(): Int {
        return R.layout.fragment_details_breeds
    }

    override fun doViewCreated() {
        hideBottomNavigation()
        initListener()
    }

    private fun initListener() {
        imgBackHome.setOnClickListener(this)
    }

    private fun backToHome() {
        activity?.onBackPressed()
    }

    private fun hideBottomNavigation() {
        if (activity is MainActivity) {
            (activity as MainActivity).hideBottomNavigation()
        }
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.imgBackHome -> backToHome()
        }
    }
}