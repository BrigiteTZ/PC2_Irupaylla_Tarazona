package dev.brigitetarazona.pc2_irupaylla_tarazona.ui.ResumenFinanciero.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class FinanzasModel(
    val descripcion: String = "",
    val fecha: String = "",
    val monto: Double = 0.0,
    val tipo: String = ""
)

