package com.anbui.yum.data.converters

import androidx.room.TypeConverter
import java.time.LocalDateTime

class LocalDateTimeConverter {
    @TypeConverter
    fun toLocalDateTime(dateString: String): LocalDateTime {
        return LocalDateTime.parse(dateString)
    }

    @TypeConverter
    fun toDateString(date: LocalDateTime): String {
        return date.toString()
    }
}
