package com.example.palembangfood

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar

class FoodDetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var tvDetailName : TextView
    private lateinit var tvDetailDescription : TextView
    private lateinit var tvRecipe : TextView
    private lateinit var ivDetailPhoto : ImageView
    private lateinit var btnShare : Button

    private lateinit var toolbarTitle : TextView
    private lateinit var aboutPage : ImageView
    private lateinit var toolbarBack : androidx.appcompat.widget.Toolbar


    var messageTitle = ""
    var message = ""
    val creditMessage = "\n\nUntuk informasi lebih lanjut silahkan buka aplikasi Palembang Iconic Food!"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_detail)

        val dataFood = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("key_food", ListFoodAdapter::class.java) as Food
        } else {
            intent.getParcelableExtra<Food>("key_food") as Food
        }

        tvDetailName = findViewById(R.id.tv_detail_name)
        tvDetailDescription = findViewById(R.id.tv_description_detail)
        tvRecipe = findViewById(R.id.tv_recipe_detail)
        ivDetailPhoto = findViewById(R.id.img_detail_photo)

        tvDetailName.text = dataFood.name
        tvDetailDescription.text = dataFood.descriptionDetail
        tvRecipe.text = dataFood.recipe
        ivDetailPhoto.setImageResource(dataFood.photo)

        btnShare = findViewById(R.id.action_share)
        btnShare.setOnClickListener(this)

        messageTitle = dataFood.name
        message = dataFood.description

        toolbarTitle = findViewById(R.id.toolbar_back_title)
        aboutPage = findViewById(R.id.toolbar_about_page)

        toolbarTitle.text = messageTitle
        aboutPage.setOnClickListener(this)

        toolbarBack = findViewById(R.id.toolbar_back)
        setSupportActionBar(toolbarBack)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(p0: View?) {
        val message = messageTitle.plus("\n\n").plus(message).plus(creditMessage)

        when(p0?.id) {
            R.id.action_share -> {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.putExtra(Intent.EXTRA_TEXT, message)
                shareIntent.type = "text/plain"

                startActivity(Intent.createChooser(shareIntent, "Share to : "))
            }

            R.id.toolbar_about_page -> {
                val moveIntent = Intent(this@FoodDetailActivity, AboutPageActivity::class.java)
                startActivity(moveIntent)
            }

        }
    }
}