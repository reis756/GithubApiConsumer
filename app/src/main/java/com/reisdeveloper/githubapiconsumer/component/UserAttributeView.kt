package com.reisdeveloper.githubapiconsumer.component

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.reisdeveloper.githubapiconsumer.R
import com.reisdeveloper.githubapiconsumer.databinding.ViewUserAttributeBinding

class UserAttributeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr), LifecycleOwner {

    private val binding: ViewUserAttributeBinding

    private val lifecycleRegistry: LifecycleRegistry by lazy {
        LifecycleRegistry(this)
    }

    override val lifecycle: Lifecycle
        get() = lifecycleRegistry

    var attribute: String = ""
        set(value) {
            field = value
            initViews()
        }

    var counter: String = ""
        set(value) {
            field = value
            initViews()
        }

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.UserAttributeView, 0, defStyleAttr)
            .apply {
                try {
                    binding = ViewUserAttributeBinding.inflate(
                        LayoutInflater.from(context),
                        this@UserAttributeView,
                        true
                    )
                    onLoadAttrs(this)
                    initViews()
                } finally {
                    recycle()
                }
            }
    }

    private fun onLoadAttrs(attrs: TypedArray) {
        with(attrs) {
            if (hasValue(R.styleable.UserAttributeView_attribute)) {
                attribute = getString(R.styleable.UserAttributeView_attribute) ?: ""
            }

            if (hasValue(R.styleable.UserAttributeView_counter)) {
                counter = getString(R.styleable.UserAttributeView_counter) ?: ""
            }
        }
    }

    private fun initViews() {
        binding.txtAttributeUser.text = attribute
        binding.txtAttributeCount.text = counter
    }
}