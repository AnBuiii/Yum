package com.anbui.yum.common.alarm

import java.time.LocalDateTime

data class AlarmItem(
    val time: LocalDateTime,
    val message: String,
    val id: Int,
)
