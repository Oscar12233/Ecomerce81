package com.oscar.a281_proy

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.oscar.a281_proy.database.SQLite

class RegisterActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var dbHelper: SQLite // Inicializar tu objeto de base de datos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicializar el objeto SQLite
        dbHelper = SQLite(this)

        // Inicializar el Spinner
        val spinnerRoles = findViewById<Spinner>(R.id.spinnerRoles)

        // Crear un array de roles
        val roles = arrayOf("Comprador", "Vendedor", "Delivery")

        // Crear un ArrayAdapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roles)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRoles.adapter = adapter
        spinnerRoles.onItemSelectedListener = this

        // Configurar el botón de registro
        val buttonRegister = findViewById<Button>(R.id.buttonRegister)
        buttonRegister.setOnClickListener {
            registerUser(spinnerRoles) // Llamar a la función para registrar el usuario
        }
    }

    private fun registerUser(spinnerRoles: Spinner) {
        val email = findViewById<EditText>(R.id.editTextcorreo).text.toString()
        val password = findViewById<EditText>(R.id.editTextcontraseña).text.toString()
        val selectedRole = spinnerRoles.selectedItem.toString()

        // Validar los campos
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
            return
        }

        // Intentar agregar el usuario a la base de datos
        try {
            dbHelper.addUser(email, password, selectedRole) // Llama al método para insertar
            Toast.makeText(this, "Registro exitoso.", Toast.LENGTH_SHORT).show()
            finish() // Cierra la actividad
        } catch (e: Exception) {
            Toast.makeText(this, "Error en el registro: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    // Implementar los métodos de la interfaz OnItemSelectedListener
    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val selectedRole = parent.getItemAtPosition(position).toString()
        Toast.makeText(this, "Seleccionaste: $selectedRole", Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Aquí puedes manejar la lógica cuando no hay selección
    }
}