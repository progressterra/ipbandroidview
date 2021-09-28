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
class FilledButtonWithLoading(
    context: Context,
    attrs: AttributeSet?
) : FrameLayout(context, attrs) {

    private val button: AppCompatButton
    private val progressBar: ProgressBar

    private var mText: String = ""
    private var mLoading: Boolean = false
    private var mTextColor: Int = -1
    private var mButtonColor: Int = -1
    private var mLoadingIsClickable = false

    private val duration = 200L

    init {
        inflate(context, R.layout.view_filled_button_with_loading, this)

        button = findViewById(R.id.btn)
        progressBar = findViewById(R.id.pb)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.FilledButtonWithLoading)

        val text = attributes.getString(R.styleable.FilledButtonWithLoading_text)
        val isLoading = attributes.getBoolean(R.styleable.FilledButtonWithLoading_isLoading, false)
        val clickableOnLoading =
            attributes.getBoolean(R.styleable.FilledButtonWithLoading_loadingIsClickable, false)
        mButtonColor = attributes.getColor(R.styleable.FilledButtonWithLoading_buttonTint, -1)
        val enabled = attributes.getBoolean(R.styleable.FilledButtonWithLoading_enabled, true)
        val textColor = attributes.getColor(R.styleable.FilledButtonWithLoading_textColor, -1)
        val textPadding = attributes.getDimension(R.styleable.FilledButtonWithLoading_paddingText, 8f)


        mLoadingIsClickable = clickableOnLoading
        mText = text ?: ""
        mLoading = isLoading
        mTextColor = if (textColor != -1) textColor else button.currentTextColor
        button.setTextColor(mTextColor)
        button.setPadding(
            dpToPx(textPadding),
            button.paddingTop,
            dpToPx(textPadding),
            button.paddingBottom
        )
        val list = buttonColorStateList()

        if (mButtonColor != -1) {
            button.backgroundTintList = list
        }

        progressBar.visibility = if (mLoading) VISIBLE else GONE
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

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
        button.setOnClickListener(l)
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        button.isEnabled = enabled
    }

    fun setText(text: String) {
        mText = text
        if (!mLoading && button.text != mText)
            changeText()
    }

    fun setIsLoading(isLoading: Boolean) {
        if (mLoading != isLoading) {
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
            override fun onAnimationStart(animation: Animator?) {
                onAnimationStart?.invoke()
            }

            override fun onAnimationEnd(animation: Animator?) {
                onAnimationEnd?.invoke()
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationRepeat(animation: Animator?) {

            }
        })
    }

    private fun dpToPx(v: Float): Int {
        return v.times(Resources.getSystem().displayMetrics.density).toInt()
    }
}