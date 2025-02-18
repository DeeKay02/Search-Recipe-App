package com.learn.search.data.repository

import com.learn.search.data.mappers.toDomain
import com.learn.search.data.remote.SearchApiService
import com.learn.search.domain.model.Recipe
import com.learn.search.domain.model.RecipeDetails
import com.learn.search.domain.repository.SearchRepository

class SearchRepoImpl(
    private val searchApiService: SearchApiService
) : SearchRepository {
    override suspend fun getRecipes(s: String): Result<List<Recipe>> {
        val response = searchApiService.getRecipes(s)
        return if (response.isSuccessful) {
            response.body()?.meals?.let {
                Result.success(it.toDomain())
            } ?: run { Result.failure(Exception("Error occurred")) }
        } else {
            Result.failure(Exception("Error in fetching recipes"))
        }
    }

    override suspend fun getRecipeDetails(id: String): Result<RecipeDetails> {
        val response = searchApiService.getRecipeDetails(id)
        return if (response.isSuccessful) {
            response.body()?.meals?.let {
                if (it.isNotEmpty()) {
                    Result.success(it.first().toDomain())
                }
                else {
                    Result.failure(Exception("Error occurred"))
                }
            } ?: run { Result.failure(Exception("Error occurred")) }
        } else {
            Result.failure(Exception("Error in fetching recipe details"))
        }
    }

}