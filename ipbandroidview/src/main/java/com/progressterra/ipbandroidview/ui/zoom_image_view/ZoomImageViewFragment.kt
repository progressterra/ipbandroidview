package com.progressterra.ipbandroidview.ui.zoom_image_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.progressterra.ipbandroidview.R
import com.squareup.picasso.Picasso


class ZoomImageViewFragment : Fragment() {
    private var onCloseClickListener: View.OnClickListener? = null
    private lateinit var closeIv: ImageView
    private lateinit var zoomImageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image_zoom_viewer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        setupListeners()
        val pictureUrl = arguments?.getString(PICTURE_ADDRESS_ARGUMENT)
        val pictureScaleType =
            arguments?.getSerializable(PICTURE_SCALE_TYPE) as ImageView.ScaleType?
        pictureScaleType?.let {
            zoomImageView.scaleType = it
        }
        Picasso.get().load(pictureUrl).into(zoomImageView)
    }

    private fun setupListeners() {
        if (onCloseClickListener != null) {
            closeIv.setOnClickListener(onCloseClickListener)
        } else
            closeIv.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
            }
    }

    private fun initView(view: View) {
        zoomImageView = view.findViewById(R.id.zoom_iv)
        closeIv = view.findViewById(R.id.close_iv)
    }

    fun setOnCloseClickListener(onClickListener: View.OnClickListener) {
        onCloseClickListener = onClickListener
    }

    /**
     * @param pictureUrl - адрес, с которого грузится картинка для просмотра
     * @param scaleType - стандартный параметр, отвечающий за масшабирование изображения.
     */
    companion object {
        const val PICTURE_ADDRESS_ARGUMENT = "picture_url"
        const val PICTURE_SCALE_TYPE = "picture_scale_type"

        fun newInstance(
            pictureUrl: String,
            scaleType: ImageView.ScaleType? = null,
        ): ZoomImageViewFragment {
            return ZoomImageViewFragment().apply {
                val args = Bundle()
                args.putString(PICTURE_ADDRESS_ARGUMENT, pictureUrl)
                args.putSerializable(PICTURE_SCALE_TYPE, scaleType)
                this.arguments = args
            }
        }
    }
}