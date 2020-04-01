package com.example.createcontrolsdynamically.ui.gridlayoutstaticdemo

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.createcontrolsdynamically.R

class GridLayoutStaticDemo : Fragment() {

    companion object {
        fun newInstance() = GridLayoutStaticDemo()
    }

    private lateinit var viewModel: GridLayoutStaticDemoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.grid_layout_static_demo_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GridLayoutStaticDemoViewModel::class.java)

    }

}
