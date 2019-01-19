package com.dabekka.btuchat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_gallery.*

class galleryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        init()
    }

    fun init() {
        Picasso.get().load("http://i.imgur.com/DvpvklR.png").placeholder(R.mipmap.placeholder).into(ImageView1)
        Picasso.get().load("http://www.artandantiquesmag.com/wp-content/uploads/2016/10/201611_picasso_02.jpg")
            .placeholder(R.mipmap.placeholder).into(ImageView2)
        Picasso.get().load("https://arthive.com/res/media/img/orig/work/261/437372.jpg")
            .placeholder(R.mipmap.placeholder).into(ImageView3)
        Picasso.get().load("https://media.timeout.com/images/101759135/630/472/image.jpg")
            .placeholder(R.mipmap.placeholder).into(ImageView4)
    }
}
