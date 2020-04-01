package com.example.createcontrolsdynamically.thelayoutmanager

import android.content.Context
import android.graphics.Color
import android.util.DisplayMetrics
import android.util.TypedValue
import android.widget.GridLayout
import android.widget.ScrollView
import com.example.createcontrolsdynamically.data.DataGridDesign
import com.example.createcontrolsdynamically.jsonhelper.JsonHelper
import com.example.createcontrolsdynamically.thecontrolshelper.GridLayoutHelper
import com.example.createcontrolsdynamically.thecontrolshelper.TextViewHelper
import com.google.gson.Gson
import kotlin.math.roundToInt


class TheLayoutManager {

    public companion object {

        var screenWidthPX: Int = 0
        var screenHeightPX: Int = 0

        lateinit var grdLytHelper: GridLayoutHelper

        fun convertPixelsToDp(context: Context,px: Float): Float {
            val metrics: DisplayMetrics = context?.resources?.displayMetrics!!
            val dp = px / (metrics.densityDpi / 160f)
            return dp.roundToInt().toFloat()
        }

        fun convertDpToPixel(context: Context, dp: Float): Float {
            val metrics: DisplayMetrics = context?.resources?.displayMetrics!!
            val px = dp * (metrics.densityDpi / 160f)
            return px.roundToInt().toFloat()
        }

        fun setupUIFromJSON(
            gridLyt: GridLayout,
            viewMain: ScrollView,
            theContext: Context?,
            bluprintFileName: String
        ) {

            var jsonResult =
                theContext?.let { JsonHelper.getJsonDataFromAsset(it, bluprintFileName) }

            val dataClassObject = Gson().fromJson(jsonResult, DataGridDesign::class.java)

            viewMain.setBackgroundColor(Color.parseColor(dataClassObject.background))

            grdLytHelper = GridLayoutHelper()
            grdLytHelper.prepareGridLayout(
                gridLyt,
                dataClassObject.rowCount,
                dataClassObject.columnCount,
                dataClassObject.background,
                theContext
            )
//
//            var colWidth = (screenWidthPX / gridLyt.columnCount) - (TheLayoutManager.convertDpToPixel(
//                theContext!!,
//                300f
//            ))
//            for (i in 0 until gridLyt.columnCount) {
//
//                grdLytHelper.addASpaceControl(
//                    theContext!!,
//                    0,
//                    i,
//                    gridLyt,
//                    convertDpToPixel(theContext!!, 2f).toInt(),
//                    colWidth.toInt()
//                )
//
//            }

            grdLytHelper.addRowsToGrid(gridLyt, dataClassObject, theContext)

        }
    }
}