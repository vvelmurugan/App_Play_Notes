package com.example.app_play.data.source.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by vel-4009 on 2019-09-18.
 *
 *  Love your Job
 *
 */

class DbHelper internal constructor(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    companion object {
        internal var DATABASE_NAME = "zohoBpHub.db" //No I18N

        // If you change the database schema, you must increment the database version.
        internal var DATABASE_VERSION = 5
    }


    override fun onCreate(db: SQLiteDatabase) {

        for (createTable in CreateTable.ALL_TABLES) {
            db.execSQL(createTable)
        }

    }

    override fun onUpgrade(db: SQLiteDatabase,
                           old_version: Int,
                           new_version: Int) {

    }

    private interface CreateTable {
        companion object {

            val CREATE_NOTES_TABLE =   """CREATE TABLE ${NotesEntry.TABLE_NAME} (
                        ${NotesEntry.COLUMN_NOTE_ID} TEXT NOT NULL ,
                        ${NotesEntry.COLUMN_NOTE_TITLE} TEXT NOT NULL,
                        ${NotesEntry.COLUMN_NOTE_DESCRIPTION} TEXT NOT NULL,
                        PRIMARY KEY (${NotesEntry.COLUMN_NOTE_ID}) ON CONFLICT REPLACE )
                    """.trimIndent()

            val ALL_TABLES = arrayOf(CREATE_NOTES_TABLE)
        }
    }

}