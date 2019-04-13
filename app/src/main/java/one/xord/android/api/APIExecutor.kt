package one.xord.android.api

import android.util.Log
import one.xord.android.statics.APP_TAG
import one.xord.android.statics.ERROR
import one.xord.android.statics.NIC_NUMBER
import one.xord.android.statics.SUCCESS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by sami on 10/27/18.
 */

abstract class APIExecutor {

    companion object {
        const val GET_REQUESTS = 0
        const val GET_DATA = 1
        const val WRITE_DATA = 2
        const val APPROVE_REQUEST = 3
        const val DECLINE_REQUEST = 4
        const val REVOKE_ACCESS = 5
        const val WRITE_PERSON = 6

        fun getInstance(type: Int): APIExecutor? {
            return when (type) {
                GET_REQUESTS -> GetRequestsExecutor()
                GET_DATA -> GetDataExecutor()
                WRITE_DATA -> WriteDataExecutor()
                APPROVE_REQUEST -> ApproveRequestExecutor()
                DECLINE_REQUEST -> DeclineRequestExecutor()
                REVOKE_ACCESS -> RevokeAccessExecutor()
                WRITE_PERSON -> WritePersonExecutor()
                else -> {
                    return null
                }
            }
        }
    }

    interface RequestExecutedCallback {
        fun onExecuted(status: String?, message: String?, any: Any?)
    }

    protected var requestCallback: RequestExecutedCallback? = null

    fun registerRequestExecutedCallback(callback: RequestExecutedCallback) {
        requestCallback = callback
    }

    protected var apiService: APIService? = APIClient.client?.create(APIService::class.java)

    abstract fun execute(request: DataRequest)
    abstract protected fun terminateRequest(): Nothing

    protected fun onResponse(status: String, message: String, any: Any?) {
        Log.d(APP_TAG, status + " " + message)
        if (status == SUCCESS) {
            requestCallback?.onExecuted(status = status, message = message, any = any)
        } else {
            requestCallback?.onExecuted(status = status, message = message, any = null)
        }
    }

    protected fun onFailure(message: String?) {
        requestCallback?.onExecuted(status = ERROR, message = message, any = null)
        Log.v(APP_TAG, message)
    }
}

private class GetRequestsExecutor : APIExecutor() {
    override fun execute(request: DataRequest) {
        val call: Call<ListBodyResponse<Request>>? = apiService?.getRequests(NIC_NUMBER)

        val callback = object : Callback<ListBodyResponse<Request>> {
            override fun onResponse(call: Call<ListBodyResponse<Request>>, response: Response<ListBodyResponse<Request>>) {
                println(response.body())
                response.body()?.let {
                    onResponse(status = it.status, message = it.message, any = it.body)
                }
            }

            override fun onFailure(call: Call<ListBodyResponse<Request>>, t: Throwable) {
                onFailure(message = t.message)
            }
        }

        call?.enqueue(callback)
    }

    override fun terminateRequest(): Nothing {
        throw IncorrectRequestException("No request is the correct request")
    }
}

private class GetDataExecutor : APIExecutor() {
    override fun execute(request: DataRequest) {
        request as NICRequest
        val call: Call<SingleBodyResponse<Data>>? = apiService?.getDataForUser(request.nic)

        val callback = object : Callback<SingleBodyResponse<Data>> {
            override fun onResponse(call: Call<SingleBodyResponse<Data>>, response: Response<SingleBodyResponse<Data>>) {
                response.body()?.let {
                    onResponse(status = it.status, message = it.message, any = it.body)
                }
            }

            override fun onFailure(call: Call<SingleBodyResponse<Data>>, t: Throwable) {
                onFailure(message = t.message)
            }
        }

        call?.enqueue(callback)
    }

    override fun terminateRequest(): Nothing {
        throw IncorrectRequestException("UserNameRequest is the correct request")
    }
}

private class WriteDataExecutor : APIExecutor() {
    override fun execute(request: DataRequest) {
        request as WriteDataRequest
        val call: Call<BodyResponse>? = apiService?.writeData(request.nic, request.name, request.fatherName, request.gender,
                request.country, request.dob, request.doe)

        val callback = object : Callback<BodyResponse> {
            override fun onResponse(call: Call<BodyResponse>, response: Response<BodyResponse>) {
                response.body()?.let {
                    onResponse(status = it.status, message = it.message, any = it)
                }
            }

            override fun onFailure(call: Call<BodyResponse>, t: Throwable) {
                onFailure(message = t.message)
            }
        }

        call?.enqueue(callback)
    }

    override fun terminateRequest(): Nothing {
        throw IncorrectRequestException("LocationRequest is the correct request")
    }
}

private class WritePersonExecutor : APIExecutor() {
    override fun execute(request: DataRequest) {
        request as WritePersonRequest
        val call: Call<BodyResponse>? = apiService?.writePerson(request.cnic, request.phoneNumber)

        val callback = object : Callback<BodyResponse> {
            override fun onResponse(call: Call<BodyResponse>, response: Response<BodyResponse>) {
                response.body()?.let {
                    onResponse(status = it.status, message = it.message, any = it)
                }
            }

            override fun onFailure(call: Call<BodyResponse>, t: Throwable) {
                onFailure(message = t.message)
            }
        }

        call?.enqueue(callback)
    }

    override fun terminateRequest(): Nothing {
        throw IncorrectRequestException("LocationRequest is the correct request")
    }
}

private class ApproveRequestExecutor : APIExecutor() {
    override fun execute(request: DataRequest) {
        request as ReqIDRequest
        val call: Call<BodyResponse>? = apiService?.approveRequest(request.nic, request.reqId)

        val callback = object : Callback<BodyResponse> {
            override fun onResponse(call: Call<BodyResponse>, response: Response<BodyResponse>) {
                response.body()?.let {
                    onResponse(status = it.status, message = it.message, any = it)
                }
            }

            override fun onFailure(call: Call<BodyResponse>, t: Throwable) {
                onFailure(message = t.message)
            }
        }

        call?.enqueue(callback)
    }

    override fun terminateRequest(): Nothing {
        throw IncorrectRequestException("LocationRequest is the correct request")
    }
}

private class DeclineRequestExecutor : APIExecutor() {
    override fun execute(request: DataRequest) {
        request as ReqIDRequest
        val call: Call<BodyResponse>? = apiService?.declineRequest(request.nic, request.reqId)

        val callback = object : Callback<BodyResponse> {
            override fun onResponse(call: Call<BodyResponse>, response: Response<BodyResponse>) {
                response.body()?.let {
                    onResponse(status = it.status, message = it.message, any = it)
                }
            }

            override fun onFailure(call: Call<BodyResponse>, t: Throwable) {
                onFailure(message = t.message)
            }
        }

        call?.enqueue(callback)
    }

    override fun terminateRequest(): Nothing {
        throw IncorrectRequestException("LocationRequest is the correct request")
    }
}

private class RevokeAccessExecutor : APIExecutor() {
    override fun execute(request: DataRequest) {
        request as ReqIDRequest
        val call: Call<BodyResponse>? = apiService?.revokeAccess(request.nic, request.reqId)

        val callback = object : Callback<BodyResponse> {
            override fun onResponse(call: Call<BodyResponse>, response: Response<BodyResponse>) {
                response.body()?.let {
                    onResponse(status = it.status, message = it.message, any = it)
                }
            }

            override fun onFailure(call: Call<BodyResponse>, t: Throwable) {
                onFailure(message = t.message)
            }
        }

        call?.enqueue(callback)
    }

    override fun terminateRequest(): Nothing {
        throw IncorrectRequestException("LocationRequest is the correct request")
    }
}