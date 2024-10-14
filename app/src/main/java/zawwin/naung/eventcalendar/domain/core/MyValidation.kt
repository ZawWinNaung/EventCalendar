package zawwin.naung.eventcalendar.domain.core

abstract class MyValidation<P> {
    abstract fun execute(param: P): ValidationResult
}