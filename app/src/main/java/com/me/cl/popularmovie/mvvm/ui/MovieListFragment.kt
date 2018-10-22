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
import com.me.cl.popularmovie.mvvm.api.CATE_POPULAR
import com.me.cl.popularmovie.mvvm.api.CATE_TOP_RATED
import com.me.cl.popularmovie.mvvm.di.Injectable
import com.me.cl.popularmovie.mvvm.ui.adapter.MovieListAdapter
import com.me.cl.popularmovie.mvvm.viewmodel.MovieListViewModel
import com.paginate.Paginate
import kotlinx.android.synthetic.main.fragment_movie_list.*
import javax.inject.Inject


class MovieListFragment : Fragment(), Injectable {

    companion object {
        fun newInstance() = MovieListFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var movieListAdapter: MovieListAdapter

    lateinit var viewModel: MovieListViewModel
    var page: Int = 1

    var category = MovieCategory.Popular
    var isLoading = false


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
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel::class.java)
        rv_list.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            movieListAdapter = MovieListAdapter(arrayListOf())
            adapter = movieListAdapter
            Paginate.with(this, object : Paginate.Callbacks {
                override fun onLoadMore() {
                    this@MovieListFragment.isLoading = true
                    viewModel.getMovieList(category.value,page.let {
                        it+1
                    })
                }

                override fun isLoading(): Boolean {
                    return this@MovieListFragment.isLoading
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
            viewModel.getMovieList(category.value,1) { sr_list.isRefreshing = false }
        }

        viewModel.mediatorMoviesLiveData.observe(this, Observer {
            //Maintain page only if the data is correctly loaded to avoid inconsistent problem when error occurs
            val tpage = it?.mPage
            it?.movieList?.original?.let {
                if (tpage == 1) {
                    movieListAdapter.apply {
                        movieList.clear()
                        movieList.addAll(it)
                        movieListAdapter.notifyDataSetChanged()
                        isLoading = false
                        page = tpage
                        rv_list.post { rv_list.scrollToPosition(0) }
                    }
                } else {
                    movieListAdapter.apply {
                        movieList.addAll(it)
                        notifyDataSetChanged()
                        isLoading = false
                        page = tpage?:1
                    }
                }
            }
        })
        if(savedInstanceState==null){
            viewModel.getMovieList(category.value, page)
        }else{

        }



    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_movie_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_popular -> {
                if(category!= MovieCategory.Popular){
                    category= MovieCategory.Popular
                    page=1
                    viewModel.getMovieList(category.value,page)
                }
            }
            R.id.action_rated -> {
                if(category!= MovieCategory.TOP_RATED){
                    category= MovieCategory.TOP_RATED
                    page=1
                    viewModel.getMovieList(category.value,page)
                }
            }
            R.id.action_favortie -> {
            }
            else -> {
            }
        }
        return true
    }

}

enum class MovieCategory(val value:String){
    Popular(CATE_POPULAR),
    TOP_RATED(CATE_TOP_RATED)
}