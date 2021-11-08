package uz.pdp.memorygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import uz.pdp.memorygame.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.elevation = 0f
        supportActionBar?.title = ""

        val loadAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_text)

        binding.tv1.startAnimation(loadAnimation)
        binding.tv2.startAnimation(loadAnimation)
        binding.tv3.startAnimation(loadAnimation)
        binding.tv4.startAnimation(loadAnimation)


        Handler(Looper.getMainLooper()).postDelayed({
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }
}