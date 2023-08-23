package com.omrfrkg.mobilfilmoneriuygulamasi.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.omrfrkg.mobilfilmoneriuygulamasi.DataBaseHelper
import com.omrfrkg.mobilfilmoneriuygulamasi.R
import com.omrfrkg.mobilfilmoneriuygulamasi.databinding.FragmentYoneticiKullaniciListeleBinding
import com.omrfrkg.mobilfilmoneriuygulamasi.databinding.FragmentYoneticiSifreSifirlamaBinding

class YoneticiSifreSifirlamaFragment : Fragment() {

    private var _binding: FragmentYoneticiSifreSifirlamaBinding? = null

    private val binding get() = _binding!!

    private lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentYoneticiSifreSifirlamaBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = DataBaseHelper(mContext)


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context

    }
}