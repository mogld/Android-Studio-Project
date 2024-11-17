package com.example.proj1.kotlin

import android.content.Context

class DiseaseRepository(context: Context) {
    private val dbHelper = DiseaseDatabase(context)


    fun getAllDiseases(): List<Disease> {
        val diseases = mutableListOf<Disease>()
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("select * from diseases", null)

        print(cursor.count)
        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndexOrThrow("id"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val description = cursor.getString(cursor.getColumnIndexOrThrow("description"))
            val iconImage = cursor.getString(cursor.getColumnIndexOrThrow("iconImage"))
            val ageFilter = cursor.getInt(cursor.getColumnIndexOrThrow("ageFilter"))
            val forbiddenHealthConditionFilter =
                cursor.getInt(cursor.getColumnIndexOrThrow("forbiddenHealthConditionFilter"))

            println("Add")
            diseases.add(
                Disease(
                    id,
                    name,
                    description,
                    iconImage,
                    ageFilter,
                    forbiddenHealthConditionFilter
                )
            )
        }
        cursor.close()
        db.close()

        println(diseases.size)
        return diseases
    }
}
