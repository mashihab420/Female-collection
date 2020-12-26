package com.codian.femalecollection.UI.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.codian.femalecollection.UI.Model.ModelCartRoom;


@Database(entities = ModelCartRoom.class,version = 3,exportSchema = false)
public abstract class MyRoomDataBase extends RoomDatabase {

    private static MyRoomDataBase roomDataBase = null;

    public abstract RoomDao roomDao();

    public static synchronized MyRoomDataBase getInstance(Context context)
    {
        if (roomDataBase==null)
        {
            roomDataBase = Room.databaseBuilder(context,MyRoomDataBase.class,"cartdb")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return roomDataBase;
    }

}
