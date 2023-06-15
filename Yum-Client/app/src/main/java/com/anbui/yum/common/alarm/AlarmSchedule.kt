package com.anbui.yum.common.alarm

interface AlarmScheduler {
    fun schedule(item: AlarmItem)
    fun cancel(item: AlarmItem)
}
