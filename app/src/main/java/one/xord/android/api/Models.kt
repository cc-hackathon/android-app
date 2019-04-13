package one.xord.android.api

/**
 * Created by sami on 10/27/18.
 */

class Request {
    var id = ""
    var nic = ""
    var status = ""
    var requestingAuthority = ""
    var requestingReason= ""
    var requestingFields = ""
}

class Person {
    var cnic = ""
    var phoneNumber = ""
}

class Data {
    var nic = ""
    var name = ""
    var fatherName = ""
    var gender = ""
    var country = ""
    var dob = ""
    var doe = ""
}

open class DataRequest

class WriteDataRequest(val nic: String, val name: String, val fatherName: String, val gender: String,
                       val country: String, val dob: String, val doe: String) : DataRequest()

class WritePersonRequest(val cnic: String, val phoneNumber: String) : DataRequest()

class NICRequest(val nic: String) : DataRequest()

class ReqIDRequest(val nic: String, val reqId: String) : DataRequest()

open class BodyResponse(val status: String, val message: String)

class ListBodyResponse<BodyModel>(status: String, message: String) : BodyResponse(status, message) {
    var body: List<BodyModel>? = null
}

class SingleBodyResponse<BodyModel>(status: String, message: String) : BodyResponse(status, message) {
    var body: BodyModel? = null
}

class IncorrectRequestException(message: String) : Exception(message)