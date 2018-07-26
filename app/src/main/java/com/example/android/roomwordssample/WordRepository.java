package com.example.android.roomwordssample;

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

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;
import java.util.List;

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */

public class WordRepository {

  private WordDao mWordDao;
  private LiveData<List<Word>> mAllWords;
  private static int count = 20000;

  // Note that in order to unit test the WordRepository, you have to remove the Application
  // dependency. This adds complexity and much more code, and this sample is not about testing.
  // See the BasicSample in the android-architecture-components repository at
  // https://github.com/googlesamples
  WordRepository(Application application) {
    WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
    mWordDao = db.wordDao();
    mAllWords = db.wordDao().getAlphabetizedWords();
  }

  // Room executes all queries on a separate thread.
  // Observed LiveData will notify the observer when the data has changed.
  LiveData<List<Word>> getAllWords() {
    return mAllWords;
  }

  // You must call this on a non-UI thread or your app will crash.
  // Like this, Room ensures that you're not doing any long running operations on the main
  // thread, blocking the UI.
  public void insert() {
    new Thread(new Runnable() {
      @Override public void run() {
        Log.d("Main", "insert begin" + System.currentTimeMillis());
        for (int i = 0; i < count; i++) {
          mWordDao.insert(new Word("" + i));
        }
        Log.d("Main", "insert end" + System.currentTimeMillis());
      }
    }).start();
  }

  public void update() {
    new updateAsyncTask(mWordDao).execute();
  }

  public void clear() {
    new deleteAsyncTask(mWordDao).execute();
  }

  private static class deleteAsyncTask extends android.os.AsyncTask<Word, Void, Void> {

    private WordDao mAsyncTaskDao;

    deleteAsyncTask(WordDao dao) {
      mAsyncTaskDao = dao;
    }

    @Override protected Void doInBackground(final Word... params) {
      mAsyncTaskDao.deleteAll();
      return null;
    }
  }

  private static class updateAsyncTask extends AsyncTask<Word, Void, Void> {

    private WordDao mAsyncTaskDao;

    updateAsyncTask(WordDao dao) {
      mAsyncTaskDao = dao;
    }

    @Override protected Void doInBackground(final Word... params) {
      Log.d("Main", "update begin" + System.currentTimeMillis());
      for (int i = 0; i < count; i++) {
        mAsyncTaskDao.update(i, i + "s");
      }
      Log.d("Main", "update end" + System.currentTimeMillis());
      return null;
    }
  }
}
