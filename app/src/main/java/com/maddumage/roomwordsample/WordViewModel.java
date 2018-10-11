package com.maddumage.roomwordsample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.maddumage.roomwordsample.db.WordRepository;
import com.maddumage.roomwordsample.db.model.Word;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepository mRepository;

    private LiveData<List<Word>> mAllWords;

    public WordViewModel(Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        mRepository.insert(word);
    }
}