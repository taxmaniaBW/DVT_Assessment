package com.utick.dvtcodingassessment.ui.view

import android.content.Context
import com.utick.dvtcodingassessment.R
import com.utick.dvtcodingassessment.util.Failure

open class BaseView {

    fun getFailureMessage(failure: Failure, context: Context): String {
       return when(failure) {
            is Failure.ServerError -> context.getString(R.string.server_error_message)
            is Failure.NetworkConnection -> context.getString(R.string.network_error_message)
            is Failure.None -> ""
        }
    }
}