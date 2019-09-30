package com.example.app_play.data.source.local

/**
 * Created by vel-4009 on 2019-09-30.
 *
 *  Love your Job
 *
 */

//$Id$


import androidx.annotation.IntDef

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Created by vel-4009 on 03/03/17.
 *
 * Love your Job :)
 *
 */

class ErrorMessage(@ErrorTypes type: Int) : Exception() {

    var type = OTHER // default

    private var mErrorCode = -1

    private var mErrorMessage = ""


    @IntDef(
        NO_NETWORK,
        NO_LOCAL_DATA,
        NO_SERVER_DATA,
        SERVER,
        CLIENT,
        INVALID_SCOPES,
        INVALID_ACCESS_TOKEN,
        TIMEOUT,
        PARSER_EXCEPTION,
        NO_PERMISSION,
        OTHER
    )
    @Retention(RetentionPolicy.SOURCE)
    annotation class ErrorTypes

    override fun toString(): String {
        var errorType = "AppError: "        //No I18N

        when (type) {
            NO_NETWORK -> errorType += "NO_NETWORK"      //No I18N

            TIMEOUT -> errorType += "TIMEOUT"         //No I18N

            PARSER_EXCEPTION -> errorType += "PARSE EXCEPTION"           //No I18N

            INVALID_ACCESS_TOKEN -> errorType += "INVALID_ACCESS_TOKEN"      //No I18N

            INVALID_SCOPES -> errorType += "INVALID_SCOPES"          //No I18N

            SERVER -> errorType += "SERVER"                  //No I18N

            CLIENT -> errorType += "CLIENT"                  //No I18N

            NO_LOCAL_DATA -> errorType += "NO_LOCAL_DATA"           //No I18N

            NO_SERVER_DATA -> errorType += "NO_SERVER_DATA"          //No I18N

            else -> errorType += "OTHER"                   //No I18N
        }

        return errorType
    }

    init {
        this.type = type
    }

    fun setErrorCode(errorCode: Int): ErrorMessage {
        mErrorCode = errorCode
        return this
    }

    fun setErrorMessage(errorMessage: String): ErrorMessage {
        mErrorMessage = errorMessage
        return this
    }

    fun getErrorCode(): Int {
        return mErrorCode
    }

    fun getErrorMessage(): String {
        return mErrorMessage
    }

    companion object {
        const val NO_NETWORK = 1
        const val TIMEOUT = 2
        const val PARSER_EXCEPTION = 3
        const val INVALID_ACCESS_TOKEN = 4
        const val INVALID_SCOPES = 5
        const val SERVER = 6
        const val CLIENT = 7
        const val NO_LOCAL_DATA = 8
        const val NO_SERVER_DATA = 9
        const val NO_PERMISSION = 10
        const val OTHER = 100
    }
}
