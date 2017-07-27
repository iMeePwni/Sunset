package com.imeepwni.android.sunset.ui

import android.support.v4.app.*
import com.imeepwni.android.sunset.app.*

class SunsetActivity : SingleFragmentActivity() {
    override fun getFragment(): Fragment {
        return SunsetFragment.newInstance()
    }
}
