package com.example.createcontrolsdynamically.thecontrolshelper

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.GridLayout
import android.widget.Space
import com.example.createcontrolsdynamically.data.RowsControl

class SpaceHelper : IControlHelpers<Space> {

    fun setUpControl(theContext: Context, rowNum: Int, colNum: Int, rHeight: Int = 1,rWidth: Int = 1): Space {
        var spc = Space(theContext)

        //region Layout Settings
        val layoutParams = GridLayout.LayoutParams()
        layoutParams.height = if(rHeight == 0) 5 else rHeight
        layoutParams.width = rWidth

        layoutParams.rowSpec = GridLayout.spec(
            rowNum, GridLayout.FILL,
            1f
        )
        layoutParams.columnSpec = GridLayout.spec(
            colNum, GridLayout.FILL,
            1f
        )
        spc.layoutParams = layoutParams
        if(rowNum%2 ==0)
            spc.setBackgroundColor(Color.BLUE)
        else
            spc.setBackgroundColor(Color.YELLOW)


        return spc
    }

    override fun setUpControl(theContext: Context, rowNum: Int, it: RowsControl?): Space {
        return Space(theContext)
    }
}