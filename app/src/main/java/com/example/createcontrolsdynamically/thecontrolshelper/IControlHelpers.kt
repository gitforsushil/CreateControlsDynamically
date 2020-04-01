package com.example.createcontrolsdynamically.thecontrolshelper

import android.content.Context
import com.example.createcontrolsdynamically.data.RowsControl

interface IControlHelpers<T> {
    fun setUpControl(theContext: Context, rowNum: Int, it: RowsControl?) : T
}