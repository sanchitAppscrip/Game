package com.test.data.model

import androidx.annotation.NonNull


data class Response(val status: Status, var data: String? = null, val error: Throwable? = null) {
    companion object {
        fun loading(): Response {
            return Response(Status.LOADING, null, null)
        }

        fun success(@NonNull data: String?): Response {
            return Response(Status.SUCCESS, data, null)
        }

        fun error(@NonNull error: Throwable?): Response {
            return Response(Status.ERROR, null, error)
        }
    }

}