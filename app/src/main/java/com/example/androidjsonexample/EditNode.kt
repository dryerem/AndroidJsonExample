package com.example.androidjsonexample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView

class EditNode : AppCompatActivity() {

    private lateinit var _note: Note;
    private lateinit var _name: TextView;
    private lateinit var _desc: TextView;
    private lateinit var _status: CheckBox;
    private var _isEdit: Boolean = false;

    private var _noteId: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_node)

        _isEdit = intent.getBooleanExtra("isEdit", true)
        _name   = findViewById(R.id.name)
        _desc   = findViewById(R.id.text)
        _status = findViewById(R.id.status)

        if (_isEdit) {
            _note = NotesReader.getGson().fromJson(intent.getStringExtra("data"),
                Note::class.java)

            _name.text = _note.name
            _desc.text = _note.text
            if (_note.status) {
                _status.text = "Выполнено"
            }
            else {
                _status.text = "Не выполнено"
            }
            _status.isChecked = _note.status

            if(_note.status) {
                _status.setTextColor(Color.GREEN)
            } else {
                _status.setTextColor(Color.RED)
            }
        }

        val saveButton: Button = findViewById(R.id.save)
        saveButton.setOnClickListener {
            if (_isEdit) {
                var notes: Notes = NotesReader.readNotes()
                notes.remove(_note)

                _note.text = _desc.text.toString()
                _note.name = _name.text.toString()
                _note.status = _status.isChecked

                notes.add(_note)
                NotesReader.Save(notes)
            } else {
                NotesReader.AddNote(Note(_name.text.toString(), _desc.text.toString(),
                    _status.isChecked))
            }
            finish()
        }

    }
}