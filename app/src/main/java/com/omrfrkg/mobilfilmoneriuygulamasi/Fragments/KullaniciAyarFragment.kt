package com.omrfrkg.mobilfilmoneriuygulamasi.Fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.INotificationSideChannel
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.omrfrkg.mobilfilmoneriuygulamasi.DataBaseHelper
import com.omrfrkg.mobilfilmoneriuygulamasi.databinding.FragmentKullaniciAyarBinding
import com.omrfrkg.mobilfilmoneriuygulamasi.fragmentViewModel

class KullaniciAyarFragment : Fragment() {

    private var _binding: FragmentKullaniciAyarBinding? = null

    private val viewModel : fragmentViewModel by activityViewModels()

    private lateinit var mContext: Context

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentKullaniciAyarBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //var id : Int = 0

        var kId : Int = 0

        var kAdi : String = ""

        val db = DataBaseHelper(mContext)
        var data2 = db.readKullaniciData()

        viewModel.data.observe(viewLifecycleOwner,{

            kId = it

            for (i in 0 until data2.size){
                if(data2.get(i).KullaniciId == it){
                    binding.etKullaniciAdiGuncelleme.setText(data2.get(i).KullaniciAdi)
                    binding.etAdSoyadGuncelleme.setText(data2.get(i).AdSoyad)
                    binding.etMailGuncelleme.setText(data2.get(i).Mail)
                    binding.etTelefonGuncelleme.setText(data2.get(i).Telefon)
                    binding.etSifreGuncelleme.setText(data2.get(i).Sifre)
                    //kId  = data2.get(i).KullaniciId
                }
            }

            binding.btnBilgileriGuncelleme.setOnClickListener {
                var kAdi = binding.etKullaniciAdiGuncelleme.text.toString()
                var kAdSoyad = binding.etAdSoyadGuncelleme.text.toString()
                var kMail = binding.etMailGuncelleme.text.toString()
                var kTelefon = binding.etTelefonGuncelleme.text.toString()
                var kSifre = binding.etSifreGuncelleme.text.toString()
                db.updateKullaniciData(kId,kAdi,kAdSoyad,kMail,kTelefon,kSifre)
                Toast.makeText(activity, "Bilgi Güncelleme İşleminiz Başarıyla Gerçekleştirildi!", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context

    }



}