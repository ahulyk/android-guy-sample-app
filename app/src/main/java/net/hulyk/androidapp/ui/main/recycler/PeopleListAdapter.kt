package net.hulyk.androidapp.ui.main.recycler

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.squareup.picasso.Picasso
import net.hulyk.androidapp.databinding.ListItemPeopleBinding
import net.hulyk.androidapp.domain.model.PersonModel

class PeopleListAdapter(clickedListener: ((PersonModel) -> Unit)?) :
    ListDelegationAdapter<List<PeopleDisplayableItem>>(peopleAdapterDelegate(clickedListener))

fun peopleAdapterDelegate(clickedListener: ((PersonModel) -> Unit)?) =
    adapterDelegateViewBinding<PersonModel, PeopleDisplayableItem, ListItemPeopleBinding>({ i, root ->
        ListItemPeopleBinding.inflate(i, root, false)
    }) {
        bind {
            with(binding) {
                title.text = item.name
                iconImg.run {
                    Picasso.get().load(item.avatarImage).into(this)
                }
                root.setOnClickListener { clickedListener?.invoke(item) }
            }
        }
    }