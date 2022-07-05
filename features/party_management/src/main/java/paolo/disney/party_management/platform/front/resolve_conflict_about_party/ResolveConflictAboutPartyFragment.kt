package paolo.disney.party_management.platform.front.resolve_conflict_about_party

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import paolo.disney.party_management.databinding.FragmentResolveConflictAboutPartyBinding


class ResolveConflictAboutPartyFragment : Fragment() {

    private lateinit var binding: FragmentResolveConflictAboutPartyBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentResolveConflictAboutPartyBinding.inflate(inflater)
        return binding.root
    }

}