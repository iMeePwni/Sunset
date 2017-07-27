package com.imeepwni.android.sunset.ui


import android.animation.*
import android.databinding.*
import android.os.*
import android.support.v4.app.*
import android.view.*
import android.view.animation.*
import com.imeepwni.android.sunset.*
import com.imeepwni.android.sunset.databinding.*
import kotlinx.android.synthetic.main.fragment_sunset.*

class SunsetFragment : Fragment() {

    var isDay = true

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentSunsetBinding>(inflater, R.layout.fragment_sunset, container, false)
        val view = binding.root
        view.setOnClickListener {
            if (isDay) sunsetAnimation()
            else sunupAnimation()
        }
        return view
    }

    val start by lazy { sun.top.toFloat() }
    val end by lazy { sky.bottom.toFloat() }
    val blueSky by lazy { getColor(R.color.blue_sky) }
    val sunsetSky by lazy { getColor(R.color.sunset_sky) }
    val nightSky by lazy { getColor(R.color.night_sky) }

    private fun sunsetAnimation() {
        isDay = false
        val sunsetAnimator = ObjectAnimator.ofFloat(sun, "y", start, end).apply {
            duration = 3000
            interpolator = AccelerateInterpolator()
        }

        val sunsetSkyAnimator = ObjectAnimator.ofInt(sky, "backgroundColor", blueSky, sunsetSky).apply {
            duration = 3000
            setEvaluator(ArgbEvaluator())
        }

        val nightAnimator = ObjectAnimator.ofInt(sky, "backgroundColor", sunsetSky, nightSky).apply {
            duration = 1500
            setEvaluator(ArgbEvaluator())
        }

        val set = AnimatorSet()
        set.play(sunsetAnimator).with(sunsetSkyAnimator).before(nightAnimator)
        set.start()
    }

    private fun sunupAnimation() {
        isDay = true
        val dayAnimator = ObjectAnimator.ofInt(sky, "backgroundColor", nightSky, sunsetSky).apply {
            duration = 1500
            setEvaluator(ArgbEvaluator())
        }

        val sunupSkyAnimator = ObjectAnimator.ofInt(sky, "backgroundColor", sunsetSky, blueSky).apply {
            duration = 3000
            setEvaluator(ArgbEvaluator())
        }

        val sunupAnimator = ObjectAnimator.ofFloat(sun, "y", end, start).apply {
            duration = 3000
            interpolator = AccelerateInterpolator()
        }

        val set = AnimatorSet()
        set.play(sunupAnimator).with(sunupSkyAnimator).after(dayAnimator)
        set.start()
    }

    private fun getColor(resId: Int) = activity.getColor(resId)

    companion object {
        fun newInstance(): SunsetFragment {
            val sunsetFragment = SunsetFragment()
            return sunsetFragment
        }
    }
}
