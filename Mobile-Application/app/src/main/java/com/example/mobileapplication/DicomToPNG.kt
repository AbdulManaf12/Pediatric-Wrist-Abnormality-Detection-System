package com.example.mobileapplication

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream


class DicomToPNG : AppCompatActivity() {

    val PICK_IMAGE_REQUEST = 47
    var dicomFileUri: Uri? = null
    var convertedImageFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()
        setContentView(R.layout.activity_dicomtopng)
    }

    fun selectDicomFile(view: View) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "application/dicom"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            dicomFileUri = data.data
            val imageView = findViewById<ImageView>(R.id.imageView)
            imageView.setImageURI(dicomFileUri)
            findViewById<View>(R.id.selectButton).visibility = View.GONE
            findViewById<View>(R.id.convertButton).visibility = View.VISIBLE
            findViewById<View>(R.id.previewButton).visibility = View.GONE
        }
    }

    fun convertAndSave(view: View) {
        try {
            val inputStream = contentResolver.openInputStream(dicomFileUri!!)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            val outputFile = File(
                getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                "converted.png"
            )
            val outputStream = FileOutputStream(outputFile)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
            convertedImageFile = outputFile
            Toast.makeText(
                this,
                "File saved to ${outputFile.absolutePath}",
                Toast.LENGTH_SHORT
            ).show()
            findViewById<View>(R.id.previewButton).visibility = View.VISIBLE
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(
                this,
                "Error converting and saving file: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun preview(view: View) {
        if (convertedImageFile != null) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.fromFile(convertedImageFile), "image/png")
            startActivity(intent)
        } else {
            Toast.makeText(this, "No converted image file found", Toast.LENGTH_SHORT).show()
        }
    }
}