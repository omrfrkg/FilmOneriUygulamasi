package com.omrfrkg.mobilfilmoneriuygulamasi.Fragments


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.omrfrkg.mobilfilmoneriuygulamasi.DataBaseHelper
import com.omrfrkg.mobilfilmoneriuygulamasi.Favori
import com.omrfrkg.mobilfilmoneriuygulamasi.Film
import com.omrfrkg.mobilfilmoneriuygulamasi.FilmAnasayfaDetayActivity
import com.omrfrkg.mobilfilmoneriuygulamasi.Kullanici
import com.omrfrkg.mobilfilmoneriuygulamasi.KullaniciActivity
import com.omrfrkg.mobilfilmoneriuygulamasi.MainActivity
import com.omrfrkg.mobilfilmoneriuygulamasi.R
import com.omrfrkg.mobilfilmoneriuygulamasi.databinding.FragmentKullaniciAnasayfaBinding
import com.omrfrkg.mobilfilmoneriuygulamasi.fragmentViewModel
import kotlin.random.Random;




class KullaniciAnasayfaFragment : Fragment() {

    private var filmId: Int = 0

    private var _binding: FragmentKullaniciAnasayfaBinding? = null

    private val viewModel: fragmentViewModel by activityViewModels()

    private val binding get() = _binding!!

    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
        /*inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?*/
    ): View? {
        // Inflate the layout for this fragment
        /*return inflater.inflate(com.omrfrkg.mobilfilmoneriuygulamasi.R.layout.fragment_kullanici_anasayfa, container, false)*/
        _binding = FragmentKullaniciAnasayfaBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView: ImageView = view.findViewById(R.id.ivFilmPoster)

        val db = DataBaseHelper(mContext)
        var data = db.readFilmData()

        var fID: Int = 0


        viewModel.data.observe(viewLifecycleOwner) {

            var fdata = db.readFiltreData(it)
            var kategori : String = ""

            for (i in 0 until fdata.size){
                kategori = fdata.get(i).FiltreKategori
            }

            FilmBilgileriniGetir(imageView,kategori)

            binding.ivFilmDegistir.setOnClickListener {

                FilmBilgileriniGetir(imageView,kategori)

            }

            binding.ivFilmPoster.setOnClickListener {
                val intent = Intent(activity, FilmAnasayfaDetayActivity::class.java)
                intent.putExtra(
                    "kID",
                    filmId
                ) // "anahtar" veriye erişmek için kullanılacak bir etikettir
                startActivity(intent)
            }

            var kId = it

            binding.ivFavorilereEkle.setOnClickListener {


                var resim: String = ""
                for (i in 0 until data.size) {
                    if (data.get(i).FilmId == filmId) {
                        resim = data.get(i).Resim
                        fID = data.get(i).FilmId
                    }
                }

                //var filmId = FilmBilgileriniGetir(imageView,kategori)
                var favori = Favori(fID, kId, resim)
                db.insertFavoriData(favori)
            }

        }
    }

    var resim: String = ""

    fun FilmBilgileriniGetir(imageView: ImageView,kategori : String) {

        var sayiListesi = ArrayList<Int>()
        val db = DataBaseHelper(mContext)

        var data2 = db.readFilmFiltreData(kategori)

        if(kategori == "Hepsi"){
            data2 = db.readFilmData()
        }


        for (i in 0 until data2.size) {
            sayiListesi.add(data2.get(i).FilmId)
        }

        val randomIndex = kotlin.random.Random.nextInt(sayiListesi.size)
        val randomId = sayiListesi[randomIndex]

        //random = Random.nextInt(1, data2.size)

        //var data = db.readFilmFiltreData("Macera")

        for (i in 0 until data2.size) {
                if (data2.get(i).FilmId == randomId) {
                    binding.tvFilmAdi.text = data2.get(i).FilmAdi
                    resim = data2.get(i).Resim
                    val id = resources.getIdentifier(
                        "com.omrfrkg.mobilfilmoneriuygulamasi:drawable/" + resim,
                        null,
                        null
                    )
                    binding.ivFilmPoster.setImageResource(id)
                    binding.tvFilmKonu.text = data2.get(i).FilmKonu
                    filmId = data2.get(i).FilmId
                }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context

    }

}



