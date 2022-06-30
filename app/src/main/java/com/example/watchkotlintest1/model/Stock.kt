package com.example.watchkotlintest1.model

class Stock(
    var BIDPRICE: String,
    var PERCENT_CHANGE: String,
    var ISUP: String,
    var CHANGE: String,
    var SYMBOL: String,
    private var _ID: String
) {
    fun get_ID(): String {
        return _ID
    }

    fun set_ID(_ID: String) {
        this._ID = _ID
    }
}