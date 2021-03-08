package com.ratobing.catatan

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class],
    version = 1)
abstract class NoteRoomDatabase : RoomDatabase(){

    abstract fun noteDao() : NoteDao


    companion object{
        var INSTANCE: NoteRoomDatabase? = null

        fun getDatabase(context: Context): NoteRoomDatabase?{
            if (INSTANCE == null){
                synchronized(NoteRoomDatabase::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NoteRoomDatabase::class.java,
                    "note_database")
                        .build()
                }
            }
            return INSTANCE
        }
    }
}
