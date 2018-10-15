package com.me.cl.popularmovie.mvvm.ui

import android.os.Bundle
import com.me.cl.popularmovie.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(tb_title)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_content, MovieListFragment.newInstance())
                    .commitNow()
        }
    }

}
