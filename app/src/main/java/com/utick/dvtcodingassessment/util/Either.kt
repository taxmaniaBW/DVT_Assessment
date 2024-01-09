package com.utick.dvtcodingassessment.util

sealed class Either<out L, out R> {

    /**
     * Represents the left side of [Either] class which by convention
     * is a "Failure".
     */
    data class Left<out L>
    constructor(val left: L) : Either<L, Nothing>()

    /**
     * Represents the right side of [Either] class which by convention
     * is a "Success".
     */
    data class Right<out R>
    constructor(val right: R) : Either<Nothing, R>()

    /**
     * Returns true if this is a Right, false otherwise.
     * @see Right
     */
    val isRight get() = this is Right<R>

    /**
     * Returns true if this is a Left, false otherwise.
     * @see Left
     */
    val isLeft get() = this is Left<L>

    /**
     * Applies fnL if this is a Left or fnR if this is a Right.
     * @see Left
     * @see Right
     */
    fun <T> fold(fnL: (L) -> T, fnR: (R) -> T): T =
        when (this) {
            is Left -> fnL(left)
            is Right -> fnR(right)
        }


    companion object {
        /**
         * Transforms a try/catch in an Either<Exception, Right>
         * **/
        @Suppress("TooGenericExceptionCaught")
        suspend fun <Right> catch(
            operation: suspend () -> Right
        ): Either<Exception, Right> =
            try {
                operation().toRight()
            } catch (e: Exception) {
                e.toLeft()
            }
    }
}

/** Represents the right side of [Either] class which by convention is a "Success". **/
fun <T> T.toRight(): Either.Right<T> =
    Either.Right(this)

/** Represents the left side of [Either] class which by convention is a "Failure". */
fun <T> T.toLeft(): Either.Left<T> =
    Either.Left(this)
