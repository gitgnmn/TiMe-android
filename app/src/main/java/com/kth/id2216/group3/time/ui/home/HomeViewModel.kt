package com.kth.id2216.group3.time.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kth.id2216.group3.time.data.dao.TimerDAO
import com.kth.id2216.group3.time.data.db.TiMeDatabase_Impl


class HomeViewModel(val database: TimerDAO, application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}