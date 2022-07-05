package paolo.disney.party_management.platform.front.list_guests.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import paolo.disney.party_management.databinding.ItemGuestTypeBinding
import paolo.disney.party_management.databinding.ItemSelectableGuestBinding
import paolo.disney.party_management.platform.front.common.dtos.GuestModel


class GuestsByTypeAdapter(
    private val listener: OnGuestClicked
): ListAdapter<GuestByTypeHolder, RecyclerView.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            TITLE_VIEW_HOLDER -> {
                TitleViewHolder.from(parent)
            }
            else -> {
                GuestViewHolder.from(parent)
            }
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            if(!it.title.isNullOrBlank()) {
                (viewHolder as TitleViewHolder).bind(it.title)
            }
            else {
                (viewHolder as GuestViewHolder).bind(it.guest!!, listener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(currentList[position].title != null) {
            TITLE_VIEW_HOLDER
        } else {
            SELECT_GUEST_VIEW_HOLDER
        }
    }

    /**
     * View holder for the title of the lists. It is created at the moment an update to the list
     * generates a new list that updates its members.
     * */
    class TitleViewHolder(private val binding: ItemGuestTypeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(title: String) {
            binding.title = title
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): TitleViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemGuestTypeBinding.inflate(layoutInflater, parent, false)
                return TitleViewHolder(binding)
            }
        }
    }

    /**
     * View holder for listing and selecting guest to make parties. It comes from default from the
     * database.
     * */
    class GuestViewHolder(private val binding: ItemSelectableGuestBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(guest: GuestModel, listener: OnGuestClicked) {
            binding.guest = guest
            binding.listener = listener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): GuestViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemSelectableGuestBinding.inflate(layoutInflater, parent, false)
                return GuestViewHolder(binding)
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<GuestByTypeHolder>() {
            override fun areItemsTheSame(old: GuestByTypeHolder, new: GuestByTypeHolder) =
                (old.guest?.id == new.guest?.id) || (old.title == new.title)

            override fun areContentsTheSame(old: GuestByTypeHolder, new: GuestByTypeHolder) =
                old == new
        }

        private const val TITLE_VIEW_HOLDER = 1
        private const val SELECT_GUEST_VIEW_HOLDER = 2
    }

}