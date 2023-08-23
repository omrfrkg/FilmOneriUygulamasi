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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omrfrkg.mobilfilmoneriuygulamasi.DataBaseHelper
import com.omrfrkg.mobilfilmoneriuygulamasi.R
import com.omrfrkg.mobilfilmoneriuygulamasi.YoneticiDuyuruAdapter
import com.omrfrkg.mobilfilmoneriuygulamasi.databinding.FragmentDuyuruBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DuyuruFragment : Fragment() {

    private var _binding: FragmentDuyuruBinding? = null

    private val binding get() = _binding!!

    private lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDuyuruBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = DataBaseHelper(mContext)

        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val tarih = currentDateTime.format(formatter)

        var data = db.readDuyuruData()

        for (i in 0 until data.size) {
            if (data.get(i).DuyuruTarih == tarih.toString()) {
                Toast.makeText(mContext, "${data.get(i).DuyuruTarih} , $tarih", Toast.LENGTH_LONG)
                    .show()
                binding.tvKullaniciDuyuruTarih.text = data.get(i).DuyuruTarih
                binding.tvKullaniciDuyuruKonu.text = data.get(i).DuyuruKonu
                binding.tvKullaniciDuyuruIcerik.text = data.get(i).DuyuruIcerik
            }

        }

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context

    }
}