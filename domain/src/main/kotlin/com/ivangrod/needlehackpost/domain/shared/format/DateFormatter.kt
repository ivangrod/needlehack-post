package com.ivangrod.needlehackpost.domain.shared.format

import java.time.LocalDateTime

import java.time.format.DateTimeFormatter


internal object DateFormatter {

    private val TIME_FORMATTER = DateTimeFormatter
        .ofPattern("yyyy-MM-dd HH:mm")

    fun dateToString(dateTime: LocalDateTime?): String {
        return dateTime?.format(TIME_FORMATTER) ?: ""
    }

    fun stringToDate(dateTimeInString: String?): LocalDateTime {
        return LocalDateTime.parse(dateTimeInString, TIME_FORMATTER)
    }
}
