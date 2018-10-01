package com.android.example.virtualfoodassist

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.io.File

class History() : Fragment() {

    val filename = "history.txt"
    var file = File(filename)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.history, container, false)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).setTitle(R.string.history)
        setHasOptionsMenu(true)

        val txtShowNote = view.findViewById<TextView>(R.id.txtShowNotes)

        //currently empty
        //crashes if empty. add null check , when done, remove comments
        //TODO: nullcheck
        /*
        txtShowNote.text =
                context!!.openFileInput(filename).bufferedReader().use {
                    it.readText()
                }*/

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}