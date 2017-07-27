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
            if (isDay) startSunsetAnimator()
            else sunupAnimation()
        }
        return view
    }

    val start by lazy { sun.top.toFloat() }
    val end by lazy { sky.bottom.toFloat() }

    val sunsetAnimator by lazy {
        ObjectAnimator.ofFloat(sun, "y", start, end).apply {
            duration = 3000
            interpolator = AccelerateInterpolator()
        }!!
    }
    val sunsetSkyAnimator by lazy {
        getAnimator(R.animator.sunset_sky).apply {
            target = sky
            setEvaluator(ArgbEvaluator())
        }
    }
    val nightAnimator by lazy {
        getAnimator(R.animator.night_sky).apply {
            target = sky
            setEvaluator(ArgbEvaluator())
        }
    }

    private fun getAnimator(resId: Int) = AnimatorInflater.loadAnimator(activity, resId) as ObjectAnimator

    private fun startSunsetAnimator() {
        isDay = false
        val set = AnimatorSet()
        set.play(sunsetAnimator).with(sunsetSkyAnimator).before(nightAnimator)
        set.start()
    }

    val dayAnimator by lazy {
        getAnimator(R.animator.day_sky).apply {
            target = sky
            setEvaluator(ArgbEvaluator())
        }
    }

    val sunupSkyAnimator by lazy {
        getAnimator(R.animator.sunup_sky).apply {
            target = sky
            setEvaluator(ArgbEvaluator())
        }
    }

    val sunupAnimator by lazy {
        ObjectAnimator.ofFloat(sun, "y", end, start).apply {
            duration = 3000
            interpolator = AccelerateInterpolator()
        }!!
    }

    private fun sunupAnimation() {
        isDay = true
        val set = AnimatorSet()
        set.play(sunupAnimator).with(sunupSkyAnimator).after(dayAnimator)
        set.start()
    }

    companion object {
        fun newInstance(): SunsetFragment {
            val sunsetFragment = SunsetFragment()
            return sunsetFragment
        }
    }
}
