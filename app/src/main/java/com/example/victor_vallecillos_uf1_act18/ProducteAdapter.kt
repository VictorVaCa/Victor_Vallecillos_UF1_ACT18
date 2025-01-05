package com.example.victor_vallecillos_uf1_act18

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private val products: List<Product>,
    private val onQuantityChanged: () -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageViewProduct)
        val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        val textViewPrice: TextView = itemView.findViewById(R.id.textViewPrice)
        val textViewQuantity: TextView = itemView.findViewById(R.id.textViewQuantity)
        val buttonAdd: Button = itemView.findViewById(R.id.buttonAdd)
        val buttonRemove: Button = itemView.findViewById(R.id.buttonRemove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.imageView.setImageResource(product.imageResId)
        holder.textViewName.text = product.name
        holder.textViewPrice.text = "€${product.price}"
        holder.textViewQuantity.text = product.quantity.toString()

        holder.buttonAdd.setOnClickListener {
            product.quantity++
            holder.textViewQuantity.text = product.quantity.toString()
            onQuantityChanged()
        }

        holder.buttonRemove.setOnClickListener {
            if (product.quantity > 0) {
                product.quantity--
                holder.textViewQuantity.text = product.quantity.toString()
                onQuantityChanged()
            }
        }
    }

    override fun getItemCount() = products.size
}

