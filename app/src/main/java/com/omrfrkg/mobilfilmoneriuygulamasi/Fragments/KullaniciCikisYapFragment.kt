package com.omrfrkg.mobilfilmoneriuygulamasi.Fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.omrfrkg.mobilfilmoneriuygulamasi.KullaniciActivity
import com.omrfrkg.mobilfilmoneriuygulamasi.MainActivity
import com.omrfrkg.mobilfilmoneriuygulamasi.R
import com.omrfrkg.mobilfilmoneriuygulamasi.YeniKullaniciActivity

class KullaniciCikisYapFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_kullanici_cikis_yap, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed({
            // Burada yapılacak işlem
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }, 1000)

    }
}