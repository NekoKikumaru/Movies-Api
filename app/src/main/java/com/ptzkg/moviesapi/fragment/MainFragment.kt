package com.ptzkg.moviesapi.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.ptzkg.moviesapi.R
import com.ptzkg.moviesapi.adapter.MovieAdapter
import com.ptzkg.moviesapi.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root = inflater.inflate(R.layout.fragment_main, container, false)
        Log.d("Fragment >>>> ", "onCreateView")
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("Fragment >>>> ", "onViewCreated")
        viewManager = GridLayoutManager(activity, 2)
        movieAdapter = MovieAdapter()
        recyclerMain.adapter = movieAdapter
        recyclerMain.layoutManager = viewManager
        observeViewModel()

    }

    override fun onResume() {
        super.onResume()
        Log.d("Fragment >>>> ", "onResume")
        viewModel.loadResults()
    }

    fun observeViewModel() {
        Log.d("Fragment >>>> ", "observiewModel -> before")
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        Log.d("Fragment >>>> ", "observiewModel -> after")
        viewModel.getResults().observe(viewLifecycleOwner, Observer { result ->
            movieAdapter.updateList(result)
            Log.d("result >>>> ", result.toString())
        })
    }
}
