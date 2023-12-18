package me.asanchez.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val items: List<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.etId)
        val nombre: TextView = itemView.findViewById(R.id.editNombre)
        val email: TextView = itemView.findViewById(R.id.editEmail)
        val contacto: TextView = itemView.findViewById(R.id.editContacto)
        val direccion: TextView = itemView.findViewById(R.id.editDireccion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]

        holder.id.text = currentItem.id
        holder.nombre.text = currentItem.nombre
        holder.email.text = currentItem.email
        holder.contacto.text = currentItem.contacto
        holder.direccion.text = currentItem.direccion
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
