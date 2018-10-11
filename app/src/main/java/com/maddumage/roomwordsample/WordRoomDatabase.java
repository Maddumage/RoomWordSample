package com.maddumage.roomwordsample;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.maddumage.roomwordsample.dao.WordDao;
import com.maddumage.roomwordsample.model.Word;

@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {

    private static WordRoomDatabase INSTANCE;


    public static WordRoomDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    // create database
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordRoomDatabase.class, "word_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract WordDao wordDao();
}
