package com.example.artapp.data.local

import com.example.artapp.data.entity.DepartmentObjects
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.artapp.data.entity.ArtObject
import com.example.artapp.data.entity.Department

@Dao
interface ArtDao {

    @Query("SELECT * FROM art_objects WHERE objectID = :objectId")
    suspend fun getArtObjectById(objectId: Int): ArtObject?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtObject(artObject: ArtObject): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtObjects(artObjects: List<ArtObject>): List<Long>

    @Query("SELECT * FROM art_objects")
    suspend fun getAllArtObjects(): List<ArtObject>

    @Query("DELETE FROM art_objects")
    suspend fun deleteAllArtObjects(): Int

    @Query("SELECT * FROM department_objects WHERE departmentId = :departmentId")
    suspend fun getObjectsForDepartment(departmentId: Int): List<DepartmentObjects>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDepartmentObjects(departmentObjects: DepartmentObjects)

    @Query("SELECT * FROM department")
    suspend fun getAllDepartments(): List<Department>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDepartments(departments: List<Department>)
}
