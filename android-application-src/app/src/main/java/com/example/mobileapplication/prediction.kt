import android.graphics.Bitmap
import android.util.Log
import okhttp3.*
import java.io.ByteArrayOutputStream
import java.io.IOException

class Prediction {
    fun sendImageAndGetJSONData(imageBitmap: Bitmap) {
        val url = "https://e3b6-121-52-154-73.ap.ngrok.io/get-data"

        val byteArrayOutputStream = ByteArrayOutputStream()
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()

        val requestBody = RequestBody.create(MediaType.parse("image/jpeg"), byteArray)

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        val client = OkHttpClient()
        println("-----------------------------------------------------------------")
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("TAG", "Failed to execute request: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body()?.string()
                Log.i("TAG", "Response data: $responseData")
            }
        })
    }
}