package com.android.example.virtualfoodassist

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.*


class Info : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.info, container, false)
        setUpActionBar()
        return view
    }

    private fun setUpActionBar() {
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).setTitle(R.string.general_app_information)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
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