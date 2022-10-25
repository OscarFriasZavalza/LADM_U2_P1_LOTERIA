package com.example.ladm_u2_p1_loteria


class Carta(id:Int, nombre:String,img:Int,audio:Int) {
    var id=0
    var nombre=""
    var img=0
    var audio=0

    init {
        this.id=id
        this.nombre=nombre
        this.img=img
        this.audio=audio
    }

    override fun toString(): String {
        return "Carta(id=$id,nombre=$nombre,img=$img,audio=$audio"
    }
}