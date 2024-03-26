package md.webmaster.borgi.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson

@Database(entities = [DebtEntity::class, UserEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class BorgiDatabase: RoomDatabase() {
    abstract fun debtDao(): DebtDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: BorgiDatabase? = null

        fun getInstance(context: Context): BorgiDatabase {
            if (INSTANCE == null) {
                synchronized(BorgiDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            BorgiDatabase::class.java,
                            "borgi_database"
                        ).fallbackToDestructiveMigration().build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}

class Converters {

    @TypeConverter
    fun strListToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun longListToJson(value: List<Long>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToStringList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun jsonToLongList(value: String) = Gson().fromJson(value, Array<Long>::class.java).toList()
}