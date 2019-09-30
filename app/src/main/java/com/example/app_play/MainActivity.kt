package com.example.app_play

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_play.data.source.local.AppDataRepository
import com.example.app_play.data.source.local.AppDataSource
import com.example.app_play.data.source.local.ErrorMessage
import com.example.app_play.databinding.ActivityMainBinding
import com.example.app_play.notes.AddNotesFragment
import com.example.app_play.notes.Note
import com.example.app_play.notes.NotesListRecyclerView
import com.example.app_play.utils.addFragment

class MainActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityMainBinding
    val mAdapter = NotesListRecyclerView()
    val dataSource = AppDataRepository.getInstance(BaseApplication.getInstance())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mBinding.rvNotesList.layoutManager = LinearLayoutManager(baseContext)

        setSupportActionBar(mBinding.toolbar)
        supportActionBar?.title = "Notes"
        showNotes()

        mBinding.addNotes.setOnClickListener { addNotesClicked() }

    }

    private fun addNotesClicked()
    {
        mBinding.addNotes.visibility = View.GONE
        addFragment(supportFragmentManager, AddNotesFragment.newInstance(), "add new note", R.id.fragments_holder, true)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        mBinding.addNotes.visibility = View.VISIBLE
    }

    private fun showNotes()
    {
        dataSource.getNotes(object : AppDataSource.DataLayerCallback<List<Note>>
        {
            override fun onSuccess(response: List<Note>) {
                mAdapter.mNotes = response as MutableList<Note>
                mBinding.rvNotesList.adapter = mAdapter
            }

            override fun onError(errorMessage: ErrorMessage) {

            }

        })
    }

}

