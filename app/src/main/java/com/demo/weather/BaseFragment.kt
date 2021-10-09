package com.demo.weather

import android.Manifest
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.view.Window
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment


open class BaseFragment : Fragment() {

    private var progressDialog: ProgressDialog? = null

    fun showProgress() {
        if (context == null || progressDialog != null) return
        try {
            progressDialog = ProgressDialog(context)
            progressDialog?.let {
                it.apply {
                    requestWindowFeature(Window.FEATURE_NO_TITLE)
                    setMessage(context.getString(R.string.loading))
                    setCancelable(true)
                    show()
                }
            }
        } catch (e: java.lang.Exception) {
        }
    }

    fun hideProgress() {
        progressDialog?.let {
            if (it.window != null && it.isShowing)
                it.dismiss()
        }
        progressDialog = null
    }

    fun requestLocationPermissionIfRequired() {
        if (isLocationPermissionRequired()) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                99
            )
        } else {
            onLocationPermissionGranted()
        }
    }

    private fun isLocationPermissionRequired() = ActivityCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.ACCESS_FINE_LOCATION
    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) != PackageManager.PERMISSION_GRANTED

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 99) {

            permissions.forEach {
                val showRationale = shouldShowRequestPermissionRationale(it)

                if (!showRationale) {
                    // user also CHECKED "never ask again"
                    // you can either enable some fall back,
                    // disable features of your app
                    // or open another dialog explaining
                    // again the permission and directing to
                    // the app setting

                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", requireContext().packageName, null)
                    intent.data = uri
                    startActivityForResult(intent, 100)

                } else {
                    requestLocationPermissionIfRequired()
                }
            }
        }
    }

    open fun onLocationPermissionGranted() {

    }
}