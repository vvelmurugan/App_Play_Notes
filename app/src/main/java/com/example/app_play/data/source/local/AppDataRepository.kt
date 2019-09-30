package com.example.app_play.data.source.local

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import com.example.app_play.notes.Note
import com.example.app_play.utils.getString

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


    override fun getNotes(callback: AppDataSource.DataLayerCallback<List<Note>>) {
        val cursor = mContentResolver.query(NotesEntry.CONTENT_URI,null,null,null,null)
        val notesList = mutableListOf<Note>()

        cursor?.use {
            cursor.apply {
                while(moveToNext())
                {
                    val noteId = getString(NotesEntry.COLUMN_NOTE_ID)
                    val title = getString(NotesEntry.COLUMN_NOTE_TITLE)
                    val description = getString(NotesEntry.COLUMN_NOTE_DESCRIPTION)

                     notesList.add(Note(noteId, title, description))
                }
            }
        }

        if(notesList.isNotEmpty())
        {
            callback.onSuccess(notesList)
        }
        else{
            callback.onError(ErrorMessage(ErrorMessage.NO_LOCAL_DATA))
        }
    }
}