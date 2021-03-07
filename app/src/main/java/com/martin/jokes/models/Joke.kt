package com.martin.jokes.models

import com.google.gson.annotations.SerializedName

data class Joke(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("punchline")
    var punchline: String = "",
    @SerializedName("setup")
    var setup: String = "",
    @SerializedName("type")
    var type: String = ""
) {
    override fun toString(): String {
        return "$setup\n$punchline"
    }
}