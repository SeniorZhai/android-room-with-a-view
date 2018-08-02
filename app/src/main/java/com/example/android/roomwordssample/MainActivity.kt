package com.example.android.roomwordssample

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.fab1
import kotlinx.android.synthetic.main.content_main.recyclerview


class MainActivity : AppCompatActivity() {

  private var mWordViewModel: WordViewModel? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val toolbar = findViewById<Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)

    val adapter = WordListAdapter()
    recyclerview.adapter = adapter
    recyclerview.layoutManager = LinearLayoutManager(this)

    mWordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
    mWordViewModel!!.messages.observe(this, Observer { words ->
      adapter.submitList(words)
      recyclerview.postDelayed({
        (recyclerview.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(0, 0)
      }, 30)
    })


    fab1.setOnClickListener {
      Thread {
        mWordViewModel!!.insert(System.currentTimeMillis().toString())
      }.start()
    }
    th.start()
  }


  val th by lazy {
    Thread {
      mWordViewModel!!.clear()
      mWordViewModel!!.insert()
    }
  }

  companion object {
    val NEW_WORD_ACTIVITY_REQUEST_CODE = 1
  }
}
