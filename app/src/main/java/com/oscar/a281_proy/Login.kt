package com.oscar.a281_proy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.oscar.a281_proy.database.SQLite
import kotlinx.coroutines.*

class Login : AppCompatActivity() {

    private lateinit var dbHelper: SQLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val emailInput = findViewById<EditText>(R.id.username_input)
        val passwordInput = findViewById<EditText>(R.id.password_input)
        val loginButton = findViewById<Button>(R.id.login_btn)
        val registerButton = findViewById<Button>(R.id.register_btn)

        loginButton.setOnClickListener {
            val intent = Intent(this@Login, Panelprincipal::class.java)
            startActivity(intent)
        }

        registerButton.setOnClickListener {
            val intent = Intent(this@Login, RegisterActivity::class.java)
            startActivity(intent)
        }

        dbHelper = SQLite(this)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(!email.endsWith("@gmail.com")){
                Toast.makeText(this, "Correo incorrecto", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            CoroutineScope(Dispatchers.Main).launch {
                val userExists = withContext(Dispatchers.IO) {
                    dbHelper.checkUser(email, password)
                }

                if (userExists) {
                    // Si el usuario existe, redirige al panel
                    val intent = Intent(this@Login, Panelprincipal::class.java)
                    startActivity(intent)
                } else {
                    // Si el usuario no existe, muestra un mensaje
                    Toast.makeText(this@Login, "Usuario no encontrado.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}