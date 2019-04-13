package one.xord.android.adapters

import android.content.Context
import android.provider.ContactsContract
import android.provider.ContactsContract.Contacts.Photo
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import one.xord.android.R
import android.graphics.drawable.Drawable



/**
 * Created by sami on 10/27/18.
 */

class IdentitiesAdapter(context: Context, val mData: List<IdentitiesAdapterObject>) : RecyclerView.Adapter<IdentitiesAdapter.IdentitiesViewHolder>() {

    private var inflater = LayoutInflater.from(context)
    private var mClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IdentitiesViewHolder {
        val view = inflater.inflate(R.layout.identities_recyclerview_item, parent, false)
        return IdentitiesViewHolder(view)
    }

    override fun onBindViewHolder(holder: IdentitiesViewHolder?, position: Int) {
        val obj = mData[position]
        val drawable = inflater.context.resources.getIdentifier("_"+(position+1), "drawable" , inflater.context.packageName  )
        holder?.nameTextView?.text = obj.name
        holder?.nameImageView?.setImageResource(drawable)

    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun setClickListener(itemClickListener: ItemClickListener) {
        this.mClickListener = itemClickListener
    }

    inner class IdentitiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var nameTextView: TextView? = null
        var nameImageView: ImageView? = null

        init {
            nameTextView = itemView.findViewById(R.id.identities_item_name)
            nameImageView = itemView.findViewById(R.id.identities_item_image)
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



class IdentitiesAdapterObject(val name: String, val resource: String)