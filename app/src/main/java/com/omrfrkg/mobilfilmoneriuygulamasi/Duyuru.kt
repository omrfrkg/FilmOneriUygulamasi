package com.omrfrkg.mobilfilmoneriuygulamasi

import java.io.Serializable
import java.text.DateFormat

class Duyuru {

    var DuyuruID : Int = 0
    var DuyuruKonu : String = ""
    var DuyuruIcerik : String = ""
    var DuyuruTarih : String = ""

    constructor(dKonu : String,dIcerik : String,dTarih : String){

        this.DuyuruKonu = dKonu
        this.DuyuruIcerik = dIcerik
        this.DuyuruTarih = dTarih
    }

    constructor(){

    }
}