package com.omrfrkg.mobilfilmoneriuygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.omrfrkg.mobilfilmoneriuygulamasi.databinding.ActivityYeniKullaniciBinding

class YeniKullaniciActivity : AppCompatActivity() {
    private lateinit var binding: ActivityYeniKullaniciBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYeniKullaniciBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val context = this
        var db = DataBaseHelper(context)

        binding.btnKayitOl.setOnClickListener {

            var etKullaniciAdiKayit = binding.etKullaniciAdiKayit.text.toString()
            var etAdSoyadKayit = binding.etAdSoyadKayit.text.toString()
            var etMailKayit = binding.etMailKayit.text.toString()
            var etTelefonKayit = binding.etTelefonKayit.text.toString()
            var etSifreKayit = binding.etSifreKayit.text.toString()

            if(etKullaniciAdiKayit.isEmpty() || etAdSoyadKayit.isEmpty() || etMailKayit.isEmpty() || etTelefonKayit.isEmpty() || etSifreKayit.isEmpty()){
                Toast.makeText(context, "Lütfen Boş Alan Bırakmayalım!", Toast.LENGTH_LONG).show()
            }
            else if(!adSoyadDogrula(etAdSoyadKayit)){
                binding.etAdSoyadKayit.error = "Bu Alanda Numerik İfade Kullanamazsınız!"
            }
            else if (!telefonNumarasiniDogrula(etTelefonKayit)){
                binding.etTelefonKayit.error = "Telefon Numarasını Lütfen Eksiksiz ve Doğru Giriniz!"
            }
            else if(!mailAdresiniDogrula(etMailKayit)){
                binding.etMailKayit.error = "Mail Adresinin Doğrulu Çok Önemli Lütfen Eksiksiz ve Doğru Giriniz!"
            }
            else if(!sifreDogrula(etSifreKayit)){
                binding.etSifreKayit.error = "Şifreniz En Kısa 6 Karakter En Uzun 16 Karakter İçerebilir!"
            }
            else{
                if (binding.etSifreKayit.text.toString() != binding.etSifreTekrarKayit.text.toString()){
                    binding.etSifreTekrarKayit.error = "Şifreler Uyuşmuyor Lütfen Kontrol Ediniz!"
                }
                else{
                        var kullanici = Kullanici(etKullaniciAdiKayit,etAdSoyadKayit,etMailKayit,etTelefonKayit,etSifreKayit)
                        db.insertKullaniciData(kullanici)

                        binding.etKullaniciAdiKayit.setText("")
                        binding.etAdSoyadKayit.setText("")
                        binding.etMailKayit.setText("")
                        binding.etTelefonKayit.setText("")
                        binding.etSifreKayit.setText("")
                        binding.etSifreTekrarKayit.setText("")

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                }
            }
        }
    }

    fun telefonNumarasiniDogrula(telefon : String):Boolean{

        if(telefon.length in 1..10){
            return false
        }

        if(telefon[0] == '+' ){
            return false
        }

        for(i in 1 until telefon.length){
            if(!Character.isDigit(telefon[i])){
                return false
            }
        }

        return true
    }

    fun mailAdresiniDogrula(mail : String) : Boolean{

        if(mail.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(mail).matches()){

            return true

        }

        return false
    }

    fun sifreDogrula(sifre : String) : Boolean{
        if(sifre.length in 6..16){
            return true
        }
        return false
    }

    fun adSoyadDogrula(adSoyad : String) : Boolean{

        for(i in 1 until adSoyad.length){
            if(Character.isDigit(adSoyad[i])){
                return false
            }
        }

        return true
    }
}