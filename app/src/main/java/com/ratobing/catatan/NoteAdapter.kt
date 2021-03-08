package com.ratobing.catatan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(note: Note){
            with(itemView){
                tv_note.text = note.note
            }
        }
    }


    private lateinit var list : List<Note>

    fun setList(list: List<Note>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size



}

