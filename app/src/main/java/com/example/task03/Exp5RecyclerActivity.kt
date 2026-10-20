package com.example.task03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class Animal(val name: String, val imgRes: Int)

class AnimalAdapter(
    private val data: MutableList<Animal>,
    private val onItemClick: (Animal) -> Unit
) : RecyclerView.Adapter<AnimalAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPic: ImageView = itemView.findViewById(R.id.iv_pic)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_animal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val animal = data[position]
        holder.tvName.text = animal.name
        holder.ivPic.setImageResource(animal.imgRes)
        holder.itemView.setOnClickListener { onItemClick(animal) }
    }

    override fun getItemCount(): Int = data.size
}

class Exp5RecyclerActivity : AppCompatActivity() {

    private val images = intArrayOf(
        R.drawable.lion, R.drawable.tiger, R.drawable.monkey,
        R.drawable.dog, R.drawable.cat, R.drawable.elephant
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exp5)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val animals = mutableListOf(
            Animal("Lion", R.drawable.lion),
            Animal("Tiger", R.drawable.tiger),
            Animal("Monkey", R.drawable.monkey),
            Animal("Dog", R.drawable.dog),
            Animal("Cat", R.drawable.cat),
            Animal("Elephant", R.drawable.elephant)
        )

        val adapter = AnimalAdapter(animals) { animal ->
            // 单击显示名称
            Toast.makeText(this, animal.name, Toast.LENGTH_SHORT).show()
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // 增加一条记录后列表立即更新
        findViewById<Button>(R.id.btn_add).setOnClickListener {
            val index = animals.size
            val newAnimal = Animal("Animal ${index + 1}", images[index % images.size])
            animals.add(newAnimal)
            adapter.notifyItemInserted(animals.size - 1)
            recyclerView.scrollToPosition(animals.size - 1)
        }
    }
}
