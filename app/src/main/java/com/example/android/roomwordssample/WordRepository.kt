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
import android.arch.paging.DataSource
import android.util.Log

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */

class WordRepository constructor(application: Application) {

  private val mWordDao: WordDao

  val data: DataSource.Factory<Int, Word>
    get() = mWordDao.getData()

  init {
    val db = WordRoomDatabase.getDatabase(application)
    mWordDao = db.wordDao()
  }

  // You must call this on a non-UI thread or your app will crash.
  // Like this, Room ensures that you're not doing any long running operations on the main
  // thread, blocking the UI.
  fun insert() {
    Thread(Runnable {
      Log.d("Main", "insert begin" + System.currentTimeMillis())
      for (i in 0 until count) {
        mWordDao.insert(Word(System.currentTimeMillis().toString() + ""))
      }
      Log.d("Main", "insert end" + System.currentTimeMillis())
    }).start()
  }

  fun insert(int: Int) {
    Thread(Runnable {
      Log.d("Main", "insert begin" + System.currentTimeMillis())
      try {
        for (i in 0 until int) {
          mWordDao.insert(Word(System.currentTimeMillis().toString() + ""))
          Log.d("Main", "insert $i")
        }
      } catch (e: Exception) {
        insert(int)
        Log.e("---", e.message)
      }
      Log.d("Main", "insert end" + System.currentTimeMillis())
    }).start()
  }

  fun insert(str: String) {
    mWordDao.insert(Word(str))
  }

  fun update(srouce: String, update: String) {
    mWordDao.update(srouce, update)
  }

  fun clear() {
    deleteAsyncTask(mWordDao).execute()
  }

  private class deleteAsyncTask internal constructor(
      private val mAsyncTaskDao: WordDao) : android.os.AsyncTask<Word, Void, Void>() {

    override fun doInBackground(vararg params: Word): Void? {
      mAsyncTaskDao.deleteAll()
      return null
    }
  }

  companion object {
    private val count = 200
  }
}
