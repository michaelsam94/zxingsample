package com.example.zxingsample

import android.hardware.Camera
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.zxingsample.camera.CameraManager
import com.example.zxingsample.decode.SurfaceViewActivityHandler
import com.google.zxing.Result
import java.io.IOException


class SurfaceViewActivity : AppCompatActivity(), SurfaceHolder.Callback {

    lateinit var surfaceView: SurfaceView
    lateinit var camera: Camera
    private var mHasSurface = false

    private var mCaptureActivityHandler: SurfaceViewActivityHandler? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surface)
        CameraManager.init(this)
        surfaceView = findViewById(R.id.qr_code_preview_view)

    }

    override fun onResume() {
        super.onResume()
        val surfaceHolder = surfaceView.holder
        if(mHasSurface){
            initCamera(surfaceHolder)
        } else {
            surfaceHolder.addCallback(this)
        }

    }

    override fun onPause() {
        super.onPause()
        CameraManager.get().closeDriver()
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        if(!mHasSurface) {
            mHasSurface = true
            initCamera(holder)
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        mHasSurface = false
    }

    private fun initCamera(holder: SurfaceHolder) {
        try {
            CameraManager.get().openDriver(holder)
            if (mCaptureActivityHandler == null) {
                mCaptureActivityHandler = SurfaceViewActivityHandler(this)
            }
        } catch (e: IOException) {
            Toast.makeText(this, "couldn't detect camera", Toast.LENGTH_SHORT)
                .show()
        }
    }

    fun handleDecode(result: Result?) {
        if (null == result) {
            restartPreview()
        } else {
            val resultString = result.text
            Toast.makeText(this,resultString,Toast.LENGTH_SHORT).show()
            finish()
            Log.d("SurfaceViewActivity",resultString)
        }
    }

    fun getCaptureActivityHandler(): Handler? {
        return mCaptureActivityHandler
    }

    private fun restartPreview() {
        if (null != mCaptureActivityHandler) {
            mCaptureActivityHandler?.restartPreviewAndDecode()
        }
    }
}