package com.example.eventcalendar.domain.validation

import com.example.eventcalendar.domain.core.MyValidation
import com.example.eventcalendar.domain.core.ValidationResult
import javax.inject.Inject

class ValidateEventTitle @Inject constructor() : MyValidation<String>() {

    override fun execute(param: String): ValidationResult {
        if (param.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please add title!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }


}