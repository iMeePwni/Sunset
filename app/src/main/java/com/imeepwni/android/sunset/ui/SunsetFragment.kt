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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentSunsetBinding>(inflater, R.layout.fragment_sunset, container, false)
        val view = binding.root
        view.setOnClickListener {
            startAnimation()
        }
        return view
    }

    private fun startAnimation() {
        val start = sun.top.toFloat()
        val end = sky.bottom.toFloat()

        val sunAnimator = ObjectAnimator.ofFloat(sun, "y", start, end).apply {
            duration = 3000
            interpolator = AccelerateInterpolator()
        }

        val blueSky = getColor(R.color.blue_sky)
        val sunsetSky = getColor(R.color.sunset_sky)

        val sunsetSkyAnimator = ObjectAnimator.ofInt(sky, "backgroundColor", blueSky, sunsetSky).apply {
            duration = 3000
            setEvaluator(ArgbEvaluator())
        }

        val nightSky = getColor(R.color.night_sky)

        val nightAnimator = ObjectAnimator.ofInt(sky, "backgroundColor", sunsetSky, nightSky).apply {
            duration = 1500
            setEvaluator(ArgbEvaluator())
        }

        val set = AnimatorSet()
        set.play(sunAnimator).with(sunsetSkyAnimator).before(nightAnimator)
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
