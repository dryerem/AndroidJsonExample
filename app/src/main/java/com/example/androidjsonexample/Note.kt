package com.example.androidjsonexample

data class Note(
    var name: String,
    var text: String,
    var status: Boolean
) {

    override fun toString(): String {
        return name
    }

}