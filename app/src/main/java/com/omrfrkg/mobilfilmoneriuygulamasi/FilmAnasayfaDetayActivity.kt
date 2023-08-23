package com.omrfrkg.mobilfilmoneriuygulamasi


import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.omrfrkg.mobilfilmoneriuygulamasi.databinding.ActivityFilmAnasayfaDetayBinding


class FilmAnasayfaDetayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilmAnasayfaDetayBinding
    private lateinit var webView: WebView
    //private lateinit var videoView : VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmAnasayfaDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        webView = findViewById(R.id.wvFragman)
        //videoView = findViewById(R.id.vvFragman)
        val veri = intent.getIntExtra("kID",99)
        val db = DataBaseHelper(this)
        var data =  db.readFilmData()


        for (i in 0 until data.size){
            if (data.get(i).FilmId == veri){
                binding.tvYonetmen.text = "Filmin Yönetmeni : "+data.get(i).FilmYonetmen
                binding.tvVizyon.text ="Çıkış Yılı : "+ data.get(i).VizyonaGirisYili.toString()
                binding.tvFilmSure.text ="Süre : "+ data.get(i).FilmSuresi
                binding.tvFilmPuan.text = "IMDB Puanı : "+data.get(i).FilmPuan.toString()
                binding.tvFilmKategori.text = "Kategori : "+data.get(i).FilmKategori
                binding.tvFilmBasrol.text = "Başroller : "+data.get(i).FilmBasroller

                webView.webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        if (url?.contains("imdb.com/video/") == true) {
                            view?.loadUrl("javascript:(function() { var player = document.getElementsByTagName('video')[0]; player.style.height = '100%'; player.style.width = '100%'; })()")
                        }
                    }
                }
                webView.settings.javaScriptEnabled = true
                webView.loadUrl(data.get(i).Fragman)


                /*val videoDosyasi = "android.resource://${packageName}/${resources.getIdentifier(videoAdi, "raw", packageName)}"
                videoView.setVideoPath(videoDosyasi)

                val mediaController = MediaController(this)

                mediaController.setAnchorView(videoView)

                videoView.setMediaController(mediaController)

                videoView.start()*/
            }
        }

       /* binding.btnGeriDon.setOnClickListener {
            val fragment = KullaniciAnasayfaFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .commit()
        }*/

    }
}