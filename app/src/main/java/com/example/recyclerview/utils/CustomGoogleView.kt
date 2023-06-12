package com.example.recyclerview.utils

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.graphics.drawable.Drawable
import android.graphics.drawable.InsetDrawable
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.recyclerview.R

class CustomGoogleView(context: Context, attrs: AttributeSet) : AppCompatButton(context, attrs) {

    private val defaultTextSize: Int = 15
    private val defaultTextColor = Color.BLACK

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomGoogleView)

        setButtonText(typedArray)
        setButtonTextSize(typedArray)
        setButtonTextColor(typedArray)
        setButtonTextFontFamily(typedArray)
        setButtonBackground(typedArray)
        setButtonImage(typedArray)

        typedArray.recycle()
    }

    private fun setButtonText(typedArray: TypedArray) {
        val customText = typedArray.getString(R.styleable.CustomGoogleView_buttonText)
        text = customText
    }

    private fun setButtonTextSize(typedArray: TypedArray) {
        val customTextSize = typedArray.getDimensionPixelSize(
            R.styleable.CustomGoogleView_buttonTextSize,
            defaultTextSize
        )
        textSize = customTextSize.toFloat()
    }

    private fun setButtonTextColor(typedArray: TypedArray) {
        val customTextColor = typedArray.getColor(
            R.styleable.CustomGoogleView_buttonTextColor,
            defaultTextColor
        )
        setTextColor(customTextColor)
    }

    private fun setButtonTextFontFamily(typedArray: TypedArray) {
        val fontFamily = typedArray.getResourceId(R.styleable.CustomGoogleView_buttonFontFamily, 0)
        if (fontFamily > 0) {
            typeface = ResourcesCompat.getFont(context, fontFamily)
        }
    }

    private fun setButtonBackground(typedArray: TypedArray) {
        val customBackgroundResId = typedArray.getResourceId(
            R.styleable.CustomGoogleView_buttonBackground, R.drawable.bc_default
        )
        val customBackground: Drawable? = ContextCompat.getDrawable(context, customBackgroundResId)
        background = customBackground
    }

    private fun setButtonImage(typedArray: TypedArray) {
        val customImageResId = typedArray.getResourceId(
            R.styleable.CustomGoogleView_buttonImage,
            R.drawable.bc_default
        )
        val customImage = ContextCompat.getDrawable(context, customImageResId)
        val imagePadding =
            typedArray.getDimensionPixelSize(R.styleable.CustomGoogleView_imagePaddingLeft, 0)
        val insetDrawable = InsetDrawable(customImage, imagePadding, 0, 0, 0)

        setCompoundDrawablesRelativeWithIntrinsicBounds(insetDrawable, null, null, null)
    }

}
