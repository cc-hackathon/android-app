package one.xord.android.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import one.xord.android.R
import one.xord.android.adapters.IdentitiesAdapterObject
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import one.xord.android.activities.NICActivity
import one.xord.android.activities.NICFormActivity
import one.xord.android.adapters.IdentitiesAdapter
import one.xord.android.utils.PreferenceHelper

class IdentitiesFragment : Fragment(), IdentitiesAdapter.ItemClickListener {

    private val identities = ArrayList<IdentitiesAdapterObject>()


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initIdentitiesList()
        return inflater!!.inflate(R.layout.fragment_identities, null)
    }

    override fun onStart() {
        super.onStart()
        val identitiesRecyclerView = view?.findViewById<View>(R.id.identitiesRecyclerView) as RecyclerView?
        identitiesRecyclerView?.layoutManager = GridLayoutManager(context, 3)
        val adapter = IdentitiesAdapter(context, identities)
        adapter.setClickListener(this)
        identitiesRecyclerView?.adapter = adapter
    }

    @SuppressLint("ShowToast")
    override fun onItemClick(view: View, position: Int) {
        if (identities[position].name == "NIC") {
            startActivity(Intent(activity, NICActivity::class.java))
        } else
            Toast.makeText(context, "Function not available yet.", Toast.LENGTH_SHORT).show();
    }

    private fun initIdentitiesList() {
        identities.add(IdentitiesAdapterObject("NIC", ""))
        identities.add(IdentitiesAdapterObject("Driver's License", ""))
        identities.add(IdentitiesAdapterObject("Domicile", ""))
        identities.add(IdentitiesAdapterObject("Degrees", ""))
        identities.add(IdentitiesAdapterObject("Employment Status", ""))
        identities.add(IdentitiesAdapterObject("Utility Bills", ""))
        identities.add(IdentitiesAdapterObject("Passport", ""))
        identities.add(IdentitiesAdapterObject("Attestation", ""))
    }
}