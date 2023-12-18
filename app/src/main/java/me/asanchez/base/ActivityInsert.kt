package me.asanchez.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.asanchez.base.R
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException

class ActivityInsert : AppCompatActivity() {

    lateinit var etnombre: EditText
    lateinit var etemail: EditText
    lateinit var etcontacto: EditText
    lateinit var etdireccion: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        etnombre = findViewById(R.id.etNombre)
        etemail = findViewById(R.id.etEmail)
        etcontacto = findViewById(R.id.etContacto)
        etdireccion = findViewById(R.id.etDireccion)

        val bSubir: Button = findViewById(R.id.bSubir)

        bSubir.setOnClickListener {
            insertar()
        }
    }

    private fun insertar() {
        val nombre = etnombre.text.toString()
        val email = etemail.text.toString()
        val contacto = etcontacto.text.toString()
        val direccion = etdireccion.text.toString()

        if (nombre.isEmpty() || email.isEmpty() || contacto.isEmpty() || direccion.isEmpty()) {
            Toast.makeText(baseContext, "COMPLETE LOS CAMPOS!", Toast.LENGTH_SHORT).show()
            return
        }

        val url = "https://musteline-resident.000webhostapp.com/base/insertar.php"

        val jsonObject = JSONObject()
        jsonObject.put("nombre", nombre)
        jsonObject.put("email", email)
        jsonObject.put("contacto", contacto)
        jsonObject.put("direccion", direccion)

        val client = OkHttpClient()

        val body = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), jsonObject.toString())
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                runOnUiThread {
                    Toast.makeText(baseContext, "Datos ingresados correctamente", Toast.LENGTH_SHORT).show()
                    Log.e("RESPONSE", responseData ?: "")

                    val intent = Intent(this@ActivityInsert, MainActivity::class.java)
                    startActivity(intent)
                    finish()


                }
            }

            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(baseContext, "Error en la inserción", Toast.LENGTH_SHORT).show()
                    Log.e("RESPONSE", e.toString())
                }
            }
        })
    }
}
