package com.progressterra.ipbandroidview.view

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isVisible
import com.progressterra.ipbandroidview.R

/**
 * Attributes:
 *  text
 *  isLoading - boolean
 *  paddingText - right and left paddings
 *  loadingIsClickable - by default - false
 *  buttonTint - color
 *  enabled
 *  textColor
 */
class OutlinedButtonWithLoading(
    context: Context,
    attrs: AttributeSet?
) : FrameLayout(context, attrs) {

    private val button: AppCompatButton
    private val progressBar: ProgressBar

    private var mText: String = ""
    private var mLoading: Boolean = false
    private var mTextColor: Int = DEF_RES
    private var mButtonColor: Int = DEF_RES
    private var mLoadingIsClickable = false

    private val duration = 200L

    init {
        inflate(context, R.layout.view_outlined_button_with_loading, this)

        button = findViewById(R.id.btn)
        progressBar = findViewById(R.id.pb)

        val attributes =
            context.obtainStyledAttributes(attrs, R.styleable.OutlinedButtonWithLoading)

        val text = attributes.getString(R.styleable.OutlinedButtonWithLoading_text)
        val isLoading =
            attributes.getBoolean(R.styleable.OutlinedButtonWithLoading_isLoading, false)
        val clickableOnLoading =
            attributes.getBoolean(R.styleable.OutlinedButtonWithLoading_loadingIsClickable, false)
        mButtonColor =
            attributes.getColor(R.styleable.OutlinedButtonWithLoading_buttonTint, DEF_RES)
        val enabled = attributes.getBoolean(R.styleable.OutlinedButtonWithLoading_enabled, true)
        val textColor =
            attributes.getColor(R.styleable.OutlinedButtonWithLoading_textColor, DEF_RES)
        val textPadding =
            attributes.getDimension(R.styleable.OutlinedButtonWithLoading_paddingText, 8f)


        mLoadingIsClickable = clickableOnLoading
        mText = text ?: ""
        mLoading = isLoading

        mTextColor = if (textColor != DEF_RES) textColor else button.currentTextColor

        button.setTextColor(buttonTextColorStateList())

        button.setPadding(
            dpToPx(textPadding),
            button.paddingTop,
            dpToPx(textPadding),
            button.paddingBottom
        )
        val list = buttonColorStateList()

        if (mButtonColor != DEF_RES) {
            button.backgroundTintList = list
        }

        progressBar.isVisible = mLoading
        progressBar.indeterminateTintList = button.textColors
        if (!mLoading) button.text = mText
        setIsLoading(mLoading)
        isEnabled = enabled

        attributes.recycle()
    }

    private fun buttonColorStateList(): ColorStateList {
        val tv = TypedValue()
        context.theme.resolveAttribute(R.attr.app_buttonDisabledColor, tv, true)
        @ColorInt val disabledColor = tv.data

        val states =
            arrayOf(
                intArrayOf(android.R.attr.state_enabled),
                intArrayOf(-android.R.attr.state_enabled)
            )

        val colors = intArrayOf(mButtonColor, disabledColor)

        return ColorStateList(states, colors)
    }

    private fun buttonTextColorStateList(): ColorStateList {
        val tv = TypedValue()
        context.theme.resolveAttribute(R.attr.app_buttonDisabledColor, tv, true)
        @ColorInt val disabledColor = tv.data

        val states = arrayOf(
            intArrayOf(android.R.attr.state_enabled),
            intArrayOf(-android.R.attr.state_enabled)
        )

        val colors = intArrayOf(mTextColor, disabledColor)

        return ColorStateList(states, colors)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
        button.setOnClickListener(l)
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        button.isEnabled = enabled
        progressBar.isEnabled = enabled
    }

    fun setText(text: String) {
        mText = text
        if (!mLoading && button.text != mText)
            changeText()
    }

    fun setIsLoading(isLoading: Boolean) {
        if (mLoading == isLoading)
            return

        mLoading = isLoading

        if (!mLoadingIsClickable) {
            isClickable = !mLoading
            button.isClickable = !mLoading
        }

        if (mLoading) {
            button.text = ""
            animateLoading()
        } else {
            button.text = mText
            animateText()
        }
    }

    private fun animateLoading() {
        val textAnim =
            ObjectAnimator.ofInt(button, "textColor", mTextColor, Color.TRANSPARENT)
        textAnim.duration = duration
        textAnim.setEvaluator(ArgbEvaluator())

        val pbAnim = ObjectAnimator.ofFloat(progressBar, View.ALPHA, 0f, 1f)
        pbAnim.duration = duration

        textAnim.animationListener(
            onAnimationEnd = {
                button.text = ""
                pbAnim.start()
            }
        )

        pbAnim.animationListener(
            onAnimationStart = {
                progressBar.visibility = VISIBLE
            }
        )

        textAnim.start()
    }

    private fun animateText() {
        val textAnim = ObjectAnimator.ofInt(button, "textColor", Color.TRANSPARENT, mTextColor)
        textAnim.duration = duration
        textAnim.setEvaluator(ArgbEvaluator())

        val pbAnim = ObjectAnimator.ofFloat(progressBar, View.ALPHA, 1f, 0f)
        pbAnim.duration = duration

        pbAnim.animationListener(onAnimationEnd = {
            progressBar.visibility = GONE
            textAnim.start()
        })

        pbAnim.animationListener(onAnimationStart = {
            button.text = mText
        })

        pbAnim.start()

    }

    private fun changeText() {
        val textGone = ObjectAnimator.ofInt(button, "textColor", mTextColor, Color.TRANSPARENT)
        textGone.duration = duration
        textGone.setEvaluator(ArgbEvaluator())

        val textAppear =
            ObjectAnimator.ofInt(button, "textColor", Color.TRANSPARENT, mTextColor)
        textAppear.duration = duration
        textAppear.setEvaluator(ArgbEvaluator())

        textGone.animationListener {
            button.text = mText
            textAppear.start()
        }

        textGone.start()
    }

    private fun ObjectAnimator.animationListener(
        onAnimationStart: (() -> Unit)? = null,
        onAnimationEnd: (() -> Unit)? = null
    ) {
        this.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {
                onAnimationStart?.invoke()
            }

            override fun onAnimationEnd(p0: Animator) {
                onAnimationEnd?.invoke()
            }

            override fun onAnimationCancel(p0: Animator) {

            }

            override fun onAnimationRepeat(p0: Animator) {

            }
        })
    }

    private fun dpToPx(v: Float): Int {
        return v.times(Resources.getSystem().displayMetrics.density).toInt()
    }

    companion object {
        private const val DEF_RES = -1
    }
}