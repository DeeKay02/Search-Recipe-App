package com.learn.search.domain.repository

import com.learn.search.domain.model.Recipe
import com.learn.search.domain.model.RecipeDetails

interface SearchRepository {

    suspend fun getRecipes(s: String): Result<List<Recipe>>

    suspend fun getRecipeDetails(id: String): Result<RecipeDetails>
}