package com.example.proj1.kotlin

import android.content.Context

class HealthConditionRepository(context: Context) {
    private val dbHelper = DiseaseDatabase(context)

    fun getAllHealthConditions(): List<HealthCondition> {
        val healthConditions = mutableListOf<HealthCondition>()
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("select * from health_conditions", null)

        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndexOrThrow("id"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val filterValue =
                cursor.getInt(cursor.getColumnIndexOrThrow("filterValue"))

            println("Add")
            healthConditions.add(
                HealthCondition(id, name, filterValue)
            )
        }
        cursor.close()
        db.close()

        return healthConditions
    }
}
