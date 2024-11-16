package dev.brigitetarazona.pc2_irupaylla_tarazona.ui.Movimientos

import FinanzasAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dev.brigitetarazona.pc2_irupaylla_tarazona.R
import dev.brigitetarazona.pc2_irupaylla_tarazona.ui.ResumenFinanciero.model.FinanzasModel

class FinanzasFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var balanceTextView: TextView
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_finanzas, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        balanceTextView = view.findViewById(R.id.balanceTextView)
        db = FirebaseFirestore.getInstance()

        // Configurar el RecyclerView con un LinearLayoutManager
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Cargar las transacciones desde Firestore
        loadTransactions()

        return view
    }

    private fun loadTransactions() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId == null) {
            Toast.makeText(context, "Usuario no autenticado.", Toast.LENGTH_SHORT).show()
            return
        }

        // Obtener los movimientos desde Firestore para el usuario actual
        db.collection("users").document(userId).collection("movimientos")
            .get()
            .addOnSuccessListener { documents ->
                val finanzasList = mutableListOf<FinanzasModel>()
                var totalBalance = 0.0

                for (document in documents) {
                    val item = document.toObject(FinanzasModel::class.java)
                    finanzasList.add(item)
                    totalBalance += item.monto // Usar 'monto' en lugar de 'amount'
                    Log.d("FinanzasFragment", "movimientos: ${item.descripcion}, ${item.monto}, ${item.fecha}, ${item.tipo}")
                }

                recyclerView.adapter = FinanzasAdapter(finanzasList)
                balanceTextView.text = "Balance Total: $totalBalance"
            }
            .addOnFailureListener {
                Toast.makeText(context, "Error al obtener las transacciones.", Toast.LENGTH_SHORT).show()
            }

    }
}
