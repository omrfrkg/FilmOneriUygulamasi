package com.omrfrkg.mobilfilmoneriuygulamasi

import java.io.Serializable

class Favori : Serializable {
    var FavoriId : Int = 0
    var FavoriFilmId: Int = 0
    var FavoriKullaniciId: Int = 0
    var FavoriResim : String = ""
    /*
    var FavoriFilmAdi : String = ""
    var FavoriResim : String = ""
    var FavoriFragman : String = ""
    var FavoriFilmKonu : String = ""
    var FavoriFilmPuan : Float = 0.0f
    var FavoriFilmSuresi : String = ""
    var FavoriVizyonaGirisYili : Int = 0
    var FavoriFilmYonetmen : String = ""
    var FavoriFilmKategori : String = ""
    var FavoriFilmBasroller : String = ""*/

   /* constructor(filmAdi : String,resim : String,fragman : String,konu : String,puan : Float,sure : String,yil : Int,yonetmen : String,kategori : String,basrol : String){

        this.FavoriFilmAdi = filmAdi
        this.FavoriResim = resim
        this.FavoriFragman = fragman
        this.FavoriFilmKonu = konu
        this.FavoriFilmPuan = puan
        this.FavoriFilmSuresi = sure
        this.FavoriVizyonaGirisYili = yil
        this.FavoriFilmYonetmen = yonetmen
        this.FavoriFilmKategori = kategori
        this.FavoriFilmBasroller = basrol

    }*/

   constructor(filmId : Int,kullaniciId : Int,resim : String){

       this.FavoriFilmId = filmId
       this.FavoriKullaniciId = kullaniciId
       this.FavoriResim = resim


   }

    constructor(){

    }
}