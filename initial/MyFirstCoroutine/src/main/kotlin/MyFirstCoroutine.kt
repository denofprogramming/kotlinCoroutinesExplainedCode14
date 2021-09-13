import kotlinx.coroutines.*
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext


fun main() = runBlocking {


    val scope = CoroutineScope(Job())

    //Child 1
    scope.launch() {

        delay(10)
        logMessage("Hello")
    }

    //Child 2
    scope.launch() {

        delay(10)
        logMessage("World")
    }


    delay(100)
    logMessage("The End.")

}




fun logMessage(msg: String) {
    println("Running on: [${Thread.currentThread().name}] | $msg")
}


fun CoroutineScope.logContext(id: String) {
    coroutineContext.logDetails(id)
}


fun CoroutineContext.logDetails(id: String) {
    sequenceOf(
        Job,
        ContinuationInterceptor,
        CoroutineExceptionHandler,
        CoroutineName
    )
        .mapNotNull { key -> this[key] }
        .forEach { logMessage("id: $id ${it.key} = $it") }
}