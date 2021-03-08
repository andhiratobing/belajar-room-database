package com.ratobing.catatan

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: NoteViewModel
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = NoteAdapter()
        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        ShowListNotes()


        btn_create.setOnClickListener {
            Intent(applicationContext, CreateNoteActivity::class.java).also {
                startActivityForResult(it, CREATE_NOTE_REQUEST_CODE)
            }
        }
    }

    private fun ShowListNotes() {
        rv_note.layoutManager = LinearLayoutManager(this)
        rv_note.setHasFixedSize(true)
        viewModel.getLstNote().observe(this, object : Observer<List<Note>>{
            override fun onChanged(t: List<Note>?) {
                if (t != null) {
                    adapter.setList(t)
                }
                rv_note.adapter = adapter
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_NOTE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val note = data?.getStringExtra(CreateNoteActivity.NEW_NOTE)
            if (note != null) {
                viewModel.insert(note)
            }
            Toast.makeText(applicationContext, "Note berhasil ditambahkan", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(applicationContext, "Note gagal ditambahkan", Toast.LENGTH_SHORT).show()
        }
    }


    companion object {
        val CREATE_NOTE_REQUEST_CODE = 1
    }
}