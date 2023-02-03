package com.example.weathermvvm.extensions

import android.widget.SearchView
import android.widget.TextView

inline fun SearchView.onTextChange(crossinline listener: (String?) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            listener(query)
            return true
        }

        override fun onQueryTextChange(query: String?): Boolean {
            return false
        }
    })
}

inline fun SearchView.onCloseButton(crossinline listener: () -> Unit) {
    this.setOnCloseListener {
        listener()
        true
    }
}

fun TextView.clear() {
    this.text = ""
}