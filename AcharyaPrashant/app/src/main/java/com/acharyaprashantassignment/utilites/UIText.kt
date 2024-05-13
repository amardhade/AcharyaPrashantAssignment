package com.acharyaprashantassignment.utilites

import android.content.Context
import androidx.annotation.StringRes

sealed class UIText {

    data class DynamicString(val str: String): UIText()

    data class StringResource(@StringRes val resId: Int, val arg: String = ""): UIText()

    fun asString(context: Context): String {
        return when(this) {
            is StringResource -> {
                context.getString(resId, arg)
            }
            is DynamicString -> str
        }
    }

}