package com.ratobing.catatan

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {
    @Insert
    fun insert(note: Note)


    @Query("SELECT*FROM notes")
    fun getListNote(): LiveData<List<Note>>
}