package com.github.nunes03.av1.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseConnection(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val context: Context = context

    companion object {
        const val DATABASE_NAME: String = "pet_shop";

        const val DATABASE_VERSION: Int = 1;

        const val CREATE_TABLE_USER: String = "create table `user` (" +
                "id integer primary key autoincrement," +
                "name text not null," +
                "email text not null," +
                "password text not null" +
                ");"

        const val CREATE_TABLE_KIND: String = "create table kind (" +
                "id integer primary key autoincrement," +
                "name text not null" +
                ");"

        const val CREATE_TABLE_ANIMAL: String = "create table animal (" +
                "id integer primary key autoincrement," +
                "name text not null," +
                "description text," +
                "kind_id integer not null," +
                "user_id integer not null, " +
                "constraint fk_kind_animal" +
                "    foreign key (kind_id)" +
                "       references kind (id), " +
                "constraint fk_user_animal" +
                "    foreign key (user_id)" +
                "       references user (id) " +
                ");"

        const val CREATE_TABLE_CONSULTATION: String = "create table consultation (" +
                "id integer primary key autoincrement," +
                "scheduled_time text not null," +
                "animal_id integer not null," +
                "constraint fk_consultation_animal" +
                "    foreign key (animal_id)" +
                "       references animal (id) " +
                ");"
    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        log("Creating tables...")

        sqLiteDatabase.execSQL(CREATE_TABLE_USER)
        sqLiteDatabase.execSQL(CREATE_TABLE_KIND)
        sqLiteDatabase.execSQL(CREATE_TABLE_ANIMAL)
        sqLiteDatabase.execSQL(CREATE_TABLE_CONSULTATION)

        log("Created tables.")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        log("onUpgrade")
    }

    private fun log(message: String) {
        Log.d(DatabaseConnection::class.simpleName, message)
    }

    fun getPathDatabase(): String {
        return context.getDatabasePath(DATABASE_NAME).absolutePath
    }
}