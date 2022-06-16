package com.codingurkan.example.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.codingurkan.example.adapter.TodoAdapter
import com.codingurkan.example.databinding.ActivityMainBinding
import com.codingurkan.example.model.Todo
import com.codingurkan.example.util.showMessage


class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null
    private var viewModel : MainActivityViewModel? = null
    private var adapter : TodoAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initViewModel()
        initObservers()
        viewModel?.downloadData()
    }
    private fun initBinding(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }
    private fun initViewModel(){
        viewModel = ViewModelProvider(this@MainActivity)[MainActivityViewModel::class.java]
    }
    private fun initObservers(){
        viewModel?.todoList?.observe(this) { _data ->
            initAdapter(_data)
        }
    }
    private fun initAdapter(data: ArrayList<Todo>) {
        adapter = TodoAdapter(data,object : TodoAdapter.ItemClickListener{
            override fun onClick(data: Todo) {
                navigateToOtherActivity(data)
            }

        })
        binding?.rvTodos?.adapter = adapter
    }
    private fun navigateToOtherActivity(data : Todo){
        val intent = Intent(this@MainActivity,DetailsActivity::class.java)
        intent.putExtra("data",data)
        startActivity(intent)
    }





}