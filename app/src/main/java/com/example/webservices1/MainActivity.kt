package com.example.webservices1

import android.annotation.SuppressLint
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createProgressDialog()

        getDataButton.setOnClickListener {

            //First Checking Id and Then Showing Data
            checkId()
            }
        }

    private fun createProgressDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading..")
        progressDialog.setMessage("Please wait while we fetch data")
        progressDialog.setCancelable(false)
    }

    private fun checkId() {
        if(dataIdEt.text.toString().isNullOrBlank() ||
                dataIdEt.text.toString().toInt() > 10 ||
                dataIdEt.text.toString().toInt() < 1)
                {
            dataIdEt.error = "Please enter digits between 1 to 10 only"
        }
        else {
            showData(dataIdEt.text.toString().toInt())
            progressDialog.show()
            Log.e("Retrofit", "data is: ${dataIdEt.text.toString()}")
        }
    }

    private fun showData(postId: Int) {

        val call = ApiClient.getClient.getData(postId)
        call.enqueue(object : retrofit2.Callback<List<DataModel>>{

            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<List<DataModel>>,
                response: Response<List<DataModel>>
            ) {
                for (data in response.body()!!){

                    progressDialog.dismiss()
                    //storing data in cardview's textviews
                    id_tv.text = "ID: ${data.postId.toString()}"
                    title_tv.text = "TITLE: ${data.postTitle} "
                    body_tv.text = "BODY: ${data.postBody}"
                }
            }

            override fun onFailure(call: Call<List<DataModel>>, t: Throwable) {

                progressDialog.dismiss()
                Log.e("Failure","Error is ${t.localizedMessage}")
                showToast("Some Error Occurred while fetching data")
            }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
        })

    }
}