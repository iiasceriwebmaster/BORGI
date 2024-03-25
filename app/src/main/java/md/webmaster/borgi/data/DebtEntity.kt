package md.webmaster.borgi.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "debts_info")
data class DebtEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 1,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "account") val account: String?,
    @ColumnInfo(name = "nr") val nr: String?,
    @ColumnInfo(name = "debt_amount") val debtAmount: Int
)