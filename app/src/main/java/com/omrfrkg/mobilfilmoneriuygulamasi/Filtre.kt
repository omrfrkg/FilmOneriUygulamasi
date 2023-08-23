package com.omrfrkg.mobilfilmoneriuygulamasi

class Filtre {

    var FiltreID : Int = 0
    var FiltreKullaniciID : Int = 0
    var FiltreKategori : String = "Hepsi"

    constructor(fKategori : String,fKullaniciId : Int){

        this.FiltreKategori = fKategori
        this.FiltreKullaniciID = fKullaniciId
    }

    constructor(){

    }
}