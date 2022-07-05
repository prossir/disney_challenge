package paolo.disney.party_management.platform.front.add_guest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import paolo.disney.party_management.R
import paolo.disney.party_management.databinding.FragmentAddGuestBinding
import paolo.disney.party_management.platform.front.common.views.GuestManagementActionState
import paolo.disney.party_management.platform.front.common.views.GuestManagementViewModel


class AddGuestFragment: BottomSheetDialogFragment() {

    private val viewModel by activityViewModels<GuestManagementViewModel>()
    private val viewStateObserver = Observer<GuestManagementActionState> { state ->
        when (state) {
            is GuestManagementActionState.GuestCreationFailure -> reportErrorEvent(state.message)
            else -> { /* Do nothing */ }
        }
    }
    private lateinit var binding: FragmentAddGuestBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_guest, container, false)
        initUi()
        initObservers()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        resetStatusOptions()
    }

    private fun initUi() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    private fun initObservers() {
        viewModel.actionState.observe(viewLifecycleOwner, viewStateObserver)
    }

    private fun resetStatusOptions() {
        binding.actvState.apply {
            this.setText(getString(R.string.actv_state_text_default))
            val options = resources.getStringArray(
                resources.getIdentifier("status_options", "array", requireContext().packageName)
            )
            this.setAdapter(
                ArrayAdapter(requireContext(), paolo.disney.foundation.R.layout.disney_dropdown_item, options)
            )
        }
    }

    private fun reportErrorEvent(failure: String) {
        activity?.let {
            Snackbar.make(it.findViewById(android.R.id.content), failure,
                Snackbar.LENGTH_LONG).show()
        }
    }

    companion object {
        fun newInstance() = AddGuestFragment()
    }

}