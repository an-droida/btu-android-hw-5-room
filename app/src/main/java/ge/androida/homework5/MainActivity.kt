package ge.androida.homework5

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ge.androida.homework5.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        CoroutineScope(IO).launch {
            getWorkoutInfo()
        }

    }

    private fun init() {
        binding.btnSave.setOnClickListener {
            val calories = binding.etCalories.text
            val run = binding.etRunDistance.text
            val swim = binding.etSwimDistance.text
            if (!calories.isNullOrEmpty() && !run.isNullOrEmpty() && !swim.isNullOrEmpty()) {
                CoroutineScope(IO).launch {

                    App.instance.db.userDao().insert(
                        TrainModel(
                            calories = calories.toString().toDouble(),
                            run = run.toString().toDouble(),
                            swim = swim.toString().toDouble()
                        )
                    )
                    getWorkoutInfo()
                }
                binding.etCalories.text?.clear()
                binding.etRunDistance.text?.clear()
                binding.etSwimDistance.text?.clear()
                Toast.makeText(this, "მონაცემები შეინახა წარმატებით", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "fill all fields!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getWorkoutInfo() {
        val info = App.instance.db.userDao().getAll()
        if (info.isNotEmpty()) {
            var avgRun = 0.0
            var avgSwim = 0.0
            var avgCalories = 0.0
            info.forEach {
                avgRun += it.run ?: 0.0
                avgSwim += it.swim ?: 0.0
                avgCalories += it.calories ?: 0.0
            }
            CoroutineScope(Main).launch {
                binding.tvAvgRun.text = "საშუალო გარბენი: ${avgRun / info.size}"
                binding.tvAvgSwim.text = "საშუალო შედეგი ცურვაში: ${avgSwim / info.size}"
                binding.tvAvgCalories.text = "საშუალო კალორიები: ${avgCalories / info.size}"
                binding.tvTotalRunningDistance.text = "სულ გარბენილი მანძილი: $avgRun"
            }
        }

    }
}