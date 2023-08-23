package com.omrfrkg.mobilfilmoneriuygulamasi

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.view.View
import android.widget.Toast

var database_name = "FilmAppDatabase"


// FİLM TABLOSU
var table_film = "Film"
var col_filmId = "FilmId"
var col_filmAdi = "FilmAdi"
var col_resim = "Resim"
var col_fragman = "Fragman"
var col_filmKonu = "FilmKonu"
var col_filmPuan = "FilmPuan"
var col_filmSuresi = "FilmSuresi"
var col_filmVizyonGirisYili = "VizyonaGirisYili"
var col_filmYonetmen = "FilmYonetmen"
var col_filmKategorisi = "FilmKategori"
var col_filmBasrol = "FilmBasrol"

// KULLANICI TABLOSU
var table_kullanici = "Kullanici"
var col_kullaniciId = "KullaniciId"
var col_kullaniciAdi = "KullaniciAdi"
var col_kullaniciAdSoyad = "AdSoyad"
var col_kullaniciMail = "Mail"
var col_kullaniciTelefon = "Telefon"
var col_kullaniciSifre = "Sifre"
var col_kullaniciStatu = "Statu"
var col_kullaniciDurum = "Durum"

// FAVORİ TABLOSU
var table_favori = "Favori"
var col_favoriId = "FavoriId"
var col_favoriFilmId = "FilmId"
var col_favoriKullaniciId = "KullaniciId"
var col_favoriResim = "Resim"

// FİLTRE TABLOSU
var table_filtre = "Filtre"
var col_filtreId = "FiltreID"
var col_filtreKullaniciId = "KullaniciID"
var col_filtreKategori = "Kategori"

// DUYURU TABLOSU
var table_duyuru = "Duyuru"
var col_duyuruId = "DuyuruID"
var col_duyuruKonu = "Konu"
var col_duyuruIcerik = "Icerik"
var col_duyuruTarih = "Tarih"

class DataBaseHelper(var context: Context) : SQLiteOpenHelper(context, database_name,null,1) {

    override fun onCreate(db: SQLiteDatabase?) {
        // Veri tabanı oluştuğunda bir kez çalışır
        var createTableKullanici = " CREATE TABLE "+ table_kullanici + "("+
                col_kullaniciId+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                col_kullaniciAdi+" VARCHAR(50),"+
                col_kullaniciAdSoyad+" VARCHAR(100),"+
                col_kullaniciMail+" VARCHAR(100),"+
                col_kullaniciTelefon+" VARCHAR(11),"+
                col_kullaniciSifre+" VARCHAR(50),"+
                col_kullaniciStatu+" VARCHAR(20),"+
                col_kullaniciDurum+" INT)"

        db?.execSQL(createTableKullanici)

        var createTableFilm = " CREATE TABLE "+ table_film + "("+
                col_filmId+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                col_filmAdi+" VARCHAR(50),"+
                col_resim+" VARCHAR(100),"+
                col_fragman+" VARCHAR(100),"+
                col_filmKonu+" VARCHAR(500),"+
                col_filmPuan+" FLOAT,"+
                col_filmSuresi+" VARCHAR(15),"+
                col_filmVizyonGirisYili+" INT,"+
                col_filmYonetmen+" VARCHAR(50),"+
                col_filmKategorisi+" VARCHAR(120),"+
                col_filmBasrol+" VARCHAR(200))"

        db?.execSQL(createTableFilm)

        var createTableFavori = " CREATE TABLE "+ table_favori + "("+
                col_favoriId+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                col_favoriFilmId+" INT,"+
                col_favoriKullaniciId+" INT,"+
                col_favoriResim+" VARCHAR(50))"
        db?.execSQL(createTableFavori)

        var crateTableFiltre = " CREATE TABLE "+ table_filtre + "("+
                col_filtreId+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                col_filtreKullaniciId+" INT,"+
                col_filtreKategori+" VARCHAR(120))"
        db?.execSQL(crateTableFiltre)

        var createTableDuyuru = " CREATE TABLE "+ table_duyuru + "("+
                col_duyuruId+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                col_duyuruKonu+" VARCHAR(80),"+
                col_duyuruIcerik+" VARCHAR(250),"+
                col_duyuruTarih+" VARCHAR(20))"
        db?.execSQL(createTableDuyuru)

    }

    //Yeni Kullanıcı Ekleyen Fonksiyon
    fun insertKullaniciData(kullanici : Kullanici){

        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(col_kullaniciAdi,kullanici.KullaniciAdi)
        cv.put(col_kullaniciAdSoyad,kullanici.AdSoyad)
        cv.put(col_kullaniciMail,kullanici.Mail)
        cv.put(col_kullaniciTelefon,kullanici.Telefon)
        cv.put(col_kullaniciSifre,kullanici.Sifre)
        cv.put(col_kullaniciStatu,"Kullanıcı")
        cv.put(col_kullaniciDurum,1)

        var sonuc = db.insert(table_kullanici,null,cv)

        if(sonuc == (-1).toLong()){
            Toast.makeText(context, "Kayıt Olma İşleminiz Gerçekleştirilemedi!", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context, "Kayıt Olma İşleminiz Başarıyla Gerçekleştirildi!", Toast.LENGTH_LONG).show()
        }
    }

    //Duyuru Ekleyen Fonksiyon
    fun insertDuyuruData(duyuru : Duyuru){

        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(col_duyuruKonu,duyuru.DuyuruKonu)
        cv.put(col_duyuruIcerik,duyuru.DuyuruIcerik)
        cv.put(col_duyuruTarih,duyuru.DuyuruTarih)

        var sonuc = db.insert(table_duyuru,null,cv)

        if(sonuc == (-1).toLong()){
            Toast.makeText(context, "Duyuru Yayınlama İşleminiz Gerçekleştirilemedi!", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context, "Duyuru Yayınlama İşleminiz Başarıyla Gerçekleştirildi!", Toast.LENGTH_LONG).show()
        }
    }

    //Admin Kullanıcısını Ekleyen Fonksiyon
    fun insertYoneticiData(){

        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(col_kullaniciAdi,"admin")
        cv.put(col_kullaniciAdSoyad,"Yönetici")
        cv.put(col_kullaniciMail,"Yönetici")
        cv.put(col_kullaniciTelefon,"00000000000")
        cv.put(col_kullaniciSifre,"admin123")
        cv.put(col_kullaniciStatu,"Yönetici")
        cv.put(col_kullaniciDurum,1)

        var sonuc = db.insert(table_kullanici,null,cv)

    }

    fun readDuyuruData(): ArrayList<Duyuru> {

        var liste : ArrayList<Duyuru> = ArrayList()
        var db = this.readableDatabase
        var sorgu  = "Select * From $table_duyuru"
        var sonuc = db.rawQuery(sorgu,null)

        if (sonuc.moveToFirst()){
            do {

                var duyuru = Duyuru()
                duyuru.DuyuruID = sonuc.getString(sonuc.getColumnIndexOrThrow(col_duyuruId)).toInt()
                duyuru.DuyuruKonu = sonuc.getString(sonuc.getColumnIndexOrThrow(col_duyuruKonu))
                duyuru.DuyuruIcerik = sonuc.getString(sonuc.getColumnIndexOrThrow(col_duyuruIcerik))
                duyuru.DuyuruTarih = sonuc.getString(sonuc.getColumnIndexOrThrow(col_duyuruTarih))

                liste.add(duyuru)

            }while (sonuc.moveToNext())
        }

        sonuc.close()
        db.close()
        return liste
    }

    fun readKullaniciSifreTalepData(): ArrayList<Kullanici> {

        var liste : ArrayList<Kullanici> = ArrayList()
        var db = this.readableDatabase
        var sorgu  = "Select * From $table_kullanici Where $col_kullaniciDurum == 0"
        var sonuc = db.rawQuery(sorgu,null)

        if (sonuc.moveToFirst()){
            do {

                var kullanici = Kullanici()

                kullanici.KullaniciId = sonuc.getString(sonuc.getColumnIndexOrThrow(col_kullaniciId)).toInt()
                kullanici.AdSoyad = sonuc.getString(sonuc.getColumnIndexOrThrow(col_kullaniciAdSoyad))
                kullanici.Mail = sonuc.getString(sonuc.getColumnIndexOrThrow(col_kullaniciMail))
                kullanici.KullaniciAdi = sonuc.getString(sonuc.getColumnIndexOrThrow(col_kullaniciAdi))

                liste.add(kullanici)

            }while (sonuc.moveToNext())
        }

        sonuc.close()
        db.close()
        return liste
    }

    fun updateDuyuruData(
        dId : Int,
        dKonu: String,
        dIcerik : String
        ){

        val db = this.readableDatabase
        var sorgu = " Select * From $table_duyuru"
        var sonuc = db.rawQuery(sorgu,null)
        if (sonuc.moveToFirst()){
            do{
                var cv = ContentValues()

                cv.put(col_duyuruKonu,dKonu)
                cv.put(col_duyuruIcerik,dIcerik)

                db.update(table_duyuru,cv,"$col_duyuruId=?",
                    arrayOf(dId.toString()))

            }while (sonuc.moveToNext())
        }
        sonuc.close()
        db.close()
    }

    fun updateKullaniciSifreData(
        kId: Int,
        kSifre: String
    ){

        val db = this.readableDatabase
        var sorgu = " Select * From $table_kullanici"
        var sonuc = db.rawQuery(sorgu,null)
        if (sonuc.moveToFirst()){
            do{
                var cv = ContentValues()

                cv.put(col_kullaniciSifre,kSifre)

                db.update(table_kullanici,cv,"$col_kullaniciId=?",
                    arrayOf(kId.toString()))

            }while (sonuc.moveToNext())
        }
        sonuc.close()
        db.close()
    }

    fun deleteDuyuruData(dId : Int){
        val db = this.writableDatabase
        db.delete(table_duyuru,"$col_duyuruId=?",arrayOf(dId.toString()))
        db.close()
    }

    fun deleteKullaniciData(kId : Int){
        val db = this.writableDatabase
        db.delete(table_kullanici,"$col_kullaniciId=?",arrayOf(kId.toString()))
        db.close()
    }

    //Filtre Tablosu Ekleme Fonksiyonu
    fun insertFiltreData(filtre: Filtre){

        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(col_filtreKullaniciId,filtre.FiltreKullaniciID)
        cv.put(col_filtreKategori,filtre.FiltreKategori)

        var sonuc = db.insert(table_filtre,null,cv)

        if(sonuc == (-1).toLong()){
            Toast.makeText(context, "Önerilenleri Değiştirme İşleminiz Başarısız Oldu!", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context, "Önerilenleri Değiştirme İşleminiz Başarıyla Gerçekleştirildi!", Toast.LENGTH_LONG).show()
        }
    }

    fun readFiltreData(kId: Int): MutableList<Filtre> {

        var liste : MutableList<Filtre> = ArrayList()
        var db = this.readableDatabase
        var sorgu  = "Select * From $table_filtre Where $col_filtreKullaniciId = $kId"
        var sonuc = db.rawQuery(sorgu,null)

        if (sonuc.moveToFirst()){
            do {

                var filtre = Filtre()
                filtre.FiltreID = sonuc.getString(sonuc.getColumnIndexOrThrow(col_filtreId)).toInt()
                filtre.FiltreKullaniciID = sonuc.getString(sonuc.getColumnIndexOrThrow(col_filtreKullaniciId)).toInt()
                filtre.FiltreKategori = sonuc.getString(sonuc.getColumnIndexOrThrow(col_filtreKategori))

                liste.add(filtre)

            }while (sonuc.moveToNext())
        }

        sonuc.close()
        db.close()
        return liste
    }

