package com.example.messengerapp

import android.os.Bundle
import android.widget.Switch
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class MainActivity : ComponentActivity() {

    private val chatItems: ArrayList<ChatListItem> = ArrayList()
    val adapter: ChatRecyclerView = ChatRecyclerView(this, chatItems)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val header: TextView = findViewById(R.id.header)
        header.text = "Messenger 2.0"

        val recyclerView: RecyclerView = findViewById(R.id.chatList)
        dummyChatItems()

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

//        val service = RetrofitClient.getClient().create(ApiService::class.java)
//        val call: Call<ResponseBody> = service.getUserData()
//
//        call.enqueue(object : Callback<ResponseBody> {
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                onResponse(call, response)
//            }
//
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                // Handle failure
//                t.printStackTrace()
//            }
//        })
    }

    override fun onResume() {
        super.onResume()
        val darkModeSwitch: Switch = findViewById(R.id.darkMode)
//        val registerButton: Button = findViewById(R.id.registerButton)

        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            darkMode(isChecked)
        }

//        registerButton.setOnClickListener {
//            val intent = Intent(this, RegisterActivity::class.java)
//            startActivity(intent)
//        }
    }

    private fun dummyChatItems() {
        val chatNames = arrayOf("John Fury", "David Joshua", "Anthony Parker", "Benjamin Perez", "Brandon Roberts", "Brian Robinson", "Christopher Rodriguez", "Daniel Smith", "David Taylor", "John Fury", "David Joshua", "Anthony Parker", "Benjamin Perez", "Brandon Roberts", "Brian Robinson", "Christopher Rodriguez", "Daniel Smith", "David Taylor")
        val messageTexts = arrayOf("On my way to the meeting.", "I'm running late, be there in 15 minutes.", "Can you pick me up from the airport?", "I'm at the store, what else do you need?", "I'm going to bed, see you tomorrow.", "I'm just leaving, I'll be there in 10.", "I'm at the doctor's, I'll call you later.", "I'm at the gym, I'll text you when I'm done.", "I'm at work, I'll talk to you tonight.", "On my way to the meeting.", "I'm running late, be there in 15 minutes.", "Can you pick me up from the airport?", "I'm at the store, what else do you need?", "I'm going to bed, see you tomorrow.", "I'm just leaving, I'll be there in 10.", "I'm at the doctor's, I'll call you later.", "I'm at the gym, I'll text you when I'm done.", "I'm at work, I'll talk to you tonight.")
        for (i in chatNames.indices) {
            chatItems.add(ChatListItem(chatNames[i], messageTexts[i]))
        }
    }

    private fun darkMode(darkMode: Boolean) {
        val background = findViewById<ConstraintLayout>(R.id.mainActivity)
        val header = findViewById<TextView>(R.id.header)
        val darkModeSwitch = findViewById<Switch>(R.id.darkMode)

        if (darkMode) {
            background.setBackgroundColor(android.graphics.Color.parseColor("#FF000000"))
            header.setTextColor(android.graphics.Color.parseColor("#FFFFFFFF"))
            darkModeSwitch.setTextColor(android.graphics.Color.parseColor("#FFFFFFFF"))
        } else {
            background.setBackgroundColor(android.graphics.Color.parseColor("#FFFFFFFF"))
            header.setTextColor(android.graphics.Color.parseColor("#FF000000"))
            darkModeSwitch.setTextColor(android.graphics.Color.parseColor("#FF000000"))
        }
        for (item in chatItems) {
            item.isDarkMode = darkMode
        }
        adapter.notifyDataSetChanged()
    }

    private fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
        if (response.isSuccessful) {
            val responseBody: ResponseBody? = response.body()
            val jsonString: String? = responseBody?.string()

            // Print the raw JSON data to the console
            println("Received JSON data: $jsonString")

        } else {
            // Handle error response
            // You can check response.errorBody() for more details
        }
    }
}
