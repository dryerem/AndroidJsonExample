package com.example.androidjsonexample

import kotlin.collections. *

class Notes {

    private var _notes : MutableList<Note> = mutableListOf()

    fun getNotes() : MutableList<Note> {
        return _notes
    }

    fun remove(note: Note) {

        _notes.remove(note)
    }

    fun add(note: Note) {
        _notes.add(note)
    }

}