package com.reisdeveloper.githubapiconsumer.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.reisdeveloper.githubapiconsumer.R
import com.reisdeveloper.githubapiconsumer.databinding.ViewUserDataBinding
import com.reisdeveloper.githubapiconsumer.ext.toPx

class UserDataView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr), LifecycleOwner {

    private val binding: ViewUserDataBinding

    private val lifecycleRegistry: LifecycleRegistry by lazy {
        LifecycleRegistry(this)
    }

    override val lifecycle: Lifecycle
        get() = lifecycleRegistry

    var avatar: String = ""
        set(value) {
            field = value
            initViews()
        }

    var name: String = ""
        set(value) {
            field = value
            initViews()
        }

    var url: String = ""
        set(value) {
            field = value
            initViews()
        }

    var login: String = ""
        set(value) {
            field = value
            initViews()
        }

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.UserDataView, 0, defStyleAttr)
            .apply {
                try {
                    binding = ViewUserDataBinding.inflate(
                        LayoutInflater.from(context),
                        this@UserDataView,
                        true
                    )
                    initViews()
                } finally {
                    recycle()
                }
            }
    }

    private fun initViews() {
        Glide.with(this)
            .load(avatar)
            .error(R.drawable.ic_person)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .apply(
                RequestOptions().transform(
                    CenterCrop(),
                    RoundedCorners(36.toPx(binding.root.context))
                )
            )
            .into(binding.imgUser)

        binding.txtUserName.text = name
        binding.txtUserUrl.text = url
    }

}