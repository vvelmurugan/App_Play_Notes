package com.example.app_play.data.source.local

import android.content.ContentResolver
import android.content.ContentUris
import android.net.Uri
import com.example.app_play.BuildConfig

/**
 * Created by vel-4009 on 2019-09-18.
 *
 *  Love your Job
 *
 */



// Name for the entire content provider. package name is used, as it will be unique in the device.
// This says what ContentProvider, the ContentResolver is supposed to be using.
// This must be unique to locate the ContentProvider.
const val CONTENT_AUTHORITY = BuildConfig.APPLICATION_ID // for build types

// Base of all URI's which apps will use to contact the content provider.
// scheme [the part precedes the colon] "content" says this is a Content URI (Uniform Resource Identifier).
// It identifies the protocol that the URI will be using, like http/ https/ ftp.
// scheme "content" refers to ContentProvider.
val BASE_CONTENT_URI = Uri.parse("content://$CONTENT_AUTHORITY")  //No I18N

// Possible paths.
// For instance, content://com.example.app_play/notes is a valid path for looking at notes data.
const val PATH_NOTES = "note"                     //No I18N


object NotesEntry
{
    // content://com.example.app_play/notes
    @JvmStatic
    val CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_NOTES).build()

    /**
     * The MIME-type of [.CONTENT_URI] providing a directory of
     * contact directories.
     */
    @JvmStatic
    val CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NOTES //No I18N

    /**
     * The MIME type of a [.CONTENT_URI] item.
     */
    @JvmStatic
    val CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NOTES//No I18N

    const val TABLE_NAME = "notes"                           //No I18N

    const val COLUMN_NOTE_ID = "note_id"                  //No I18N
    const val COLUMN_NOTE_TITLE = "note_title"              //No I18N
    const val COLUMN_NOTE_DESCRIPTION = "note_description"                    //No I18N


    // methods to help build the Content Provider queries.
    @JvmStatic
    fun buildNotesUri(id: Long): Uri
    {
        return ContentUris.withAppendedId(CONTENT_URI, id)
    }
}

