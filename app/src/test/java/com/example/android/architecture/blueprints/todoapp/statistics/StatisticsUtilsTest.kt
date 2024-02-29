package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class StatisticsUtilsTest {
    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {
        // Create an active task
        val tasks = listOf(Task("title", "desc", isCompleted = false))
        // Call your function
        val result = getActiveAndCompletedStats(tasks)
        // Check the result
        assertThat(result.completedTasksPercent, equalTo(0f))
        assertThat(result.activeTasksPercent, equalTo(100f))
    }


    @Test
    fun getActiveAndCompletedStats_noActive_returnsZeroHundred() {
        val tasks = listOf(Task("title", "desc", isCompleted = true))
        // When the list of tasks is computed with a completed task
        val result = getActiveAndCompletedStats(tasks)

        // Then the percentages are 0 and 100
        assertThat(result.activeTasksPercent, equalTo(0f))
        assertThat(result.completedTasksPercent, equalTo(100f))
    }

    @Test
    fun getActiveAndCompletedStats_both_returnsFortySixty() {
        // Given 3 completed tasks and 2 active tasks
        val tasks = listOf(
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = false),
        )

        // When the list of tasks is computed
        val result = getActiveAndCompletedStats(tasks)

        // Then the result is 40-60
        assertThat(result.completedTasksPercent, equalTo(40f))
        assertThat(result.activeTasksPercent, equalTo(60f))
    }

    @Test
    fun getActiveAndCompletedStats_empty_returnsZeros() {
        val tasks = listOf<Task>()
        // When there are no tasks
        val result = getActiveAndCompletedStats(tasks)

        // Both active and completed tasks are 0
        assertThat(result.completedTasksPercent, equalTo(0f))
        assertThat(result.activeTasksPercent, equalTo(0f))
    }

    @Test
    fun getActiveAndCompletedStats_error_returnsZeros() {
        val tasks : List<Task>? = null
        // When there's an error loading stats
        val result = getActiveAndCompletedStats(tasks)
        // Both active and completed tasks are 0
        assertThat(result.completedTasksPercent, equalTo(0f))
        assertThat(result.activeTasksPercent, equalTo(0f))
    }

}