package md.webmaster.borgi.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface DebtDao {
    @Query("SELECT * FROM debts_info")
    fun getAll(): List<DebtEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(debtEntity: DebtEntity)

    @Update
    suspend fun update(debtEntity: DebtEntity)

    @Delete
    fun delete(debtEntity: DebtEntity)

    @Query("DELETE FROM debts_info")
    suspend fun deleteAll(): Int
}