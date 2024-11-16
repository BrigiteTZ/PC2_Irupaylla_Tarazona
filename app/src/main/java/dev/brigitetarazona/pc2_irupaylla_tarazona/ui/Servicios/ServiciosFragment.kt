package dev.brigitetarazona.pc2_irupaylla_tarazona.ui.Servicios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.brigitetarazona.pc2_irupaylla_tarazona.R
import dev.brigitetarazona.pc2_irupaylla_tarazona.ui.Servicios.adapter.ServiciosAdapter
import dev.brigitetarazona.pc2_irupaylla_tarazona.ui.Servicios.model.ServiciosModel

class ServiciosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_servicios, container, false)

        val rvServicios:RecyclerView = view.findViewById(R.id.rvServicios)
        rvServicios.layoutManager= LinearLayoutManager(requireContext())
        rvServicios.adapter = ServiciosAdapter(ListServicios())

        return view
    }

    private fun ListServicios():List<ServiciosModel> {
        var lstServicios: ArrayList<ServiciosModel> = ArrayList()

        lstServicios.add(ServiciosModel(1, "Registro de Gastos"))
        lstServicios.add(ServiciosModel(2, "Registro de Ingresos"))
        lstServicios.add(ServiciosModel(2, "Resumen Financiero"))

        return lstServicios
    }
}