    fun readFavoriFiltreData(kId: Int): MutableList<Favori> {

        var liste : MutableList<Favori> = ArrayList()
        var db = this.readableDatabase
        var sorgu  = "Select * From $table_favori Where $col_favoriKullaniciId = $kId"
        var sonuc = db.rawQuery(sorgu,null)

        if (sonuc.moveToFirst()){
            do {

                var favori = Favori()
                favori.FavoriId = sonuc.getString(sonuc.getColumnIndexOrThrow(col_favoriId)).toInt()
                favori.FavoriFilmId = sonuc.getString(sonuc.getColumnIndexOrThrow(col_favoriFilmId)).toInt()
                favori.FavoriKullaniciId = sonuc.getString(sonuc.getColumnIndexOrThrow(col_favoriKullaniciId)).toInt()
                favori.FavoriResim = sonuc.getString(sonuc.getColumnIndexOrThrow(col_favoriResim))

                liste.add(favori)

            }while (sonuc.moveToNext())
        }

        sonuc.close()
        db.close()
        return liste
    }

    //Kullanıcıyı Güncelleyen Fonksiyon
    fun updateFiltreData(
        fKullaniciId: Int,
        fKategori: String){

        val db = this.readableDatabase
        var sorgu = " Select * From $table_filtre"
        var sonuc = db.rawQuery(sorgu,null)
        if (sonuc.moveToFirst()){
            do{
                var cv = ContentValues()

                cv.put(col_filtreKategori,fKategori)

                db.update(table_filtre,cv,"$col_filtreKullaniciId=?",
                    arrayOf(fKullaniciId.toString()))

            }while (sonuc.moveToNext())
        }
        sonuc.close()
        db.close()
    }





    //Verileri okumak için fonksiyon tanımlıyoruz
    fun readKullaniciData(): MutableList<Kullanici> {

        var liste : MutableList<Kullanici> = ArrayList()
        var db = this.readableDatabase
        var sorgu  = "Select * From "+ table_kullanici
        var sonuc = db.rawQuery(sorgu,null)

        if (sonuc.moveToFirst()){
            do {

                var kullanici = Kullanici()
                kullanici.KullaniciId = sonuc.getString(sonuc.getColumnIndexOrThrow(col_kullaniciId)).toInt()
                kullanici.KullaniciAdi = sonuc.getString(sonuc.getColumnIndexOrThrow(col_kullaniciAdi))
                kullanici.AdSoyad = sonuc.getString(sonuc.getColumnIndexOrThrow(col_kullaniciAdSoyad))
                kullanici.Mail = sonuc.getString(sonuc.getColumnIndexOrThrow(col_kullaniciMail))
                kullanici.Telefon = sonuc.getString(sonuc.getColumnIndexOrThrow(col_kullaniciTelefon))
                kullanici.Sifre = sonuc.getString(sonuc.getColumnIndexOrThrow(col_kullaniciSifre))
                kullanici.Statu = sonuc.getString(sonuc.getColumnIndexOrThrow(col_kullaniciStatu))
                kullanici.Durum = sonuc.getString(sonuc.getColumnIndexOrThrow(col_kullaniciDurum)).toInt()

                liste.add(kullanici)

            }while (sonuc.moveToNext())
        }

        sonuc.close()
        db.close()
        return liste
    }

    //Kullanıcıyı Güncelleyen Fonksiyon
    fun updateKullaniciData(
        kId: Int,
        kAdi: String,
        kAdSoyad: String,
        kMail: String,
        kTelefon: String,
        kSifre: String){

        val db = this.readableDatabase
        var sorgu = " Select * From $table_kullanici"
        var sonuc = db.rawQuery(sorgu,null)
        if (sonuc.moveToFirst()){
            do{
                var cv = ContentValues()
                cv.put(col_kullaniciAdi,kAdi)
                cv.put(col_kullaniciAdSoyad,kAdSoyad)
                cv.put(col_kullaniciMail,kMail)
                cv.put(col_kullaniciTelefon,kTelefon)
                cv.put(col_kullaniciSifre,kSifre)
                db.update(table_kullanici,cv,"$col_kullaniciId=?",
                    arrayOf(kId.toString()))

            }while (sonuc.moveToNext())
        }

        sonuc.close()
        db.close()
    }


