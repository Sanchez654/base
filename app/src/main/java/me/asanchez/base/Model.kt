package me.asanchez.base

data class Model (
    val registros: List<Data>

){
    data class Data (val id: String?, val nombre: String?, val email: String?, val contacto: String?, val direccion: String? )

}