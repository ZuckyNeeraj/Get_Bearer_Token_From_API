package com.example.get_bearer_token_app

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var clientNameEditText: EditText
    private lateinit var clientEmailEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clientNameEditText = findViewById(R.id.editTextClientName)
        clientEmailEditText = findViewById(R.id.editTextClientEmail)
        submitButton = findViewById(R.id.buttonSubmit)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://simple-books-api.glitch.me")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        submitButton.setOnClickListener {
            val clientName = clientNameEditText.text.toString()
            val clientEmail = clientEmailEditText.text.toString()
            val tokenRequest = TokenRequest(clientName, clientEmail)

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response: TokenResponse = apiService.getToken(tokenRequest)
                    val token = response.accessToken

                    Log.d("response", token.toString())
                } catch (e: Exception) {
                    Log.e("error", "There is some errors $e")
                }
            }
        }
    }

}