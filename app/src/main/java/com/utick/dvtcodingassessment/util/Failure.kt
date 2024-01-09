package com.utick.dvtcodingassessment.util


sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()

}