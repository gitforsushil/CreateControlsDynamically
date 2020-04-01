package com.example.createcontrolsdynamically.thecontrolshelper

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.text.InputType
import android.widget.EditText
import android.widget.GridLayout
import com.example.createcontrolsdynamically.R
import com.example.createcontrolsdynamically.data.RowsControl
import com.example.createcontrolsdynamically.thelayoutmanager.TheLayoutManager

class EditTextHelper {

    @SuppressLint("ResourceType")
    fun setUpControl(theContext: Context, rowNum: Int, it: RowsControl?): EditText {
        var txtEditView = EditText(theContext)
        txtEditView.id = 101

        txtEditView.setText(it?.text)
        txtEditView.hint = it?.textHint
        txtEditView.setHintTextColor(Color.DKGRAY)
        txtEditView.inputType = InputType.TYPE_CLASS_TEXT

        txtEditView.setBackgroundResource(R.drawable.crude_editbox_selector)


        //region Layout Settings
        val layoutParams = GridLayout.LayoutParams()

        var sizeInDP = TheLayoutManager.convertDpToPixel(theContext, 5f).toInt()
        layoutParams.setMargins(
            sizeInDP,
            sizeInDP,
            sizeInDP,
            sizeInDP
        )

        if (it!!.width <= 0) {
            layoutParams.rowSpec = GridLayout.spec(
                rowNum,
                if (it.rowSpan == 0) 1 else it.rowSpan,
                1f
            )
            layoutParams.columnSpec = GridLayout.spec(
                it.columnNum,
                if (it.columnSpan == 0) 1 else it.columnSpan,
                1f
            )

            txtEditView.minWidth =
                TheLayoutManager.convertDpToPixel(theContext, it?.minWidth?.toFloat()!!).toInt()

        } else {
            layoutParams.width =
                TheLayoutManager.convertDpToPixel(theContext, it?.width?.toFloat()!!).toInt()

        }
        //endregion
        txtEditView.layoutParams = layoutParams
        txtEditView.setPadding(sizeInDP, 0, 0, 0)
        return txtEditView
    }

}