package com.ratobing.catatan

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class NoteViewModel(application: Application) : AndroidViewModel(application){

    private var noteDao: NoteDao?
    private var noteDB: NoteRoomDatabase?
    private var list: LiveData<List<Note>>

    init {
        noteDB = NoteRoomDatabase.getDatabase(application)
        noteDao = noteDB?.noteDao()
        list = noteDao?.getListNote()!!
    }

    fun insert(textNote : String){
        insertToDatabase(textNote)
    }

    fun getLstNote(): LiveData<List<Note>> = list

    private fun insertToDatabase(textNote: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val noteId = UUID.randomUUID().toString()
            val note = Note(
                noteId,
                textNote)

            noteDao?.insert(note)
        }
    }


}