package com.me.cl.popularmovie.mvvm.ui

import android.os.Bundle
import com.me.cl.popularmovie.R


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_content, MovieListFragment.newInstance())
                    .commitNow()
        }
    }

}
