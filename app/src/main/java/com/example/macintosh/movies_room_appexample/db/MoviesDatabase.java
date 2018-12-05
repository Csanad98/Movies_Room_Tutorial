package com.example.macintosh.movies_room_appexample.db;



/*
Now, that we have entity and dao classes created, lets create a database class itself.
It should be abstract and extend RoomDatabase. With @Database annotation we provide the
array of entities and a version. Usually, there’s no need for more than one instance of
a DB, thus make it a singleton.


For db initialization, use Room.databaseBuilder(). Just for simplifying the code, I’ve added allowMainThreadQueries().
If not provided, each DB transaction will throw:

"java.lang.IllegalStateException: Cannot access database on the main thread since it may potentially
lock the UI for a long period of time"

in case its run on the UI thread.
I’m also adding a callback hooked to db’s creation (there’s also another one for
opening — onOpen()), so I can pre-populate the DB with some data.


What you definitely need to do in RoomDatabase class, is to provide abstract
methods for getting DAOs. In our case it’s movieDao() and directorDao().


 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Movie.class, Director.class}, version = 1)
public abstract class MoviesDatabase extends RoomDatabase {
    private static MoviesDatabase INSTANCE;
    private static final String DB_NAME = "movies.db";


    public static MoviesDatabase getDatabase(final Context context) {

        if(INSTANCE == null) {
            
        }


        return INSTANCE;
    }
}
