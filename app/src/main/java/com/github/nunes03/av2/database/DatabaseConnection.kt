package com.github.nunes03.av2.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.github.nunes03.av2.database.entities.AbstractEntity
import com.github.nunes03.av2.mappers.interfaces.MapperInterface

class DatabaseConnection(private val context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME: String = "pet_shop";

        const val DATABASE_VERSION: Int = 1;

        const val CREATE_TABLE_USER: String = "create table `user` (" +
                "id integer primary key autoincrement," +
                "name text not null," +
                "email text not null" +
                ");"

        const val CREATE_TABLE_KIND: String = "create table kind (" +
                "id integer primary key autoincrement," +
                "name text not null" +
                ");"

        const val CREATE_TABLE_ANIMAL: String = "create table animal (" +
                "id integer primary key autoincrement," +
                "name text not null," +
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
                "description text not null," +
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

    fun insert(entity: AbstractEntity, contentValues: ContentValues): Long {
        val databaseConnection = DatabaseConnection(context)
        val sqLiteDatabase = databaseConnection.writableDatabase

        try {
            return sqLiteDatabase.insert(
                entity.getTableName(),
                null,
                contentValues
            )
        } catch (exception: Exception) {
            throw RuntimeException("Error when executing insert in the base:", exception)
        } finally {
            databaseConnection.close()
            sqLiteDatabase.close()
        }
    }

    fun update(entity: AbstractEntity, contentValues: ContentValues, whereCondition: String) {
        val databaseConnection = DatabaseConnection(context)
        val sqLiteDatabase = databaseConnection.writableDatabase

        try {
            sqLiteDatabase.update(
                entity.getTableName(),
                contentValues,
                whereCondition,
                null
            )
        } catch (exception: Exception) {
            throw RuntimeException("Error when executing update on the base:", exception)
        } finally {
            databaseConnection.close()
            sqLiteDatabase.close()
        }
    }

    fun delete(entity: AbstractEntity, whereCondition: String) {
        val databaseConnection = DatabaseConnection(context)
        val sqLiteDatabase = databaseConnection.writableDatabase

        try {
            sqLiteDatabase.delete(
                entity.getTableName(),
                whereCondition,
                null
            )
        } catch (exception: Exception) {
            throw RuntimeException("Error when executing delete on the base:", exception)
        } finally {
            databaseConnection.close()
            sqLiteDatabase.close()
        }
    }

    fun <T : AbstractEntity> queryOne(
        entity: AbstractEntity,
        mapperInterface: MapperInterface<T>,
        whereCondition: String?,
        groupBy: String?,
        orderBy: String?
    ): T? {
        val response = query(
            entity,
            mapperInterface,
            whereCondition,
            groupBy,
            orderBy
        )

        if (response.isEmpty()) {
            return null
        }

        return response[0];
    }

    fun <T : AbstractEntity> query(
        entity: AbstractEntity,
        mapperInterface: MapperInterface<T>,
        whereCondition: String?,
        groupBy: String?,
        orderBy: String?
    ): List<T> {
        val databaseConnection = DatabaseConnection(context)
        val sqLiteDatabase = databaseConnection.readableDatabase

        try {
            val cursor = sqLiteDatabase.query(
                entity.getTableName(),
                arrayOf("*"),
                whereCondition,
                null,
                groupBy,
                null,
                orderBy,
            )

            val response: ArrayList<T> = ArrayList()

            while (cursor.moveToNext()) {
                response.add(mapperInterface.convert(cursor))
            }

            cursor.close()
            return response;
        } catch (exception: Exception) {
            throw RuntimeException("Error when executing database query:", exception);
        } finally {
            databaseConnection.close()
            sqLiteDatabase.close()
        }
    }
}