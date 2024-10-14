package zawwin.naung.eventcalendar.domain.core

abstract class MyUseCase<I, O> {

    suspend fun execute(input: I): MyResult<O> {
        return try {
            provide(input)
        } catch (e: Throwable) {
            handleError(e)
        }
    }

    abstract suspend fun provide(input: I): MyResult<O>

    protected open suspend fun handleError(e: Throwable): MyResult<O> {
        return MyResult.Error(e)
    }
}