package com.example.app_play

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.app_play.databinding.ActivityMainBinding
import com.example.app_play.notes.AddNotesFragment
import com.example.app_play.utils.addFragment

class MainActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        mBinding.addNotes.setOnClickListener { addNotesClicked() }

    }

    private fun addNotesClicked()
    {
        mBinding.addNotes.visibility = View.GONE
        addFragment(supportFragmentManager, AddNotesFragment.newInstance(), "add new note", R.id.fragments_holder, true)
    }
}

