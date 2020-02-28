package com.example.kotlinrxjavaretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinrxjavaretrofit.Adapter.PostAdapter
import com.example.kotlinrxjavaretrofit.Model.Post
import com.example.kotlinrxjavaretrofit.Retrofit.IMyAPI
import com.example.kotlinrxjavaretrofit.Retrofit.RetrofitClient
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.disposables.ArrayCompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.create

class MainActivity : AppCompatActivity() {

    internal lateinit var jsonAPI: IMyAPI
    internal var compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init API
        val retrofit = RetrofitClient.instance
        jsonAPI = retrofit.create(IMyAPI::class.java)

        //view
        recycler_posts.setHasFixedSize(true)
        recycler_posts.layoutManager = LinearLayoutManager(this)
        fetchData()
    }

    private fun fetchData() {
        compositeDisposable.add(
            jsonAPI.posts
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ posts ->
                    displayData(posts)
                }, { e ->
                    Toast.makeText(this,""+e.message,Toast.LENGTH_SHORT).show()
                })
        )
    }

    private fun displayData(posts: List<Post>?) {
        val adapter = PostAdapter(this, posts!!)
        recycler_posts.adapter = adapter
    }
}
