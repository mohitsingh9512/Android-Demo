package com.example.test1.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.R
import com.example.test1.di.factory.ViewModelProviderFactory
import com.example.test1.network.response.Movie
import com.example.test1.ui.adapter.MoviesAdapter
import com.example.test1.ui.adapter.MoviesInterface
import com.example.test1.ui.base.BaseFragment
import com.example.test1.ui.viewholder.uimodel.BaseDataModel
import com.example.test1.ui.viewholder.uimodel.MoviesDataModel
import com.example.test1.viewmodel.MainUiState
import com.example.test1.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : BaseFragment(), MoviesInterface {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    lateinit var mainViewModel: MainViewModel

    private var recyclerView : RecyclerView? = null
    private var progress : ProgressBar? = null
    private var moviesAdapter : MoviesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(this, viewModelProviderFactory)[MainViewModel::class.java]
        setUpViews(view)
        setUpObserver()
    }

    private fun setUpViews(view: View) {
        recyclerView = view.findViewById(R.id.movies_rv)
        progress = view.findViewById(R.id.progress)
        setUpAdapter()
    }

    private fun setUpAdapter() {
        recyclerView?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            moviesAdapter = MoviesAdapter(this@MainFragment)
            adapter = moviesAdapter
        }
    }

    private fun setUpObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    mainViewModel.uiState.collect {
                        handleState(it)
                    }
                }
                mainViewModel.getMovies(1)
            }
        }
    }

    private fun handleState(it: MainUiState){
        loadingState(it.isLoading)
        handleError(it.userMessage)
        handleSuccess(it.items)
    }

    private fun handleSuccess(items: List<BaseDataModel>) {
        if(items.isNotEmpty())
            moviesAdapter?.submitList(items)
    }

    private fun handleError(it: Int?) {
        if(it != null) Toast.makeText(context, "Error ${it}", Toast.LENGTH_SHORT).show()
    }

    private fun loadingState(isLoading: Boolean) {
        if(isLoading){
            progress?.visibility = View.VISIBLE
        }else {
            progress?.visibility = View.GONE
        }
    }

    companion object {
        fun newInstance(bundle: Bundle) : MainFragment {
            return MainFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onStarClick(movie: Movie) {

    }
}