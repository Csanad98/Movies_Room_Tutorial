package com.example.macintosh.movies_room_appexample.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

// contains foreignKeys param. assumption: a Director can have many movies.
//I define this relation with foreignKeys, by providing parentColumns and childColumns values
//(there is an alternative to foreignKeys and it’s a @Relation annotation)

//can provide two more params: onUpdate and onDelete. By default their values are 1 or
//ForeignKey.NO_ACTION. By writing onDelete = ForeignKey.CASCADE I basically tell Room to
//delete a movie if its director gets removed from the database. Other possible values are: RESTRICT,
//SET_NULL or SET_DEFAULT.
@Entity(tableName = "movie", foreignKeys = @ForeignKey(entity = Director.class,
                    parentColumns = "did",
                    childColumns = "directorId",
                    onDelete = ForeignKey.CASCADE),
                indices = {@Index("title"), @Index("directorId")})

public class Movie {

    @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "mid")
        public int id;

    @ColumnInfo(name = "title")
        @NonNull
        public String title;

    @ColumnInfo(name = "directorId")
        public int directorId;


    public Movie(@NonNull String title, int directorId) {
        this.title = title;
        this.directorId = directorId;
    }
}
