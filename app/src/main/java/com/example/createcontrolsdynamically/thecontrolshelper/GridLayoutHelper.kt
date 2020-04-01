package com.example.createcontrolsdynamically.thecontrolshelper

import android.content.Context
import android.graphics.Color
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.Space
import com.example.createcontrolsdynamically.data.DataGridDesign
import com.example.createcontrolsdynamically.thelayoutmanager.TheLayoutManager


class GridLayoutHelper() {

    fun prepareGridLayout(
        grdLyt: GridLayout,
        rowCount: Int,
        colCount: Int,
        bgColor: String?,
        theContext: Context?
    ): GridLayout {

        grdLyt.rowCount = rowCount
        grdLyt.columnCount = colCount
        bgColor?.let {
            grdLyt.setBackgroundColor(Color.parseColor(it))
        }

        return grdLyt
    }

    fun addRowsToGrid(
        grid: GridLayout,
        fullDataClass: DataGridDesign,
        theContext: Context?,
        gridTopMargin: Int = 5
    ) {
        var columnWeights: List<String>? = null
        if (fullDataClass.columnWeights != null && fullDataClass.columnWeights.split(',')
                .count() == fullDataClass.columnCount
        ) {
            columnWeights = fullDataClass.columnWeights.split(',')
        }

        var rowslist = fullDataClass.rows
        val theRows = rowslist.sortedBy { it.rowNum }
        theRows.forEach()
        { itOuter ->
            //it.rowNum
            for (i in 0 until grid.columnCount) {

                var isMultipleControlsInAColumn: Boolean = false

                val foundControlAtColumn = itOuter.rowsControls.filter { it.columnNum == i }

                isMultipleControlsInAColumn = foundControlAtColumn.count() > 1
                var parentLinearLayout = LinearLayout(theContext)
                if (isMultipleControlsInAColumn) {
                    var it = foundControlAtColumn[0]
                    val layoutParams = GridLayout.LayoutParams()
                    layoutParams.rowSpec = GridLayout.spec(
                        itOuter.rowNum,
                        if (it.rowSpan == 0) 1 else it.rowSpan,
                        if (it.rowSpan == 0) 1f else it.rowSpan * 1f
                    )
                    layoutParams.columnSpec = GridLayout.spec(
                        it.columnNum,
                        if (it.columnSpan == 0) 1 else it.columnSpan,
                        if (it.columnSpan == 0) 1f else it.columnSpan * 1f
                    )

                    parentLinearLayout.layoutParams = layoutParams
                    parentLinearLayout.orientation = LinearLayout.HORIZONTAL
//                  llayout.setBackgroundColor(Color.YELLOW)
                }

                if (foundControlAtColumn.count() == 0) {
                    var weight: Float = 1f

                    if (columnWeights != null && columnWeights.count() == grid.columnCount)
                        weight = columnWeights[i].toFloat()


                    var colWidth =
                        if (grid.tag is GridLayout)
                            ((TheLayoutManager.screenWidthPX / (grid.tag as GridLayout).columnCount) * weight) - (TheLayoutManager.convertDpToPixel(
                                theContext!!,
                                25f
                            ))
                        else
                            ((TheLayoutManager.screenWidthPX / grid.columnCount) * weight) - (TheLayoutManager.convertDpToPixel(
                                theContext!!,
                                25f
                            ))
                    var colHeight = TheLayoutManager.convertDpToPixel(
                        theContext,
                        3f
                    )

                    var spaceControl = addASpaceControl(
                        theContext, itOuter.rowNum, i, colHeight.toInt(), colWidth.toInt()
                    )
                    grid.addView(
                        spaceControl
                    )
                } else {
                    foundControlAtColumn.forEach()
                    {
                        //A control was found. Adding Control

                        when (it.controlType.toLowerCase()) {
                            "textview" -> {
                                var colWidth: Float = 0f //= it.width.toFloat()
                                var minRowHeight = TheLayoutManager.convertDpToPixel(
                                    theContext!!,
                                    fullDataClass.minRowHeight.toFloat()
                                )
                                if (it.wrapText.toBoolean() && it.text.length > 20) {
                                    val span =
                                        if (it.columnSpan == null || it.columnSpan == 0) 1 else it.columnSpan

                                    colWidth =
                                        ((TheLayoutManager.screenWidthPX / grid.columnCount) - (TheLayoutManager.convertDpToPixel(
                                            theContext!!,
                                            10f
                                        ))) * span
                                }

                                val txtView =
                                    TextViewHelper().setUpControl(
                                        theContext!!,
                                        itOuter.rowNum,
                                        it,
                                        colWidth.toInt(),
                                        minRowHeight.toInt()
                                    )

                                if (isMultipleControlsInAColumn)
                                    parentLinearLayout.run {
                                        addView(
                                            txtView
                                        )
                                    }
                                else
                                    grid.run {
                                        addView(
                                            txtView
                                        )
                                    }
                            }

                            "edittext" -> {

                                val txtEdit =
                                    EditTextHelper().setUpControl(theContext!!, itOuter.rowNum, it)

                                if (it.width > 0) {
                                    var llayout = LinearLayout(theContext)
                                    val layoutParams = GridLayout.LayoutParams()
                                    layoutParams.rowSpec = GridLayout.spec(
                                        itOuter.rowNum,
                                        if (it.rowSpan == 0) 1 else it.rowSpan,
                                        1f
                                    )
                                    layoutParams.columnSpec = GridLayout.spec(
                                        it.columnNum,
                                        if (it.columnSpan == 0) 1 else it.columnSpan,
                                        1f
                                    )

                                    llayout.layoutParams = layoutParams
//                                    llayout.setBackgroundColor(Color.YELLOW)
                                    llayout.addView(txtEdit)

                                    if (isMultipleControlsInAColumn)
                                        parentLinearLayout.run {
                                            addView(
                                                llayout
                                            )
                                        }
                                    else
                                        grid.run {
                                            addView(
                                                llayout
                                            )
                                        }
                                } else {
                                    if (isMultipleControlsInAColumn)
                                        parentLinearLayout.run {
                                            addView(
                                                txtEdit
                                            )
                                        }
                                    else
                                        grid.run {
                                            addView(
                                                txtEdit
                                            )
                                        }
                                }
                            }

                            "gridlayout" -> {

                                var gridNext = GridLayout(theContext)
                                prepareGridLayout(
                                    gridNext,
                                    it.containerControls.rowCount,
                                    it.containerControls.columnCount,
                                    it.containerControls.background, theContext
                                )
                                addRowsToGrid(gridNext, it.containerControls, theContext, 3)

                                //region Layout Settings
                                val layoutParams = GridLayout.LayoutParams()
                                var scale: Float = theContext?.resources?.displayMetrics?.density!!
                                var sizeInDP = (gridTopMargin * scale + 0.5f).toInt()
                                layoutParams.setMargins(
                                    sizeInDP,
                                    sizeInDP,
                                    sizeInDP,
                                    sizeInDP
                                )
                                layoutParams.rowSpec = GridLayout.spec(
                                    itOuter.rowNum,
                                    if (it.rowSpan == 0) 1 else it.rowSpan,
                                    if (it.rowSpan == 0) 1f else it.rowSpan * 1f
                                )
                                layoutParams.columnSpec = GridLayout.spec(
                                    it.columnNum,
                                    if (it.columnSpan == 0) 1 else it.columnSpan,
                                    if (it.columnSpan == 0) 1f else it.columnSpan * 1f
                                )
                                layoutParams.height = GridLayout.LayoutParams.WRAP_CONTENT
                                //endregion
                                if (isMultipleControlsInAColumn)
                                    parentLinearLayout.run {
                                        addView(
                                            gridNext
                                        )
                                    }
                                else {
                                    gridNext.tag = grid
                                    grid.run {
                                        addView(
                                            gridNext, layoutParams
                                        )
                                    }
                                }
                            }

                            "emptyrow" -> {
                                var colWidth =
                                    (TheLayoutManager.screenWidthPX / grid.columnCount) - (TheLayoutManager.convertDpToPixel(
                                        theContext!!,
                                        5f
                                    ))
                                var colHeight = TheLayoutManager.convertDpToPixel(
                                    theContext!!,
                                    it.emptyRowHeight.toFloat()
                                )

                                var spaceControl = addASpaceControl(
                                    theContext!!,
                                    itOuter.rowNum,
                                    i,
                                    colHeight.toInt(),
                                    colWidth.toInt()
                                )
                                if (isMultipleControlsInAColumn)
                                    parentLinearLayout.run {
                                        addView(
                                            spaceControl
                                        )
                                    }
                                else
                                    grid.addView(
                                        spaceControl
                                    )
                            }
                        }

                    }


                    if (isMultipleControlsInAColumn) {
                        grid.addView(
                            parentLinearLayout
                        )
                    }
                }
            }
        }

        //If there are multiple grids placed para;;e;;y on same rows and one grid is larger than second one
        //The second grid expands in height and cause issue
        for (i in fullDataClass.rows.count() until fullDataClass.rowCount) {

            var colHeight = TheLayoutManager.convertDpToPixel(
                theContext!!,
                fullDataClass.minRowHeight.toFloat()
            )

            var spaceControl = addASpaceControl(
                theContext!!,
                i,
                0,
                colHeight.toInt(),
                0
            )
            grid.addView(
                spaceControl
            )
        }

    }

    public fun addASpaceControl(
        theContext: Context,
        rowNum: Int,
        colNum: Int,
        spaceHeight: Int = 1,
        spaceWidth: Int = 1
    ): Space {

        //No control was found. Adding Space

        return SpaceHelper().setUpControl(theContext, rowNum, colNum, spaceHeight, spaceWidth)
        //endregion

    }
}