package com.omrfrkg.mobilfilmoneriuygulamasi.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omrfrkg.mobilfilmoneriuygulamasi.DataBaseHelper
import com.omrfrkg.mobilfilmoneriuygulamasi.R
import com.omrfrkg.mobilfilmoneriuygulamasi.YoneticiDuyuruAdapter
import com.omrfrkg.mobilfilmoneriuygulamasi.databinding.FragmentDuyuruBinding
import com.omrfrkg.mobilfilmoneriuygulamasi.databinding.FragmentYoneticiDuyuruListeleBinding

class YoneticiDuyuruListeleFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private  var adapter: YoneticiDuyuruAdapter? = null

    private var _binding: FragmentYoneticiDuyuruListeleBinding? = null

    private val binding get() = _binding!!

    private lateinit var mContext: Context


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentYoneticiDuyuruListeleBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = DataBaseHelper(mContext)

        val dyrList = db.readDuyuruData()

        recyclerView = view.findViewById(R.id.rvDuyuru)

        recyclerView.layoutManager = LinearLayoutManager(mContext)
        adapter = YoneticiDuyuruAdapter()
        recyclerView.adapter = adapter

        adapter?.addItems(dyrList)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context

    }
}