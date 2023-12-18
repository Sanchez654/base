package me.asanchez.base

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EditarActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etEmail: EditText
    private lateinit var etContacto: EditText
    private lateinit var etDireccion: EditText

    private lateinit var bActualizar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar)


        etNombre = findViewById(R.id.editNombre)
        etEmail = findViewById(R.id.editEmail)
        etContacto = findViewById(R.id.editContacto)
        etDireccion = findViewById(R.id.editDireccion)


        bActualizar = findViewById(R.id.bActualizar)

        // Obtiene los datos existentes y muestra en los EditText

        bActualizar.setOnClickListener {
            actualizarDatos()
        }
    }

    private fun actualizarDatos() {
        // Obtén los datos editados
        val nuevoNombre = etNombre.text.toString()
        val nuevoEmail = etEmail.text.toString()
        val nuevoContacto = etContacto.text.toString()
        val nuevoDireccion = etDireccion.text.toString()

        // Validación de campos
        if (nuevoNombre.isEmpty() || nuevoEmail.isEmpty() || nuevoContacto.isEmpty() || nuevoDireccion.isEmpty()) {
            Toast.makeText(this@EditarActivity, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

            val apiService = RetrofitClient.getApiService()

        val call = apiService.actualizarDatos(nuevoNombre, nuevoEmail, nuevoContacto, nuevoDireccion)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    // Manejar la respuesta exitosa
                    Toast.makeText(
                        this@EditarActivity,
                        "Datos actualizados correctamente",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Redirigir a la actividad principal
                    val intent = Intent(this@EditarActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Manejar la respuesta no exitosa
                    Toast.makeText(
                        this@EditarActivity,
                        "Error al actualizar datos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // Manejar el fallo en la llamada a la API
                Toast.makeText(this@EditarActivity, "Error en la conexión: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

