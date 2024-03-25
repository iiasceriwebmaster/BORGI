package md.webmaster.borgi.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 1,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "middle_name") val middleName: String = "",
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "date_of_birth") val dateOfBirth: String?,
    @ColumnInfo(name = "passport_nr") val passportNr: String?,
    @ColumnInfo(name = "passport_issuer") val passportIssuer: String?,
    @ColumnInfo(name = "passport_country") val passportCountry: String?,
    @ColumnInfo(name = "passport_city") val passportCity: String?,
    @ColumnInfo(name = "passport_street") val passportStreet: String?,
    @ColumnInfo(name = "passport_street_nr") val passportStreetNr: String?,
    @ColumnInfo(name = "passport_flat_nr") val passportFlatNr: String?,
    @ColumnInfo(name = "phone_nr") val phoneNr: String,
    @ColumnInfo(name = "passport_issue_date") val passportIssueDate: String?,
    @ColumnInfo(name = "signature") val signature: String,
)