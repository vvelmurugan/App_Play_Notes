package com.example.app_play.notes

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.app_play.BaseApplication
import com.example.app_play.R
import com.example.app_play.data.source.local.AppDataRepository
import com.example.app_play.databinding.AddNewNotesBinding
import com.example.app_play.utils.hideKeyboard

/**
 * Created by vel-4009 on 2019-09-23.
 *
 *  Love your Job
 *
 */

class AddNotesFragment : Fragment() {

    companion object {
        fun newInstance(): AddNotesFragment {
            val bundle = Bundle()
            val fragment = AddNotesFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    lateinit var mBinding: AddNewNotesBinding
    val dataSource = AppDataRepository.getInstance(BaseApplication.getInstance())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        mBinding = DataBindingUtil.inflate(inflater, R.layout.add_new_notes, container, false)
        //To enable toolbar to have options
        setHasOptionsMenu(true)

        return mBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.add_note_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.add_note_menu -> addNoteToDb()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun addNoteToDb()
    {
        val title = mBinding.notesTitle.text.toString()
        val description = mBinding.notesDescription.text.toString()

        if(title.isNotBlank())
        {
            dataSource.addNote(title,description)

            Toast.makeText(context,"Your Note Added Successfully", Toast.LENGTH_SHORT).show()
            context?.hideKeyboard(mBinding.notesTitle)
            activity?.onBackPressed()
        }
        else{
            Toast.makeText(context,"Notes cannot be empty", Toast.LENGTH_SHORT).show()
        }

    }
}
