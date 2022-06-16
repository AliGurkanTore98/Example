package com.codingurkan.example.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codingurkan.example.databinding.ItemTodoBinding
import com.codingurkan.example.model.Todo

class TodoAdapter(private var todos : ArrayList<Todo>,
                  private val itemClickListener : ItemClickListener ) : RecyclerView.Adapter<TodoAdapter.TodoVH>(){

    inner class TodoVH(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoVH {
        val view = ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TodoVH(view)
    }
    override fun onBindViewHolder(holder: TodoVH, position: Int) {
        with(holder.binding){

            cbDone.isChecked = todos[position].completed!!
            tvTitle.text = todos[position].title

            tvTitle.setOnClickListener {
                itemClickListener.onClick(todos[position])

            }
        }
    }
    override fun getItemCount(): Int {
       return todos.size
    }
    interface ItemClickListener{
        fun onClick(data: Todo)
    }
}