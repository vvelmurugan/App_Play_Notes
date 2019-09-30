package com.example.app_play.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.app_play.BaseApplication
import com.example.app_play.R
import com.example.app_play.data.source.local.AppDataRepository
import com.example.app_play.data.source.local.AppDataSource
import com.example.app_play.data.source.local.ErrorMessage
import com.example.app_play.data.source.local.NotesEntry
import com.example.app_play.databinding.NoteDetailFragmentBinding

/**
 * Created by vel-4009 on 2019-09-30.
 *
 *  Love your Job
 *
 */

class NoteDetailFragment : Fragment()
{
    lateinit var mNoteId: String
    companion object {
        fun newInstance(noteId: String): NoteDetailFragment {

            val bundle = Bundle()
            bundle.putString(NotesEntry.COLUMN_NOTE_ID, noteId)

            val fragment = NoteDetailFragment()
            fragment.arguments = bundle

            return fragment
        }
    }

    lateinit var mBinding: NoteDetailFragmentBinding
    val dataSource = AppDataRepository.getInstance(BaseApplication.getInstance())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        mNoteId = arguments?.getString(NotesEntry.COLUMN_NOTE_ID) ?: "-1"
        mBinding = DataBindingUtil.inflate(inflater, R.layout.note_detail_fragment, container, false)
        //To enable toolbar to have options
        setHasOptionsMenu(true)

        loadNoteDetail()

        return mBinding.root

    }

    private fun loadNoteDetail()
    {
        dataSource.getNoteDetail(mNoteId, object : AppDataSource.DataLayerCallback<Note>
        {
            override fun onSuccess(response: Note) {
                showNoteDetail(response)
            }

            override fun onError(errorMessage: ErrorMessage) {

            }

        })

    }

    private fun showNoteDetail(note: Note)
    {
        mBinding.notesTitle.setText(note.title)
        mBinding.notesDescription.setText(note.description)
    }

}