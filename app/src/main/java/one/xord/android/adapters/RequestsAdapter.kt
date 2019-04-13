package one.xord.android.adapters

import android.content.Context
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import one.xord.android.R
import one.xord.android.api.Request

/**
 * Created by sami on 10/27/18.
 */

class RequestsAdapter(context: Context, val mData: List<Request>) : RecyclerView.Adapter<RequestsAdapter.RequestsViewHolder>() {

    private var inflater = LayoutInflater.from(context)
    private var mClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestsViewHolder {
        val view = inflater.inflate(R.layout.requests_recyclerview_item, parent, false)
        return RequestsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RequestsViewHolder?, position: Int) {
        val obj = mData[position]
        holder?.fromTextView?.setText(obj.requestingReason)
        holder?.requestingFields?.setText("Fields : ("+obj.requestingFields+")")
        holder?.requesterTextView?.setText(obj.requestingAuthority)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun setClickListener(itemClickListener: ItemClickListener) {
        this.mClickListener = itemClickListener
    }

    inner class RequestsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var fromTextView: TextView? = null
        var requestAcceptButton: TextView? = null
        var requestRejectButton: TextView? = null
        var requesterTextView: TextView? = null
        var requestingFields: TextView? = null



        init {
            fromTextView = itemView.findViewById(R.id.requests_item_name)
            requesterTextView = itemView.findViewById(R.id.requests_item_requester)
            requestingFields = itemView.findViewById(R.id.requests_item_field)
            requestAcceptButton = itemView.findViewById(R.id.requests_item_accept_button)
            requestRejectButton = itemView.findViewById(R.id.requests_item_reject_button)

            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            if (mClickListener != null) mClickListener?.onItemClick(itemView, getAdapterPosition());
        }
    }

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
}