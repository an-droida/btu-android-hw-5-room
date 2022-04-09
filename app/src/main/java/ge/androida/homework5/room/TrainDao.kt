package ge.androida.homework5.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ge.androida.homework5.TrainModel

@Dao
interface TrainDao {

    @Query("SELECT * FROM trainmodel")
    fun getAll(): List<TrainModel>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg trainModel: TrainModel)


}
