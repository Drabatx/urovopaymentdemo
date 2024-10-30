package com.test.urovopaymentapp.presentation.view.activities

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.test.urovopaymentapp.presentation.navigation.SetupNavGraph
import com.test.urovopaymentapp.presentation.view.theme.UrovoPaymentAppTheme
import com.test.urovopaymentapp.utils.RuntimePermissionManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UrovoPaymentAppTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController)
            }
        }

        requestPermission()
    }

    private var mRuntimePermissionManager: RuntimePermissionManager? = null

    private fun requestPermission() {
        mRuntimePermissionManager = RuntimePermissionManager(this)
        var pres = arrayOf<String?>(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_SMS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE
        )
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            pres = arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_SMS,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.BLUETOOTH_SCAN,
                Manifest.permission.BLUETOOTH_ADVERTISE,
                Manifest.permission.BLUETOOTH_CONNECT
            )
        }
        executeRequestPermissionTask(pres, object : RuntimePermissionManager.RequestPermissionCallback {

            override fun onCallback(
                permisssions: Array<String?>?,
                grantResults: IntArray?,
                allGranted: Boolean
            ) {
                Log.e(TAG, "allGranted:$allGranted")
                if (!allGranted) {
//                    showMessage("Permission denied")
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    if (!Environment.isExternalStorageManager()) {
                        val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                        intent.setData(Uri.parse("package:$packageName"))
                        startActivityForResult(intent, 1024)
                    }
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1024 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 检查是否有权限
            if (Environment.isExternalStorageManager()) {
                // 授权成功
            } else {
                // 授权失败
            }
        }
    }

    /**
     * 申请一组权限，无显示说明提醒
     * 检查权限->请求权限->回调
     *
     * @param permissions 所需权限 android.Manifest.permission.
     * @param callback 需要权限的回调
     */
    fun executeRequestPermissionTask(
        permissions: Array<String?>?,
        callback: RuntimePermissionManager.RequestPermissionCallback?
    ) {
        if (callback != null) {
            mRuntimePermissionManager?.executeRequestPermissionTask(permissions, null, null, callback)
        }
    }

    /**
     * 权限申请结果转交给runtimePermissionManager处理，最后的结果在调用executeRequestPermissionTask()的任务中返回.
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val nullablePermissions: Array<String?>? = arrayOfNulls<String?>(permissions.size)
        for (i in permissions.indices) {
            nullablePermissions?.set(i, permissions[i]) // Se convierte automáticamente a String?
        }
        mRuntimePermissionManager?.handleReuqestPermissionResult(
            requestCode,
            nullablePermissions,
            grantResults
        )
    }

}
