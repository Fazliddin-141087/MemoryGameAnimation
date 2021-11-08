package uz.pdp.memorygame

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast

import uz.pdp.memorygame.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    val listOpenClose = arrayOf(
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false
    )
    var isBack=false
    var imageIndex = arrayOfNulls<Int>(2)
    var imageId = arrayOfNulls<Int>(2)
    var openImage = 0
    var animationDoing = false
    var timer: CountDownTimer? = null
    var count = 0
    var timeNull = false
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.image1.setOnClickListener {
            imageClick(binding.image1, R.drawable.ic_banana, 0)
        }
        binding.image2.setOnClickListener {
            imageClick(binding.image2, R.drawable.ic_strawberry, 1)
        }
        binding.image3.setOnClickListener {
            imageClick(binding.image3, R.drawable.ic_grapes, 2)
        }
        binding.image4.setOnClickListener {
            imageClick(binding.image4, R.drawable.ic_lemon, 3)
        }
        binding.image5.setOnClickListener {
            imageClick(binding.image5, R.drawable.ic_papaya, 4)
        }
        binding.image6.setOnClickListener {
            imageClick(binding.image6, R.drawable.ic_watermelon, 5)
        }
        binding.image7.setOnClickListener {
            imageClick(binding.image7, R.drawable.ic_banana, 6)
        }
        binding.image8.setOnClickListener {
            imageClick(binding.image8, R.drawable.ic_strawberry, 7)
        }
        binding.image9.setOnClickListener {
            imageClick(binding.image9, R.drawable.ic_grapes, 8)
        }
        binding.image10.setOnClickListener {
            imageClick(binding.image10, R.drawable.ic_lemon, 9)
        }
        binding.image11.setOnClickListener {
            imageClick(binding.image11, R.drawable.ic_papaya, 10)
        }
        binding.image12.setOnClickListener {
            imageClick(binding.image12, R.drawable.ic_watermelon, 11)
        }

        timer = object : CountDownTimer(15000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                var number = DecimalFormat("00")
                var min = (millisUntilFinished / 60000) % 60
                var sek = (millisUntilFinished / 1000) % 60
                binding.txtTime.text = "${number.format(min)}:${number.format(sek)}"
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                binding.txtTime.text = "00:00"
                timeNull = true
            }
        }.start()



    }

    fun imageClick(imageView: ImageView, rasim: Int, index: Int) {
        if (!timeNull) {
            if (!animationDoing) {
                if (!listOpenClose[index]) {
                    animationOpen(imageView, rasim, index)
                } else {
                    animationClose(imageView, rasim, index)
                }
            }
        } else {
            Toast.makeText(this, "VAQT TUGADI ???", Toast.LENGTH_SHORT).show()
        }
    }

    fun animationOpen(imageView: ImageView, rasim: Int, index: Int) {
        var animation = AnimationUtils.loadAnimation(this, R.anim.anim_1)

        imageView.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                animationDoing = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                var animation2 = AnimationUtils.loadAnimation(this@MainActivity, R.anim.anim_2)

                imageView.startAnimation(animation2)

                imageView.setImageResource(rasim)

                animation2.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        listOpenClose[index] = true
                        imageIndex[openImage] = index
                        imageId[openImage] = rasim
                        openImage++

                        if (openImage == 2) {
                            if (imageId[0] == imageId[1]) {
                                imageIdentify(imageIndex[0]).visibility = View.INVISIBLE
                                openImage--
                                imageIdentify(imageIndex[1]).visibility = View.INVISIBLE
                                openImage--
                                count += 1
                                binding.countTxt.text = "${count}"
                                counters(count)
                            } else {
                                animationClose(imageIdentify(imageIndex[0]), -1, imageIndex[0])
                                animationClose(imageIdentify(imageIndex[1]), -1, imageIndex[1])
                            }
                        }
                        animationDoing = false
                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }
                })
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
    }


    fun animationClose(imageView: ImageView, rasim: Int, index: Int?) {
        var animation = AnimationUtils.loadAnimation(this, R.anim.anim_1)

        imageView.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                animationDoing = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                var animation2 = AnimationUtils.loadAnimation(this@MainActivity, R.anim.anim_2)

                imageView.startAnimation(animation2)

                imageView.setImageResource(R.drawable.ic_question)

                animation2.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        animationDoing = false
                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }
                })
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

        })
        listOpenClose[index!!] = false
        openImage--
    }

    fun counters(i:Int){
        if (i==6){
           binding.finishTv.visibility=View.VISIBLE
        }
    }

    fun imageIdentify(index: Int?): ImageView {
        var imageView: ImageView? = null
        when (index) {
            0 -> imageView = binding.image1
            1 -> imageView = binding.image2
            2 -> imageView = binding.image3
            3 -> imageView = binding.image4
            4 -> imageView = binding.image5
            5 -> imageView = binding.image6
            6 -> imageView = binding.image7
            7 -> imageView = binding.image8
            8 -> imageView = binding.image9
            9 -> imageView = binding.image10
            10 -> imageView = binding.image11
            11 -> imageView = binding.image12
        }
        return imageView!!
    }

    override fun onBackPressed() {
        if (isBack){
            super.onBackPressed()
            return
        }
        isBack=true
        handler= Handler(Looper.getMainLooper())
        Toast.makeText(this, "Please again click", Toast.LENGTH_SHORT).show()
        handler.postDelayed({
            isBack=false
        },2000)
    }

    override fun onStop() {
        super.onStop()
        isBack=false
    }

    override fun onResume() {
        super.onResume()
        isBack=false
    }
}