    //Yeni Film Ekleyen Fonksiyon
    fun insertFilmData(){

        val db = this.writableDatabase
        val cv = ContentValues()

       /* var table_film = "Film"
        var col_filmId = "FilmId"
        var col_filmAdi = "FilmAdi"
        var col_resim = "Resim"
        var col_fragman = "Fragman"
        var col_filmKonu = "FilmKonu"
        var col_filmPuan = "FilmPuan"
        var col_filmSuresi = "FilmSuresi"
        var col_filmVizyonGirisYili = "VizyonaGirisYili"
        var col_filmYonetmen = "FilmYonetmen"*/

        cv.put(col_filmAdi,"Kötü Ruh Uyanışı")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/pictures/23/01/30/08/06/3144546.jpg")
        cv.put(col_resim,"kotu_ruh_uyanis")
        cv.put(col_fragman,"https://www.imdb.com/video/vi584631577/?ref_=tt_vi_i_3")
        cv.put(col_filmKonu,"Zamanla birbirlerinden uzaklaşan iki kardeş, aradan geçen yılların ardından yeniden bir araya gelir. " +
                            "Ancak büyük bir tehlike onları beklemektedir çünkü et yiyen kötü ruhlar canlanmıştır. " +
                            "Kendilerini bir anda büyük bir kabusun içinde bulan iki kardeş, hayatta kalabilmek için " +
                            "farklılıklarının üstesinden gelip, birlikte hareket etmek zorundadır.")
        cv.put(col_filmPuan,7.4)
        cv.put(col_filmSuresi,"1 Saat 32 Dakika")
        cv.put(col_filmVizyonGirisYili,2023)
        cv.put(col_filmYonetmen,"Lee Cronin")
        cv.put(col_filmKategorisi,"Korku")
        cv.put(col_filmBasrol,"Mirabai Pease, Richard Crouchley, Anna-Maree Thomas")
        db.insert(table_film,"1",cv)

        cv.put(col_filmAdi,"Galaksinin Koruyucuları 3")
        //cv.put(col_resim,"https://tr.web.img4.acsta.net/c_310_420/pictures/bzp/01/11736.jpg")
        cv.put(col_resim,"guardian_of_galaxy_3")
        cv.put(col_fragman,"https://www.imdb.com/video/vi1043842073/?ref_=tt_vi_i_4")
        cv.put(col_filmKonu,"Galaksinin Koruyucuları 3, Rocket’in hayatını kurtarmak için yeniden bir araya gelen " +
                            "Koruyucular'ın hikayesini konu ediyor. Sevilen uyumsuzlar takımı Koruyucular, yaşananların " +
                            "ardından kendilerine Knowhere’de yeni bir hayat kurar. Ancak onların sakin yaşamı, Rocket’in " +
                            "çalkantılı geçmişinin izleri nedeniyle pek de uzun sürmez. Gamora’yı kaybetmesinin etkisinden " +
                            "çıkamayan Peter Quill, Rocket’in hayatını kurtarmak için takımı yeniden bir araya getirmeye karar " +
                            "verir. Bu görevin başarıyla yerine getirilememesi durumunda Koruyucular’ın sonu gelecektir..")
        cv.put(col_filmPuan,8.4)
        cv.put(col_filmSuresi,"2 Saat 30 Dakika")
        cv.put(col_filmVizyonGirisYili,2023)
        cv.put(col_filmYonetmen,"James Gunn")
        cv.put(col_filmKategorisi,"Aksiyon Macera Komedi")
        cv.put(col_filmBasrol,"Chris Pratt, Chukwudi Iwuji, Bradley Cooper")
        db.insert(table_film,"2",cv)

        cv.put(col_filmAdi,"Üç Billboard Ebbing Çıkışı, Missouri")
        //cv.put(col_resim,"https://tr.web.img4.acsta.net/c_310_420/medias/nmedia/18/91/63/78/20155809.jpg")
        cv.put(col_resim,"uc_billboard_ebbing_cikisi_missouri")
        cv.put(col_fragman,"https://www.imdb.com/video/vi1287501849/?ref_=tt_vi_i_2")
        cv.put(col_filmKonu,"Üç Billboard Ebbing Çıkışı, Missouri, kızının cinayetini aydınlatmak isteyen bir anneyi temel " +
                            "alıyor. Kızının cinayet davasında bir suçlu bulunmadan aylar geçtikten sonra, Mildred Hayes " +
                            "cesur bir hamle yapar. Kentin saygıdeğer polis şefi William Willoughby'ye yöneltilen tartışmalı " +
                            "bir mesajla kentin ücra bir yolunda üç adet billboard kiralar. Şiddet eğilimli, çocuksu bir " +
                            "annenin çocuğu olan, şefin sağ kolu Dixon'ın adı olaya karıştığında, acılı anne ile güvenlik " +
                            "güçleri arasındaki savaş daha da şiddetlenecektir...")
        cv.put(col_filmPuan,8.1)
        cv.put(col_filmSuresi,"1 Saat 55 Dakika")
        cv.put(col_filmVizyonGirisYili,2017)
        cv.put(col_filmYonetmen,"Martin McDonagh")
        cv.put(col_filmKategorisi,"Komedi Suç Drama")
        cv.put(col_filmBasrol,"Frances McDormand, Woody Harrelson, Sam Rockwell")
        db.insert(table_film,"3",cv)

        cv.put(col_filmAdi,"Kara Şövalye Yükseliyor")
        //cv.put(col_resim,"https://tr.web.img4.acsta.net/c_310_420/medias/nmedia/18/85/20/56/20129570.jpg")
        cv.put(col_resim,"kara_sovalye_yukseliyor")
        cv.put(col_fragman,"https://www.imdb.com/video/vi324468761/?playlistId=tt0468569&ref_=tt_pr_ov_vi")
        cv.put(col_filmKonu,"Kara Şövalye Yükseliyor'da terörist lider Bane'nin yaptığı gizli planları durdurmaya bu sefer " +
                            "ne Bruce Wayne'nin ne de Batman'in gücü yeter. Kedikadın Selina Kyle'ı da kendi safına çeken " +
                            "Bane, Gotham kentini ve halkını ciddi bir tehditle karşı karşıya bırakacaktır. Ne yerel kuvvetler, " +
                            "ne kahraman Jim Gordon ne de ordu olacakların önüne geçemez. Batman ilk kez kendisinden daha " +
                            "güçlü bir rakibe karşı mücadele verecektir...")
        cv.put(col_filmPuan,9.0)
        cv.put(col_filmSuresi,"2 Saat 32 Dakika")
        cv.put(col_filmVizyonGirisYili,2008)
        cv.put(col_filmYonetmen,"Christopher Nolan")
        cv.put(col_filmKategorisi,"Aksiyon Suç Drama")
        cv.put(col_filmBasrol,"Christian Bale, Heath Ledger, Aaron Eckhart")
        db.insert(table_film,"4",cv)

        cv.put(col_filmAdi,"Top Gun: Maverick")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/22/05/23/12/43/0003377.jpg")
        cv.put(col_resim,"top_gun_maverick")
        cv.put(col_fragman,"https://www.imdb.com/video/vi632472089/?playlistId=tt1745960&ref_=tt_pr_ov_vi")
        cv.put(col_filmKonu,"Top Gun: Maverick, pilot Pete “Maverick” Mitchell'in hava kuvvetlerine geri dönüşü sonrası " +
                            "yaşananlara odaklanıyor. Amerika Birleşik Devletleri Deniz Kuvvetleri Teğmeni Pete “Maverick” " +
                            "Mitchell, donanmaya verdiği 30 yıllık başarılı hizmetin ardından, test pilotu olarak sınırları " +
                            "zorlar. Kendisini karaya bağlayacak herhangi bir görevden uzak durmak isteyen Maverick, kendisini " +
                            "Top Gun mezunlarından oluşan bir müfrezeyi özel bir görev için eğitirken bulur. Hem belirsiz bir " +
                            "gelecekle hem de geçmişindeki anılarıyla karşı karşıya olan Maverick, gitmek zorunda olduğu görev " +
                            "nedeniyle en büyük korkularıyla yüzleşmek zorunda kalır.")
        cv.put(col_filmPuan,8.2)
        cv.put(col_filmSuresi,"2 Saat 10 Dakika")
        cv.put(col_filmVizyonGirisYili,2022)
        cv.put(col_filmYonetmen,"Joseph Kosinski")
        cv.put(col_filmKategorisi,"Aksiyon Drama")
        cv.put(col_filmBasrol,"Tom Cruise, Jennifer Connelly, Miles Teller")
        db.insert(table_film,"5",cv)

        cv.put(col_filmAdi,"Örümcek Adam: Eve Dönüş Yok")
        //cv.put(col_resim,"https://tr.web.img3.acsta.net/c_310_420/pictures/22/08/22/16/29/5693673.jpg")
        cv.put(col_resim,"eve_donus_yok")
        cv.put(col_fragman,"https://www.imdb.com/video/vi3348546073/?playlistId=tt10872600&ref_=tt_ov_vi")
        cv.put(col_filmKonu,"Örümcek-Adam Eve Dönüş Yok, kimliği açığa Örümcek-Adam'ın, sırrını geri vermesi için Doktor " +
                            "Strange'den yardım istemesiyle birlikte yaşananları konu ediyor. Örümcek-Adam'ın kimliği ifşa " +
                            "edilerek onun ve sevdiklerinin hayatı, halkın gözü önüne serilir. Kendisini büyük bir kaosun " +
                            "ortasında bulan Peter, aynı zamanda suç ortakları olarak lanse edilen MJ ve Ned'in hayatının da " +
                            "olumsuz etkilenmesine şahit olur. Arkadaşların üniversiteye girme şanslarının yok olmasına " +
                            "seyirci kalmak istemeyen Peter, sırrını geri vermesi için Doktor Strange'den yardım ister. " +
                            "Peter'ın yakarışından etkilenip ona yardım etmeyi kabul eden Strange, Unutma Büyüsü'nü yapmaya " +
                            "başlar. Ancak bu büyü, MJ, Ned, May ve Happy'nin de Örümcek-Adam'ın kim olduğunu unutmasına " +
                            "neden olacaktır. Arkadaşlarının kim olduğunu hatırlamasını, diğer kişilerin unutmasını isteyen " +
                            "Peter, Strange büyüyü yaparken parametreleri değiştirir. Ancak bu durum beklenmedik olaylara " +
                            "neden olur.")
        cv.put(col_filmPuan,8.2)
        cv.put(col_filmSuresi,"2 Saat 28 Dakika")
        cv.put(col_filmVizyonGirisYili,2021)
        cv.put(col_filmYonetmen,"Jon Watts")
        cv.put(col_filmKategorisi,"Aksiyon Macera Fantezi")
        cv.put(col_filmBasrol,"Tom Holland, Zendaya, Benedict Cumberbatch")
        db.insert(table_film,"6",cv)

        cv.put(col_filmAdi,"Baba")
        //cv.put(col_resim,"https://tr.web.img4.acsta.net/c_310_420/pictures/21/06/30/15/02/4433234.jpg")
        cv.put(col_resim,"father")
        cv.put(col_fragman,"https://www.imdb.com/video/vi279101721/?playlistId=tt10272386&ref_=tt_ov_vi")
        cv.put(col_filmKonu,"Baba, yaşlılığı ile başa çıkmaya çalışan bir adamın hikayesini konu ediyor. 80 yaşında huysuz " +
                            "bir adam olan ve yaşlılığın etkilerini gün geçtikçe daha çok hisseden Anthony, bu süreçte yanında " +
                            "olmaya çalışan kızının tüm yardımlarını reddeder. Ancak zamanla Anthony’nin gerçeklik algısının " +
                            "sarsılmaya başlaması ve kızının da günlük olarak onu ziyaret edememesi üzerine bir yardımcı bulmak " +
                            "zorunlu hale gelir. Hafıza problemleri yaşayan Anthony, sevdiklerinden, kendi zihninden hatta " +
                            "gerçekliğinden şüphe duymaya başlar.")
        cv.put(col_filmPuan,8.2)
        cv.put(col_filmSuresi,"1 Saat 37 Dakika")
        cv.put(col_filmVizyonGirisYili,2020)
        cv.put(col_filmYonetmen,"Florian Zeller")
        cv.put(col_filmKategorisi,"Drama Gizem")
        cv.put(col_filmBasrol,"Anthony Hopkins, Olivia Colman, Mark Gatiss")
        db.insert(table_film,"7",cv)

        cv.put(col_filmAdi,"1917")
        //cv.put(col_resim,"https://tr.web.img4.acsta.net/c_310_420/pictures/20/01/15/11/22/1391557.jpg")
        cv.put(col_resim,"s1917")
        cv.put(col_fragman,"https://www.imdb.com/video/vi4095655705/?playlistId=tt8579674&ref_=tt_ov_vi")
        cv.put(col_filmKonu,"1917, I. Dünya Savaşı sırasında askerlerin hayatını etkileyecek önemde bir mesajı iletmekle " +
                            "görevlendirilen iki askerin hikayesini konu ediyor. konu ediyor. I. Dünya Savaşı sırasında " +
                            "Britanya askeri olan Kıdemsiz Onbaşı Schofield ve Kıdemsiz Onbaşı Blake, gerçekleştirilmesi " +
                            "imkansız gibi görünen bir göreve atanır. Görevleri, zamana karşı yarışırken düşman bölgesini " +
                            "geçerek yüzlerce askerin ölümünü engellemek üzere bir mesaj iletmektir. Blake'in kardeşi de " +
                            "kurtarılabilecek askerlerin arasındadır. Bu durumda Blake'i daha da fazla ciddiye alması gereken" +
                            " bir mücadele bekliyordur.")
        cv.put(col_filmPuan,8.2)
        cv.put(col_filmSuresi,"1 Saat 59 Dakika")
        cv.put(col_filmVizyonGirisYili,2019)
        cv.put(col_filmYonetmen,"Sam Mendes")
        cv.put(col_filmKategorisi,"Aksiyon Drama Savaş")
        cv.put(col_filmBasrol,"Dean-Charles Chapman, George MacKay, Daniel Mays")
        db.insert(table_film,"8",cv)

        cv.put(col_filmAdi,"Klaus: Sihirli Plan")
        //cv.put(col_resim,"https://tr.web.img4.acsta.net/c_310_420/pictures/19/10/07/12/38/2313944.jpg")
        cv.put(col_resim,"klaus")
        cv.put(col_fragman,"https://www.imdb.com/video/vi3213606681/?playlistId=tt4729430&ref_=tt_pr_ov_vi")
        cv.put(col_filmKonu,"Klaus, genç bir postacının yaşamına odaklanıyor. Smeerensburg'un yeni postacısı olan Jesper, " +
                            "oyuncaklarla dolu bir yerde tek başına yaşayan oyuncakçı Klaus ile tanışır. Oyuncak imalatçısı " +
                            "olan Klaus ile anlaşma yapmak isteyen Jesper, Klaus'tan eski oyuncaklarını bedava dağıtılmak " +
                            "üzere bağışlamasını ister. Teklifi kabul eden Klaus, oyuncakların dağıtılması için Jesper ile " +
                            "birlikte gitmeye karar verir. Birlikte oyuncak dağıtmaya başlayan Jesper ile Klaus, dünyanın en " +
                            "mutsuz yeri olan Smeerensburg'u değiştirmeye başlar. Ancak onların kasabaya neşe getirmesinden " +
                            "rahatsız olan bir takım insanlar vardır.Fragmanlar.")
        cv.put(col_filmPuan,8.2)
        cv.put(col_filmSuresi,"1 Saat 36 Dakika")
        cv.put(col_filmVizyonGirisYili,2019)
        cv.put(col_filmYonetmen,"Sergio Pablos, Carlos Martínez López")
        cv.put(col_filmKategorisi,"Animasyon Macera Komedi")
        cv.put(col_filmBasrol,"Jason Schwartzman, J.K. Simmons, Rashida Jones")
        db.insert(table_film,"9",cv)

        cv.put(col_filmAdi,"Joker")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/19/09/11/16/43/1511539.jpg")
        cv.put(col_resim,"joker")
        cv.put(col_fragman,"https://www.imdb.com/video/vi1723318041/?playlistId=tt7286456&ref_=tt_ov_vi")
        cv.put(col_filmKonu,"Joker, başarısız bir komedyen olan Arthur Fleck'in hayatına odaklanıyor. Toplum tarafından " +
                            "dışlanan bir adam olan Arthur, hayatta yapayalnızdır. Sürekli bir bağ kurma arayışında olan " +
                            "Arthur, yaşamını taktığı iki maske ile geçirir. Gündüzleri, geçimini sağlamak için palyaço " +
                            "maskesini yüzüne takan Arthur, geceleri ise asla üzerinden silip atamayacağı bir maske takar. " +
                            "Babasız büyüyen Arthur’u en yakın arkadaşı olan annesi Happy adıyla çağırır. Bu lakap, Arthur’un " +
                            "içindeki acıyı gizlemesine yardımcı olur. Ancak maruz kaldığı zorbalıklar, onun gitgide toluma " +
                            "aykırı bir adam haline gelmesine neden olur. Yavaş yavaş psikolojik olarak tekinsiz sulara yelken " +
                            "açılan Arthur, bir süre sonra kendisini Gotham Şehri’nde suç ve kaosun içinde bulur. Arthur, " +
                            "zamanla kendi kimliğinden uzaklaşıp Joker karakterine bürünür.")
        cv.put(col_filmPuan,8.4)
        cv.put(col_filmSuresi,"2 Saat 2 Dakika")
        cv.put(col_filmVizyonGirisYili,2019)
        cv.put(col_filmYonetmen,"Todd Phillips")
        cv.put(col_filmKategorisi,"Suç Dram Gerilim")
        cv.put(col_filmBasrol,"Joaquin Phoenix, Robert De Niro, Zazie Beetz")
        db.insert(table_film,"10",cv)

        cv.put(col_filmAdi,"Asfaltın Kralları")
        //cv.put(col_resim,"https://tr.web.img4.acsta.net/c_310_420/pictures/19/10/16/16/28/5029277.jpg")
        cv.put(col_resim,"asfaltin_krallari")
        cv.put(col_fragman,"https://www.imdb.com/video/vi1737539353/?playlistId=tt1950186&ref_=tt_pr_ov_vi")
        cv.put(col_filmKonu,"Asfaltın Kralları, 1966 yılında düzenlenen Le Mans 24 Saat Yarışı’nın gerçek hikayesini konu " +
                            "alıyor. Henry Ford II ve Lee Iacocca ikilisi, tuhaf ancak kararlı bir grup Amerikalı mühendis ve" +
                            " tasarımcıdan sıfırdan bir otomobil yapmalarını ister. Otomotiv vizyoneri Carroll Shelby " +
                            "(Matt Damon) ve İngiliz şoförü Ken Miles’ın (Christian Bale) başını çektiği ekip, uzun yıllardır" +
                            " pistlere egemen olan Ferrari’yi Fransa’da düzenlenen 1966 Le Mans Dünya Şampiyonası’nda alt " +
                            "etmek için işe koyulur.")
        cv.put(col_filmPuan,8.1)
        cv.put(col_filmSuresi,"2 Saat 32 Dakika")
        cv.put(col_filmVizyonGirisYili,2019)
        cv.put(col_filmYonetmen,"James Mangold")
        cv.put(col_filmKategorisi,"Aksiyon Biyografi Drama")
        cv.put(col_filmBasrol,"Matt Damon, Christian Bale, Jon Bernthal")
        db.insert(table_film,"11",cv)

        cv.put(col_filmAdi,"Parazit")
        //cv.put(col_resim,"https://tr.web.img4.acsta.net/c_310_420/pictures/20/02/13/09/53/3464479.jpg")
        cv.put(col_resim,"parazit")
        cv.put(col_fragman,"https://www.imdb.com/video/vi1015463705/?playlistId=tt6751668&ref_=tt_pr_ov_vi")
        cv.put(col_filmKonu,"Park Ailesi'yle tanışın: soyla gelen servetin klasik bir tablosu. Diğer yanda ise Kim Ailesi, " +
                            "sokak zekası bakımından zengin ama başka hiçbir zenginliğe sahip değil. Şans veya kader olsun, " +
                            "bu iki ev halkı bir şekilde bir araya gelir ve Kim ailesi altın bir fırsatın varlığını hemen " +
                            "sezer. Kolej çağındaki Ki-woo tarafından manipülasyon konusunda yetiştirilen Kim çocukları, " +
                            "öğretmen ve sanat terapisti görevleriyle kendilerini Park ailesinin arasına çabucak yerleştirir." +
                            " Kim'ler “vazgeçilmez” lüks hizmetler sunarken, Parklar ise habersizce evlerindeki her şeyi " +
                            "Kim ailesine kaybetmektedir. Ancak kısa sürede bu düzen bir tehditle karşılaşır. Asalak bir " +
                            "misafir Kim ailesinin yeni keşfettikleri konforu tehdit eder hale geldiğinde, vahşi ve zorlayıcı " +
                            "bir üstünlük mücadelesi patlak verir. Bu mücadele Kim ve Park aileleri arasındaki kırılgan " +
                            "ekosistemi yıkmakla tehdit etmektedir...")
        cv.put(col_filmPuan,8.5)
        cv.put(col_filmSuresi,"2 Saat 12 Dakika")
        cv.put(col_filmVizyonGirisYili,2019)
        cv.put(col_filmYonetmen,"Bong Joon Ho")
        cv.put(col_filmKategorisi,"Drama Gerilim")
        cv.put(col_filmBasrol,"Song Kang-ho, Lee Sun-kyun, Cho Yeo-jeong")
        db.insert(table_film,"12",cv)

        cv.put(col_filmAdi,"Yenilmezler: Son Oyun")
        //cv.put(col_resim,"https://tr.web.img3.acsta.net/c_310_420/pictures/19/03/28/11/29/4805705.jpg")
        cv.put(col_resim,"endgame")
        cv.put(col_fragman,"https://www.imdb.com/video/vi2163260441/?playlistId=tt4154796&ref_=tt_ov_vi")
        cv.put(col_filmKonu,"Avengers Infinity War'un ardından pek çok süper kahraman küle dönüşmüştür. Doktor Strange, " +
                            "Gamora, Drax, Mantis, genç Örümcek Adam, Black Panther, Bucky Barnes, Groot, Scarlet Witch, " +
                            "Vision, Star Lord, Maria Hill, The Wasp ve Nick Fury gibi pek çok kahraman, Thanos'un Sonsuzluk " +
                            "Eldiveni'ni ele geçirmesi ve kendi dengesini kurması yüzünden yok olmuştur ve dünya umutsuz " +
                            "haldedir. Dünya üzerinde kalan Black Widow, Kaptan Amerika, Thor ve Hulk kendi yaslarını " +
                            "tutmaktayken, Iron ve Nebula ise kontrol edemedikleri bir uzay gemisinin içinde, uzay boşluğunda " +
                            "sürüklenmektedirler. Süper kahramanlar takımı için işler pek de iyi görünmemektedir. Ancak " +
                            "Kuantum Bölgesi'nden çıkmanın bir yolunu bularak Avengers ekibinin kalan üyelerine giden " +
                            "Ant-Man, yeni bir umut ışığı olacaktır. Daha önce var olduğunu bilmedikleri bölgeler, " +
                            "kahramanlar ve evrenlerin varlığını öğrenen ekip, Thanos'un kurduğu bu çarpık dengeyi " +
                            "değiştirmek ve kendilerinden alınanı geri getirmek için hayatlarının en büyük mücadelesine " +
                            "girişeceklerdir. Hepsi kişisel olarak önem verdikleri şeyleri kaybetmiş olan kahramanlarımız " +
                            "için intikam vakti gelmiştir.")
        cv.put(col_filmPuan,8.4)
        cv.put(col_filmSuresi,"3 Saat 1 Dakika")
        cv.put(col_filmVizyonGirisYili,2019)
        cv.put(col_filmYonetmen,"Anthony Russo, Joe Russo")
        cv.put(col_filmKategorisi,"Aksiyon Macera Drama")
        cv.put(col_filmBasrol,"Robert Downey Jr., Chris Evans, Mark Ruffalo")
        db.insert(table_film,"13",cv)

        cv.put(col_filmAdi,"X-Men : Logan")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"logan")
        cv.put(col_fragman,"https://www.imdb.com/video/vi1946727961/?playlistId=tt3315342&ref_=tt_ov_vi")
        cv.put(col_filmKonu,"Logan: Wolverine'de, yakın gelecekte yaşlanmış ve yorgun olan Wolverine ve Professor X, " +
                            "Meksika sınırında saklanmaktadır. Fakat Logan'ın dünyadan gizlenmesi ve mirası, karanlık güçler " +
                            "tarafından takip edilen genç bir mutant geldiğinde sona erer. Şimdi Wolverine'de genç bir kadın " +
                            "klonunu Nathanial Essex'in liderliğindeki kötü bir organizasyondan korumalıdır." +
                            "Hugh Jackman'ı son kez Wolverine olarak gördüğümüz film Logan'ı James Mangold yönetiyor. " +
                            "Başrollerinde ise Hugh Jackman dışında Boyd Holbrook, Patrick Stewart ve Doris Morgado " +
                            "yer alıyor.")
        cv.put(col_filmPuan,8.1)
        cv.put(col_filmSuresi,"2 Saat 17 Dakika")
        cv.put(col_filmVizyonGirisYili,2017)
        cv.put(col_filmYonetmen,"James Mangold")
        cv.put(col_filmKategorisi,"Aksiyon Drama Bilim-Kurgu")
        cv.put(col_filmBasrol,"Hugh Jackman, Patrick Stewart, Dafne Keen")
        db.insert(table_film,"14",cv)

