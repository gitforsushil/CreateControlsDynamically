package com.example.createcontrolsdynamically.thecontrolshelper

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.widget.GridLayout
import android.widget.TextView
import com.example.createcontrolsdynamically.R
import com.example.createcontrolsdynamically.data.RowsControl
import com.example.createcontrolsdynamically.thelayoutmanager.TheLayoutManager

class TextViewHelper {

    @SuppressLint("ResourceType")
    fun setUpControl(theContext: Context, rowNum: Int, it: RowsControl?, colWidth: Int, colHeight: Int): TextView {
        var txtView = TextView(theContext)
        txtView.id = 101

        txtView.text = it?.text

//        var scale: Float = theContext.resources?.displayMetrics?.density!!
        txtView.setTextAppearance(R.style.TextViewTextStyle)

        if (it?.textBackgroundColor != null) txtView.setBackgroundColor(
            Color.parseColor(
                it.textBackgroundColor
            )
        )

        if (it?.textColor != null) txtView.setTextColor(
            Color.parseColor(
                it.textColor
            )
        )

        if (it?.textAlignment != null)
            when (it.textAlignment.toLowerCase()) {
                "center" -> txtView.gravity = Gravity.CENTER
                "left" -> txtView.gravity = Gravity.LEFT
                "right" -> txtView.gravity = Gravity.RIGHT
            }
        else
            txtView.gravity = Gravity.CENTER_VERTICAL

        if (it?.textTypeface != null) {
            when (it.textTypeface.toLowerCase()) {
                "bold" -> txtView.setTypeface(null, Typeface.BOLD)
                "italic" -> txtView.setTypeface(null, Typeface.ITALIC)
                "bold|italic" -> txtView.setTypeface(null, Typeface.BOLD_ITALIC)
                "italic|bold" -> txtView.setTypeface(null, Typeface.BOLD_ITALIC)
            }
        }

        if (it?.textSize != null && it.textSize != 0) {
            txtView.textSize =TheLayoutManager.convertDpToPixel(theContext,it.textSize.toFloat())
        }

        //region Uncomment this In Case you want to view colored column
//        when (it!!.columnNum) {
//            0 -> txtView.setBackgroundColor(
//                Color.parseColor(
//                    "#0F7D0F"
//                )
//            )
//            1 -> txtView.setBackgroundColor(
//                Color.parseColor(
//                    "#2B6666"
//                )
//            )
//            2 -> txtView.setBackgroundColor(
//                Color.parseColor(
//                    "#6002EE"
//                )
//            )
//            3 -> txtView.setBackgroundColor(
//                Color.parseColor(
//                    "#827F87"
//                )
//            )
//            4 -> txtView.setBackgroundColor(
//                Color.parseColor(
//                    "#8ED384"
//                )
//            )
//            5 -> txtView.setBackgroundColor(
//                Color.parseColor(
//                    "#37F61C"
//                )
//            )
//        }
        //endregion

        //region Layout Settings
        val layoutParams = GridLayout.LayoutParams()
        var sizeInDP = TheLayoutManager.convertDpToPixel(theContext,3f).toInt()
        layoutParams.setMargins(
            sizeInDP,
            sizeInDP,
            0,
            0
        )
        layoutParams.rowSpec = GridLayout.spec(
            rowNum,
            if (it!!.rowSpan == 0) 1 else it.rowSpan,
            if (it.rowSpan == 0) 1f else it.rowSpan * 1f
        )
        layoutParams.columnSpec = GridLayout.spec(
            it.columnNum,
            if (it.columnSpan == 0) 1 else it.columnSpan,
            if (it.columnSpan == 0) 1f else it.columnSpan * 1f
        )
        //endregion
        layoutParams.setGravity(Gravity.CENTER_VERTICAL)
        if (colWidth > 0) layoutParams.width = colWidth
        if (colHeight > 0) txtView.minHeight = colHeight
//        layoutParams.height = GridLayout.LayoutParams.WRAP_CONTENT
        txtView.layoutParams = layoutParams

        return txtView
    }

}