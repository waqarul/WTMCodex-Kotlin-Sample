package com.wtmcodex.kotlinsample.data.network

import com.google.gson.annotations.SerializedName


/**
 *
 *
 * This is the standard messaging DTO between different entities in the system. Whether it be from Presenter to View OR from Webservice to client
 *
 *
 * This can also be termed as a IOC (Inter object communication) object. However this abbreviation is misleading as it can also represent Inversion of Control.
 */

open class Message {

    @SerializedName("Message")
    var message: String? = null // the actual message
    @SerializedName("Code")
    var code: ErrorMessage? = null // the error code
    @SerializedName("Type")
    var type: String? = null //type of message , either a success message , or an error message
    @SerializedName("OperationResultValue")
    var operationResultValue: String? = null //value returned by an add, update etc operation
    //these two props below are basically returned from the server in case of account quereies
    @SerializedName("userIdHashed")
    var userIdHashed: String? = null
    @SerializedName("userId")
    var userId: String? = null
    @SerializedName("error_description")
    private val errorDescription: ErrorMessage? = null
    var successMessage: SuccessMessage? = null
    @SerializedName("EntityErrors")
    var entityErrors: List<EntityError>? = null
    private val messageResource: Int = 0

    constructor() {

    }

    constructor(message: String) {

        this.message = message
    }

    constructor(message: String, code: ErrorMessage) {
        this.message = message
        this.code = code
    }

    constructor(errorMessages: ErrorMessage) {

        this.code = errorMessages
    }

    constructor(successMessage: SuccessMessage) {

        this.successMessage = successMessage
    }

    override fun toString(): String {


        return super.toString()
    }
}