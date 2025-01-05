package com.example.victor_vallecillos_uf1_act18

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SummaryActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var buttonConfirm: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        recyclerView = findViewById(R.id.recyclerViewSummary)
        buttonConfirm = findViewById(R.id.buttonConfirm)

        val products = intent.getSerializableExtra("products") as? ArrayList<Product> ?: arrayListOf()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ProductAdapter(products) {}

        buttonConfirm.setOnClickListener {
            val summary = products.joinToString("\n") { "${it.name} x ${it.quantity}" }
            Log.d("SummaryActivity", "Compra confirmada:\n$summary")
            finish()
        }
    }
}

