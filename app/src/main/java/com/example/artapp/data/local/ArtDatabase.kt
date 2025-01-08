package com.example.artapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.artapp.data.entity.AllDepartments
import com.example.artapp.data.entity.ArtObject
import com.example.artapp.data.entity.Department
import com.example.artapp.data.entity.DepartmentObjects

@Database(
    entities = [ArtObject::class, DepartmentObjects::class, Department::class, AllDepartments::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ArtDatabase : RoomDatabase() {
    abstract fun artDao(): ArtDao
}
