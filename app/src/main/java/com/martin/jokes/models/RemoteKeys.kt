package com.martin.jokes.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RemoteKeys(@PrimaryKey val jokeId: Int, val prevKey: Int?, val nextKey: Int?)