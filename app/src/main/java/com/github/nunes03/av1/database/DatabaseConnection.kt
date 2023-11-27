package com.github.nunes03.av1.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseConnection(context: Context?) : SQLiteOpenHelper(context, "pet_shop", null, 2) {

    private val className: String? = DatabaseConnection::class.simpleName

    private val createTableUser: String = "create table user (" +
            "id integer primary key autoincrement," +
            "name text not null," +
            "email text not null," +
            "password text not null" +
            ");"

    private val createTableKind: String = "create table kind (" +
            "id integer primary key autoincrement," +
            "name text not null" +
            ");"

    private val createTableAnimal: String = "create table user (" +
            "id integer primary key autoincrement," +
            "name text not null," +
            "description text," +
            "kind_id integer not null," +
            "user_id integer not null, " +
            "constraint fk_kind_animal" +
            "    foreign key (kind_id)" +
            "    references kind (id), " +
            "constraint fk_user_animal" +
            "    foreign key (user_id)" +
            "    references user (id) " +
            ");"

    init {
        log("Iniciando banco de dados")
    }

    override fun onCreate(db: SQLiteDatabase) {
        log("Criando tabelas")
        db.execSQL(createTableUser)
        db.execSQL(createTableKind)
        db.execSQL(createTableAnimal)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d(
            "Fey",
            "DB->onUpgrade() oldVersion:" + oldVersion.toString() + " newVersion:" + newVersion.toString()
        )
        //db.execSQL("DROP TABLE tb_usuario ") //

        try {
            val content = ContentValues()
            content.put("us_nomusu", "Usuário v:" + newVersion.toString())
            var qtd = db.update("tb_usmuario", content, null, null)
            Log.d("Fey", "DB->onUpgrade() alterados = $qtd")
        } catch (e: RuntimeException) {
            Log.d("Fey", "DB->onUpgrade() erro = " + e.message)
        }
    }

    private fun log(message: String) {
        Log.d(className, message)
    }
}