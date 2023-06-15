package com.anbui.yum.common.ext

import android.text.format.DateUtils
import java.text.ParseException
import java.time.LocalDateTime

fun Long.timeAgo(): String {
    return try {
        val now = System.currentTimeMillis()
        val ago = DateUtils.getRelativeTimeSpanString(
            this,
            now,
            DateUtils.MINUTE_IN_MILLIS,
        )
        ago.toString()


    } catch (e: ParseException) {
        e.printStackTrace()
        "fail"
    }
}

