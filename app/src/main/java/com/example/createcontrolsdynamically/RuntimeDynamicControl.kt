package com.example.createcontrolsdynamically

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import com.example.createcontrolsdynamically.thelayoutmanager.TheLayoutManager
import kotlinx.android.synthetic.main.runtime_dynamic_control_fragment.*

class RuntimeDynamicControl : Fragment() {

    private lateinit var mainGrid: GridLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.runtime_dynamic_control_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var fileName = arguments?.getString("FileName")

        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        TheLayoutManager.screenHeightPX = displayMetrics.heightPixels
        TheLayoutManager.screenWidthPX = displayMetrics.widthPixels

        TheLayoutManager.setupUIFromJSON(theMainGrid, theOuterScrollView, context, fileName!!)

    }
}

