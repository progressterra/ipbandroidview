package com.progressterra.android.ipbandroidview.bonuses_banner

import android.graphics.Color
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
        setupViewModel()
    }

    private fun setupViewModel() {
        bonusesBannerViewModel = ViewModelProvider(this).get(BonusesBannerViewModel::class.java)
        bonusesBannerViewModel.bonusesInfo.observe(this) {
            it.data.currentQuantity
            it.data.dateBurning
            it.data.forBurningQuantity
            it.data.typeBonusName
        }
        bonusesBannerViewModel.bonusesCountIsZero.observe(this) {
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

    fun setNextButtonColor(color: String) {
        nextBtn.setColorFilter(
            Color.parseColor(color),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
    }

    fun setBackgroundColor(vararg color: String) {
        val parsedColor = color.map { Color.parseColor(it) }
        val gradientDrawable =
            GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, parsedColor.toIntArray())
        gradientDrawable.cornerRadius = 20f
        backgroundIv.setImageDrawable(gradientDrawable)
    }

    fun setBaseTextSize(textSize: Float) {
        bonusExpirationDateTv.setTextSize(COMPLEX_UNIT_SP, textSize)
        bonusExpirationNumberTv.setTextSize(COMPLEX_UNIT_SP, textSize)
    }

    fun setHeaderTextSize(textSize: Float) {
        numberOfBonusesTv.setTextSize(COMPLEX_UNIT_SP, textSize)
    }

    fun setBaseTextColor(color: String) {
        bonusExpirationDateTv.setTextColor(Color.parseColor(color))
        bonusExpirationNumberTv.setTextColor(Color.parseColor(color))
    }

    fun setHeaderColor(color: String) {
        numberOfBonusesTv.setTextColor(Color.parseColor(color))
    }
}