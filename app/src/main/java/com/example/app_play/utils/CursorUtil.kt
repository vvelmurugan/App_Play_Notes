package com.example.app_play.utils

import android.database.Cursor

/**
 * Created by vel-4009 on 2019-09-30.
 *
 *  Love your Job
 *
 */


fun Cursor.getString(columnName: String) = getString(getColumnIndex(columnName))
fun Cursor.getInt(columnName: String): Int = getInt(getColumnIndex(columnName))
fun Cursor.getLong(columnName: String): Long = getLong(getColumnIndex(columnName))
