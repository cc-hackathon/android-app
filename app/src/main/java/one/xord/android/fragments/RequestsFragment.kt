package one.xord.android.fragments

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import one.xord.android.R
import one.xord.android.adapters.RequestsAdapter
import one.xord.android.api.*
import one.xord.android.statics.NIC_NUMBER
import one.xord.android.statics.SUCCESS
import one.xord.android.statics.showToast

class RequestsFragment : Fragment(), RequestsAdapter.ItemClickListener {

    private val requests = ArrayList<Request>()
    private val requestsAPIExecutor = APIExecutor.getInstance(APIExecutor.GET_REQUESTS)
    private val approveRequestAPIExecutor = APIExecutor.getInstance(APIExecutor.APPROVE_REQUEST)
    private var adapter: RequestsAdapter? = null

    private var identitiesRecyclerView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fetchRequestsData()
        return inflater!!.inflate(R.layout.fragment_requests, null)
    }

    override fun onStart() {
        super.onStart()
        identitiesRecyclerView = view?.findViewById<View>(R.id.requests_recyclerview) as RecyclerView?
        identitiesRecyclerView?.layoutManager = LinearLayoutManager(context)

        adapter = RequestsAdapter(context, requests)
        executeGetRequestsAPI()
        setListeners()
    }

    private fun setListeners() {
        requestsAPIExecutor?.registerRequestExecutedCallback(object : APIExecutor.RequestExecutedCallback {
            override fun onExecuted(status: String?, message: String?, any: Any?) {
                if (status == SUCCESS) {
                    if (any is List<*>) {
                        any as List<Request>?
                        requests.clear()
                        requests.addAll(any)
                        setAdapter()
                    }
                } else {
//                    showToast(context, message)
                }
            }
        })

        approveRequestAPIExecutor?.registerRequestExecutedCallback(object : APIExecutor.RequestExecutedCallback {
            override fun onExecuted(status: String?, message: String?, any: Any?) {
                if (status == SUCCESS) {
                    executeGetRequestsAPI()
                } else {
                    showToast(context, message)
                }
            }
        })

        adapter?.setClickListener(this)
    }

    override fun onItemClick(view: View, position: Int) {
        executeApproveRequestAPI(NIC_NUMBER, requests[position].id)
    }

    private fun executeGetRequestsAPI() {
        requestsAPIExecutor?.execute(NICRequest(NIC_NUMBER))
    }

    private fun executeApproveRequestAPI(nic: String, reqId: String) {
        approveRequestAPIExecutor?.execute(ReqIDRequest(nic, reqId))
    }

    private fun setAdapter() {
        identitiesRecyclerView?.adapter = adapter
    }

    private fun fetchRequestsData() {

    }
}