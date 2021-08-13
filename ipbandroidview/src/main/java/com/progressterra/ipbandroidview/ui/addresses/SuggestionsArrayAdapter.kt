package com.progressterra.ipbandroidview.ui.addresses

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.ui.addresses.models.SuggestionUI

class SuggestionsArrayAdapter(context: Context, var items: MutableList<SuggestionUI>) :
    ArrayAdapter<SuggestionUI>(context, R.layout.item_suggestions_lib, items) {

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