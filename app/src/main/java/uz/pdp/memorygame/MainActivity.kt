package uz.pdp.memorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

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

    var imageIndex = arrayOfNulls<Int>(2)
    var imageId = arrayOfNulls<Int>(2)
    var openImage = 0
    var animationDoing = false
    var timer: CountDownTimer? = null
    var count = 0
    var timeNull = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image_1.setOnClickListener {
            imageClick(image_1, R.drawable.ic_banana, 0)
        }
        image_2.setOnClickListener {
            imageClick(image_2, R.drawable.ic_strawberry, 1)
        }
        image_3.setOnClickListener {
            imageClick(image_3, R.drawable.ic_grapes, 2)
        }
        image_4.setOnClickListener {
            imageClick(image_4, R.drawable.ic_lemon, 3)
        }
        image_5.setOnClickListener {
            imageClick(image_5, R.drawable.ic_papaya, 4)
        }
        image_6.setOnClickListener {
            imageClick(image_6, R.drawable.ic_watermelon, 5)
        }
        image_7.setOnClickListener {
            imageClick(image_7, R.drawable.ic_banana, 6)
        }
        image_8.setOnClickListener {
            imageClick(image_8, R.drawable.ic_strawberry, 7)
        }
        image_9.setOnClickListener {
            imageClick(image_9, R.drawable.ic_grapes, 8)
        }
        image_10.setOnClickListener {
            imageClick(image_10, R.drawable.ic_lemon, 9)
        }
        image_11.setOnClickListener {
            imageClick(image_11, R.drawable.ic_papaya, 10)
        }
        image_12.setOnClickListener {
            imageClick(image_12, R.drawable.ic_watermelon, 11)
        }

        timer = object : CountDownTimer(12000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                var number = DecimalFormat("00")
                var min = (millisUntilFinished / 60000) % 60
                var sek = (millisUntilFinished / 1000) % 60
                txt_time.setText("${number.format(min)}:${number.format(sek)}")
            }

            override fun onFinish() {
                txt_time.setText("00:00")
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
                                count_txt.setText("${count}")
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

    fun imageIdentify(index: Int?): ImageView {
        var imageView: ImageView? = null
        when (index) {
            0 -> imageView = image_1
            1 -> imageView = image_2
            2 -> imageView = image_3
            3 -> imageView = image_4
            4 -> imageView = image_5
            5 -> imageView = image_6
            6 -> imageView = image_7
            7 -> imageView = image_8
            8 -> imageView = image_9
            9 -> imageView = image_10
            10 -> imageView = image_11
            11 -> imageView = image_12
        }
        return imageView!!
    }
}