import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.brigitetarazona.pc2_irupaylla_tarazona.R
import dev.brigitetarazona.pc2_irupaylla_tarazona.ui.ResumenFinanciero.model.FinanzasModel

class FinanzasAdapter(private val finanzasList: List<FinanzasModel>) :
    RecyclerView.Adapter<FinanzasAdapter.FinanzasViewHolder>() {

    // Definir la clase ViewHolder dentro del adaptador
    class FinanzasViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDescription: TextView = view.findViewById(R.id.tvDescription)
        val tvAmount: TextView = view.findViewById(R.id.tvamount)
        val tvDate: TextView = view.findViewById(R.id.tvdate)
        val tvTipo: TextView = view.findViewById(R.id.tvTipo) // Nuevo TextView para tipo

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinanzasViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_finanzas, parent, false)  // Nombre correcto del archivo XML
        return FinanzasViewHolder(view)
    }

    override fun onBindViewHolder(holder: FinanzasViewHolder, position: Int) {
        val item = finanzasList[position]
        holder.tvDescription.text = item.descripcion  // Asegúrate de que esté usando 'descripcion'
        holder.tvAmount.text = item.monto.toString()  // Asegúrate de que esté usando 'monto'
        holder.tvDate.text = item.fecha  // Asegúrate de que esté usando 'fecha'
        holder.tvTipo.text = item.tipo
    }

    override fun getItemCount(): Int = finanzasList.size
}
