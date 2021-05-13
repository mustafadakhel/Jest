package com.martin.jokes.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.martin.jokes.models.RemoteKeys

@Dao
interface RemoteKeysDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAll(remoteKey: List<RemoteKeys>)

	@Query("SELECT * FROM remotekeys WHERE repoId = :id")
	suspend fun remoteKeysDoggoId(id: String): RemoteKeys?

	@Query("DELETE FROM remotekeys")
	suspend fun clearRemoteKeys()
}