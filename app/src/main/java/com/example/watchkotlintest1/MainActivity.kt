package com.example.watchkotlintest1

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.wear.widget.WearableLinearLayoutManager
import androidx.wear.widget.WearableRecyclerView
import com.example.watchkotlintest1.model.Stock
import java.util.ArrayList

class MainActivity : Activity() {

    private var stockArrayList: ArrayList<Stock>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main);

        val header: TextView = findViewById<TextView>(R.id.app_header)
        Log.d("headerText", (header.text as String))
        stockArrayList = initializeStockList()
        
        val recyclerView: WearableRecyclerView = findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = WearableLinearLayoutManager(this)
        recyclerView.adapter = RecyclerViewAdapter(this, stockArrayList!!, object :
            RecyclerViewAdapter.AdapterCallback {
            override fun onItemClicked(menuPosition: Int?) {
                val clickedStock: Stock = stockArrayList!![menuPosition!!]
                Log.d("clickedStock", clickedStock.SYMBOL)
            }
        })
    }

    private fun initializeStockList(): ArrayList<Stock> {
        val stocks: ArrayList<Stock> = ArrayList<Stock>()
        val stock1 = Stock("52.49", "-0.14%", "-1", "-0.66", "YHOO", "1")
        val stock2 = Stock("104.14", "1.12%", "1", "1.39", "AAPL", "2")
        val stock3 = Stock("768.00", "0.52%", "1", "4.14", "GOOG", "3")
        val stock4 = Stock("155.84", "-0.76%", "-1", "-1.20", "META", "4")
        stocks.add(stock1)
        stocks.add(stock2)
        stocks.add(stock3)
        stocks.add(stock4)
        Log.d("initializeStockList", stocks.toString())
        return stocks
    }
}