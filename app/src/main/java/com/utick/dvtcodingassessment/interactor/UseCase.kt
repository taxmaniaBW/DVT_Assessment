package com.utick.dvtcodingassessment.interactor



import com.utick.dvtcodingassessment.util.Either
import com.utick.dvtcodingassessment.util.Failure
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent


abstract class UseCase<out Type, in Params>( private val ioDispatcher: CoroutineDispatcher): KoinComponent where Type : Any {

    abstract suspend fun run(params: Params): Either<Failure, Type>


    operator fun invoke(
        params: Params,
        scope: CoroutineScope = MainScope(),
        dispatcher: CoroutineDispatcher,
        onResult: (Either<Failure, Type>) -> Unit = {}
    ) {
        scope.launch {
            val deferredJob = async(ioDispatcher) {
                run(params)
            }
            onResult(deferredJob.await())
        }
    }
}


