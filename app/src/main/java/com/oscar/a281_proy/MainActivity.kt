package com.oscar.a281_proy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.oscar.a281_proy.database.SQLite

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton = findViewById<Button>(R.id.login_btn)
        val registerButton = findViewById<Button>(R.id.register_btn)

        loginButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.username_input).text.toString()
            val password = findViewById<EditText>(R.id.password_input).text.toString()

            // Verificar que los campos no estén vacíos
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Lógica para buscar en la base de datos
            val dbHelper = SQLite(this)  // Reemplaza con tu clase SQLite
            val userExists = dbHelper.checkUser(email, password)

            if (userExists) {
                // Si el usuario existe, redirige a panel.xml
                val intent = Intent(this, PanelPrincipal::class.java)  // Reemplaza con tu Activity del panel
                startActivity(intent)
            } else {
                // Si el usuario no existe, muestra un mensaje
                Toast.makeText(this, "Usuario no creado.", Toast.LENGTH_SHORT).show()
            }
        }


        registerButton.setOnClickListener {
            // Navegar a la pantalla de registro
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }
}