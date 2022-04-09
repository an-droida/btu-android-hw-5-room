package ge.androida.homework5

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TrainModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "run")
    val run: Double? = null,
    @ColumnInfo(name = "swim")
    val swim: Double? = null,
    @ColumnInfo(name = "calories")
    val calories: Double? = null
)