package com.example.zxingsample

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import java.util.concurrent.ExecutionException
import androidx.camera.core.ImageAnalysis


class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_QR_SCAN = 101

    private var previewView: PreviewView? = null
    private var qrCodeFoundButton: Button? = null
    private var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>? = null

    private var qrCode: String = ""

    private val PERMISSION_REQUEST_CAMERA = 0

    private var scanBtn: Button? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scanBtn = findViewById(R.id.scanBtn)
        scanBtn?.setOnClickListener {
            //Start the qr scan activity
            val i = Intent(this,SurfaceViewActivity::class.java)
            startActivity(i)
        }
    }



}