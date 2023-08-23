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
import com.omrfrkg.mobilfilmoneriuygulamasi.databinding.FragmentYoneticiDuyuruIslemleriBinding
import com.omrfrkg.mobilfilmoneriuygulamasi.databinding.FragmentYoneticiDuyuruListeleBinding

class YoneticiDuyuruIslemleriFragment : Fragment() {

    private var _binding: FragmentYoneticiDuyuruIslemleriBinding? = null

    private val binding get() = _binding!!

    private lateinit var mContext: Context

    private var duyuruId : Int  = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentYoneticiDuyuruIslemleriBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = DataBaseHelper(mContext)

        val spinner: Spinner = view.findViewById(R.id.spDuyuruId)


        verileriGetir(spinner)


        binding.btnDuyuruSil.setOnClickListener {

            db.deleteDuyuruData(duyuruId)

            Toast.makeText(mContext, "Duyuru Silme İşleminiz Başarıyla Gerçekleştirildi.", Toast.LENGTH_LONG).show()

            verileriGetir(spinner)

        }

        binding.btnDuyuruGuncelle.setOnClickListener {

            var konu = binding.etDuyuruIslemKonu.text.toString()
            var icerik = binding.etDuyuruIslemIcerik.text.toString()
            db.updateDuyuruData(duyuruId,konu,icerik)

            Toast.makeText(mContext, "Duyuru Güncelleme İşleminiz Başarıyla Gerçekleştirildi.", Toast.LENGTH_LONG).show()

            verileriGetir(spinner)

        }


    }

    fun verileriGetir(spinner: Spinner){

        val items = ArrayList<Int>()

        val db = DataBaseHelper(mContext)

        var data = db.readDuyuruData()

        for (i in 0 until data.size){

            items.add(data.get(i).DuyuruID)
        }


        val adapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, items)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()

                duyuruId = selectedItem.toInt()



                for (i in 0 until data.size){
                    if(selectedItem == data.get(i).DuyuruID.toString()){
                        binding.etDuyuruIslemKonu.setText(data.get(i).DuyuruKonu)
                        binding.etDuyuruIslemIcerik.setText(data.get(i).DuyuruIcerik)
                        binding.etDuyuruIslemKonu.setText(data.get(i).DuyuruKonu)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context

    }

}