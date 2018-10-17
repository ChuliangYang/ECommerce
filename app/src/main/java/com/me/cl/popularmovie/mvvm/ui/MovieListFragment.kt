package com.me.cl.popularmovie.mvvm.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.*
import com.me.cl.popularmovie.R
import com.me.cl.popularmovie.mvvm.CustomLoadingListItemCreator
import com.me.cl.popularmovie.mvvm.Movie
import com.me.cl.popularmovie.mvvm.di.Injectable
import com.me.cl.popularmovie.mvvm.ui.adapter.MovieListAdapter
import com.me.cl.popularmovie.mvvm.viewmodel.MovieListViewModel
import com.paginate.Paginate
import kotlinx.android.synthetic.main.fragment_movie_list.*
import javax.inject.Inject


class MovieListFragment : Fragment() ,Injectable{

    companion object {
        fun newInstance() = MovieListFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var movieListAdapter:MovieListAdapter

    lateinit var movieList:MutableList<Movie>

    var page:Int=1

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
        var isLoading=true
        rv_list.apply {
            layoutManager= GridLayoutManager(context,2)
            setHasFixedSize(true)
            movieListAdapter=MovieListAdapter(arrayListOf())
            adapter=movieListAdapter
            Paginate.with(this, object: Paginate.Callbacks {
                override fun onLoadMore() {
                    isLoading=true
                    page+=1
                    viewModel.getPopularMoiveList(page).observe(this@MovieListFragment, Observer {
                        it?.original?.let {
//                            movieListAdapter.movieList.addAll(it)
//                            movieListAdapter.notifyDataSetChanged()
                            isLoading=false
                        }
                    })
                }

                override fun isLoading(): Boolean {
                    return isLoading
                }

                override fun hasLoadedAllItems(): Boolean {
                    return false
                }
            })
                    .setLoadingTriggerThreshold(3)
                    .addLoadingListItem(true)
                    .setLoadingListItemCreator(CustomLoadingListItemCreator())
                    .setLoadingListItemSpanSizeLookup { 2 }
                    .build()
        }

        sr_list.setOnRefreshListener {
            page=1
            viewModel.getPopularMoiveList(page).observe(this@MovieListFragment, Observer {
                it?.original?.let {
                    movieList=it as MutableList<Movie>
                    movieListAdapter.movieList=movieList
                    movieListAdapter.notifyDataSetChanged()
                    sr_list.isRefreshing=false
                }
            })
        }

        viewModel.getPopularMoiveList(page).observe(this, Observer {
            it?.original?.run {
                movieList= this as MutableList<Movie>
                movieListAdapter.movieList=movieList
                movieListAdapter.notifyDataSetChanged()
                isLoading=false
                rv_list.post { rv_list.scrollToPosition(0) }
            }
        })




    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_movie_list,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }

}
