package com.example.macintosh.movies_room_appexample.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

//annotated with @Entity to become a table in database.
//can provide optional parameters such as tableName and indices. Adding an index on a column which
// will be used in search queries, speeds the search. Read more on this at:
// https://medium.com/@JasonWyatt/squeezing-performance-from-sqlite-indexes-indexes-c4e175f3c346

//the unique = true for full_name index: Room does not have @Unique annotation, but you can
// enforce uniqueness on a index, so it’s one way to do it.
@Entity(tableName = "director", indices = {@Index(value = "full_name", unique = true)})

public class Director {

    //Each table needs to have at least one primary key. In our case it’s the id. @ColumnInfo(name = “did”)
    //renames id to did for a column’s name. I don’t want to bother with providing the ids myself, hence
    //autoGenerate = true for Room to do that.
    @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "did") //director id
        public int id;

    @ColumnInfo(name = "full_name")
        @NonNull
        public String fullName;

    //also have an ignored field age with @Ignore annotation. This will exclude it
    //from being added to database’s schema.
    @Ignore
        public int age;

    //constructor of the class
    public Director(@NonNull String fullName) {
        this.fullName = fullName;
    }


}
