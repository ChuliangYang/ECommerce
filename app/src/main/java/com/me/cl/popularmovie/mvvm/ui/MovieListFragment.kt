package com.me.cl.popularmovie.mvvm.ui

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.*
import com.me.cl.popularmovie.R
import com.me.cl.popularmovie.mvvm.CustomLoadingListItemCreator
import com.me.cl.popularmovie.mvvm.ui.adapter.MovieListAdapter
import com.me.cl.popularmovie.mvvm.viewmodel.MovieListViewModel
import com.paginate.Paginate
import kotlinx.android.synthetic.main.fragment_movie_list.*


class MovieListFragment : Fragment() {

    companion object {
        fun newInstance() = MovieListFragment()
    }

    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this,viewModelFactory).get(MovieListViewModel::class.java)
        rv_list.apply {
            layoutManager=GridLayoutManager(context,2)
            setHasFixedSize(true)
            adapter=MovieListAdapter(arrayListOf())
            Paginate.with(this, object: Paginate.Callbacks {
                override fun onLoadMore() {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun isLoading(): Boolean {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun hasLoadedAllItems(): Boolean {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
                    .setLoadingTriggerThreshold(2)
                    .addLoadingListItem(true)
                    .setLoadingListItemCreator(CustomLoadingListItemCreator())
                    .setLoadingListItemSpanSizeLookup { 2 }
                    .build()
        }

        sr_list.setOnRefreshListener {

        }




    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }

}
