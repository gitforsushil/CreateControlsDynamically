package com.example.createcontrolsdynamically.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.createcontrolsdynamically.R
import kotlinx.android.synthetic.main.fragment_slideshow.view.*


class SlideshowFragment : Fragment() {

    private lateinit var txtNew: TextView
    lateinit var btnChange: Button

    private lateinit var slideshowViewModel: SlideshowViewModel

    private lateinit var constraintSet: ConstraintSet

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)

        createAndAddControls(root.fragmentMainLayout)

        slideshowViewModel =
            ViewModelProviders.of(this).get(SlideshowViewModel::class.java)
        slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
            txtNew?.text = it
        })
        return root
    }

    private fun createAndAddControls(theview: View) {

        //txtNew.getLayoutParams().width = 200 params . leftMargin = 100 params.topMargin = 200

        txtNew = TextView(this.context)
        txtNew.id = 1001
        txtNew.text = "Default Text"
        theview.fragmentMainLayout.addView(txtNew)

        btnChange = Button(this.context)
        btnChange.id = 1002
        btnChange.text = "Change The Text Bro!"
        btnChange.setOnClickListener { slideshowViewModel.updateText() }
        theview.fragmentMainLayout.addView(btnChange)

        constraintSet = ConstraintSet()
        constraintSet.clone(theview.fragmentMainLayout)
        constraintSet.connect(btnChange.id, ConstraintSet.TOP, txtNew.id, ConstraintSet.BOTTOM, 18);
        constraintSet.applyTo(theview.fragmentMainLayout);

    }
}
