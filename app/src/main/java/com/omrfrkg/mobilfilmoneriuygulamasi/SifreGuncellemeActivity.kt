package com.omrfrkg.mobilfilmoneriuygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.omrfrkg.mobilfilmoneriuygulamasi.databinding.ActivitySifreGuncellemeBinding
import com.omrfrkg.mobilfilmoneriuygulamasi.databinding.ActivitySifreSifirlamaBinding

private lateinit var binding: ActivitySifreGuncellemeBinding
class SifreGuncellemeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySifreGuncellemeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val veri = intent.getIntExtra("kID",99)




        binding.btnSifreGuncelle.setOnClickListener {


            var sifre = binding.etSifreGuncelle.text.toString()
            var sifreTekrar = binding.etSifreGuncelleTekrar.text.toString()

            val context = this
            var db = DataBaseHelper(context)



            if (sifre.isEmpty() || sifreTekrar.isEmpty()){
            Toast.makeText(this, "Lütfen boş alan bırakmayınız!", Toast.LENGTH_SHORT).show()
            }
            else if(!sifreDogrula(sifre)){
            binding.etSifreGuncelle.error = "Şifreniz En Kısa 6 Karakter En Uzun 16 Karakter İçerebilir!"
            }
            else if ( binding.etSifreGuncelle.text.toString() != binding.etSifreGuncelleTekrar.text.toString()){
                binding.etSifreGuncelleTekrar.error = "Şifreler Uyuşmuyor!"
            }

            else{
                db.updateKullaniciSifreData(veri,sifre)
                Toast.makeText(this, "Şifre Güncelleme İşleminiz Başarıyla Gerçekleştirilmiştir!", Toast.LENGTH_LONG).show()
                binding.etSifreGuncelle.setText("")
                binding.etSifreGuncelleTekrar.setText("")
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    fun sifreDogrula(sifre : String) : Boolean{
        if(sifre.length in 6..16){
            return true
        }
        return false
    }
}