package paolo.disney.party_management.platform.front.list_guests

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import paolo.disney.foundation.events.Event
import paolo.disney.foundation.utils.SnackBarUtils
import paolo.disney.party_management.R
import paolo.disney.party_management.databinding.FragmentListGuestsBinding
import paolo.disney.party_management.platform.front.add_guest.AddGuestFragment
import paolo.disney.party_management.platform.front.common.dtos.GuestModel
import paolo.disney.party_management.platform.front.common.views.GuestManagementActionState
import paolo.disney.party_management.platform.front.common.views.GuestManagementViewModel
import paolo.disney.party_management.platform.front.list_guests.adapter.GuestsByTypeAdapter
import paolo.disney.party_management.platform.front.list_guests.adapter.OnGuestClicked


class ListGuestsFragment : Fragment(), OnGuestClicked {

    private val viewModel by activityViewModels<GuestManagementViewModel>()
    private val viewStateObserver = Observer<GuestManagementActionState> { state ->
        when (state) {
            is GuestManagementActionState.GuestCreationDismissed -> dismissGuestCreation()
            is GuestManagementActionState.GuestCreated -> dismissGuestCreation()
            is GuestManagementActionState.NavigateToConfirmParty -> navigateToConfirmParty(state.event)
            is GuestManagementActionState.NavigateToResolveConflictAboutParty -> navigateToResolveConflictAboutParty(state.event)
            is GuestManagementActionState.EventInvalidPartyRegistration -> eventInvalidPartyRegistration()
            else -> { /* Do nothing */ }
        }
    }
    private val addGuestFragment by lazy { AddGuestFragment.newInstance() }
    private lateinit var binding: FragmentListGuestsBinding
    private lateinit var adapter: GuestsByTypeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListGuestsBinding.inflate(inflater)
        initUi()
        initObservers()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.resetParty()
        resetCheckedGuestOfAdapter()
    }

    private fun initUi() {
        initAdapter()
        initMenu()

        viewModel.actionState.observe(viewLifecycleOwner, viewStateObserver)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    private fun initAdapter() {
        adapter = GuestsByTypeAdapter(this)
        binding.rvGuestByStatus.adapter = adapter
    }

    private fun initMenu() {
        (activity as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_guest_management, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.i_new_guest -> {
                        viewModel.initNewGuest()
                        if(!addGuestFragment.isAdded) {
                            addGuestFragment.show(childFragmentManager, TAG_FRAGMENT_ADD_GUEST)
                        }
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun resetCheckedGuestOfAdapter() {
        adapter.currentList.map {
            it.guest?.isChecked = false
        }
    }

    private fun initObservers() {
        observeThatGuestsAreLoaded()
    }

    private fun observeThatGuestsAreLoaded() {
        viewModel.areGuestByTypeLoaded.observe(viewLifecycleOwner) {
            if(it) {
                observeLoadedGuests()
                viewModel.areGuestByTypeLoaded.removeObservers(viewLifecycleOwner)
            }
        }
    }

    private fun observeLoadedGuests() {
        viewModel.guestsByType.observe(viewLifecycleOwner) {
            // we reset the selected guests
            viewModel.resetParty()
            // we reload the adapter
            adapter.submitList(it)
        }
    }

    private fun dismissGuestCreation() {
        if(isAdded) {
            addGuestFragment.dismiss()
        }
    }

    private fun navigateToConfirmParty(event: Event<Long>) {
        event.getContentIfNotHandled()?.apply {
            view?.findNavController()?.navigate(
                ListGuestsFragmentDirections.actionListGuestsToConfirmParty())
        }
    }

    private fun navigateToResolveConflictAboutParty(event: Event<Long>) {
        event.getContentIfNotHandled()?.apply {
            view?.findNavController()?.navigate(
                ListGuestsFragmentDirections.actionListGuestsToResolveConflictAboutParty())
        }
    }

    private fun eventInvalidPartyRegistration() {
        SnackBarUtils.fromCustomView(
            activity = requireActivity(),
            view = requireActivity().findViewById(android.R.id.content),
            title = "Reservation Needed",
            message = "Select at least one Guest that has a reservation to continue"
        )
    }

    override fun onGuestChecked(guest: GuestModel, isChecked: Boolean) {
        guest.isChecked = isChecked
        viewModel.onGuestChecked(guest)
    }

    companion object {
        private const val TAG_FRAGMENT_ADD_GUEST = "TAG_FRAGMENT_ADD_GUEST"
    }

}