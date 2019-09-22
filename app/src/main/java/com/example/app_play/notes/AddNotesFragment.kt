package com.example.app_play.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.app_play.R
import com.example.app_play.databinding.AddNewNotesBinding

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        mBinding = DataBindingUtil.inflate(inflater, R.layout.add_new_notes, container, false)
        return mBinding.root
    }
}