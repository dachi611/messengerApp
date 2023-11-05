package com.example.messengerapp

import android.os.Bundle
import android.widget.Switch

import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : ComponentActivity() {

    private val chatItems: ArrayList<ChatListItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.headline)
        textView.text = "Messenger 2.0"

        val recyclerView :RecyclerView = findViewById(R.id.chatList)
        dummyChatItems()
        val darkModeSwitch = findViewById<Switch>(R.id.darkMode)

        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            darkMode(isChecked)
        }

        val adapter : ChatRecyclerView = ChatRecyclerView(this, chatItems)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun dummyChatItems(){
        val chatNames = arrayOf("John Fury", "David Joshua", "Anthony Parker", "Benjamin Perez", "Brandon Roberts", "Brian Robinson", "Christopher Rodriguez", "Daniel Smith","David Taylor", "John Fury", "David Joshua", "Anthony Parker", "Benjamin Perez", "Brandon Roberts", "Brian Robinson","Christopher Rodriguez", "Daniel Smith", "David Taylor")
        val messageTexts = arrayOf("On my way to the meeting.", "I'm running late, be there in 15 minutes.", "Can you pick me up from the airport?", "I'm at the store, what else do you need?", "I'm going to bed, see you tomorrow.", "I'm just leaving, I'll be there in 10.", "I'm at the doctor's, I'll call you later.", "I'm at the gym, I'll text you when I'm done.", "I'm at work, I'll talk to you tonight.", "On my way to the meeting.", "I'm running late, be there in 15 minutes.", "Can you pick me up from the airport?", "I'm at the store, what else do you need?", "I'm going to bed, see you tomorrow.", "I'm just leaving, I'll be there in 10.", "I'm at the doctor's, I'll call you later.", "I'm at the gym, I'll text you when I'm done.", "I'm at work, I'll talk to you tonight.")
        for (i in chatNames.indices){
            chatItems.add(ChatListItem(chatNames[i], messageTexts[i]))
        }
    }

    private fun darkMode(darkMode: Boolean){
        val background = findViewById<ConstraintLayout>(R.id.mainActivity)
        val messageText = findViewById<TextView>(R.id.message_text)
        val chatName = findViewById<TextView>(R.id.chat_name)
        if(darkMode){
            background.setBackgroundColor(android.graphics.Color.parseColor("#FF000000"))
            messageText.setTextColor(android.graphics.Color.parseColor("#FFFFFFFF"))
            chatName.setTextColor(android.graphics.Color.parseColor("#FFFFFFFF"))
        }else{
            background.setBackgroundColor(android.graphics.Color.parseColor("#FFFFFFFF"))
            messageText.setTextColor(android.graphics.Color.parseColor("#FF000000"))
            chatName.setTextColor(android.graphics.Color.parseColor("#FF000000"))
        }
    }
}