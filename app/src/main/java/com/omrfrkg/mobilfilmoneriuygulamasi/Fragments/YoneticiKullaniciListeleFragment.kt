package com.omrfrkg.mobilfilmoneriuygulamasi.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.omrfrkg.mobilfilmoneriuygulamasi.DataBaseHelper
import com.omrfrkg.mobilfilmoneriuygulamasi.R
import com.omrfrkg.mobilfilmoneriuygulamasi.databinding.FragmentYoneticiDuyuruListeleBinding
import com.omrfrkg.mobilfilmoneriuygulamasi.databinding.FragmentYoneticiKullaniciListeleBinding

class YoneticiKullaniciListeleFragment : Fragment() {

    private var _binding: FragmentYoneticiKullaniciListeleBinding? = null

    private val binding get() = _binding!!

    private lateinit var mContext: Context

    private var kullaniciId : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentYoneticiKullaniciListeleBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = DataBaseHelper(mContext)

        val spinner: Spinner = view.findViewById(R.id.sKullaniciId)


        verileriGetir(spinner)

        binding.btnKullaniciHesabiniSil.setOnClickListener {
            db.deleteKullaniciData(kullaniciId)
            Toast.makeText(mContext, "Seçilen Kullanıcının Hesabının Kaldırılma İşlemi Başarıyla Gerçekleştirildi.", Toast.LENGTH_SHORT).show()
            verileriGetir(spinner)
        }


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context

    }

    fun verileriGetir(spinner: Spinner){

        val items = ArrayList<Int>()

        val db = DataBaseHelper(mContext)

        var data = db.readKullaniciData()

        for (i in 0 until data.size){

            if (data.get(i).KullaniciId != 1){
                items.add(data.get(i).KullaniciId)
            }
        }


        val adapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, items)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()

                kullaniciId = selectedItem.toInt()

                for (i in 0 until data.size){
                    if(selectedItem == data.get(i).KullaniciId.toString()){
                        binding.tvKullaniciAdiYonetici.text = data.get(i).KullaniciAdi
                        binding.tvAdiSoyadiYonetici.text = data.get(i).AdSoyad
                        binding.tvMailYonetici.text = data.get(i).Mail
                        binding.tvTelefonYonetici.text = data.get(i).Telefon
                        binding.tvSifreYonetici.text = data.get(i).Sifre

                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

}