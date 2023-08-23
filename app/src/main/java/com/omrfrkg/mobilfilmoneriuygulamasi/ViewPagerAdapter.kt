package com.omrfrkg.mobilfilmoneriuygulamasi

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omrfrkg.mobilfilmoneriuygulamasi.Fragments.KullaniciFavoriFragment
import com.omrfrkg.mobilfilmoneriuygulamasi.databinding.FragmentKullaniciFavoriBinding

class ViewPagerAdapter(var resim : List<Int>,var id : List<Int>,var filmAdi : List<String>) : RecyclerView.Adapter<ViewPagerAdapter.PageViewHolder>() {

    class PageViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val imageView : ImageView = itemView.findViewById(R.id.imageView2)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        return PageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.afisler,parent,false))
    }

    override fun getItemCount(): Int {
        return resim.size
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {

        holder.imageView.setOnClickListener {
            val intent = Intent(holder.itemView.context,FilmAnasayfaDetayActivity::class.java)
            intent.putExtra("kID",id[position])
            holder.itemView.context.startActivity(intent)
        }
        holder.imageView.setImageResource(resim[position])
    }
}