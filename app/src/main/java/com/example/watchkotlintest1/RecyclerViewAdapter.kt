package com.example.watchkotlintest1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.watchkotlintest1.model.Stock
import kotlin.collections.ArrayList

class RecyclerViewAdapter(
    private val context: Context,
    dataArgs: ArrayList<Stock>,
    callback: AdapterCallback?
) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder?>() {

    private val dataSource: ArrayList<Stock> = dataArgs
    private val callback: AdapterCallback? = callback
    private val drawableIcon: String? = null

    interface AdapterCallback {
        fun onItemClicked(menuPosition: Int?){}
    }

    class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemContainer: RelativeLayout
        var stockSymbol: TextView
        var stockPrice: TextView
        var stockChange: TextView
        var stockIcon: ImageView

        init {
            itemContainer = view.findViewById<RelativeLayout>(R.id.item_container)
            stockSymbol = view.findViewById<TextView>(R.id.stock_symbol)
            stockPrice = view.findViewById<TextView>(R.id.stock_price)
            stockChange = view.findViewById<TextView>(R.id.stock_change)
            stockIcon = view.findViewById(R.id.stock_icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val data_provider: Stock = dataSource[position]
        holder.stockSymbol.text = data_provider.SYMBOL
        holder.stockPrice.text = String.format("$ %s", data_provider.BIDPRICE)
        holder.stockChange.text = data_provider.PERCENT_CHANGE
        if (data_provider.PERCENT_CHANGE.contains("-")) {
            holder.stockIcon.setImageResource(R.drawable.stockdown)
        } else {
            holder.stockIcon.setImageResource(R.drawable.stockup)
        }
        holder.itemContainer.setOnClickListener(View.OnClickListener {    //v is lambda for new View.OnClickListener()
            callback?.onItemClicked(position)
        })
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }
}