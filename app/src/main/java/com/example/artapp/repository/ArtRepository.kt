// com/example/artapp/repository/ArtRepository.kt

package com.example.artapp.repository

import com.example.artapp.data.entity.ArtObject
import com.example.artapp.data.entity.Department
import com.example.artapp.data.entity.DepartmentObjects
import com.example.artapp.data.entity.AllDepartments
import com.example.artapp.data.entity.SearchResponse
import com.example.artapp.data.local.ArtDao
import com.example.artapp.data.server.ArtApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArtRepository(
    private val apiService: ArtApiService,
    private val artDao: ArtDao
) {

    suspend fun getArtObjectById(objectId: Int): ArtObject? = withContext(Dispatchers.IO) {
        // Пробуємо отримати дані з локальної бази
        val cachedObject = artDao.getArtObjectById(objectId)
        if (cachedObject != null) {
            return@withContext cachedObject
        }

        // Якщо немає в кеші, отримуємо з API
        val response = apiService.getArtObjectById(objectId)
        return@withContext if (response.isSuccessful) {
            response.body()?.also { artDao.insertArtObject(it) } // Кешування даних
        } else {
            null
        }
    }

    suspend fun getObjectsForDepartment(departmentId: Int): DepartmentObjects? = withContext(Dispatchers.IO) {
        val cachedObjects = artDao.getObjectsForDepartment(departmentId)
        if (cachedObjects != null) {
            return@withContext cachedObjects
        }

        val response = apiService.getObjectsForDepartment(departmentId)
        return@withContext if (response.isSuccessful) {
            response.body()?.also { artDao.insertDepartmentObjects(departmentId, it) }
        } else {
            null
        }
    }

    suspend fun searchArtObjects(query: String): SearchResponse? = withContext(Dispatchers.IO) {
        val response = apiService.searchArtObjects(query)
        return@withContext if (response.isSuccessful) response.body() else null
    }

    suspend fun getAllCategories(): List<Department> = withContext(Dispatchers.IO) {
        val cachedCategories = artDao.getAllDepartments()
        if (cachedCategories.isNotEmpty()) {
            return@withContext cachedCategories
        }

        val response = apiService.getAllCategories()
        if (response.isSuccessful) {
            response.body()?.departments?.also { artDao.insertDepartments(it) } ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun insertArtObject(artObject: ArtObject): Long {
        return artDao.insertArtObject(artObject)
    }

    suspend fun insertArtObjects(artObjects: List<ArtObject>): List<Long> {
        return artDao.insertArtObjects(artObjects)
    }

    suspend fun getAllArtObjects(): List<ArtObject> {
        return artDao.getAllArtObjects()
    }

    suspend fun deleteAllArtObjects() {
        artDao.deleteAllArtObjects()
    }
}
