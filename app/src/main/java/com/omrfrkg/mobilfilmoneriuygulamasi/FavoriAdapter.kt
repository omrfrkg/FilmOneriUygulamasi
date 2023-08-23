package com.omrfrkg.mobilfilmoneriuygulamasi

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omrfrkg.mobilfilmoneriuygulamasi.databinding.RecyclerItemBinding


class FavoriAdapter(val favoriListe : MutableList<Favori>) : RecyclerView.Adapter<FavoriAdapter.FavoriHolder>() {

    class FavoriHolder(val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriHolder {
        val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoriHolder(binding)
    }

    override fun getItemCount(): Int {
        return favoriListe.size
    }

    override fun onBindViewHolder(holder: FavoriHolder, position: Int) {
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context,FilmAnasayfaDetayActivity::class.java)
            intent.putExtra("favori",favoriListe.get(position))
            /*intent.putExtra("kullaniciYas",kullaniciListe.get(position).yasi)
            intent.putExtra("kullaniciId",kullaniciListe.get(position).id)*/
            holder.itemView.context.startActivity(intent)
        }
        holder.binding.recyclerTextView.text = favoriListe.get(position).FavoriResim
    }
}