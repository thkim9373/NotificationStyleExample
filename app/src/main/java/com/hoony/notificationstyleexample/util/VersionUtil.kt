package com.hoony.notificationstyleexample.util

import android.os.Build

class VersionUtil {
    companion object {
        fun isMoreThanN(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
        fun isLessThanN(): Boolean = Build.VERSION.SDK_INT < Build.VERSION_CODES.N
        fun isMoreThanO(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
    }
}