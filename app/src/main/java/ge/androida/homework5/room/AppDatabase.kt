package ge.androida.homework5.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ge.androida.homework5.TrainModel

@Database(entities = [TrainModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): TrainDao

}