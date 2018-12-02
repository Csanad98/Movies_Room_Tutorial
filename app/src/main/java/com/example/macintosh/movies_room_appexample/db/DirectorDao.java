package com.example.macintosh.movies_room_appexample.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


//define methods that we’re gonna use to manipulate our data.

/*
Room provides four different annotations: @Insert, @Update, @Delete and @Query.
For insert and update you can provide OnConflictStrategy value, customizing the behavior
in case of arisen conflict. By default the transaction is aborted, but in our case,
we ignore it. As a parameter of insert(), update() and delete() you can provide a single
object, a few of them or a list. In case you need an id of a newly inserted row(s), just
return long/long[]. For update() you can return int, getting an info of how many rows were affected.
*/
@Dao
public interface DirectorDao {

    //There are a few queries that we need: findDirectorById(), findDirectorByName() and getAllDirectors().
    //full_name column is used for searching and ordering. It is a good candidate for an index.

    @Query("SELECT * FROM director WHERE did = :id LIMIT 1")
        Director findDirectorById(int id);

    @Query("SELECT * FROM director WHERE full_name = :fullName LIMIT 1")
        Director findDirectorByName(String fullName);

    //I return long for insert(Director director),
    //as I will need an id of a newly created row for a movie object.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
        long insert(Director director);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
        void insert(Director... directors);

    @Update(onConflict = OnConflictStrategy.IGNORE)
        void update(Director director);

    @Query("DELETE FROM director")
        void deleteAll();

    //Notice the return type of getAllDirectors(). I wrap the
    //list of results in LiveData, as I would like the list to
    //update automatically when the underlined data changes.
    @Query("SELECT * FROM director ORDER BY full_name ASC")
        LiveData<List<Director>> getAllDirectors();
}
