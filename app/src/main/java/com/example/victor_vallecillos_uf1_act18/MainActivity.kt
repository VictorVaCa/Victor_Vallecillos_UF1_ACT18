package com.example.victor_vallecillos_uf1_act18

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var textViewTotal: TextView
    private lateinit var buttonCheckout: Button
    private val products = listOf(
        Product("Producte 1", 10.0, R.drawable.product1),
        Product("Producte 2", 15.0, R.drawable.product2),
        Product("Producte 3", 20.0, R.drawable.product3)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewProducts)
        textViewTotal = findViewById(R.id.textViewTotal)
        buttonCheckout = findViewById(R.id.buttonCheckout)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ProductAdapter(products) { updateTotal() }

        buttonCheckout.setOnClickListener {
            val intent = Intent(this, SummaryActivity::class.java)
            val selectedProducts = products.filter { it.quantity > 0 }
            intent.putExtra("productNames", selectedProducts.map { it.name }.toTypedArray())
            intent.putExtra("productQuantities", selectedProducts.map { it.quantity }.toIntArray())
            intent.putExtra("productPrices", selectedProducts.map { it.price }.toDoubleArray())
            intent.putExtra("productImageIds", selectedProducts.map { it.imageResId }.toIntArray())
            startActivity(intent)
        }

        updateTotal()
    }

    private fun updateTotal() {
        val total = products.sumOf { it.price * it.quantity }
        textViewTotal.text = "Total: €%.2f".format(total)
    }
}


