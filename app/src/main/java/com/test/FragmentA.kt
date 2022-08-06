package com.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class FragmentA : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    companion object{

        private var fragmentA : FragmentA? = null

        fun getInstance() : FragmentA {
            if (fragmentA == null){
                fragmentA = FragmentA()
            }
            return fragmentA!!
        }
    }

}