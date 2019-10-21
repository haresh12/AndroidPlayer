package com.example.androidplayer

import android.app.ProgressDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import android.widget.EditText




private const val API_KEY = "6SJboIfBDR7N6ewaB2IyHbj1qV1EFBLR"
private const val LIMIT = 25
private const val QUERY_TEXT = "Justin"
private const val RESPONSE_CODE = "responseCode"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        stagRecycleView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        getGitList(QUERY_TEXT)
        btnSearch.setOnClickListener {
            if (edtSearch.text.isNotEmpty()) {
                val queryText = edtSearch.text.toString()
                 getGitList(queryText)
            } else {
                Toast.makeText(this@MainActivity, "Please Enter the text", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getGitList(queryText: String) {
        val progressBar = ProgressBar(this@MainActivity, null, android.R.attr.progressBarStyleLarge)
        val params = RelativeLayout.LayoutParams(100, 100)
        params.addRule(RelativeLayout.CENTER_IN_PARENT)
        consMain.addView(progressBar, params)
        val apiService = JsonPlaceHolderApi.create()
        progressBar.visibility = View.VISIBLE
        apiService.getGifList(apiKey = API_KEY, limit = LIMIT, searchQuery = queryText).enqueue(
            object : Callback<GifModel> {
                override fun onResponse(call: Call<GifModel>?, response: Response<GifModel>?) {
                    progressBar.visibility = View.GONE
                    if (!response!!.isSuccessful) {
                        Log.e(RESPONSE_CODE, response.code().toString())
                        return
                    }
                    hideSoftKeyboard(edtSearch)
                    val gifModel = response.body()
                    stagRecycleView.adapter = GifListAdapter(gifModel)
                }

                override fun onFailure(call: Call<GifModel>?, t: Throwable?) {
                }
            }
        )
    }

    private fun hideSoftKeyboard(input: EditText) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(input.windowToken, 0)
    }

}
