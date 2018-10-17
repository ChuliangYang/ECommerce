package com.me.cl.popularmovie.mvvm.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import com.me.cl.popularmovie.R
import com.me.cl.popularmovie.mvvm.di.Injectable
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity() , HasSupportFragmentInjector,Injectable{
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    override fun supportFragmentInjector() =dispatchingAndroidInjector

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
