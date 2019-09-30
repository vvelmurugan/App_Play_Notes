package com.example.app_play.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_play.R

/**
 * Created by vel-4009 on 2019-09-30.
 *
 *  Love your Job
 *
 */

class NotesListRecyclerView(val onItemClicked: (String) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    var mNotes = mutableListOf<Note>()

    fun swapModel(notesList: MutableList<Note>)
    {
        mNotes = notesList
    }

    override fun getItemCount()= mNotes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_list_item,parent,false)
        return NotesViewHolder(view){onItemClicked.invoke(it)}
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val note = mNotes[position]
        (holder as NotesViewHolder).setTitle(note.id, note.title)
    }


    class NotesViewHolder(itemView: View, onItemClicked: (String) -> Unit): RecyclerView.ViewHolder(itemView)
    {
        private val titleView = itemView.findViewById<TextView>(R.id.note_item)
        private var itemId:String = "-1"

        init {
            itemView.setOnClickListener { onItemClicked.invoke(itemId) }
        }

        fun setTitle(noteId: String, title: String)
        {
            itemId = noteId
            titleView.text = title
        }
    }

}