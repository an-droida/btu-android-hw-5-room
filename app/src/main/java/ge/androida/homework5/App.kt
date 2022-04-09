package ge.androida.homework5

import android.app.Application
import androidx.room.Room
import ge.androida.homework5.room.AppDatabase

class App : Application() {

    lateinit var db: AppDatabase

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        db = Room
            .databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "my_database"
            ).build()
    }
}