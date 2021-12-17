package com.ops.flipclass.utilities


class ErrorStatus {
    companion object {
        const val BAD_REQUEST = 400
        const val UN_AUTHORIZED = 401
        const val FORBIDDEN = 403
        const val PAGE_NOT_FOUND = 404
        const val METHOD_NOT_ALLOWED = 405
        const val INTERNAL_SERVER_ERROR = 500
        const val BAD_GATEWAY = 502
        const val GATEWAY_TIME_OUT = 504
        const val NO_INTERNET = 600
        const val OK = 200
        const val NO_RECORD = 902 // No Data Found
        const val SERVER_ERROR = 901 // Status 'exception' or Exception while parsing
    }
}