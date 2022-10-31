package com.example.androidjsonexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private var _notesList: MutableList<Note> = mutableListOf()
    private lateinit var _listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NotesReader.Init(applicationContext)
        _notesList = NotesReader.readNotes().getNotes()

        val notesAdapter: ArrayAdapter<Note> = ArrayAdapter<Note>(this,
            android.R.layout.simple_list_item_1, _notesList)

        val addButton: FloatingActionButton = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            val intent = Intent(applicationContext, EditNode::class.java)
            intent.putExtra("isEdit", false)
            startActivity(intent)
        }

        _listView = findViewById(R.id.listview_notes)
        _listView.adapter = notesAdapter
        _listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(applicationContext, EditNode::class.java)
            intent.putExtra("data", NotesReader.getGson().toJson(_notesList.get(position)))
            intent.putExtra("isEdit", true)
            startActivity(intent)
        }
    }

    override fun onResume() {
        _notesList = NotesReader.readNotes().getNotes();
        val notesAdapter: ArrayAdapter<Note> = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, _notesList)
        _listView.adapter = notesAdapter
        super.onResume()
    }
}