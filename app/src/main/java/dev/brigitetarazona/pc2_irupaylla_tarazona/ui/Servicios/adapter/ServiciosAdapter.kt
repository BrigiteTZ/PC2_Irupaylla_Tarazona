package dev.brigitetarazona.pc2_irupaylla_tarazona.ui.Servicios.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.brigitetarazona.pc2_irupaylla_tarazona.R
import dev.brigitetarazona.pc2_irupaylla_tarazona.ui.Servicios.model.ServiciosModel

class ServiciosAdapter(private var lstServicios: List<ServiciosModel>)
    :RecyclerView.Adapter<ServiciosAdapter.ViewHolder>(){

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val etServicio = itemView.findViewById<TextView>(R.id.etServicio)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_servicios, parent, false))
    }

    override fun getItemCount(): Int {
        return lstServicios.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemServicio = lstServicios[position]
        holder.etServicio.text=itemServicio.name
    }

}

