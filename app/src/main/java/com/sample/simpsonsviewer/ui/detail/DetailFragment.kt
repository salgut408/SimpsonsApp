package com.sample.simpsonsviewer.ui.detail

import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.sample.simpsonsviewer.R
import com.sample.simpsonsviewer.data.constants.Constants.Companion.BASE_IMAGE_URL
import com.sample.simpsonsviewer.databinding.FragmentDetailBinding
import com.sample.simpsonsviewer.domain.domain_models.RelatedTopicModel
import java.io.IOException

class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding
    lateinit var character: RelatedTopicModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)
        val character = DetailFragmentArgs.fromBundle(requireArguments()).itemArd
        binding.name.text = character?.name
        binding.description.text = character?.text
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = DetailFragmentArgs.fromBundle(requireArguments()).itemArd
        if (args != null) {
            character = args
        }
                Glide.with(this)
                    .load(BASE_IMAGE_URL + character.icon)
                    .apply(
                        RequestOptions().override(800, 800)
                            .placeholder(R.drawable.loading_anim)
                            .error(R.drawable.broken_image)
                            .fallback(R.drawable.ic_baseline_question_mark_24)
                    )
                    .fitCenter()
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return  false
                        }
                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean,
                        ): Boolean {
                            return false
                        }
                    })
                    .transition(withCrossFade())
                    .into(binding.imageView)
    }
}