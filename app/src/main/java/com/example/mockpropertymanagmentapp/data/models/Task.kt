package com.example.mockpropertymanagmentapp.data.models

data class Task(
    var priority: String? = null,
    var summary: String? = null,
    var dueDate: String? = null,
    var estimatedCost: String? = null,
    var actualCost: String? = null,
    var status: String? = null
) {
    companion object {
        const val COLLECTION_NAME = "tasks"
    }
}