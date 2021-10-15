package com.progressterra.ipbandroidview.ui.user_inviting

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.activityViewModels
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentContactsListLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseBindingFragment
import com.progressterra.ipbandroidview.ui.user_inviting.models.ContactUi

class ContactListFragment :
    BaseBindingFragment<FragmentContactsListLibBinding, InviteUserViewModel>(R.layout.fragment_contacts_list_lib) {

    override val vm by activityViewModels<InviteUserViewModel>()

    private val adapter = ContactsAdapter()

    override fun onInitBinding(
        binding: FragmentContactsListLibBinding,
        savedInstanceState: Bundle?
    ) {
        super.onInitBinding(binding, savedInstanceState)
        setupView()
        readContacts()
        setupHeader(R.string.invite_friends, true)
    }

    private fun setupView() {
        binding.contactsRecycler.adapter = adapter
        adapter.apply {
            onItemSelected = {
                vm.selectContact(it)
            }
        }

        vm.contacts.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }


    private fun readContacts() {
        val uri: Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI

        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )

        val people: Cursor? = requireActivity().contentResolver.query(
            uri, projection,
            null, null, null
        )

        val indexName = people?.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        val indexNumber = people?.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

        if (people?.moveToFirst() == false) return

        val contacts = mutableListOf<ContactUi>()

        do {
            val name = indexName?.let { people.getString(it) }
            val number = indexNumber?.let { people.getString(it) }
            contacts.add(ContactUi(name, number))
        } while (people?.moveToNext() == true)

        people?.close()

        vm.setContacts(contacts)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        vm.onDestroyView()
    }
}