package com.example.palembangfood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var rvFoods: RecyclerView
    private val list = ArrayList<Food>()
    private lateinit var btnAbout: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvFoods = findViewById(R.id.rv_foods)
        rvFoods.setHasFixedSize(true)

        list.addAll(getListFoods())
        showRecyclerList()

        btnAbout = findViewById(R.id.about_page)
        btnAbout.setOnClickListener(this)
    }

    private fun getListFoods(): ArrayList<Food> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataDescriptionDetail = resources.getStringArray(R.array.data_description_detail)
        val dataRecipe = resources.getStringArray(R.array.data_recipe)
        val listFood = ArrayList<Food>()
        for (i in dataName.indices) {
            val food = Food(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataDescriptionDetail[i], dataRecipe[i])
            listFood.add(food)
        }
        return listFood
    }

    private fun showRecyclerList() {
        rvFoods.layoutManager = LinearLayoutManager(this)
        val listFoodAdapter = ListFoodAdapter(list)
        rvFoods.adapter = listFoodAdapter

    }


    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.about_page -> {
                val moveIntent = Intent(this@MainActivity, AboutPageActivity::class.java)
                startActivity(moveIntent)
            }
        }
    }
}