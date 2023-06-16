package com.anbui.yum

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.core.app.ActivityCompat
import com.anbui.yum.ui.theme.YumTheme


@ExperimentalFoundationApi
@ExperimentalAnimationApi
class YumActivity : ComponentActivity() {

    val PERMISSION_REQUEST_CODE = 112
    override fun onCreate(savedInstanceState: Bundle?) {
//        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContent {
            YumTheme {
                YumApp()
            }
        }

        if (!shouldShowRequestPermissionRationale("112")){
            getNotificationPermission();
        }
    }

    private fun getNotificationPermission() {
        try {
            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(Manifest.permission.POST_NOTIFICATIONS),
                PERMISSION_REQUEST_CODE,
            )
        } catch (e: Exception) {
            Log.d("PERMISSION",e.toString())
        }
    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String?>,
//        grantResults: IntArray,
//    ) {
//        super.onRequestPermissionsResult(
//            requestCode,
//            permissions,
//            grantResults,
//        )
//        when (requestCode) {
//            PERMISSION_REQUEST_CODE -> {
//                return
//            }
//        }
//    }
}

