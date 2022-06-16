package com.codingurkan.example.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.codingurkan.example.databinding.ActivityDetailsBinding
import com.codingurkan.example.model.Todo

class DetailsActivity : AppCompatActivity() {

    private var binding : ActivityDetailsBinding? = null
    private var viewModel : DetailViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initViewModel()
        initObserver()
        getData()
    }

    private fun initBinding(){
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }
    private fun initViewModel(){
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
    }

    private fun getData(){
        intent?.getSerializableExtra("data")?.let {
            viewModel?.currentData?.value = it as Todo
        }
    }

    private fun initObserver(){
        viewModel?.currentData?.observe(this@DetailsActivity){
            binding?.detailTV?.text = it.title
            binding?.checkTV?.text = it.completed.toString()
        }
    }
}