package com.android.example.virtualfoodassist

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.example.virtualfoodassist.DB.DBHandler
import kotlinx.android.synthetic.main.history.*
import java.io.File

class History() : Fragment() {

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
        var data = DBHandler(this!!.context!!).readData()
        txtShowNote.text = ""
        for (i in 0..data.size - 1) {
            txtShowNote.append(data.get(i).name + "\n")
        }
         txtShowNote.textSize = 30F

        val btn_clear = view.findViewById<Button>(R.id.btn_clear_history)
        btn_clear.setOnClickListener() {
            val contxt = this.context
            val builder = AlertDialog.Builder(contxt!!)
            builder.setTitle("Clear history")
            builder.setMessage("Clear history of scanned files?")
            builder.setPositiveButton("YES") { dialog, which ->
                DBHandler(this.context!!).deleteData()
                DBHandler(this.context!!).readData()
                txtShowNote.text = ""
                for (i in 0..data.size - 1) {
                    txtShowNote.append(data.get(i).name + "\n")
                }
                txtShowNote.textSize = 30f

            }
            builder.setNegativeButton("No") { dialog, which ->
                dialog.cancel()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

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