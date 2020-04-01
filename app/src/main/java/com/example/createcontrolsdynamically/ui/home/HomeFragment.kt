package com.example.createcontrolsdynamically.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.createcontrolsdynamically.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })


        root.btnOpenOtherApp.setOnClickListener()
        {
            val pm = this.activity?.applicationContext?.packageManager
            val intent: Intent? = pm?.getLaunchIntentForPackage("com.example.myfirstkotlin")
            intent?.addCategory(Intent.CATEGORY_LAUNCHER)
            this.activity?.applicationContext?.startActivity(intent)
        }

        root.btnChangeHeight.setOnClickListener()
        {
            val lparam = ConstraintLayout.LayoutParams(
                getPixelValue(context!!, txtWidth.text.toString().toInt()),
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            lparam.setMargins(0,getPixelValue(context!!,24),0,0)

            text_home.layoutParams = lparam
        }

        return root
    }

    fun getPixelValue(context: Context, dimenId: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dimenId.toFloat(),
            context?.resources?.displayMetrics
        ).toInt()
    }
}
