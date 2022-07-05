package paolo.disney.party_management.platform.front.confirm_party

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import paolo.disney.party_management.databinding.FragmentConfirmPartyBinding


class ConfirmPartyFragment: Fragment() {

    private lateinit var binding: FragmentConfirmPartyBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
            binding = FragmentConfirmPartyBinding.inflate(inflater)
            return binding.root
    }

}