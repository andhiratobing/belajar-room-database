package com.ratobing.catatan

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey
    @NonNull
    val id: String,

    @NonNull
    @ColumnInfo(name = "note")
    val note: String?

)