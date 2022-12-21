package com.jwiseinc.onedayticket.view.activtiy

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.jwiseinc.onedayticket.ExampleUtil
import com.jwiseinc.onedayticket.R
import java.io.IOException


class ScannerActivity : AppCompatActivity() {
    private val requestCodeCameraPermission = 1001
    private lateinit var cameraSource: CameraSource
    private lateinit var barcodeDetector: BarcodeDetector
    private var scannedValue = ""
    lateinit var cameraSurfaceView: SurfaceView
    lateinit var inputButton:Button
    lateinit var backBtn:ImageView
    var isAlertOpen:Boolean = true

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)

        inputButton = findViewById(R.id.inputButton)
        backBtn = findViewById(R.id.backBtn)
        cameraSurfaceView = findViewById(R.id.cameraSurfaceView)

        backBtn.setOnClickListener {
            val intent = Intent(this@ScannerActivity, MainActivity::class.java)
            startActivity(intent)
        }

        inputButton.setOnClickListener {
            isAlertOpen = false
            var alertDialog:AlertDialog.Builder = AlertDialog.Builder(this@ScannerActivity)
                .setTitle("手動輸入核銷碼")
                .setCancelable(false)
                .setView(LayoutInflater.from(this@ScannerActivity).inflate(R.layout.input_dialog, null))
            alertDialog.setPositiveButton("確定") { _, _ ->
                val editText = LayoutInflater.from(this@ScannerActivity).inflate(R.layout.input_dialog, null).findViewById(R.id.edit_text) as EditText
                val name = editText.text.toString()
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(applicationContext, "aaa", Toast.LENGTH_SHORT).show()
                    isAlertOpen = true
                } else {
                    Toast.makeText(applicationContext, "d" + name, Toast.LENGTH_SHORT).show()
                }
            }.setNeutralButton("取消") { _, _ ->
                isAlertOpen = true
            }
            alertDialog.show()
        }

        if (ContextCompat.checkSelfPermission(
                this@ScannerActivity, android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            askForCameraPermission()
        } else {
            setupControls()
        }
    }

    private fun setupControls() {
        barcodeDetector =
            BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.ALL_FORMATS).build()

        cameraSource = CameraSource.Builder(this, barcodeDetector)
            .setRequestedPreviewSize(1920, 1080)
            .setAutoFocusEnabled(true) //you should add this feature
            .build()

        cameraSurfaceView.getHolder().addCallback(object : SurfaceHolder.Callback {
            @SuppressLint("MissingPermission")
            override fun surfaceCreated(holder: SurfaceHolder) {
                try {
                    //Start preview after 1s delay
                    cameraSource.start(holder)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            @SuppressLint("MissingPermission")
            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
                try {
                    cameraSource.start(holder)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                cameraSource.stop()
            }
        })

        barcodeDetector.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {
                Toast.makeText(applicationContext, "Scanner has been closed", Toast.LENGTH_SHORT)
                    .show()
            }
            @RequiresApi(Build.VERSION_CODES.O)
            override fun receiveDetections(detections: Detector.Detections<Barcode>) {
                val barcodes = detections.detectedItems
                if (barcodes.size() == 1) {
                    scannedValue = barcodes.valueAt(0).rawValue
                    runOnUiThread {
                        Log.d("aaaaaaaa", ExampleUtil.encodeBase64Hex("$scannedValue"!!))
                        if(isAlertOpen){
                            val intent = Intent(this@ScannerActivity, WriteOffActivity::class.java)
                            startActivity(intent)
                            cameraSource.stop()
                        }
                    }
                }else
                {

                }
            }
        })
    }

    private fun askForCameraPermission() {
        ActivityCompat.requestPermissions(
            this@ScannerActivity,
            arrayOf(android.Manifest.permission.CAMERA),
            requestCodeCameraPermission
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == requestCodeCameraPermission && grantResults.isNotEmpty()) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupControls()
            } else {
                Toast.makeText(applicationContext, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this@ScannerActivity, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraSource.stop()
    }
}