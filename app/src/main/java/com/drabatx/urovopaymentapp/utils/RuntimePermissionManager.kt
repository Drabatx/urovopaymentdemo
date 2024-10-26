package com.drabatx.urovopaymentapp.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Process
import android.text.TextUtils
import android.util.SparseArray
import androidx.core.app.ActivityCompat
import java.lang.ref.WeakReference
import java.util.concurrent.atomic.AtomicInteger
import javax.security.auth.DestroyFailedException
import javax.security.auth.Destroyable

/**
 * Created by zje on 2018/2/2.
 */
class RuntimePermissionManager(activity: Activity?) : Destroyable {
    //为防止内存溢出，将寄主置为弱引用
    protected var activity: WeakReference<Activity>

    // 原子整型，读个数， AtomicInteger可以在并发情况下达到原子化更新，避免使用了synchronized。
    private val mPermissionRequestCode = AtomicInteger(0)

    //权限任务池
    private val mCallbackPool: SparseArray<RequestPermissionCallback>? = SparseArray()

    init {
        if (activity == null) {
            throw RuntimeException("initialize RuntimePermissionManager activity is null")
        }
        this.activity = WeakReference(activity)
    }

    fun executeRequestPermissionTask(
        permissions: Array<String?>?,
        attentiveTitle: String?,
        attentiveContent: String?,
        callback: RequestPermissionCallback
    ) {
        val activity = activity.get()
        if (activity == null) {
            onDestroy()
            return
        }
        if (permissions.isNullOrEmpty()) {
            throw RuntimeException("permissions is null")
        }
        //判断全部权限是否开启
        var allGranted = true
        for (permission in permissions) {
            if (checkPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                allGranted = false
                break
            }
        }

        if (allGranted) {
            //权限已开启则直接回调任务
            val grantResults = IntArray(permissions.size)
            for (i in grantResults.indices) {
                grantResults[i] = PackageManager.PERMISSION_GRANTED
            }
            callback.onCallback(permissions, grantResults, true)
        } else {
            //若有权限未开启则申请权限
            var shouldShowRationale = false
            for (permission in permissions) {
                if (shouldShowRequestPermissionRationale(activity, permission)) {
                    shouldShowRationale = true
                    break
                }
            }
            if ((!TextUtils.isEmpty(attentiveTitle) || !TextUtils.isEmpty(attentiveContent)) && shouldShowRationale) {
                /* AlertDialogUtils.showCustomAlertDialog(activity, attentiveTitle, attentiveContent, "确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        requestPermissions(activity, permissions, callback);
                    }
                });*/
            } else {
                requestPermissions(activity, permissions, callback)
            }
        }
    }

    private fun requestPermissions(
        activity: Activity,
        permissions: Array<String?>,
        callback: RequestPermissionCallback
    ) {
        val requestCode = mPermissionRequestCode.incrementAndGet() + 1000
        mCallbackPool!!.put(requestCode, callback)
        ActivityCompat.requestPermissions(activity, permissions, requestCode)
    }

    private fun shouldShowRequestPermissionRationale(
        activity: Activity,
        permission: String?
    ): Boolean {
        if (Build.VERSION.SDK_INT >= 23) {
            return activity.shouldShowRequestPermissionRationale(permission!!)
        }
        return false
    }

    private fun checkPermission(context: Context, permission: String?): Int {
        requireNotNull(permission) { "checkPermission permission is null" }
        return context.checkPermission(permission, Process.myPid(), Process.myUid())
    }

    /**
     * 处理权限申请结果（拦截Activity onRequestPermissionsResult()）
     *
     * @param requestCode   请求码（onRequestPermissionsResult传进）
     * @param permissions   权限（onRequestPermissionsResult传进）
     * @param grantResults  返回码（onRequestPermissionsResult传进）
     */
    fun handleReuqestPermissionResult(
        requestCode: Int,
        permissions: Array<String?>?,
        grantResults: IntArray?
    ) {
        val activity = activity.get()
        if (activity == null) {
            onDestroy()
            return
        }
        //判断权限是否全部允许
        var allGranted = true
        if (grantResults == null || grantResults.size <= 0) {
            allGranted = false
        } else {
            for (result in grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false
                    break
                }
            }
        }

        //从池子里取出对应的任务
        val callback = mCallbackPool!![requestCode]
        if (callback != null) {
            //从任务池中移除
            mCallbackPool.remove(requestCode)
            callback.onCallback(permissions, grantResults, allGranted)
        }
    }

    /**
     * 清空任务池
     */
    fun clearPool() {
        mCallbackPool?.clear()
    }

    fun onDestroy() {
        activity.clear()
    }

    @Throws(DestroyFailedException::class)
    override fun destroy() {
    }

    override fun isDestroyed(): Boolean {
        return false
    }


    /**
     * 请求权限结果回调
     */
    interface RequestPermissionCallback {
        /**
         * 请求权限结果回调方法
         *
         * @param permisssions  请求的权限列表
         * @param grantResults  结果列表
         * @param allGranted    是否所有的权限都被允许
         */
        fun onCallback(permisssions: Array<String?>?, grantResults: IntArray?, allGranted: Boolean)
    }
}
