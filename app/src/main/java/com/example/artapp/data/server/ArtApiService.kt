package com.example.artapp.data.server

import com.example.artapp.data.entity.DepartmentObjects
import com.example.artapp.data.entity.ArtObject
import com.example.artapp.data.entity.AllDepartments
import com.example.artapp.data.entity.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtApiService {

    @GET("public/collection/v1/objects/[objectID]")
    suspend fun getArtObjectById(
        @Path("objectId") objectId: Int
    ): Response<ArtObject>

    // Отримати ArtObjects за департаментом
    @GET("public/collection/v1/objects")
    suspend fun getObjectsForDepartment(
        @Query("departmentIds") departmentId: Int
    ): Response<DepartmentObjects>

    @GET("/public/collection/v1/search")
    suspend fun searchArtObjects(
        @Query("q") query: String
    ): Response<SearchResponse>

    @GET("public/collection/v1/departments")
    suspend fun getAllCategories(): Response<AllDepartments>
}