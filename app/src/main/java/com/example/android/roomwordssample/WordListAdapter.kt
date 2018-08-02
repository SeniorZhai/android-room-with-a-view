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

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.android.roomwordssample.WordListAdapter.WordViewHolder


class WordListAdapter : PagedListAdapter<Word, WordViewHolder>(diffCallback) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
    Log.d("---","onCreateViewHolder")
    val itemView = View.inflate(parent.context, R.layout.recyclerview_item, null)
    return WordViewHolder(itemView)
  }

  override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
    Log.d("---","onBindViewHolder")
    getItem(position)?.let {
      holder.wordItemView.text = it.word
    }
  }

  companion object {
    private val diffCallback = object : DiffUtil.ItemCallback<Word>() {
      override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem.word == newItem.word
      }

      override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem.word == newItem.word
      }
    }
  }

  class WordViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(
      itemView) {
    val wordItemView: TextView = itemView.findViewById(R.id.textView)
  }

  override fun getItemViewType(position: Int): Int {
    return super.getItemViewType(position)
  }
}

