package paolo.disney.party_management.platform.front.common.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import paolo.disney.party_management.R
import paolo.disney.party_management.databinding.ActivityGuestManagementBinding


@AndroidEntryPoint
class GuestManagementActivity: AppCompatActivity() {

    private val viewModel by viewModels<GuestManagementViewModel>()
    private lateinit var binding: ActivityGuestManagementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_guest_management)
        viewModel.getGuests()
        initUi()
    }

    private fun initUi() {
        setSupportActionBar(binding.toolbar)
        NavigationUI.setupActionBarWithNavController(this, binding.navHostFragment.getFragment<NavHostFragment>().navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }

}