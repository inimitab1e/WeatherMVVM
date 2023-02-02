package com.example.weathermvvm.extensions

import android.widget.SearchView
import android.widget.TextView

inline fun SearchView.onTextChange(crossinline listener: (String?) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(query: String?): Boolean {
            listener(query)
            return true
        }
    })
}

fun TextView.clear() {
    this.text = ""
}