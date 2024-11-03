package com.oscar.a281_proy

import ProductAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager

data class ImageItem(val imageResId: Int)
class Panelprincipal : AppCompatActivity() {
    private lateinit var productRecyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panelprincipal)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)


        val products = listOf(
            ProductItem(R.drawable.imagen1, "precio 100$"),
            ProductItem(R.drawable.imagen2, "precio 70$"),
            ProductItem(R.drawable.imagen3, "precio 69$"),
            ProductItem(R.drawable.imagen3, "precio 69$"),
            ProductItem(R.drawable.imagen5, "precio 69$"),
            ProductItem(R.drawable.imagen6, "precio 69$"),
            ProductItem(R.drawable.imagen7, "precio 69$")

        )

        productAdapter = ProductAdapter(products)


        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        recyclerView.adapter = productAdapter

        val cartIcon: ImageView = findViewById(R.id.cartIcon)
        cartIcon.setOnClickListener{
            val intent = Intent(this@Panelprincipal, carrito::class.java)
            startActivity(intent)
        }
        val userLogo: ImageView = findViewById(R.id.userLogo)
        cartIcon.setOnClickListener{
            val intent = Intent(this@Panelprincipal, usuario::class.java)
            startActivity(intent)
        }
    }
}


