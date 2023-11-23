package com.gsoft.nimblechalenge.data.datasource.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.*

class typeConverter {
    @TypeConverter
    fun listToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun listToJsonInt(value: List<Int>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToListInt(value: String) = Gson().fromJson(value, Array<Int>::class.java).toList()



    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}