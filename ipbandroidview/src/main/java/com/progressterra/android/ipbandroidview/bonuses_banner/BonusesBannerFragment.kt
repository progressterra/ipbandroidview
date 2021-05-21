package com.progressterra.android.ipbandroidview.bonuses_banner

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.TypedValue.COMPLEX_UNIT_SP
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.progressterra.ipbandroidview.R

class BonusesBannerFragment : Fragment() {

    private lateinit var bonusesBannerViewModel: BonusesBannerViewModel
    private lateinit var bonusExpirationDateTv: TextView
    private lateinit var bonusExpirationNumberTv: TextView
    private lateinit var fireIv: ImageView
    private lateinit var nextBtn: ImageView
    private lateinit var backgroundIv: ImageView
    private lateinit var numberOfBonusesTv: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.bonuses_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        applyFragmentArguments()
        setupViewModel()
    }

    private fun setupViewModel() {
        bonusesBannerViewModel = ViewModelProvider(this).get(BonusesBannerViewModel::class.java)
        bonusesBannerViewModel.bonusesInfo.observe(this) {
            numberOfBonusesTv.text = getString(R.string.bonuses_count, it.currentQuantity.toString())
            bonusExpirationDateTv.text = it.dateBurning
            bonusExpirationNumberTv.text = it.forBurningQuantity.toString()
        }
        bonusesBannerViewModel.bonusesCountIsNonZero.observe(this) {
            showAllBonusesInfo(!it)
        }
    }

    private fun showAllBonusesInfo(show: Boolean) {
        if (show) {
            bonusExpirationDateTv.visibility = View.VISIBLE
            bonusExpirationNumberTv.visibility = View.VISIBLE
            fireIv.visibility = View.VISIBLE
        } else {
            bonusExpirationDateTv.visibility = View.GONE
            bonusExpirationNumberTv.visibility = View.GONE
            fireIv.visibility = View.GONE
        }
    }

    private fun initView(view: View) {
        bonusExpirationDateTv = view.findViewById(R.id.bonus_expiration_date_tv)
        bonusExpirationNumberTv = view.findViewById(R.id.bonus_expiration_number_tv)
        fireIv = view.findViewById(R.id.fire_iv)
        nextBtn = view.findViewById(R.id.next_btn)
        backgroundIv = view.findViewById(R.id.background_iv)
        numberOfBonusesTv = view.findViewById(R.id.number_of_bonuses_tv)
    }

    fun setOnNextButtonListener(listener: View.OnClickListener) {
        nextBtn.setOnClickListener(listener)
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
        nextBtn.setColorFilter(
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
            backgroundIv.setImageDrawable(simpleDrawable)
            return
        }
        val parsedColor = colorList.map { Color.parseColor(it) }
        val gradientDrawable =
            GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, parsedColor.toIntArray())
        backgroundIv.setImageDrawable(gradientDrawable)
    }

    private fun setBaseTextSize(textSize: Float) {
        bonusExpirationDateTv.setTextSize(COMPLEX_UNIT_SP, textSize)
        bonusExpirationNumberTv.setTextSize(COMPLEX_UNIT_SP, textSize)
    }

    private fun setHeaderTextSize(textSize: Float) {
        numberOfBonusesTv.setTextSize(COMPLEX_UNIT_SP, textSize)
    }

    private fun setBaseTextColor(color: String) {
        bonusExpirationDateTv.setTextColor(Color.parseColor(color))
        bonusExpirationNumberTv.setTextColor(Color.parseColor(color))
    }

    private fun setHeaderColor(color: String) {
        numberOfBonusesTv.setTextColor(Color.parseColor(color))
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