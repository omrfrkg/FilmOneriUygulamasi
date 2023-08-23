package com.omrfrkg.mobilfilmoneriuygulamasi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class fragmentViewModel : ViewModel() {
    val data = MutableLiveData<Int>()

    fun setData(newData : Int){
        data.value = newData
    }
}