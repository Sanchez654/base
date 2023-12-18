package me.asanchez.base

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Hacer la llamada a la API para obtener los datos
        val baseURL = "https://musteline-resident.000webhostapp.com/base/select.php/"
        val apiService = ApiService.create(baseURL)
        val call = apiService.getItems()

        val btnGoToInsertActivity: Button = findViewById(R.id.bAgregar)

        btnGoToInsertActivity.setOnClickListener {
            // Crear un Intent para dirigirse a ActivityInsertar
            val intent = Intent(this, ActivityInsert::class.java)

            // Puedes agregar datos adicionales al Intent si es necesario
            // intent.putExtra("clave", valor)

            // Iniciar la actividad
            startActivity(intent)
        }

        val bEditarActivity: Button = findViewById(R.id.bEditar)

        bEditarActivity.setOnClickListener {
            val intent = Intent(this, EditarActivity::class.java)

            startActivity(intent)
        }

            call.enqueue(object : Callback<List<Item>> {
                override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                    if (response.isSuccessful) {
                        // Obtener los datos de la respuesta
                        val items = response.body() ?: emptyList()

                        // Inicializar el adaptador con los datos obtenidos
                        itemAdapter = ItemAdapter(items)

                        // Configurar el RecyclerView con el adaptador
                        recyclerView.adapter = itemAdapter
                    } else {
                        // Manejar la respuesta no exitosa
                        // Aquí puedes implementar la lógica para manejar el caso de respuesta no exitosa
                    }
                }

                override fun onFailure(call: Call<List<Item>>, t: Throwable) {

                    t.printStackTrace()
                }

            })
        }
    }





