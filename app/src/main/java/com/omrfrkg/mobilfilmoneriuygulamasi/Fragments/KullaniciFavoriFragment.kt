package com.omrfrkg.mobilfilmoneriuygulamasi.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.omrfrkg.mobilfilmoneriuygulamasi.DataBaseHelper
import com.omrfrkg.mobilfilmoneriuygulamasi.Favori
import com.omrfrkg.mobilfilmoneriuygulamasi.R
import com.omrfrkg.mobilfilmoneriuygulamasi.ViewPagerAdapter
import com.omrfrkg.mobilfilmoneriuygulamasi.databinding.FragmentKullaniciFavoriBinding
import com.omrfrkg.mobilfilmoneriuygulamasi.fragmentViewModel

class KullaniciFavoriFragment : Fragment() {

    private var _binding: FragmentKullaniciFavoriBinding? = null

    private val binding get() = _binding!!

    private lateinit var mContext: Context

    private val viewModel : fragmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_kullanici_favori, container, false)
        _binding = FragmentKullaniciFavoriBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var resim : String = ""
        val viewPager2 : ViewPager2 = view.findViewById(R.id.viewPager2)
        val db = DataBaseHelper(mContext)
        var data = db.readFavoriData()
        var data2 = db.readFilmData()
        var id : Int = 0
        var idList = ArrayList<Int>()
        var filmIdList = ArrayList<Int>()
        var filmIsimList = ArrayList<String>()






        viewModel.data.observe(viewLifecycleOwner,{


                for (i in 0 until data.size){

                    if (data.get(i).FavoriKullaniciId == it){

                        for (j in 0 until data2.size){
                            if (data.get(i).FavoriFilmId == data2.get(j).FilmId){
                                filmIsimList.add(data2.get(j).FilmAdi)
                            }
                        }
                        resim = data.get(i).FavoriResim
                        id = resources.getIdentifier("com.omrfrkg.mobilfilmoneriuygulamasi:drawable/"+resim, null, null)
                        idList.add(id)
                        filmIdList.add(data.get(i).FavoriFilmId)
                    }
                    viewPager2.adapter = ViewPagerAdapter(idList,filmIdList,filmIsimList)

                }

        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context

    }



}