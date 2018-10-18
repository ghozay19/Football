package com.ghozy19.footballapps.utils

import android.view.View
import java.text.SimpleDateFormat
import java.util.*


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}



fun strToDate(strDate: String?, pattern: String = "yyyy-MM-dd"):Date{
    val format = SimpleDateFormat(pattern)
    val date = format.parse(strDate)

    return date
}

fun toGMTFormat(date: String?, time: String?): Date? {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    val dateTime = "$date $time"

    return formatter.parse(dateTime)
}

var locale = Locale("ID")
fun changeFormatDate(date: Date?): String? = with(date ?: Date()){
    SimpleDateFormat("EEEE, dd MMMM yyyy", locale).format(this)
}



