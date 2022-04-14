package com.example.oblig3_henrik.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Query("select * from databaseuser")
    fun getUsers(): LiveData<List<DatabaseUser>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<DatabaseUser>)
}

@Database(entities = [DatabaseUser::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {
    abstract val UserDao: UserDao
}

private lateinit var INSTANCE: UsersDatabase

fun getDatabase(context: Context): UsersDatabase {
    synchronized(UsersDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                UsersDatabase::class.java,
                "users"
            ).build()
        }
    }
    return INSTANCE
}