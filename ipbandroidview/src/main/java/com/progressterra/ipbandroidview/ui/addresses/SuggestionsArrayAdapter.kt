package com.progressterra.ipbandroidview.ui.addresses

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import com.progressterra.ipbandroidapi.remoteData.scrm.models.address.dadata.Suggestion
import com.progressterra.ipbandroidview.R

class SuggestionsArrayAdapter(context: Context, var items: MutableList<Suggestion>) :
    ArrayAdapter<Suggestion>(context, R.layout.item_suggestions_lib, items) {

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults? {
                val result = FilterResults()
                result.values = items
                result.count = items.size
                return result
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            }
        }
    }
}