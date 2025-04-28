package com.delhomme.jobbingtrack.data.forms

data class FormField(
    val name: String,
    val label: String,
    val type: FieldType,
    val isRequired: Boolean = false,
    val options: List<String>? = null,
    val initialValue: String? = null,
    val readOnly: Boolean = false
)

enum class FieldType {
    TEXT, MULTILINE_TEXT, DATE, TIME, DROPDOWN, NUMBER, EMAIL, PHONE, CHECKBOX, BOOLEAN
}
