package com.example.androidjsonexample

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class NotesReader {

    companion object {
        private lateinit var _preferences: SharedPreferences
        private lateinit var _editor: SharedPreferences.Editor
        private lateinit var _gson: Gson

        fun Init(context: Context) {
            _preferences = context.getSharedPreferences("notes",
                    Context.MODE_PRIVATE)
            _editor = _preferences.edit()
            _gson = Gson()
        }

        fun getGson() : Gson {
            return _gson
        }

        fun readNotes() : Notes {
            return _gson.fromJson(_preferences.getString("data", "{ }"), Notes::class.java)
        }

        fun AddNote(note: Note) {
            val notes: Notes = readNotes()
            notes.add(note)
            _editor.putString("data", _gson.toJson(notes))
            _editor.apply()
        }

        fun Save(notes: Notes) {
            _editor.putString("data", _gson.toJson(notes))
            _editor.apply()
        }

    }

}