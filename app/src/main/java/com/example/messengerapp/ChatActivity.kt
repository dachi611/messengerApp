package com.example.messengerapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChatActivity : ComponentActivity() {
    val messageTexts = arrayListOf( "Hey, how's it going?",
        "Did you finish that report?",
        "Let's catch up for coffee sometime." +
                "Remember to buy groceries on your way home." +
                "Any updates on the client meeting?" +
                "The project deadline is approaching.",
        "Remember to buy groceries on your way home.",
        "What are your plans for the weekend?",
        "The project deadline is approaching.",
        "I watched a great movie last night.",
        "Don't forget to call your mom.",
        "Any updates on the client meeting?",
        "Looking forward to the party next week!",
        "What are your plans for the weekend?",
        "The project deadline is approaching.",
        "I watched a great movie last night.",
        "Don't forget to call your mom.",
        "Remember to buy groceries on your way home." +
                " Any updates on the client meeting?" +
                " The project deadline is approaching.",
        "What are your plans for the weekend?",
        "The project deadline is approaching.",
        "I watched a great movie last night.",
        "Don't forget to call your mom.")
    val adapter : MessagesRecyclerView = MessagesRecyclerView(this, messageTexts)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_activity)

        val recyclerView : RecyclerView = findViewById(R.id.messages)
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(this,)
        layoutManager.reverseLayout = true
        recyclerView.layoutManager =layoutManager;
    }

    override fun onResume() {
        super.onResume()
        val backButton : Button = findViewById(R.id.backButton)
        val sendButton : Button = findViewById(R.id.sendButton)
        val typedText : EditText = findViewById(R.id.typedText)

        backButton.setOnClickListener{
            finish()
        }

        sendButton.setOnClickListener {
            if(typedText.text.toString()!="") {
                // Add the new message to the existing list
                messageTexts.add(0, typedText.text.toString())

                // Notify the adapter that the dataset has changed
                adapter.notifyDataSetChanged()

                // Clear the typed text
                typedText.text.clear()
            }
        }
    }
}

