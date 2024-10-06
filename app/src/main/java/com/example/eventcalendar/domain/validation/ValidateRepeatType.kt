package com.example.eventcalendar.domain.validation

import com.example.eventcalendar.domain.core.MyValidation
import com.example.eventcalendar.domain.core.ValidationResult
import javax.inject.Inject

class ValidateRepeatType @Inject constructor() : MyValidation<String?>() {
    override fun execute(param: String?): ValidationResult {
        if (param.isNullOrBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please select repeat mode!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}