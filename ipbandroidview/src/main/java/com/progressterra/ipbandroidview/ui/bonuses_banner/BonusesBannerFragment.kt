package com.progressterra.ipbandroidview.ui.bonuses_banner

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.TypedValue.COMPLEX_UNIT_SP
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.progressterra.ipbandroidview.databinding.FragmentBonusesBinding

class BonusesBannerFragment : Fragment() {

    private val bonusesBannerViewModel by activityViewModels<BonusesBannerViewModel>()
    private var onBntNextClickListener: View.OnClickListener? = null
    private lateinit var fragmentBonusesBinding: FragmentBonusesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBonusesBinding = FragmentBonusesBinding.inflate(inflater)
        return fragmentBonusesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applyFragmentArguments()
        setupViewModel()
        initListeners()
    }

    private fun initListeners() {
        onBntNextClickListener?.let {
            fragmentBonusesBinding.nextBtn.setOnClickListener(it)
        }
    }

    private fun setupViewModel() {
        bonusesBannerViewModel.bonusesInfo.observe(viewLifecycleOwner) {
            fragmentBonusesBinding.bonusesInfo = it
        }
    }

    fun setOnNextButtonListener(listener: View.OnClickListener) {
        onBntNextClickListener = listener
    }

    private fun applyFragmentArguments() {
        arguments?.apply {
            getString(NEXT_BUTTON_COLOR_ARG)?.let {
                setNextButtonColor(it)
            }

            getString(BASE_TEXT_COLOR_ARG)?.let {
                setBaseTextColor(it)
            }

            getString(HEADER_TEXT_COLOR_ARG)?.let {
                setHeaderColor(it)
            }

            val firstBackgroundColor = getString(FIRST_BACKGROUND_COLOR_ARG)
            val secondBackgroundColor = getString(SECOND_BACKGROUND_COLOR_ARG)
            setBackgroundColor(firstBackgroundColor, secondBackgroundColor)
            getFloat(BASE_TEXT_SIZE_ARG).let {
                if (it != 0f) {
                    setBaseTextSize(it)
                }
            }
            getFloat(HEADER_TEXT_SIZE_ARG).let {
                if (it != 0f) {
                    setHeaderTextSize(it)
                }
            }
        }
    }

    private fun setNextButtonColor(color: String) {
        fragmentBonusesBinding.nextBtn.setColorFilter(
            Color.parseColor(color),
            PorterDuff.Mode.SRC_IN
        )
    }

    private fun setBackgroundColor(vararg colors: String?) {
        val colorList: List<String?> = colors.toList().filterNotNull()

        if (colorList.isNullOrEmpty()) {
            return
        }
        if (colorList.size == 1) {
            val simpleDrawable = GradientDrawable()
            simpleDrawable.setColor(Color.parseColor(colorList.first()))
            fragmentBonusesBinding.backgroundIv.setImageDrawable(simpleDrawable)
            return
        }
        val parsedColor = colorList.map { Color.parseColor(it) }
        val gradientDrawable =
            GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, parsedColor.toIntArray())
        fragmentBonusesBinding.backgroundIv.setImageDrawable(gradientDrawable)
    }

    private fun setBaseTextSize(textSize: Float) {
        fragmentBonusesBinding.bonusExpirationDateTv.setTextSize(COMPLEX_UNIT_SP, textSize)
        fragmentBonusesBinding.bonusExpirationNumberTv.setTextSize(COMPLEX_UNIT_SP, textSize)
    }

    private fun setHeaderTextSize(textSize: Float) {
        fragmentBonusesBinding.numberOfBonusesTv.setTextSize(COMPLEX_UNIT_SP, textSize)
    }

    private fun setBaseTextColor(color: String) {
        fragmentBonusesBinding.bonusExpirationDateTv.setTextColor(Color.parseColor(color))
        fragmentBonusesBinding.bonusExpirationNumberTv.setTextColor(Color.parseColor(color))
    }

    private fun setHeaderColor(color: String) {
        fragmentBonusesBinding.numberOfBonusesTv.setTextColor(Color.parseColor(color))
    }

    fun updateBonusesInfo() {
        bonusesBannerViewModel.updateBonusesInfo()
    }

    companion object {

        const val NEXT_BUTTON_COLOR_ARG = "next_button_color"
        const val FIRST_BACKGROUND_COLOR_ARG = "first_background_color"
        const val SECOND_BACKGROUND_COLOR_ARG = "second_background_color"
        const val BASE_TEXT_SIZE_ARG = "base_text_size"
        const val HEADER_TEXT_SIZE_ARG = "header_text_size"
        const val BASE_TEXT_COLOR_ARG = "base_text_color"
        const val HEADER_TEXT_COLOR_ARG = "header_text_color"

        fun newInstance(
            nextButtonColor: String? = null,
            firstBackgroundColor: String? = null,
            secondBackgroundColor: String? = null,
            baseTextSize: Float? = null,
            headerTextSize: Float? = null,
            baseTextColor: String? = null,
            headerTextColor: String? = null,
        ): BonusesBannerFragment {
            return BonusesBannerFragment().apply {
                val args = Bundle()
                args.apply {
                    putString(NEXT_BUTTON_COLOR_ARG, nextButtonColor)
                    putString(FIRST_BACKGROUND_COLOR_ARG, firstBackgroundColor)
                    putString(SECOND_BACKGROUND_COLOR_ARG, secondBackgroundColor)
                    putString(BASE_TEXT_COLOR_ARG, baseTextColor)
                    putString(HEADER_TEXT_COLOR_ARG, headerTextColor)
                    baseTextSize?.let {
                        putFloat(BASE_TEXT_SIZE_ARG, it)
                    }
                    headerTextSize?.let {
                        putFloat(HEADER_TEXT_SIZE_ARG, it)
                    }
                    arguments = args
                }
            }
        }
    }
}