package com.omrfrkg.mobilfilmoneriuygulamasi

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
import com.omrfrkg.mobilfilmoneriuygulamasi.databinding.ActivitySifreSifirlamaBinding
import kotlin.random.Random


class SifreSifirlamaActivity : AppCompatActivity() {

    private  var kullaniciID = -1

    private lateinit var binding: ActivitySifreSifirlamaBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySifreSifirlamaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val smsManager = SmsManager.getDefault()
        ActivityCompat.requestPermissions(
            this,
            arrayOf<String>(android.Manifest.permission.SEND_SMS),
            1
        )


        var dogrulamaKodu = ""


        binding.btnSifreSifirlama.setOnClickListener {

            var kullanici = binding.etKullaniciBilgileri.text.toString()
            var favori = binding.etFilmAdi.text.toString()


            if (kullanici.isEmpty()){
                binding.etKullaniciBilgileri.error = "Bu kısım boş bırakılamaz"
            }
            else{

                var kullaniciDogruMu : Boolean = false
                var favoriDogruMu : Boolean = false
                val context = this
                var db = DataBaseHelper(context)
                var data = db.readKullaniciData()
                var filmData = db.readFilmData()
                var favoriData = db.readFavoriData()
                var filmId = -1


                for (i in 0 until data.size){
                    if(data.get(i).KullaniciAdi == kullanici || data.get(i).Telefon == kullanici || data.get(i).Mail == kullanici){
                        kullaniciDogruMu = true
                        kullaniciID = data.get(i).KullaniciId
                    }
                }


                if (favori == "Yok"){
                    var favoriData = db.readFavoriFiltreData(kullaniciID)
                    if (favoriData.size == 0){
                        favoriDogruMu = true
                    }
                }
                else{
                    for (i in 0 until favoriData.size){
                        if (kullaniciID == favoriData.get(i).FavoriKullaniciId){
                            filmId = favoriData.get(i).FavoriFilmId
                        }
                    }
                }

                for (i in 0 until filmData.size){
                    if (filmId == filmData.get(i).FilmId){
                            if (favori == filmData.get(i).FilmAdi){
                                favoriDogruMu = true
                            }
                    }
                }

                if (kullaniciDogruMu && favoriDogruMu){

                    dogrulamaKodu = dogrulamaKoduOlustur()
                    smsManager.sendTextMessage("05368522352", null, "Şifrenizi sıfırlamak için doğrulama kodunuz : $dogrulamaKodu", null, null)
                    Toast.makeText(this, "Telefonunuza Doğrulama Kodu Gönderilmiştir.", Toast.LENGTH_SHORT).show()
                }
                else{
                    val builder = AlertDialog.Builder(context)
                    builder.setTitle("Yetersiz")
                    builder.setMessage("Verilen bilgiler eksik veya yanlış olduğu için size yardımcı olamıyoruz. Daha detaylı bir destek için lütfen destekfilmoneri@gmail.com adresine mail atarak bizimle iletişime geçiniz!")
                    builder.setPositiveButton("Tamam") { dialog, _ ->
                        // Tamam butonuna tıklandığında yapılacak işlemler
                        dialog.dismiss()
                    }
                    val alertDialog = builder.create()
                    alertDialog.show()

                    //Toast.makeText(this, "$favoriDogruMu $kullaniciDogruMu", Toast.LENGTH_SHORT).show()
                }
            }

        }

        binding.btnDogrulamaKodu.setOnClickListener {

            var dogrulama = binding.etDogrulamaKodu.text.toString()

            if (dogrulama.isEmpty()){
                binding.etDogrulamaKodu.error = "Bu kısım boş bırakılamaz!"
            }
            else{
                if (dogrulamaKodu == dogrulama){

                        val intent = Intent(this, SifreGuncellemeActivity::class.java)
                        intent.putExtra("kID", kullaniciID)
                        startActivity(intent)

                }
                else{
                    Toast.makeText(this, "Doğrulama Kodunu Yanlış Girdiz!", Toast.LENGTH_SHORT).show()
                }
            }

        }








    }
    /*fun sendEmail() {
        try {
            val stringSenderEmail = "destekfilmoneri@gmail.com"
            val stringReceiverEmail = "omerfarukk61.of@gmail.com"
            val stringPasswordSenderEmail = "123456789Xx"
            val stringHost = "smtp.gmail.com"
            val properties = System.getProperties()
            properties["mail.smtp.host"] = stringHost
            properties["mail.smtp.port"] = "465"
            properties["mail.smtp.ssl.enable"] = "true"
            properties["mail.smtp.auth"] = "true"
            val session = Session.getInstance(properties, object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail)
                }
            })
            val mimeMessage = MimeMessage(session)
            mimeMessage.addRecipient(Message.RecipientType.TO, InternetAddress(stringReceiverEmail))
            mimeMessage.subject = "Subject: Android App email"
            mimeMessage.setText("Hello Programmer, \n\nProgrammer World has sent you this 2nd email. \n\n Cheers!\nProgrammer World")
            val thread = Thread {
                try {
                    Transport.send(mimeMessage)
                } catch (e: MessagingException) {
                    e.printStackTrace()
                }
            }
            thread.start()
        } catch (e: AddressException) {
            e.printStackTrace()
        } catch (e: MessagingException) {
            e.printStackTrace()
        }
    }*/

    fun dogrulamaKoduOlustur(): String {
        val characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        val sb = StringBuilder(6)
        repeat(6) {
            val randomIndex = Random.nextInt(characters.length)
            sb.append(characters[randomIndex])
        }
        return sb.toString()
    }

    fun sifreOlustur(): String {
        val characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        val sb = StringBuilder(10)
        repeat(6) {
            val randomIndex = Random.nextInt(characters.length)
            sb.append(characters[randomIndex])
        }
        return sb.toString()
    }
}