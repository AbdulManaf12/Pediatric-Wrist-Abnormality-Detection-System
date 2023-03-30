package com.example.mobileapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ImageDetection : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()
        setContentView(R.layout.activity_image_detection)
    }

    fun selectImage(view: View) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/jpeg", "image/png"))
        startActivityForResult(intent, 36)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 36 && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val imageUri = data.data

            val imageView = findViewById<ImageView>(R.id.preview_image)
            imageView.setImageURI(imageUri)

            val placeholder = findViewById<TextView>(R.id.selection_text)
            placeholder.visibility = View.INVISIBLE

            val predict_btn = findViewById<Button>(R.id.predict_btn)
            predict_btn.visibility = View.VISIBLE

            val linearLayout = findViewById<LinearLayout>(R.id.image_layout)
            val dummy = findViewById<ImageView>(R.id.dummy_image)
            linearLayout.removeView(dummy)
        }
    }

    fun predict(view : View){
        val imageView = findViewById<ImageView>(R.id.preview_image)
        if (imageView.drawable == null){
            Toast.makeText(this, "Invalid Input. Before predicting must select image", Toast.LENGTH_LONG).show()
        }
        startActivity(Intent(this, ImageResult::class.java))
    }
}