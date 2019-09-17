package com.example.app_play.data.source.local

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.SQLException
import android.net.Uri

/**
 * Created by vel-4009 on 2019-09-17.
 *
 *  Love your Job
 *
 */

class AppContentProvider : ContentProvider()
{

    private var dbHelper: DbHelper? = null

    private val NOTE = 1

    // The URI Matcher used by this content provider.
    private val sUriMatcher = object : LazyUriMatcher<UriMatcher>()
    {
        override fun initialValue(): UriMatcher
        {
            return buildUriMatcher()
        }
    }

    private fun getTableName(uri: Uri): String?
    {
        when (sUriMatcher.get().match(uri))
        {
            NOTE -> return NotesEntry.TABLE_NAME

            else ->
                //ZPUtil.showLog("Unknown URI " + uri); //No I18N
                return null
        }
    }

    private fun buildUriMatcher(): UriMatcher
    {
        // All paths added to the UriMatcher have a corresponding code to return when a match is
        // found.  The code passed into the constructor represents the code to return for the root
        // URI.  It's common to use NO_MATCH as the code for this case.
        val matcher = UriMatcher(UriMatcher.NO_MATCH)

        // For each type of URI you want to add, create a corresponding code.
        matcher.addURI(CONTENT_AUTHORITY, PATH_NOTES, NOTE)


        return matcher
    }


    override fun onCreate(): Boolean {
        dbHelper = DbHelper(context!!)
        return true
    }


    override fun getType(uri: Uri): String
    {
        // getType implementation is not necessary.
        // But its a nice way to review what URIs we are planning to handle.
        // The key information this conveys is whether the content uri will be returning a
        // database cursor containing a single record type item, or multiple records type directory.
        // ContentProviders can also be used to return other kinds of data than just database cursors.
        // For e.g., if we wanted the ContentProvider to return JPEG images for a content uri, we
        // would have this function return the standard mime type, image/jpeg.

        // Use the Uri Matcher to determine what kind of URI this is.
        val match = sUriMatcher.get().match(uri)

        when (match)
        {
            NOTE -> return NotesEntry.CONTENT_TYPE

            else ->
            {
//                DebugUtil.throwException(UnsupportedOperationException("Unknown uri: $uri")) //No I18N
                return ""
            }
        }
    }

    override fun insert(uri: Uri,
                        initialValues: ContentValues?): Uri? {
        val values: ContentValues
        if (initialValues != null)
        {
            values = ContentValues(initialValues)
        }
        else
        {
            values = ContentValues()
        }

        val table = getTableName(uri)


        val db = dbHelper?.writableDatabase

        val rowId = db?.insert(table, null, values) ?: 0
        if (rowId > 0)
        {
            val returnUri = ContentUris.withAppendedId(uri, rowId)
            context?.contentResolver?.notifyChange(returnUri, null)
            return returnUri
        }

        throw SQLException("Failed to insert row into $uri") //No I18N
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String? ,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {

        val tableName = getTableName(uri)
        val db = dbHelper?.readableDatabase
        var returnCursor: Cursor?

        val match = sUriMatcher.get().match(uri)

        when(match)
        {
            NOTE -> returnCursor = db?.query(tableName, projection, selection, selectionArgs, null, null, sortOrder)
            else -> returnCursor = db?.query(tableName, projection, selection, selectionArgs, null, null, sortOrder)
        }

        // This causes the cursor to register a content observer, to watch for changes
        // that happen to that URI and any of its descendants.
        returnCursor?.setNotificationUri(context?.contentResolver, uri)

        return returnCursor!!
    }

    override fun update(uri: Uri,
                        values: ContentValues?,
                        selection: String?,
                        selectionArgs: Array<out String>?): Int {
        val table = getTableName(uri)
        val db = dbHelper?.writableDatabase

        val count = db?.update(table, values, selection, selectionArgs) ?: 0

        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun delete(uri: Uri,
                        selection: String?,
                        selectionArgs: Array<out String>?): Int
    {
        val table = getTableName(uri)
        val db = dbHelper?.writableDatabase

        val count = db?.delete(table, selection, selectionArgs) ?: 0

        context?.contentResolver?.notifyChange(uri, null)
        return count
    }
}