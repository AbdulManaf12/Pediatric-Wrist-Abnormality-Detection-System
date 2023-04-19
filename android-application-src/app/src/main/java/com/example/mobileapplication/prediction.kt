import android.util.Log
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONObject
import java.io.File

class prediction {
    private val TAG = "ImageProcessor"

    fun getImageData(imageFile: File): JSONObject? {
        println("yes yes")
        val url = "https://255c-121-52-154-73.ap.ngrok.io/get-data"

        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(
                "file", imageFile.name, imageFile.asRequestBody("image/png".toMediaType())
            )
            .build()

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        val client = OkHttpClient()

        return try {
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val responseData = response.body?.string()
                JSONObject(responseData)
            } else {
                Log.e(TAG, "Error: ${response.code} - ${response.message}")
                null
            }
        } catch (e: Exception) {
            Log.e(TAG, "Exception: ${e.message}")
            null
        }
    }
}