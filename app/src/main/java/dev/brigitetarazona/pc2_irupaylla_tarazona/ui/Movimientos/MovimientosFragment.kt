package dev.brigitetarazona.pc2_irupaylla_tarazona.ui.Movimientos
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import dev.brigitetarazona.pc2_irupaylla_tarazona.R

class MovimientosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_movimientos, container, false)

        // Obtener referencia de los elementos
        val rgTipo: RadioGroup = view.findViewById(R.id.rgTipo)
        val etFecha: EditText = view.findViewById(R.id.etFecha)
        val etDescripcion: EditText = view.findViewById(R.id.etDescripcion)
        val etMonto: EditText = view.findViewById(R.id.etMonto)
        val btRegistrar: Button = view.findViewById(R.id.btRegistrar)

        // Inicializar Firestore
        val db = FirebaseFirestore.getInstance()

        // Mostrar DatePickerDialog
        etFecha.setOnClickListener {
            mostrarCalendario(etFecha)
        }

        btRegistrar.setOnClickListener {
            val tipoId: Int = rgTipo.checkedRadioButtonId
            if (tipoId == -1) {
                Toast.makeText(requireContext(), "Ingrese un tipo de operación", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val tipo: String = view.findViewById<RadioButton>(tipoId).text.toString()
            val montoText = etMonto.text.toString()
            val fechaText = etFecha.text.toString()
            val descripcionText = etDescripcion.text.toString()

            if (montoText.isNotEmpty() && fechaText.isNotEmpty() && descripcionText.isNotEmpty()) {
                val monto = montoText.toDouble()
                val resultado = if (tipo.equals("Ingreso", ignoreCase = true)) {
                    monto * 1
                } else {
                    monto * -1
                }

                // Crear el modelo de movimiento
                val movimiento =
                    dev.brigitetarazona.pc2_irupaylla_tarazona.ui.Movimientos.model.MovimientosModel(
                        tipo = tipo,
                        monto = resultado,
                        fecha = fechaText,
                        descripcion = descripcionText
                    )

                // Guardar el movimiento en Firestore
                db.collection("movimientos")
                    .add(movimiento)
                    .addOnSuccessListener {
                        Toast.makeText(requireContext(), "Registro Grabado con Exito", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(requireContext(), "Error al guardar el registro: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                if (montoText.isEmpty()) {
                    etMonto.error = "Ingrese un monto"
                }
                if (fechaText.isEmpty()) {
                    etFecha.error = "Ingrese una fecha"
                }
                if (descripcionText.isEmpty()) {
                    etDescripcion.error = "Ingrese una descripción"
                }
                Toast.makeText(requireContext(), "Verifique los datos ingresados", Toast.LENGTH_LONG).show()
            }
        }
        return view
    }

    private fun mostrarCalendario(etFecha: EditText) {
        val calendario = Calendar.getInstance()
        val anio = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                // Ajustar el mes (el índice comienza en 0, así que sumamos 1)
                val fechaSeleccionada = "$dayOfMonth/${month + 1}/$year"
                etFecha.setText(fechaSeleccionada)
            },
            anio, mes, dia
        )
        datePickerDialog.show()
    }
}
