package com.dabekka.btuchat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.dabekka.btuchat.R.attr.color
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_chat.*
import org.w3c.dom.Text

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message2")

        myRef.setValue("Hello, World!")

        var layout = chatLayout
        for (i in 1..15) {

            var txtView = TextView(this)
            txtView.setPadding(15, 15, 15, 15)
            txtView.setBackgroundColor(resources.getColor(R.color.appGray))
            txtView.setTextSize(TypedValue.COMPLEX_UNIT_PX, applicationContext.resources.getDimension(R.dimen.abc_text_size_medium_material))


            layout.addView(txtView)
            readFromDatabase(i, txtView)
        }
    }

    private fun readFromDatabase(id: Int, textView: TextView) {

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference(id.toString())


        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(String::class.java)

                textView.text = value.toString()
                // Log.d(TAG, "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException())
                textView.text = "Error!"
            }
        })
    }
}
