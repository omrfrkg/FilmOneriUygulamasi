package com.omrfrkg.mobilfilmoneriuygulamasi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class YoneticiDuyuruAdapter : RecyclerView.Adapter<YoneticiDuyuruAdapter.YoneticiDuyuruViewHolder>() {

    private var dyrList : ArrayList<Duyuru> = ArrayList()

    fun addItems(items : ArrayList<Duyuru>){

        this.dyrList = items
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = YoneticiDuyuruViewHolder(

        LayoutInflater.from(parent.context).inflate(R.layout.yonetici_duyuru_item,parent,false)


    )

    override fun getItemCount(): Int {
        return dyrList.size
    }

    override fun onBindViewHolder(holder: YoneticiDuyuruViewHolder, position: Int) {
        val dyr = dyrList[position]
        holder.bindView(dyr)
    }

    class YoneticiDuyuruViewHolder(var view : View) : RecyclerView.ViewHolder(view){


        private var konu = view.findViewById<TextView>(R.id.tvAdSoyadYoneticiItem)
        private var icerik = view.findViewById<TextView>(R.id.tvDuyurununIcerigi)
        private var tarih = view.findViewById<TextView>(R.id.tvKullaniciAdiYoneticiItem)

        fun bindView(duyuru : Duyuru){
            konu.text = duyuru.DuyuruKonu
            icerik.text = duyuru.DuyuruIcerik
            tarih.text = duyuru.DuyuruTarih
        }
    }
}