package zawwin.naung.eventcalendar.domain.validation

import zawwin.naung.eventcalendar.domain.core.MyValidation
import zawwin.naung.eventcalendar.domain.core.ValidationResult
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