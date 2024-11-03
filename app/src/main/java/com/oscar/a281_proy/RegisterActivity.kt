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

    private lateinit var dbHelper: SQLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        dbHelper = SQLite(this)


        val spinnerRoles = findViewById<Spinner>(R.id.spinnerRoles)


        val roles = arrayOf("Comprador", "Vendedor", "Delivery")


        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roles)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRoles.adapter = adapter
        spinnerRoles.onItemSelectedListener = this


        val buttonRegister = findViewById<Button>(R.id.buttonRegister)
        buttonRegister.setOnClickListener {
            registerUser(spinnerRoles)
        }
    }

    private fun registerUser(spinnerRoles: Spinner) {
        val email = findViewById<EditText>(R.id.editTextcorreo).text.toString()
        val password = findViewById<EditText>(R.id.editTextcontraseña).text.toString()
        val selectedRole = spinnerRoles.selectedItem.toString()


        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
            return
        }
        if (!email.endsWith("@gmail.com")) {
            Toast.makeText(this, "Por favor, ingresa un correo válido que termine en '@gmail.com'.", Toast.LENGTH_SHORT).show()
            return
        }


        try {
            dbHelper.addUser(email, password, selectedRole) // Llama al método para insertar
            Toast.makeText(this, "Registro exitoso.", Toast.LENGTH_SHORT).show()
            finish() // Cierra la actividad
        } catch (e: Exception) {
            Toast.makeText(this, "Error en el registro: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val selectedRole = parent.getItemAtPosition(position).toString()
        Toast.makeText(this, "Seleccionaste: $selectedRole", Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {

    }
}