        cv.put(col_filmAdi,"Gizli Dünya")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"gizli_dunya")
        cv.put(col_fragman,"https://www.imdb.com/video/vi304789529/?ref_=tt_vi_i_2")
        cv.put(col_filmKonu,"Gizli Dünya, 5 yaşındaki oğlu ile küçük bir odaya hapsedilen annenin dramını anlatıyor. " +
                            "Jack ve annesi dört duvarla çevrili kutu gibi bir odada yaşamaktadırlar. Mutfak, banyo, " +
                            "uyku ve televizyonun aynı metrekarede hayat bulduğu bu karanlık ve kasvetli oda, Jack'in " +
                            "tanıdığı tek dünyadır. Televizyon ise onun en iyi arkadaşıdır. Peki ne zamana kadar?.")
        cv.put(col_filmPuan,8.1)
        cv.put(col_filmSuresi,"1 Saat 58 Dakika")
        cv.put(col_filmVizyonGirisYili,2015)
        cv.put(col_filmYonetmen,"Lenny Abrahamson")
        cv.put(col_filmKategorisi,"Drama Gerilim")
        cv.put(col_filmBasrol,"Brie Larson, Jacob Tremblay, Sean Bridgers")
        db.insert(table_film,"15",cv)

        cv.put(col_filmAdi,"Çılgın Max: Öfkeli Yollar")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"mad_max_fury_road")
        cv.put(col_fragman,"https://www.imdb.com/video/vi3047862297/?playlistId=tt1392190&ref_=tt_ov_vi")
        cv.put(col_filmKonu,"Mad Max: Fury Road'da, zorlu geçmişi Mad Max’i hayatta kalmak için en iyi yolun yalnız olmak " +
                            "gerektiğine inandırmıştır. Yine de bir şekilde kendini Furiosa adlı liderlerinin peşinde çorak " +
                            "topraklardaki savaş ortamından, sürekli kaçarak hayatta kalmaya çalışan bir grubun arasında " +
                            "bulur. Yaşadıkları ortamı zalimce yöneten Immortan Joe’dan kaçmaktadırlar ve Joe kendisinden " +
                            "çalınan ve yeri doldurulamayacak derecede önemli kaybının peşindedir. Post-apokaliptik türünün " +
                            "yaratıcısı ve efsanevi “Mad Max” filmlerinin ardındaki usta yönetmen George Miller'ın bir kez " +
                            "daha yönetmen koltuğunda oturduğu Mad Max: Fury Road geri dönüyor! Charlize Theron ve Tom " +
                            "Hardy'nin rol aldığı film, serinin dördüncü bölümü. Filmin diğer başrollerinde; Nicholas " +
                            "Hoult, Hugh Keays-Byrne, Nathan Jones, Josh Helman, Rosie Huntington-Whiteley, Riley Keough, " +
                            "Zoë Kravitz yer alıyor.")
        cv.put(col_filmPuan,8.1)
        cv.put(col_filmSuresi,"2 Saat")
        cv.put(col_filmVizyonGirisYili,2015)
        cv.put(col_filmYonetmen,"George Miller")
        cv.put(col_filmKategorisi,"Aksiyon Macera Bilim-Kurgu")
        cv.put(col_filmBasrol,"Tom Hardy, Charlize Theron, Nicholas Hoult")
        db.insert(table_film,"16",cv)

        cv.put(col_filmAdi,"Yıldızlararası")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"yildizlararasi")
        cv.put(col_fragman,"https://www.imdb.com/video/vi1586278169/?playlistId=tt0816692&ref_=tt_pr_ov_vi")
        cv.put(col_filmKonu,"Yıldızlararası'nda, teknik bilgisi ve becerisi yüksek olan Cooper, geniş mısır " +
                            "tarlalarında çiftçilik yaparak geçinmektedir; amacı iki çocuğuna güvenli bir hayat " +
                            "sunmaktır. Onlarla yaşayan Büyükbaba Donald çocuklara göz kulak olurken, henüz 10 " +
                            "yaşındaki kızı Murph şaşırtıcı bir zekaya sahiptir. Geçmişte bıraktığı biliminsanı " +
                            "kariyerini özleyen Cooper'un karşısına bir gün beklenmedik bir teklif çıkar ve ailesinin, " +
                            "dahası insanlığın güvenliği için zorlu bir karar alması gerekir...")
        cv.put(col_filmPuan,8.6)
        cv.put(col_filmSuresi,"2 Saat 49 Dakika")
        cv.put(col_filmVizyonGirisYili,2014)
        cv.put(col_filmYonetmen,"Christopher Nolan")
        cv.put(col_filmKategorisi,"Macera Drama Bilim-Kurgu")
        cv.put(col_filmBasrol,"Matthew McConaughey, Anne Hathaway, Jessica Chastain")
        db.insert(table_film,"17",cv)

        cv.put(col_filmAdi,"Kayıp Kız")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"kayip_kiz")
        cv.put(col_fragman,"https://www.imdb.com/video/vi1541712921/?ref_=tt_vi_i_2")
        cv.put(col_filmKonu,"Kayıp Kız, karısının aniden ortadan kaybolmasından sorumlu tutulan bir adamın hikayesini " +
                            "anlatıyor. Amerika'nın Missouri eyaletlerinden birinde sıcak bir yaz sabahı, Nick ve Amy " +
                            "evliliklerinin beşinci yıl dönümünü kutlamaya hazırlanmaktadır. Fakat o gün Amy aniden ortadan " +
                            "kaybolur. Amy'den uzun süre haber alınamayınca polis, genç kadının ortadan kaybolması ile ilgili " +
                            "Nick'ten şüphelenmeye başlar. Olanlara bir türlü anlam veremeyen Nick'in ise Amy'nin nerede " +
                            "olduğuna dair hiçbir fikri yoktur. Genç adam bu sırada kendisini Amy'nin ailesinin düzenlediği " +
                            "bir yardım operasyonunun içinde piyon olarak bulur. Nick, masum olduğu konusunda ısrar etse de " +
                            "üstündeki şüpheleri tamamen yok edemez. Amy'nin hayatta olup olmadığı ise büyük bir muammadır...")
        cv.put(col_filmPuan,8.1)
        cv.put(col_filmSuresi,"2 Saat 29 Dakika")
        cv.put(col_filmVizyonGirisYili,2014)
        cv.put(col_filmYonetmen,"David Fincher")
        cv.put(col_filmKategorisi,"Drama Gizem Gerilim")
        cv.put(col_filmBasrol,"Ben Affleck, Rosamund Pike, Neil Patrick Harris")
        db.insert(table_film,"18",cv)

        cv.put(col_filmAdi,"Para Avcısı")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"para_avcisi")
        cv.put(col_fragman,"https://www.imdb.com/video/vi2312218649/?playlistId=tt0993846&ref_=tt_pr_ov_vi")
        cv.put(col_filmKonu,"Para Avcısı, gerçek bir yaşam öyküsünü konu ediyor. Jordan Belfort 24 yaşında genç ve hırslı " +
                            "bir adamdır. Para kazanma arzusuyla Wall Street borsasında önce komisyoncu ve ardından Stratton Oakmont " +
                            "adında bir yatırımcı firmasında zengin olmak için her şeyi yapmaya hazır bir CEO olur. 90'ların en hızlı " +
                            "günleridir ve New York işlem salonunda her şey olabilmektedir. Önemsiz tahvillerle birçok yatırımcıyı " +
                            "aldatarak, Belfort kısa zamanda bir para makinasına ve aynı zamanda bir harcama makinasına dönüşür. Bir " +
                            "günde hesapları milyon dolarlarla doldururken o gece hepsini aynı hızda harcayabilir. Profesyonel hayatının " +
                            "yanı sıra uyuşturucu, fahişeler, son derece pahalı lüks fantezilerle dolu kirli bir oyunun içindedir. Bu " +
                            "karakterin hayatındaki her şey abartılı bir şekilde devam ederken, çöküş ise çok uzakta değildir...")
        cv.put(col_filmPuan,8.2)
        cv.put(col_filmSuresi,"3 Saat")
        cv.put(col_filmVizyonGirisYili,2013)
        cv.put(col_filmYonetmen,"Martin Scorsese")
        cv.put(col_filmKategorisi,"Drama Gizem Gerilim")
        cv.put(col_filmBasrol,"Leonardo DiCaprio, Jonah Hill, Margot Robbie")
        db.insert(table_film,"19",cv)

        cv.put(col_filmAdi,"Tutsak")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"prisoners")
        cv.put(col_fragman,"https://www.imdb.com/video/vi497723673/?ref_=tt_vi_i_2")
        cv.put(col_filmKonu,"Tutsak, adaleti kendi elleriyle sağlamaya çalışan bir adamın hikayesini anlatıyor. " +
                            "Maccachusetts eyaletinin Brockton bölgesinde, Şükran Günü'nü kutlamak için bir araya gelen Dovers ve " +
                            "Birches aileleri her şeyin yolunda gittiği bu yemek esnasında korkunç bir haberle altüst olurlar. " +
                            "Gecenin ilerleyen saatlerinde ailelerin iki küçük kızlarının kaybolması sonrasında panik dolu anlar yaşanır. " +
                            "Saatler ilerler, ancak kızlar halen daha ortada yoktur ve durum kaçırılmış oldukları gerçeğini " +
                            "kuvvetlendirir. Polise başvursalar da hızlı ve nitelikli bir sonuç alamazlar. Keller Dover ise bir hayli " +
                            "panik içerisindedir ve polisin çabalarını yetersiz bulup adaleti kendi elleriyle aramaya karar verir. " +
                            "Genç ve başarılı dedektif Loki'den de yardım isteyen genç adam, kendini suçlu ve masumun birbirine " +
                            "karıştığı oldukça şaibeli bir davanın içerisinde bulur.")
        cv.put(col_filmPuan,8.1)
        cv.put(col_filmSuresi,"2 Saat 33 Dakika")
        cv.put(col_filmVizyonGirisYili,2013)
        cv.put(col_filmYonetmen,"Denis Villeneuve")
        cv.put(col_filmKategorisi,"Suç Drama Gizem")
        cv.put(col_filmBasrol,"Hugh Jackman, Jake Gyllenhaal, Viola Davis")
        db.insert(table_film,"20",cv)

        cv.put(col_filmAdi,"Başlangıç")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"baslangic")
        cv.put(col_fragman,"https://www.imdb.com/video/vi2959588889/?playlistId=tt1375666&ref_=tt_pr_ov_vi")
        cv.put(col_filmKonu,"Uzmanlık alanı, zihnin en karanlık ve savunmasız olduğu rüya görme anında, bilinçaltının " +
                            "derinliklerindeki değerli sırları çekip çıkarmak ve onları çalmaktır. Cobb'un bu nadir " +
                            "insanlarda görülebilecek yeteneği, bu ender rastgelinebilecek mahareti, onu kurumsal casusluğun " +
                            "tehlikeli yeni dünyasında aranan bir oyuncu yapmıştır. Aynı zamanda bu durum onu uluslararası " +
                            "bir kaçak yapmış ve sevdiği herşeye malolmuştur. Cobb'a içinde bulunduğu durumdan kurtulmasını " +
                            "sağlayacak bir fırsat sunulur. Ona hayatını geri verebilecek son bir iş; tabi eğer imkansız " +
                            "'başlangıç'ı tamamlayabilirse. Mükemmel soygun yerine, Cobb ve takımındaki profesyoneller bu " +
                            "sefer tam tersini yapmak zorundadır; görevleri bir fikri çalmak değil onu yerleştirmektir. " +
                            "Eğer başarırlarsa, mükemmel suç bu olacaktır.")
        cv.put(col_filmPuan,8.8)
        cv.put(col_filmSuresi,"2 Saat 28 Dakika")
        cv.put(col_filmVizyonGirisYili,2010)
        cv.put(col_filmYonetmen,"Christopher Nolan")
        cv.put(col_filmKategorisi,"Aksiyon Macera Bilim-Kurgu")
        cv.put(col_filmBasrol,"Leonardo DiCaprio, Joseph Gordon-Levitt, Elliot Page")
        db.insert(table_film,"21",cv)

        cv.put(col_filmAdi,"VOL-i")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"vol_i")
        cv.put(col_fragman,"https://www.imdb.com/video/vi2192703769/?playlistId=tt0910970&ref_=tt_pr_ov_vi")
        cv.put(col_filmKonu,"Vol.i, günümüzden çok uzak bir gelecekte geçmektedir. Filmde, insanoğlu aşırı kirlenme " +
                            "sebebiyle Dünya’yı terk edip başka bir gezegende yaşamaya başlamıştır. Çöplerle çevrili " +
                            "dünyayı temizleme görevi sevimli bir robota, Vol.i’ye verilir. İnsanoğlunun bıraktığı " +
                            "çöplerden kendine yeni bir dünya yaratan yalnız Vol.i’nin yalnızlığı, başka bir robot olan " +
                            "Eve’nın gelmesiyle son bulur. İki sevimli robotun arasında filizlenen dokunaklı ilişki " +
                            "türlü zorluklara rağmen direnmeye ve ayakta kalmaya çalışır.")
        cv.put(col_filmPuan,8.4)
        cv.put(col_filmSuresi,"1 Saat 38 Dakika")
        cv.put(col_filmVizyonGirisYili,2008)
        cv.put(col_filmYonetmen,"Andrew Stanton")
        cv.put(col_filmKategorisi,"Animasyon Macera Aile")
        cv.put(col_filmBasrol,"Andrew Stanton, Pete Docter, Jim Reardon")
        db.insert(table_film,"22",cv)

        cv.put(col_filmAdi,"Ratatuy")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"ratatuy")
        cv.put(col_fragman,"https://www.imdb.com/video/vi906147865/?playlistId=tt0382932&ref_=tt_ov_vi")
        cv.put(col_filmKonu,"Şişman fare Remy, yemeğe olan düşkünlüğü nedeniyle tek bir hayale sahiptir: Aşçı olabilmek! " +
                            "Bu idealini gerçekleştirmek için Paris'in yolunu tutan sevimli fare kaza eseri kendisini " +
                            "şehrin en iyi restoranının kanalizasyonunda bulur. Restoranın yeni çöpçüsüyle ilginç bir " +
                            "anlaşma yapan Remy, tüm hünerlerini sergileyebileceği büyülü mutfağa ulaşmaştır. Ancak bu " +
                            "tek hayalini gerçekleştirmek, farelerden iğrenen bir insanlığın var olduğu bir dünyada pek " +
                            "kolay olmayacaktır.")
        cv.put(col_filmPuan,8.1)
        cv.put(col_filmSuresi,"1 Saat 51 Dakika")
        cv.put(col_filmVizyonGirisYili,2007)
        cv.put(col_filmYonetmen,"Brad Bird, Jan Pinkava")
        cv.put(col_filmKategorisi,"Animasyon Macera Komedi")
        cv.put(col_filmBasrol,"Brad Garrett, Lou Romano, Patton Oswalt")
        db.insert(table_film,"23",cv)

        cv.put(col_filmAdi,"The Gray Man")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"the_gray_man")
        cv.put(col_fragman,"https://www.imdb.com/video/vi941736473/?playlistId=tt1649418&ref_=tt_ov_vi")
        cv.put(col_filmKonu,"CIA ajanı Court Gentry, sessiz ve profesyonel suikastleriyle yaşayan bir efsaneye dönüşmek " +
                            "üzeredir, ancak üstleri tarafından verilen bir kararla avlayandan avlanana dönüşür. Court " +
                            "Gentry'nin kendine kurulan komployu açığa çıkarması ise çok uzun sürmeyecektir. Her şeye " +
                            "rağmen müşterisinin hayatını kurtarmaya çalışan Gentry, eş zamanlı olarak kendi ailesinin " +
                            "emniyette olmasını da sağlamak zorundadır.")
        cv.put(col_filmPuan,6.5)
        cv.put(col_filmSuresi,"2 Saat 2 Dakika")
        cv.put(col_filmVizyonGirisYili,2022)
        cv.put(col_filmYonetmen,"Anthony Russo, Joe Russo")
        cv.put(col_filmKategorisi,"Aksiyon Gerilim")
        cv.put(col_filmBasrol,"Ryan Gosling, Chris Evans, Ana de Armas")
        db.insert(table_film,"24",cv)

        cv.put(col_filmAdi,"Hızlı ve  Öfkeli 9 : Hız Efsanesi")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"hizli_ve_ofkeli")
        cv.put(col_fragman,"https://www.imdb.com/video/vi1903936793/?playlistId=tt5433138&ref_=tt_pr_ov_vi")
        cv.put(col_filmKonu,"Hızlı ve Öfkeli 9, geçmişinden gelen bir tehdide karşı mücadele etmek zorunda kalan Dom ve " +
                            "ekibinin hikayesini konu ediyor. Dominic Toretto’nun artık tek önceliği oğlu Brian’ı " +
                            "korumaktır. Oğlu ve Letty ile birlikte gözlerden uzak sakin bir yaşam süren Toretto, " +
                            "istese de geçmişinden kurtulamaz. Bu kez geçmişi ile yüzleşmek zorunda kalan Toretto, " +
                            "siber suçlu Cipher ile birlikte çalışan terk edilmiş kardeşi Jakob’a karşı savaşmak " +
                            "zorunda kalır. Ekibi ile yeniden bir araya gelen Dom, kardeşinin lideri olduğu dünyayı " +
                            "yıkacak olan planı durdurmak için zorlu bir mücadeleye girişir.")
        cv.put(col_filmPuan,5.2)
        cv.put(col_filmSuresi,"2 Saat 23 Dakika")
        cv.put(col_filmVizyonGirisYili,2021)
        cv.put(col_filmYonetmen,"Justin Lin")
        cv.put(col_filmKategorisi,"Aksiyon Suç Gerilim")
        cv.put(col_filmBasrol,"Vin Diesel, Michelle Rodriguez, Jordana Brewster")
        db.insert(table_film,"25",cv)

        cv.put(col_filmAdi,"Kayıp")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"kayip")
        cv.put(col_fragman,"https://www.imdb.com/video/vi2927936793/?playlistId=tt10855768&ref_=tt_ov_vi")
        cv.put(col_filmKonu,"Annesi yeni erkek arkadaşıyla Kolombiya'da tatilde gizemli bir şekilde ortadan kaybolunca, " +
                            "June'un cevap arayışı uluslararası bürokrasi tarafından engellenir. Annesinden binlerce mil " +
                            "uzakta Los Angeles'ta olan June, çok geç olmadan onu bulmak için parmaklarının ucundaki " +
                            "en son teknolojiyi yaratıcı bir şekilde kullanır. Ancak daha derine indikçe, dijital " +
                            "hafiyeliği cevaplardan çok soruları gündeme getirir. June annesiyle ilgili sırları " +
                            "çözdüğünde, onu aslında hiç tanımadığını keşfeder.")
        cv.put(col_filmPuan,7.1)
        cv.put(col_filmSuresi,"1 Saat 51 Dakika")
        cv.put(col_filmVizyonGirisYili,2023)
        cv.put(col_filmYonetmen,"Nicholas D. Johnson, Will Merrick")
        cv.put(col_filmKategorisi,"Drama Gizem Suç")
        cv.put(col_filmBasrol, "Tim Griffin, Ava Zaria Lee, Nia Long")
        db.insert(table_film,"26",cv)

        cv.put(col_filmAdi,"Küçük İpuçları")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"kucuk_ipuclari")
        cv.put(col_fragman,"https://www.imdb.com/video/vi672579865/?playlistId=tt10016180&ref_=tt_ov_vi")
        cv.put(col_filmKonu,"Little Things, kurallara son derece bağlı bir polis ile onun tam zıttı bir karaktere sahip, " +
                            "suçluları yakalamak için kuralları çiğnemeye yatkın olan ortağının, bir seri katili yakalamak " +
                            "için giriştiği zorlu mücadeleyi konu ediyor.")
        cv.put(col_filmPuan,6.3)
        cv.put(col_filmSuresi,"2 Saat 8 Dakika")
        cv.put(col_filmVizyonGirisYili,2021)
        cv.put(col_filmYonetmen,"John Lee Hancock")
        cv.put(col_filmKategorisi,"Drama Gizem Suç")
        cv.put(col_filmBasrol, "Denzel Washington, Rami Malek, Jared Leto")
        db.insert(table_film,"27",cv)

        cv.put(col_filmAdi,"İntikam Vakti")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"intikam_vakti")
        cv.put(col_fragman,"https://www.imdb.com/video/vi3455041561/?playlistId=tt11083552&ref_=tt_ov_vi")
        cv.put(col_filmKonu,"İntikam Vakti, oğlunun intikamını almaya çalışan gizemli bir adamın hikayesini konu ediyor. " +
                            "H, varlıklı ve güçlü bir adamdır. Ancak tüm bunlara rağmen o, her hafta milyonlarca dolar " +
                            "taşıyan zırhlı bir araç şirketinde özel güvenlik görevlisi olarak işe başlar. H, " +
                            "olağanüstü yetenekleri sayesinde soygun girişimlerini engeller. Herkes onun " +
                            "çalışmasından memnundur ancak kimse onun neden bu şirkete girdiğini bilmemektedir. " +
                            "H'nin şirkette çalışmasının tek nedeni ise oğlunun ölümüne neden olanlardan intikam almaktır.")
        cv.put(col_filmPuan,7.1)
        cv.put(col_filmSuresi,"1 Saat 59 Dakika")
        cv.put(col_filmVizyonGirisYili,2021)
        cv.put(col_filmYonetmen,"Guy Ritchie")
        cv.put(col_filmKategorisi,"Aksiyon Suç Gerilim")
        cv.put(col_filmBasrol, "Jason Statham, Holt McCallany, Josh Hartnett")
        db.insert(table_film,"28",cv)

        cv.put(col_filmAdi,"Çığlık 6")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"ciglik_6")
        cv.put(col_fragman,"https://www.imdb.com/video/vi964216089/?playlistId=tt17663992&ref_=tt_ov_vi")
        cv.put(col_filmKonu,"Çığlık 6, Hayalet Maskeli cinayetlerinden kurtulduktan sonra Woodsboro kasabasını " +
                            "geride bırakıp New York'ta yeni bir hayata başkayan dört kişinin hayatına odaklanıyor. " +
                            "Son Hayalet Maskeli cinayetlerinin ardından, hayatta kalan dört kişi, artık hayatlarında " +
                            "yeni bir sayfa açmak ister ve bu amaçla Woodsboro kasabasından ayrılır. New York'ta " +
                            "kendilierine yeni bir düzen kuran 4 kişi, her ne kadar kasabayı arkalarında bırakmış " +
                            "olsalar da maskeli katilden kurtulmayı başarabilecekler midir?")
        cv.put(col_filmPuan,6.7)
        cv.put(col_filmSuresi,"2 Saat 2 Dakika")
        cv.put(col_filmVizyonGirisYili,2023)
        cv.put(col_filmYonetmen,"Matt Bettinelli-Olpin, Tyler Gillett")
        cv.put(col_filmKategorisi,"Korku Gizem Gerilim")
        cv.put(col_filmBasrol, "Courteney Cox, Melissa Barrera, Jenna Ortega")
        db.insert(table_film,"29",cv)

        cv.put(col_filmAdi,"Yedi")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"yedi")
        cv.put(col_fragman,"https://www.imdb.com/video/vi2508831257/?playlistId=tt0114369&ref_=tt_pr_ov_vi")
        cv.put(col_filmKonu,"Yedi, seri cinayetler işleyen bir katilin peşine düşen iki polis dedektifinin hikayesini " +
                            "anlatıyor. Bir seri katil 7 ölümcül günahı işleyenleri kendi yöntemleriyle öldürmektedir. " +
                            "Yedi Ölümcül Günah, Hıristiyanlık inançlarına göre Kibir, Açgözlülük, Şehvet Düşkünlüğü," +
                            "Kıskançlık, Oburluk,Yıkıcılık ve Tembellik'tir. İki polis dedektifi bu seri katilin peşindedir.")
        cv.put(col_filmPuan,8.6)
        cv.put(col_filmSuresi,"2 Saat 7 Dakika")
        cv.put(col_filmVizyonGirisYili,1995)
        cv.put(col_filmYonetmen,"David Fincher")
        cv.put(col_filmKategorisi,"Suç Drama Gizem")
        cv.put(col_filmBasrol,"Morgan FreemanBrad PittKevin Spacey")
        db.insert(table_film,"30",cv)

        cv.put(col_filmAdi,"Prestij")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"prestij")
        cv.put(col_fragman,"https://www.imdb.com/video/vi2885334553/?playlistId=tt0482571&ref_=tt_pr_ov_vi")
        cv.put(col_filmKonu,"Prestij, birbirini alt etmeye çalışan iki sihirbazın hikayesini anlatıyor. 19.yy sonlarında " +
                            "Londra’da Robert Angier, sevgili eşi Julia McCullough ve Alfred Borden hem arkadaştırlar " +
                            "hem de bir sihirbazın asistanlarıdırlar. Bir gösteri esnasında Julia ölünce Robert, onun " +
                            "ölümünden Alfred’i suçlar ve birbirlerine düşman olurlar. Zaman içinde ikisi de hem " +
                            "ünlü olurlar hem de rakip sihirbazlara dönüşerek birbirlerinin sahne üstünde performansını " +
                            "sabote etmeye kalkışırlar. Alfred başarılı bir hile yapınca Robert, rakibinin sırrını " +
                            "çözmek konusunu takıntı halinde getirir ve trajik olaylar birbirini kovalar.")
        cv.put(col_filmPuan,8.5)
        cv.put(col_filmSuresi,"2 Saat 10 Dakika")
        cv.put(col_filmVizyonGirisYili,2006)
        cv.put(col_filmYonetmen,"Christopher Nolan")
        cv.put(col_filmKategorisi,"Drama Gizem Bilim-Kurgu")
        cv.put(col_filmBasrol,"Christian Bale, Hugh Jackman, Scarlett Johansson")
        db.insert(table_film,"31",cv)

        cv.put(col_filmAdi,"Tumbbad")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"tumbbad")
        cv.put(col_fragman,"https://www.imdb.com/video/vi2075965977/?playlistId=tt8239946&ref_=tt_ov_vi")
        cv.put(col_filmKonu,"Vinayak, 1920’li yılların başında annesi ve kardeşiyle beraber Tumbbad’da yaşamaktadır. " +
                            "Geçim sıkıntısı yaşayan aile, ufak tefek işlerle para kazanmaya çalışır. Evin büyükannesi, " +
                            "zincirli bir odada yaşamaktadır ve onun yanına sadece Vinayak’ın annesi girer. Köylüler, " +
                            "Vinayak ve ailesinin yaşadığı konakta altın olduğunu düşünür ve onlara göre altının yerini " +
                            "bilen tek kişi büyükannedir. Vinayak, bir gün büyükannesini beslemek zorunda kalınca ilk " +
                            "defa gizemli odaya girme imkanı bulur. Ancak burada pek de hatırlamak istemediği " +
                            "korkunç tecrübeler edinir. Yaşananların ardından annesi tarafından zorla evden " +
                            "uzaklaştırılan Vinayak Tumbbad' ı terk eder. Ancak şimdi aradan geçen on beş yılın ardından " +
                            "geçmişte bahsedilen altınları bulma ümidiyle köyüne geri döner. Ailesi hakkındaki karanlık " +
                            "sırrı öğrenir altınları bulan genç adam servet sahibi olur. Aradan geçen yıllar onun daha " +
                            "da açgözlü olmasına neden olur. Para için yeniden Tumbbad yolunu tutan Vinayak’a bu sefer " +
                            "oğlu da eşlik edecektir. Ama bu sefer onları kötü bir sürpriz beklemektedir.")
        cv.put(col_filmPuan,8.2)
        cv.put(col_filmSuresi,"1 Saat 44 Dakika")
        cv.put(col_filmVizyonGirisYili,2018)
        cv.put(col_filmYonetmen,"Rahi Anil Barve, Anand Gandhi, Adesh Prasad")
        cv.put(col_filmKategorisi,"Drama Fantezi Korku")
        cv.put(col_filmBasrol,"Sohum Shah, Jyoti Malshe, Anita Date-Kelkar")
        db.insert(table_film,"32",cv)

        cv.put(col_filmAdi,"Tumbbad")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"tumbbad")
        cv.put(col_fragman,"https://www.imdb.com/video/vi2075965977/?playlistId=tt8239946&ref_=tt_ov_vi")
        cv.put(col_filmKonu,"Vinayak, 1920’li yılların başında annesi ve kardeşiyle beraber Tumbbad’da yaşamaktadır. " +
                "Geçim sıkıntısı yaşayan aile, ufak tefek işlerle para kazanmaya çalışır. Evin büyükannesi, " +
                "zincirli bir odada yaşamaktadır ve onun yanına sadece Vinayak’ın annesi girer. Köylüler, " +
                "Vinayak ve ailesinin yaşadığı konakta altın olduğunu düşünür ve onlara göre altının yerini " +
                "bilen tek kişi büyükannedir. Vinayak, bir gün büyükannesini beslemek zorunda kalınca ilk " +
                "defa gizemli odaya girme imkanı bulur. Ancak burada pek de hatırlamak istemediği " +
                "korkunç tecrübeler edinir. Yaşananların ardından annesi tarafından zorla evden " +
                "uzaklaştırılan Vinayak Tumbbad' ı terk eder. Ancak şimdi aradan geçen on beş yılın ardından " +
                "geçmişte bahsedilen altınları bulma ümidiyle köyüne geri döner. Ailesi hakkındaki karanlık " +
                "sırrı öğrenir altınları bulan genç adam servet sahibi olur. Aradan geçen yıllar onun daha " +
                "da açgözlü olmasına neden olur. Para için yeniden Tumbbad yolunu tutan Vinayak’a bu sefer " +
                "oğlu da eşlik edecektir. Ama bu sefer onları kötü bir sürpriz beklemektedir.")
        cv.put(col_filmPuan,8.2)
        cv.put(col_filmSuresi,"1 Saat 44 Dakika")
        cv.put(col_filmVizyonGirisYili,2018)
        cv.put(col_filmYonetmen,"Rahi Anil Barve, Anand Gandhi, Adesh Prasad")
        cv.put(col_filmKategorisi,"Drama Fantezi Korku")
        cv.put(col_filmBasrol,"Sohum Shah, Jyoti Malshe, Anita Date-Kelkar")
        db.insert(table_film,"33",cv)

        cv.put(col_filmAdi,"Şeytanı Gördüm")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"seytani_gordum")
        cv.put(col_fragman,"https://www.imdb.com/video/vi3158612505/?playlistId=tt1588170&ref_=tt_pr_ov_vi")
        cv.put(col_filmKonu,"Kyung-chul sırf kişisel tatmin için insanların canını kıymaktan çekinmeyen psikopat bir " +
                            "seri-katildir. O kadar vahşi ve acımasızdır kadın, çocuk, yaşlı ayırt etmeden herkesi " +
                            "öldürebilir ve bu haliyle polis için daha da zor, bir sonraki adımı tahmin edilemez bir " +
                            "hedef haline gelmiştir. Fakat emekli bir komiserin kızı olan Joo-yeon’u kaçırınca " +
                            "Kyung-chul bütün hıncı da kendisine çekecektir.Yetenekli, gizli bir ajan olan Dae-hoon ise " +
                            "vahşi biçimde öldürülen genç kadının nişanlısıdır. Sevdiği kadının hunharca öldürülmesi " +
                            "Dae-hoon'u tek başına bu vahşi seri katile karşı harekete geçirecektir... Canavarı " +
                            "engellemek için kendisi bir canavara dönüşmeye hazırdır...")
        cv.put(col_filmPuan,7.8)
        cv.put(col_filmSuresi,"2 Saat 24 Dakika")
        cv.put(col_filmVizyonGirisYili,2010)
        cv.put(col_filmYonetmen,"Jee-woon Kim")
        cv.put(col_filmKategorisi,"Aksiyon Suç Drama")
        cv.put(col_filmBasrol,"Lee Byung-hun, Choi Min-sik, Jeon Gook-hwan")
        db.insert(table_film,"34",cv)

        cv.put(col_filmAdi,"Kapan")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"kapan")
        cv.put(col_fragman,"https://www.imdb.com/video/vi2005186073/?playlistId=tt5052448&ref_=tt_pr_ov_vi")
        cv.put(col_filmKonu,"Chris sıradan bir siyahi gençtir. Sevgilisi Rose ile mutlu bir ilişkisi vardır. Rose " +
                            "bir gün Chris'i ailesinin mülküne davet eder. Avrupalı Amerikalı sevgilisinin ailesinde " +
                            "bir tuhaflık olduğunu sezen Chris bir süre sonra mülkte kalmakta olan siyahi kişilerin " +
                            "bir bir kaybolduğunu öğrenir. Bunun üzerine tuz biber olan şey ise başka bir siyahi " +
                            "adamın kendisine iş işten geçmeden gitmesi yönündeki uyarısı olur. Chris bir şeylerin " +
                            "ters gittiğini sezmeye başladığında kurtulması sandığından çok daha zor olacaktır...")
        cv.put(col_filmPuan,7.7)
        cv.put(col_filmSuresi,"1 Saat 44 Dakika")
        cv.put(col_filmVizyonGirisYili,2017)
        cv.put(col_filmYonetmen,"Jordan Peele")
        cv.put(col_filmKategorisi,"Korku Gizem Gerilim")
        cv.put(col_filmBasrol,"Daniel Kaluuya, Allison Williams, Bradley Whitford")
        db.insert(table_film,"35",cv)

        cv.put(col_filmAdi,"Kulübeye Tıklat")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"kulubeye_tiklat")
        cv.put(col_fragman,"https://www.imdb.com/video/vi2012202265/?playlistId=tt15679400&ref_=tt_pr_ov_vi")
        cv.put(col_filmKonu,"Kulübeye Tıklat, keyifli başlayan tatilleri, kapılarında dört yabancının bitmesiyle " +
                            "kabusa dönen bir ailenin hayatına odaklanıyor. Eric ve Andrew, evlatlık kızlarıyla Wen " +
                            "ile birlikte New Hampshire kırsalındaki ücra bir kulübede tatile çıkar. Onların hayatı, " +
                            "silahlı dört yabancı tarafından rehin alınmalarıyla bambaşka bir hal alır. Aileden, " +
                            "kıyametin önlenmesi için zorlu bir seçim yapması istenir. Dış dünya ile bağlantıları " +
                            "olmayana aile üyeleri, her şeylerini kaybetmeden önce zor bir karar vermek zorundadır.")
        cv.put(col_filmPuan,7.7)
        cv.put(col_filmSuresi,"1 Saat 40 Dakika")
        cv.put(col_filmVizyonGirisYili,2023)
        cv.put(col_filmYonetmen,"M. Night Shyamalan")
        cv.put(col_filmKategorisi,"Korku Gizem Gerilim")
        cv.put(col_filmBasrol,"Dave Bautista, Jonathan Groff, Ben Aldridge")
        db.insert(table_film,"36",cv)

        cv.put(col_filmAdi,"Solgun Mavi Gözler")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"solgun_mavi_gozler")
        cv.put(col_fragman,"https://www.imdb.com/video/vi3371943193/?playlistId=tt14138650&ref_=tt_pr_ov_vi")
        cv.put(col_filmKonu,"Solgun Mavi Gözler, genç bir öğrencinin yardımıyla West Point'teki ABD Askeri Akademisi'nde " +
                            "meydana gelen gizemli cinayetleri çözmeye çalışan kıdemli bir dedektifin hikayesini " +
                            "konu ediyor.")
        cv.put(col_filmPuan,6.6)
        cv.put(col_filmSuresi,"2 Saat 8 Dakika")
        cv.put(col_filmVizyonGirisYili,2022)
        cv.put(col_filmYonetmen,"Scott Cooper")
        cv.put(col_filmKategorisi,"Suç Korku Gizem")
        cv.put(col_filmBasrol,"Christian Bale, Harry Melling, Simon McBurney")
        db.insert(table_film,"37",cv)

        cv.put(col_filmAdi,"M3GAN")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"m3gan")
        cv.put(col_fragman,"https://www.imdb.com/video/vi4277912857/?playlistId=tt8760708&ref_=tt_pr_ov_vi")
        cv.put(col_filmKonu,"M3GAN dahi bir oyuncak şirketinde çalışan robotik mühendisi olan bir adamın hikayesini " +
                            "konu ediyor. Mühendis, yakın zamanda yetim kalmış olan yeğenine destek olması ve arkadaşlık " +
                            "etmesi için bir robot tasarlar. Ancak küçük kız ile duygusal olarak bağ kurmaya " +
                            "programlanmış insansı robot, programlaması fazla iyi işlediği için yeni arkadaşı konusunda " +
                            "aşırı korumacı bir hale gelir. Bu korkutucu bağlanma durumu pek çok tehlikeyi de beraberinde " +
                            "getirir.")
        cv.put(col_filmPuan,6.4)
        cv.put(col_filmSuresi,"1 Saat 42 Dakika")
        cv.put(col_filmVizyonGirisYili,2022)
        cv.put(col_filmYonetmen,"Gerard Johnstone")
        cv.put(col_filmKategorisi,"Korku Bilim-Kurgu Gerilim")
        cv.put(col_filmBasrol,"Allison Williams, Violet McGraw, Ronny Chieng")
        db.insert(table_film,"38",cv)

        cv.put(col_filmAdi,"Bay Harrigan'ın Telefonu")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"bay_harrigan_telefonu")
        cv.put(col_fragman,"https://www.imdb.com/video/vi4032479769/?playlistId=tt12908110&ref_=tt_ov_vi")
        cv.put(col_filmKonu,"Mr. Harrigan's Phone, kendisinden yaşa büyük münzevi milyarder Bay Harrigan ile arkadaş olan " +
                            "genç bir çocuğa odaklanıyor. Bay Harrigan öldüğünde, çocuk ölen arkadaşı henüz gömülmeden " +
                            "onun cebine bir telefon yerleştirir. Arkadaşına sesli mesajlar bırakan çocuğun hayatı, " +
                            "bir gün arkadaşından cevap almasıyla bambaşka bir hal alır.")
        cv.put(col_filmPuan,6.0)
        cv.put(col_filmSuresi,"1 Saat 44 Dakika")
        cv.put(col_filmVizyonGirisYili,2022)
        cv.put(col_filmYonetmen,"John Lee Hancock")
        cv.put(col_filmKategorisi,"Drama Korku Gizem")
        cv.put(col_filmBasrol, "Donald Sutherland, Jaeden Martell, Joe Tippett")
        db.insert(table_film,"38",cv)

        cv.put(col_filmAdi,"Gülümse")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"gulumse")
        cv.put(col_fragman,"https://www.imdb.com/video/vi1028112921/?playlistId=tt15474916&ref_=tt_pr_ov_vi")
        cv.put(col_filmKonu,"Gülümse, yaşadığı tuhaf olaylardan kurtulmak için sorunlu geçmişiyle yüzleşmek zorunda " +
                            "olan bir kadının hayatına odaklanıyor. Bir hastayı içeren tuhaf, travmatik bir olaya " +
                            "tanık olan Dr. Rose Cotter, açıklayamadığı korkutucu olaylar yaşamaya başlar. Bu karşı " +
                            "konulmaz derecede bunaltıcı olan dehşet hayatını ele geçirmeye başlarken, Rose hayatta " +
                            "kalmak ve korkunç yeni gerçekliğinden kaçmak için sıkıntılı geçmişiyle yüzleşmek zorundadır.")
        cv.put(col_filmPuan,6.5)
        cv.put(col_filmSuresi,"1 Saat 55 Dakika")
        cv.put(col_filmVizyonGirisYili,2022)
        cv.put(col_filmYonetmen,"Parker Finn")
        cv.put(col_filmKategorisi,"Korku Gizem Gerilim")
        cv.put(col_filmBasrol,"Sosie Bacon, Jessie T. Usher, Kyle Gallner")
        db.insert(table_film,"39",cv)

        cv.put(col_filmAdi,"Dune: Çöl Gezegeni")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"dun_col_gezegeni")
        cv.put(col_fragman,"https://www.imdb.com/video/vi3986080537/?playlistId=tt1160419&ref_=tt_pr_ov_vi")
        cv.put(col_filmKonu,"Uzak bir gelecekte geçen Dune, ailesi çöl gezegeni Arrakis’in kontrolüne sahip olan Paul " +
                            "Atreides’in hikayesini anlatıyor. Galaksinin farklı noktalarındaki gezegenler, rakip " +
                            "feodal aileler tarafından yönetilmektedir. Çok değerli bir kaynağın tek üreticisi olan çöl " +
                            "gezegeni Arrakis'in kontrolü asil aileler arasında son derece talep görmektedir. Baharat adı " +
                            "verilen bu kaynak, yüksek bilinç ve uzun bir yaşam süresi sunarken, beraberinde çok ciddi " +
                            "yan etkileri de getirmektedir. Ayrıca yıldızlararası yollarda gezinmeye yardımcı olan kaynak " +
                            "da bu baharat'tır. Bu kaynağı elde etmek isteyen feodal rakiplerden Harkonen ailesi tarafından " +
                            "Paul ve ailesine tuzak kurulur. Bu tuzağın sonucunda Paul'un ailesi darmadağın olarak firari " +
                            "hale gelir. Paul, ailesinin Arrakis kontrolünü yeniden kazanması için bir isyan başlatırken, " +
                            "tüm evrenin seyrini değiştirebilme ihtimalini yakalayacaktır.")
        cv.put(col_filmPuan,8.0)
        cv.put(col_filmSuresi,"2 Saat 35 Dakika")
        cv.put(col_filmVizyonGirisYili,2021)
        cv.put(col_filmYonetmen,"Denis Villeneuve")
        cv.put(col_filmKategorisi,"Aksiyon Macera Drama")
        cv.put(col_filmBasrol,"Timothée Chalamet, Rebecca Ferguson, Zendaya")
        db.insert(table_film,"40",cv)

        cv.put(col_filmAdi,"Yenilmezler")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"yenilmezler")
        cv.put(col_fragman,"https://www.imdb.com/video/vi1891149081/?playlistId=tt0848228&ref_=tt_ov_vi")
        cv.put(col_filmKonu,"SHIELD adıyla tanınan uluslararası barış örgütünün başındaki isim Nick Fury, " +
                            "tüm dünyanın güvenliğine karşı büyük bir tehdit oluşturan düşmanla karşı karşıya kalır. " +
                            "Fury, dünyayı yaklaşan bu felaketten kurtarmak için en cesur ve en 'süper' " +
                            "kahramanlardan oluşan bir ekip kurmak zorundadır...")
        cv.put(col_filmPuan,8.0)
        cv.put(col_filmSuresi,"2 Saat 23 Dakika")
        cv.put(col_filmVizyonGirisYili,2012)
        cv.put(col_filmYonetmen,"Joss Whedon")
        cv.put(col_filmKategorisi,"Aksiyon Bilim-Kurgu")
        cv.put(col_filmBasrol,"Robert Downey Jr., Chris Evans, Scarlett Johansson")
        db.insert(table_film,"41",cv)

        cv.put(col_filmAdi,"Hayır")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"hayir")
        cv.put(col_fragman,"https://www.imdb.com/video/vi1273872921/?playlistId=tt10954984&ref_=tt_ov_vi")
        cv.put(col_filmKonu,"Hayır, esrarengiz ve tüyler ürpertici bir keşfe tanıklık eden, Kaliforniya'nın " +
                            "iç kesimlerindeki ıssız bir kanyonda yaşayan insanların hikayesini konu ediyor.")
        cv.put(col_filmPuan,6.8)
        cv.put(col_filmSuresi,"2 Saat 10 Dakika")
        cv.put(col_filmVizyonGirisYili,2022)
        cv.put(col_filmYonetmen,"Jordan Peele")
        cv.put(col_filmKategorisi,"Korku Gizem Bilim-Kurgu")
        cv.put(col_filmBasrol,"Daniel Kaluuya, Keke Palmer, Brandon Perea")
        db.insert(table_film,"42",cv)

        cv.put(col_filmAdi,"Don't Look Up")
        //cv.put(col_resim,"https://tr.web.img2.acsta.net/c_310_420/pictures/17/01/31/09/54/531088.jpg")
        cv.put(col_resim,"dont_look_up")
        cv.put(col_fragman,"https://www.imdb.com/video/vi15057689/?playlistId=tt11286314&ref_=tt_ov_vi")
        cv.put(col_filmKonu,"Don’t Look Up, dev bir göktaşının gezegeni yok edeceği konusunda insanları uyarmak için " +
                            "çalışan iki gökbilimcinin hikayesini konu ediyor. İnsanlık büyük bir tehlike ile karşı " +
                            "karşıyadır. Everest Dağı büyüklüğünde bir kuyruklu yıldız hızla Dünya'ya yaklaşmaktadır " +
                            "ve oluşacak çarpışma Dünya'nın yok olmasına neden olacaktır. Astronomi yüksek lisans " +
                            "öğrencisi olan Kate Dibiasky'nin yaptığı bu keşif, insanlığın kurtulmasını sağlayacaktır. " +
                            "Kate ve Dr. Randall Mindy, insanlığı yaklaşmakta olan tehlikeye karşı uyarlamak için bir " +
                            "medya turuna çıkmaya karar verir. Kare ve Randall, insanları yaklaşan felaketten haberdar " +
                            "etmek için büyük çaba harcarken kendilerini beklenmedik durumların içinde bulur.")
        cv.put(col_filmPuan,7.2)
        cv.put(col_filmSuresi,"2 Saat 18 Dakika")
        cv.put(col_filmVizyonGirisYili,2021)
        cv.put(col_filmYonetmen,"Adam McKay")
        cv.put(col_filmKategorisi,"Komedi Drama Bilim-Kurgu")
        cv.put(col_filmBasrol,"Leonardo DiCaprio, Jennifer Lawrence, Meryl Streep")
        db.insert(table_film,"43",cv)



    }

    //Verileri okumak için fonksiyon tanımlıyoruz
    fun readFilmData(): MutableList<Film> {

        var liste : MutableList<Film> = ArrayList()
        var db = this.readableDatabase
        var sorgu  = "Select * From "+ table_film
        var sonuc = db.rawQuery(sorgu,null)

        if (sonuc.moveToFirst()){
            do {

                var film = Film()
                film.FilmId = sonuc.getString(sonuc.getColumnIndexOrThrow(col_filmId)).toInt()
                film.FilmAdi = sonuc.getString(sonuc.getColumnIndexOrThrow(col_filmAdi))
                film.Resim = sonuc.getString(sonuc.getColumnIndexOrThrow(col_resim))
                film.Fragman = sonuc.getString(sonuc.getColumnIndexOrThrow(col_fragman))
                film.FilmKonu = sonuc.getString(sonuc.getColumnIndexOrThrow(col_filmKonu))
                film.FilmPuan = sonuc.getString(sonuc.getColumnIndexOrThrow(col_filmPuan)).toFloat()
                film.FilmSuresi = sonuc.getString(sonuc.getColumnIndexOrThrow(col_filmSuresi))
                film.VizyonaGirisYili = sonuc.getString(sonuc.getColumnIndexOrThrow(col_filmVizyonGirisYili)).toInt()
                film.FilmYonetmen = sonuc.getString(sonuc.getColumnIndexOrThrow(col_filmYonetmen))
                film.FilmKategori = sonuc.getString(sonuc.getColumnIndexOrThrow(col_filmKategorisi))
                film.FilmBasroller = sonuc.getString(sonuc.getColumnIndexOrThrow(col_filmBasrol))

                liste.add(film)

            }while (sonuc.moveToNext())
        }

        sonuc.close()
        db.close()
        return liste
    }

    //Verileri okumak için fonksiyon tanımlıyoruz
    fun readFilmFiltreData(kategori : String): MutableList<Film> {

        var liste : MutableList<Film> = ArrayList()
        var db = this.readableDatabase
        var sorgu  = "Select * From "+ table_film +" Where "+ col_filmKategorisi+" LIKE '%$kategori%' "+" ORDER BY "+ col_filmPuan + " ASC, " + col_filmVizyonGirisYili+ " ASC "
        var sonuc = db.rawQuery(sorgu,null)

        if (sonuc.moveToFirst()){
            do {

                var film = Film()
                film.FilmId = sonuc.getString(sonuc.getColumnIndexOrThrow(col_filmId)).toInt()
                film.FilmAdi = sonuc.getString(sonuc.getColumnIndexOrThrow(col_filmAdi))
                film.Resim = sonuc.getString(sonuc.getColumnIndexOrThrow(col_resim))
                film.Fragman = sonuc.getString(sonuc.getColumnIndexOrThrow(col_fragman))
                film.FilmKonu = sonuc.getString(sonuc.getColumnIndexOrThrow(col_filmKonu))
                film.FilmPuan = sonuc.getString(sonuc.getColumnIndexOrThrow(col_filmPuan)).toFloat()
                film.FilmSuresi = sonuc.getString(sonuc.getColumnIndexOrThrow(col_filmSuresi))
                film.VizyonaGirisYili = sonuc.getString(sonuc.getColumnIndexOrThrow(col_filmVizyonGirisYili)).toInt()
                film.FilmYonetmen = sonuc.getString(sonuc.getColumnIndexOrThrow(col_filmYonetmen))
                film.FilmKategori = sonuc.getString(sonuc.getColumnIndexOrThrow(col_filmKategorisi))
                film.FilmBasroller = sonuc.getString(sonuc.getColumnIndexOrThrow(col_filmBasrol))

                liste.add(film)

            }while (sonuc.moveToNext())
        }

        sonuc.close()
        db.close()
        return liste
    }


    fun insertFavoriData(favori: Favori){

        val db = this.writableDatabase
        val cv = ContentValues()

        /*cv.put(col_favoriId,favori.FavoriId)
        cv.put(col_favoriFilmAdi,favori.FavoriFilmAdi)
        cv.put(col_favoriResim,favori.FavoriResim)
        cv.put(col_favoriFragman,favori.FavoriFragman)
        cv.put(col_favoriKonu,favori.FavoriFilmKonu)
        cv.put(col_favoriPuan,favori.FavoriFilmPuan)
        cv.put(col_favoriSure,favori.FavoriFilmSuresi)
        cv.put(col_favoriYil,favori.FavoriVizyonaGirisYili)
        cv.put(col_favoriYonetmen,favori.FavoriFilmYonetmen)
        cv.put(col_favoriKategori,favori.FavoriFilmKategori)
        cv.put(col_filmBasrol,favori.FavoriFilmBasroller)*/


        cv.put(col_favoriFilmId,favori.FavoriFilmId)
        cv.put(col_favoriKullaniciId,favori.FavoriKullaniciId)
        cv.put(col_favoriResim,favori.FavoriResim)


        var sonuc = db.insert(table_favori,null,cv)

        if(sonuc == (-1).toLong()){

            Toast.makeText(context, "Favorilere Ekleme İşleminiz Başarısız Oldu!", Toast.LENGTH_LONG).show()

        }else{
            Toast.makeText(context, "Film Favorilerinize Başarıyla Eklendi!", Toast.LENGTH_LONG).show()

        }
    }

    //Verileri okumak için fonksiyon tanımlıyoruz
    fun readFavoriData(): MutableList<Favori> {

        var liste : MutableList<Favori> = ArrayList()
        var db = this.readableDatabase
        var sorgu  = "Select * From "+ table_favori
        var sonuc = db.rawQuery(sorgu,null)

        if (sonuc.moveToFirst()){
            do {

                var favori = Favori()
                /*favori.FavoriId = sonuc.getString(sonuc.getColumnIndexOrThrow(col_favoriId)).toInt()
                favori.FavoriFilmAdi = sonuc.getString(sonuc.getColumnIndexOrThrow(col_favoriFilmAdi))
                favori.FavoriResim = sonuc.getString(sonuc.getColumnIndexOrThrow(col_favoriResim))
                favori.FavoriFragman = sonuc.getString(sonuc.getColumnIndexOrThrow(col_favoriFragman))
                favori.FavoriFilmKonu = sonuc.getString(sonuc.getColumnIndexOrThrow(col_favoriKonu))
                favori.FavoriFilmPuan = sonuc.getString(sonuc.getColumnIndexOrThrow(col_favoriPuan)).toFloat()
                favori.FavoriFilmSuresi = sonuc.getString(sonuc.getColumnIndexOrThrow(col_favoriSure))
                favori.FavoriVizyonaGirisYili = sonuc.getString(sonuc.getColumnIndexOrThrow(col_favoriYil)).toInt()
                favori.FavoriFilmYonetmen = sonuc.getString(sonuc.getColumnIndexOrThrow(col_favoriYonetmen))
                favori.FavoriFilmKategori = sonuc.getString(sonuc.getColumnIndexOrThrow(col_favoriKategori))
                favori.FavoriFilmBasroller = sonuc.getString(sonuc.getColumnIndexOrThrow(col_favoriBasrol))*/

                favori.FavoriId = sonuc.getString(sonuc.getColumnIndexOrThrow(col_favoriId)).toInt()
                favori.FavoriFilmId = sonuc.getString(sonuc.getColumnIndexOrThrow(col_favoriFilmId)).toInt()
                favori.FavoriKullaniciId = sonuc.getString(sonuc.getColumnIndexOrThrow(col_favoriKullaniciId)).toInt()
                favori.FavoriResim = sonuc.getString(sonuc.getColumnIndexOrThrow(col_favoriResim))

                liste.add(favori)

            }while (sonuc.moveToNext())
        }

        sonuc.close()
        db.close()
        return liste
    }



    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}