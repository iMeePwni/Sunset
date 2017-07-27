package com.imeepwni.android.sunset.app

import android.os.*
import android.support.v4.app.*
import android.support.v7.app.*
import com.imeepwni.android.sunset.*

/**
 * Create by guofeng on 2017/7/20.
 */
abstract class SingleFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_fragment)
        supportFragmentManager.run {
            if (findFragmentById(R.id.container)==null) {
                beginTransaction()
                        .add(R.id.container, getFragment())
                        .commit()
            }
        }
    }

    abstract fun getFragment(): Fragment
}