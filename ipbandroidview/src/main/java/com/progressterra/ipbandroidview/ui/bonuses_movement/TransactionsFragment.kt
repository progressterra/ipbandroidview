package com.progressterra.ipbandroidview.ui.bonuses_movement
//
//import android.graphics.Color
//import android.graphics.LinearGradient
//import android.graphics.Shader
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import com.google.android.material.snackbar.Snackbar
//import com.progressterra.ipbandroidview.databinding.FragmentTransactionsBinding
//import com.progressterra.ipbandroidview.ui.bonus_movement_mine.TransactionsAdapter
//
//
//class TransactionsFragment : Fragment() {
//    private lateinit var binding: FragmentTransactionsBinding
//    private val adapter = TransactionsAdapter(emptyList())
//    private val viewModel: TransactionsViewModel by viewModels()
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentTransactionsBinding.inflate(inflater, container, false).apply {
//            vm = viewModel
//            lifecycleOwner = this@TransactionsFragment
//        }
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setupViewModel()
//        setupListeners()
//        setupView()
//    }
//
//    private fun setupView() {
//        binding.rvTransactions.adapter = adapter
//        binding.swipeRefresh.setOnRefreshListener { viewModel.getInfo(true) }
//
//        val shader = LinearGradient(
//            0f,
//            0f,
//            200f,
//            0f,
//            Color.parseColor("#57B8EF"),
//            Color.parseColor("#27D2AC"),
//            Shader.TileMode.CLAMP
//        )
//        binding.tvRubCount.paint.shader = shader
//    }
//
//    private fun setupListeners() {
//        binding.ivBack.setOnClickListener {
//            findNavController().popBackStack()
//        }
//    }
//
//    private fun setupViewModel() {
//        viewModel.apply {
//            transactionList.observe(viewLifecycleOwner) {
//                adapter.items = it
//                adapter.notifyDataSetChanged()
//            }
//            showError.observe(viewLifecycleOwner, this@TransactionsFragment::showError)
//
//            screenState.observe(viewLifecycleOwner) {
//                if (it != ScreenState.LOADING) {
//                    binding.swipeRefresh.isRefreshing = false
//                }
//            }
//        }
//    }
//
//    private fun showError(errorEvent: Event<Int>) {
//        errorEvent.contentIfNotHandled?.let {
//            Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
//        }
//    }
//
//}