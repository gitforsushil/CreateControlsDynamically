package com.example.createcontrolsdynamically.data

data class DataGridDesign(
    val background: String,
    val columnCount: Int,
    val columnWeights: String,
    val name: String,
    val rowCount: Int,
    val minRowHeight: Int,
    val rows: List<Row>
)

data class Row(
    val rowNum: Int,
    val rowsControls: List<RowsControl>
)

data class RowsControl(
    val columnNum: Int,
    val columnSpan: Int,
    val controlType: String,
    val minWidth: Int,
    val name: String,
    val rowSpan: Int,
    val text: String,
    val textHint: String,
    val textAlignment: String,
    val textBackgroundColor: String,
    val textColor: String,
    val textTypeface: String,
    val textSize: Int,
    val emptyRowHeight: Int,
    val containerControls: DataGridDesign,
    val width: Int,
    val wrapText: String
)
