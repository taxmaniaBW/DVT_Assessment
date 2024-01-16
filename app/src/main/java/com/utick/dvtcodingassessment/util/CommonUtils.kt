package com.utick.dvtcodingassessment.util

import java.text.SimpleDateFormat
import java.util.Locale

fun getDayOfWeek(timestamp: Long): String {
    return SimpleDateFormat("EEEE", Locale.ENGLISH).format(timestamp * 1000)}

fun getTimeOfDay(timestamp: Long): String {
    return SimpleDateFormat("HH:mm", Locale.ENGLISH).format(timestamp * 1000)}


