package com.test

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

object PermissionHandler {

    fun isPermissionnabled(permission: String?,context: Context): Boolean{
        if (permission?.let { ContextCompat.checkSelfPermission(context, it) } == PackageManager.PERMISSION_GRANTED)
            return true
        return false
    }
}