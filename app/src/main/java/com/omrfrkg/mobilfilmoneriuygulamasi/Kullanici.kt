package com.omrfrkg.mobilfilmoneriuygulamasi

class Kullanici {

    var KullaniciId : Int = 0
    var KullaniciAdi : String = ""
    var AdSoyad : String = ""
    var Mail : String = ""
    var Telefon : String = ""
    var Sifre : String = ""
    var Statu : String = "Kullanıcı"
    var Durum : Int = 1

    constructor(kAdi : String,adSoyad : String,mail : String,telefon : String,sifre : String){

        this.KullaniciAdi = kAdi
        this.AdSoyad = adSoyad
        this.Mail = mail
        this.Telefon = telefon
        this.Sifre = sifre

    }

    constructor(){

    }
}