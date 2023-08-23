package com.omrfrkg.mobilfilmoneriuygulamasi.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.omrfrkg.mobilfilmoneriuygulamasi.DataBaseHelper
import com.omrfrkg.mobilfilmoneriuygulamasi.Favori
import com.omrfrkg.mobilfilmoneriuygulamasi.FilmAnasayfaDetayActivity
import com.omrfrkg.mobilfilmoneriuygulamasi.Filtre
import com.omrfrkg.mobilfilmoneriuygulamasi.R
import com.omrfrkg.mobilfilmoneriuygulamasi.database_name
import com.omrfrkg.mobilfilmoneriuygulamasi.databinding.FragmentKullaniciAnasayfaBinding
import com.omrfrkg.mobilfilmoneriuygulamasi.databinding.FragmentKullaniciFilmOneriAlgoritmasiBinding
import com.omrfrkg.mobilfilmoneriuygulamasi.fragmentViewModel

class KullaniciFilmOneriAlgoritmasiFragment : Fragment() {

    private var _binding: FragmentKullaniciFilmOneriAlgoritmasiBinding? = null

    private val binding get() = _binding!!

    private lateinit var mContext: Context

    private val viewModel: fragmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentKullaniciFilmOneriAlgoritmasiBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView: ImageView = view.findViewById(R.id.ivKategoriAfis)

        viewModel.data.observe(viewLifecycleOwner) {

            var kId = it
            val db = DataBaseHelper(mContext)

            var sdata = db.readFiltreData(kId)

            val spinner = view.findViewById<Spinner>(R.id.spinner)

            val secenekler = listOf(
                "Aksiyon",
                "Macera",
                "Gerilim",
                "Gizem",
                "Suç",
                "Korku",
                "Bilim-Kurgu",
                "Drama",
                "Hepsi"
            )
            val adapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_item, secenekler)

            spinner.adapter = adapter


            for (i in 0 until sdata.size){
                val selectedItem = sdata.get(i).FiltreKategori
                val position = adapter.getPosition(selectedItem)
                spinner.setSelection(position)
            }

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem = parent.getItemAtPosition(position) as String

                    binding.btnOneriAyariKaydet.setOnClickListener {

                        var ilkKayit: Boolean = false

                        if (db.readFiltreData(kId).size == 0){
                            ilkKayit = true
                        }

                        if (ilkKayit == true) {
                            var filtre = Filtre(selectedItem, kId)
                            db.insertFiltreData(filtre)
                        } else {
                            db.updateFiltreData(kId, selectedItem)
                            Toast.makeText(context, "Önerilenleri Değiştirme İşleminiz Başarıyla Gerçekleştirildi!", Toast.LENGTH_LONG).show()
                        }
                    }

                    if (selectedItem == "Aksiyon") {


                        val id = resources.getIdentifier(
                            "com.omrfrkg.mobilfilmoneriuygulamasi:drawable/aksiyon",
                            null,
                            null
                        )
                        binding.ivKategoriAfis.setImageResource(id)
                    }

                    if (selectedItem == "Macera") {


                        val id = resources.getIdentifier(
                            "com.omrfrkg.mobilfilmoneriuygulamasi:drawable/macera",
                            null,
                            null
                        )
                        binding.ivKategoriAfis.setImageResource(id)
                    }

                    if (selectedItem == "Gerilim") {


                        val id = resources.getIdentifier(
                            "com.omrfrkg.mobilfilmoneriuygulamasi:drawable/gerilim",
                            null,
                            null
                        )
                        binding.ivKategoriAfis.setImageResource(id)
                    }

                    if (selectedItem == "Gizem") {


                        val id = resources.getIdentifier(
                            "com.omrfrkg.mobilfilmoneriuygulamasi:drawable/gizem",
                            null,
                            null
                        )
                        binding.ivKategoriAfis.setImageResource(id)
                    }

                    if (selectedItem == "Suç") {


                        val id = resources.getIdentifier(
                            "com.omrfrkg.mobilfilmoneriuygulamasi:drawable/suc",
                            null,
                            null
                        )
                        binding.ivKategoriAfis.setImageResource(id)
                    }

                    if (selectedItem == "Korku") {


                        val id = resources.getIdentifier(
                            "com.omrfrkg.mobilfilmoneriuygulamasi:drawable/korku",
                            null,
                            null
                        )
                        binding.ivKategoriAfis.setImageResource(id)
                    }

                    if (selectedItem == "Bilim-Kurgu") {


                        val id = resources.getIdentifier(
                            "com.omrfrkg.mobilfilmoneriuygulamasi:drawable/suc",
                            null,
                            null
                        )
                        binding.ivKategoriAfis.setImageResource(id)
                    }

                    if (selectedItem == "Drama") {


                        val id = resources.getIdentifier(
                            "com.omrfrkg.mobilfilmoneriuygulamasi:drawable/dram",
                            null,
                            null
                        )
                        binding.ivKategoriAfis.setImageResource(id)
                    }

                    if (selectedItem == "Hepsi") {


                        val id = resources.getIdentifier(
                            "com.omrfrkg.mobilfilmoneriuygulamasi:drawable/hepsi",
                            null,
                            null
                        )
                        binding.ivKategoriAfis.setImageResource(id)
                    }
                    //Toast.makeText(activity, "Seçildi: $selectedItem", Toast.LENGTH_SHORT).show()
                }


                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Hiçbir şey yapılmadı
                }

            }


        }
    }
}