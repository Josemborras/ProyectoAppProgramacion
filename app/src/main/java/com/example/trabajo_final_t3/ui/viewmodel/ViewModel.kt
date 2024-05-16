package com.example.trabajo_final_t3.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.trabajo_final_t3.data.retrofit.Repository

class ViewModel: ViewModel() {
    private val repositorio by lazy { Repository() }

}