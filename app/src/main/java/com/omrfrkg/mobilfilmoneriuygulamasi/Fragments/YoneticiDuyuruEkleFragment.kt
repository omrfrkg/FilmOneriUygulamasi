package com.omrfrkg.mobilfilmoneriuygulamasi.Fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.omrfrkg.mobilfilmoneriuygulamasi.DataBaseHelper
import com.omrfrkg.mobilfilmoneriuygulamasi.Duyuru
import com.omrfrkg.mobilfilmoneriuygulamasi.R
import com.omrfrkg.mobilfilmoneriuygulamasi.YoneticiDuyuruAdapter
import com.omrfrkg.mobilfilmoneriuygulamasi.databinding.FragmentKullaniciAnasayfaBinding
import com.omrfrkg.mobilfilmoneriuygulamasi.databinding.FragmentYoneticiDuyuruEkleBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class YoneticiDuyuruEkleFragment : Fragment() {

    private var _binding: FragmentYoneticiDuyuruEkleBinding? = null

    private val binding get() = _binding!!

    private lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentYoneticiDuyuruEkleBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnDuyuruYayinla.setOnClickListener {
            val db = DataBaseHelper(mContext)

            var duyuruKonu = binding.etDuyuruKonu.text.toString()
            var duyuruIcerik = binding.etDuyuruIcerik.text.toString()
            val currentDateTime = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            val duyuruTarih = currentDateTime.format(formatter)

            if (duyuruKonu.isEmpty() || duyuruIcerik.isEmpty()){
                Toast.makeText(mContext, "Lütfen Formdaki Bütün Alanları Dolduralım!", Toast.LENGTH_LONG).show()
            }
            else{
                var duyuru = Duyuru(duyuruKonu,duyuruIcerik,duyuruTarih)
                db.insertDuyuruData(duyuru)
                binding.etDuyuruKonu.setText("")
                binding.etDuyuruIcerik.setText("")
            }
        }


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context

    }
}