package com.example.tp5.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tp5.R

/**
 * A simple [Fragment] subclass.
 * Use the [map_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class map_fragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_map_fragment, container, false)
    return view
    }


}