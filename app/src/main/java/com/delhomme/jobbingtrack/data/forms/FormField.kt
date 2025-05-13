package com.delhomme.jobbingtrack.data.forms

enum class FieldType {
    TEXT, MULTILINE_TEXT, DATE, TIME, DROPDOWN, NUMBER, EMAIL, PHONE, CHECKBOX, BOOLEAN,
    SUGGESTION_TEXT, SELECTION
}

data class FormField(
    val name: String,
    val label: String,
    val type: FieldType,
    val isRequired: Boolean = false,
    val options: List<String>? = null,
    val initialValue: String? = null,
    val readOnly: Boolean = false,
    val onNewOptionAdded: ((String) -> Unit)? = null,
    val onOptionSelected: ((String) -> Unit)? = null,
    val onOptionRemoved: ((String) -> Unit)? = null,
    val onOptionRenamed: ((old: String, new: String) -> Unit)? = null
)
