package com.android.example.virtualfoodassist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.NavUtils
import android.util.Log
import android.widget.TextView
import java.io.File
import android.support.v7.app.AppCompatActivity
import android.view.*


class Info() : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.info, container, false)

        //for back button but doesnt work like i want, trying something else atm.
        //supportActionBar?.setDisplayShowHomeEnabled(true)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).setTitle(R.string.general_app_information)

        setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.getItemId()) {
            android.R.id.home
            -> {
                //NavUtils.navigateUpFromSameTask(Info());
                //NavUtils.navigateUpTo()
                /*  val upIntent = Intent(activity, MainActivity::class.java)
                  if (NavUtils.shouldUpRecreateTask(activity!!, upIntent)) {


                      activity!!.finish()
                  } else {
                      NavUtils.navigateUpTo(activity!!, upIntent)
                  } */
                val i = Intent(activity, MainActivity::class.java)
                startActivity(i)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}