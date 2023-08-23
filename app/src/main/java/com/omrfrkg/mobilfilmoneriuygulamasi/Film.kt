package com.omrfrkg.mobilfilmoneriuygulamasi

class Film {

    var FilmId : Int = 0
    var FilmAdi : String = ""
    var Resim : String = ""
    var Fragman : String = ""
    var FilmKonu : String = ""
    var FilmPuan : Float = 0.0f
    var FilmSuresi : String = ""
    var VizyonaGirisYili : Int = 0
    var FilmYonetmen : String = ""
    var FilmKategori : String = ""
    var FilmBasroller : String = ""

    constructor(fadi : String,resim : String,fragman : String,konu : String,puan : Float,sure : String,vizyonT : Int,yonetmen : String){

        this.FilmAdi = fadi
        this.Resim = resim
        this.Fragman = fragman
        this.FilmKonu = konu
        this.FilmPuan = puan
        this.FilmSuresi = sure
        this.VizyonaGirisYili = vizyonT
        this.FilmYonetmen = yonetmen
    }

    constructor(){

    }
}