package com.omrfrkg.mobilfilmoneriuygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.omrfrkg.mobilfilmoneriuygulamasi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.deleteDatabase("FilmAppDB");

        val context = this
        var db = DataBaseHelper(context)

        if(db.readFilmData().size == 0){
            db.insertFilmData()
        }

        if(db.readKullaniciData().size == 0){
            db.insertYoneticiData()
        }

        var girisBasariliMi : Boolean = false
        var adminMi : Boolean = false
        var kullaniciID : Int = -1
        var kullaniciAdim : String = ""

        binding.btnGirisYap.setOnClickListener{

            var data = db.readKullaniciData()
            
            for (i in 0 until data.size){
                if(binding.etKullaniciAdi.text.toString() == data.get(i).KullaniciAdi && binding.etSifre.text.toString() == data.get(i).Sifre && data.get(i).Statu == "Kullanıcı"){
                    girisBasariliMi = true
                    kullaniciID = data.get(i).KullaniciId
                    kullaniciAdim = data.get(i).AdSoyad
                    adminMi == false
                }

                if(binding.etKullaniciAdi.text.toString() == data.get(i).KullaniciAdi && binding.etSifre.text.toString() == data.get(i).Sifre && data.get(i).Statu == "Yönetici"){
                    girisBasariliMi = true
                    kullaniciID = data.get(i).KullaniciId
                    kullaniciAdim = data.get(i).AdSoyad
                    adminMi = true
                }
            }

            if(adminMi == false && girisBasariliMi == true){
                val intent = Intent(this, KullaniciActivity::class.java)
                intent.putExtra("kID", kullaniciID) // "anahtar" veriye erişmek için kullanılacak bir etikettir
                intent.putExtra("kAdiSoyadi", kullaniciAdim) // "anahtar" veriye erişmek için kullanılacak bir etikettir
                startActivity(intent)
            }

            if(adminMi == true && girisBasariliMi == true){
                val intent = Intent(this, YoneticiActivity::class.java)
                intent.putExtra("kID", kullaniciID) // "anahtar" veriye erişmek için kullanılacak bir etikettir
                intent.putExtra("kAdiSoyadi", kullaniciAdim) // "anahtar" veriye erişmek için kullanılacak bir etikettir
                startActivity(intent)
            }

            if (girisBasariliMi == false){
                Toast.makeText(this, "Kullanıcı Adınızı veya Şifrenizi Yanlış Girdiniz!", Toast.LENGTH_LONG).show()
            }

        }
    }

    fun sifremiUnuttum(view : View){
        val intent = Intent(this, SifreSifirlamaActivity::class.java)
        startActivity(intent)
    }

    fun yeniHesapAc(view : View){
        val intent = Intent(this, YeniKullaniciActivity::class.java)
        startActivity(intent)
    }
}