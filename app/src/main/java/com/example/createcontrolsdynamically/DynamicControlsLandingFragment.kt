package com.example.createcontrolsdynamically

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.createcontrolsdynamically.ui.dynamiccontrolsdemo.DynamicControlsDemoViewModel
import kotlinx.android.synthetic.main.fragment_dynamic_controls_landing.*

class DynamicControlsLandingFragment : Fragment() {

    private lateinit var viewModel: DynamicControlsDemoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dynamic_controls_landing, container, false)
        root.requestLayout()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btnStaticFragment.setOnClickListener {
            findNavController().navigate(R.id.action_nav_dynamiccontrols_to_dynamicControlsDemo)
        }


        btnDynamic.setOnClickListener {
            var bndl = Bundle()
            bndl.putString("FileName", "CrudeDemoScreenBlueprint.json")
            findNavController().navigate(
                R.id.action_nav_dynamiccontrols_to_runtimeDynamicControl,
                bndl
            )
        }

        btnDynamicTanker.setOnClickListener {
            var bndl = Bundle()
            bndl.putString("FileName", "TankerDemoScreenBlueprint.json")
            findNavController().navigate(
                R.id.action_nav_dynamiccontrols_to_runtimeDynamicControl,
                bndl
            )
        }


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
