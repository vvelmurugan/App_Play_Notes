package com.example.app_play.data.source.local

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context

/**
 * Created by vel-4009 on 2019-09-30.
 *
 *  Love your Job
 *
 */


class AppDataRepository private constructor(val context: Context): AppDataSource
{
    companion object
    {
        @JvmStatic
        private var sInstance: AppDataSource? = null

        @JvmStatic
        fun getInstance(context: Context): AppDataSource
        {
            if (sInstance == null)
            {
                sInstance = AppDataRepository(context.applicationContext)
            }
            return sInstance!!
        }
    }

    private val mContentResolver: ContentResolver = context.contentResolver

    override fun addNote(title: String, description: String) {
        val contentValues = ContentValues()

        contentValues.put(NotesEntry.COLUMN_NOTE_TITLE, title)
        contentValues.put(NotesEntry.COLUMN_NOTE_DESCRIPTION, description)
        contentValues.put(NotesEntry.COLUMN_NOTE_ID, System.currentTimeMillis().toString())

        mContentResolver.insert(NotesEntry.CONTENT_URI, contentValues)
    }
}