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

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList

/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all words.
 */

class WordViewModel(application: Application) : AndroidViewModel(application) {

  private val mRepository: WordRepository = WordRepository(application)

  val messages: LiveData<PagedList<Word>>
    get() = LivePagedListBuilder(mRepository.data,
        PagedList.Config.Builder().setPageSize(20).setEnablePlaceholders(true).build()).build()

  fun insert(str: String) {
    mRepository.insert(str)
  }

  fun insert() {
    mRepository.batchInsert()
  }

  fun batchInsert(count: Int) {
    mRepository.batchInsert(count)
  }

  fun clear() {
    mRepository.clear()
  }

  fun update() {
    //mRepository.update();
  }
}