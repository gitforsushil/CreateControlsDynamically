package com.example.createcontrolsdynamically.ui.dynamiccontrolsdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.createcontrolsdynamically.R
import kotlinx.android.synthetic.main.fragment_dynamic_controls_landing.*
import kotlinx.android.synthetic.main.fragment_dynamic_controls_landing.view.*
import kotlinx.android.synthetic.main.fragment_dynamic_controls_landing.view.btnStaticFragment


class DynamicControlsDemo : Fragment() {


    private lateinit var viewModel: DynamicControlsDemoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.dynamic_controls_demo_fragment, container, false)
        root.requestLayout()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DynamicControlsDemoViewModel::class.java)

    }

    override fun onResume() {
        super.onResume()
        this.view?.requestLayout()
    }

}
