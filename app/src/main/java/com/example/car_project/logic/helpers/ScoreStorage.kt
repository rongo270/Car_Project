package com.example.car_project.logic.helpers

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ScoreStorage {
    private const val PREF_KEY = "scores_key"

    fun addScore(context: Context, newEntry: ScoreEntry) {
        val currentScores = loadScores(context).toMutableList()
        currentScores.add(newEntry)
        val top10 = currentScores.sortedByDescending { it.score }.take(10)
        saveScores(context, top10)
    }

    fun saveScores(context: Context, scores: List<ScoreEntry>) {
        val prefs = context.getSharedPreferences("scores_pref", Context.MODE_PRIVATE)
        val json = Gson().toJson(scores)
        prefs.edit().putString(PREF_KEY, json).apply()
    }

    fun loadScores(context: Context): List<ScoreEntry> {
        val prefs = context.getSharedPreferences("scores_pref", Context.MODE_PRIVATE)
        val json = prefs.getString(PREF_KEY, null) ?: return emptyList()
        val type = object : TypeToken<List<ScoreEntry>>() {}.type
        return Gson().fromJson(json, type)
    }

    fun clearScores(context: Context) {
        val prefs = context.getSharedPreferences("scores_pref", Context.MODE_PRIVATE)
        prefs.edit().remove(PREF_KEY).apply()
    }
}
