package com.example.victor_vallecillos_uf1_act18

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SummaryActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var buttonConfirm: Button
    private lateinit var buttonCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        recyclerView = findViewById(R.id.recyclerViewSummary)
        buttonConfirm = findViewById(R.id.buttonConfirm)
        buttonCancel = findViewById(R.id.buttonCancel)

        val productNames = intent.getStringArrayExtra("productNames") ?: arrayOf()
        val productQuantities = intent.getIntArrayExtra("productQuantities") ?: intArrayOf()
        val productPrices = intent.getDoubleArrayExtra("productPrices") ?: doubleArrayOf()
        val productImageIds = intent.getIntArrayExtra("productImageIds") ?: intArrayOf()

        val products = productNames.mapIndexed { index, name ->
            Product(name, productPrices[index], productImageIds[index], productQuantities[index])
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ProductAdapter(products) {}

        buttonConfirm.setOnClickListener {
            val summary = products.joinToString("\n") { "${it.name} x ${it.quantity}" }
            Log.d("SummaryActivity", "Compra confirmada:\n$summary")
            finish() // Tanca l'activitat actual
        }

        buttonCancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}





