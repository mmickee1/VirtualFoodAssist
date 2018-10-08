package com.android.example.virtualfoodassist

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.*


class Info : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.info, container, false)
        setUpActionBar()
        return view
    }

    private fun setUpActionBar() {
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).setTitle(R.string.general_app_information)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.getItemId()) {
            android.R.id.home
            -> {
                val i = Intent(activity, MainActivity::class.java)
                startActivity(i)
                (activity as AppCompatActivity).setTitle(R.string.app_name)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}