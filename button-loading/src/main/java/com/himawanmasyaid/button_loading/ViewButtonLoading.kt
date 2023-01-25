package com.himawanmasyaid.button_loading

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.himawanmasyaid.button_loading.databinding.ViewButtonLoadingBinding

class ViewButtonLoading @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: ViewButtonLoadingBinding
    var submitListener: () -> Unit = {}

    init {
        initView(attrs)
    }

    private fun initView(attrs: AttributeSet?) {

        binding = ViewButtonLoadingBinding.inflate(LayoutInflater.from(context), this, true)

        val attributArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.ViewButtonLoading, 0, 0)

        val backgroundButton =
            attributArray.getResourceId(R.styleable.ViewButtonLoading_buttonBackground, -1)

        val textButton = attributArray.getText(R.styleable.ViewButtonLoading_textButton)
        val textColor = attributArray.getColor(
            R.styleable.ViewButtonLoading_textButtonColor,
            ContextCompat.getColor(context, R.color.black)
        )

        val progressButtonColor = attributArray.getColor(
            R.styleable.ViewButtonLoading_textButtonColor,
            Color.BLACK
        )

        val isLoading =
            attributArray.getBoolean(R.styleable.ViewButtonLoading_isStartLoading, false)
        val isEnabledButton =
            attributArray.getBoolean(R.styleable.ViewButtonLoading_isEnabledButton, true)

        with(binding) {

            if (textButton.isNotEmpty()) {
                setText(textButton.toString())
            } else {
                setText("")
            }

            if (backgroundButton != -1) {
                setBackgroundButton(backgroundButton)
            } else {
                setBackgroundButton(R.color.gray)
            }

            tvButton.setOnClickListener {
                submitListener()
            }

            if (textColor != -1) {
                tvButton.setTextColor(textColor)
            } else {
                tvButton.setTextColor(ContextCompat.getColor(context, R.color.black))
            }

            tvButton.apply {
                visible()
            }

            viewButton.apply {
                visible()
            }

            setProgressColor(progressButtonColor)

        }

        setEnabledButton(isEnabledButton)

        visible()

        attributArray.recycle()

    }

    fun setProgressColor(color: Int = Color.WHITE) {
        binding.pbLoading.indeterminateTintList = ColorStateList.valueOf(color)
    }

    fun setBackgroundButton(backgroundButton: Drawable?) {
        if (backgroundButton != null) {
            binding.viewButton.background = backgroundButton
        }
    }

    fun setBackgroundButton(backgroundButton: Int?) {
        if (backgroundButton != null) {
            binding.viewButton.setBackgroundResource(backgroundButton)
        }
    }

    fun setText(title: String?) {
        binding.tvButton.text = title ?: ""
    }

    fun setLoading(isLoading: Boolean) {

        isClickable = !isLoading //Disable clickable when loading

        binding.apply {
            tvButton.isVisible = !isLoading
            pbLoading.isVisible = isLoading
        }

        if (!isLoading) {
            setEnabledButton(true)
        }

    }

    fun setEnabledButton(isEnabled: Boolean) {
        setEnabled(isEnabled)
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        binding.viewButton.isEnabled = enabled
        binding.tvButton.isEnabled = enabled
    }

    fun setOnClick(submit: () -> Unit) {
        submit.let { submitListener = it }
    }

    fun View.visible() {
        if (visibility != View.VISIBLE) visibility = View.VISIBLE
    }

}