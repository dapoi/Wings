package com.dapascript.wings.data.remote.model

import com.squareup.moshi.Json

data class LoginResponse(
    @Json(name = "status")
    val status: String,

    @Json(name = "code")
    val code: Int
)
