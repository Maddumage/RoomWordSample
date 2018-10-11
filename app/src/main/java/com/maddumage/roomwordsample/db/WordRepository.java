package com.maddumage.roomwordsample.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.maddumage.roomwordsample.db.dao.WordDao;
import com.maddumage.roomwordsample.db.model.Word;

import java.util.List;

public class WordRepository {

    private WordDao wordDao;
    private LiveData<List<Word>> words;

    public WordRepository(Application application) {
        WordRoomDatabase database = WordRoomDatabase.getInstance(application);
        wordDao = database.wordDao();
        words = wordDao.getAllWords();
    }

    LiveData<List<Word>> getAllWords() {
        return words;
    }

    public void insert(Word word) {
        new insertAsyncTask(wordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
