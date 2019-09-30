package com.example.app_play.data.source.local

import com.example.app_play.notes.Note

/**
 * Created by vel-4009 on 2019-09-30.
 *
 *  Love your Job
 *
 */

interface AppDataSource
{
    interface DataLayerCallback<T>
    {
        fun onSuccess(response: T)
        fun onError(errorMessage: ErrorMessage)
    }

    fun addNote(title: String, description: String)
    fun getNotes(callback: DataLayerCallback<List<Note>>)
}