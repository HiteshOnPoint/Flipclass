package com.ops.flipclass.models

data class LoginResponse(
    val clientData: ClientData?,
    val message: String?,
    val status: String?
)

data class ClientData(
    val Address: String,
    val BalancePaymentAccountId: String,
    val BalancePaymentCardCard_Number: String,
    val BalancePaymentCardExpiration_Month: String,
    val BalancePaymentCardExpiration_Year: String,
    val BalancePaymentCardId: String,
    val BalancePaymentCardSecurity_Code: String,
    val City: String,
    val Country: String,
    val CreatedDate: String,
    val Email: String,
    val FirstName: String,
    val IsActive: String,
    val IsPrivate: String,
    val IsRegister: String,
    val LastName: String,
    val Password: String,
    val Phone: String,
    val RegisterDate: String,
    val State: String,
    val Zip: String,
    val accessToekn: String,
    val background: String,
    val backgroundtype: String,
    val chatStatus: String,
    val clientId: String,
    val dob: String,
    val googleplus_oauth_uid: String,
    val gradeid: String,
    val isonline: String,
    val largeDesc: String,
    val mobile: String,
    val parentEmail: String,
    val profileHeaderLine: String,
    val profilePictureName: String,
    val profileShortHeaderLine: String,
    val tagLine: String,
    val thumbImageName: String,
    val thumbMidImageName: String,
    val timezone: String,
    val uuid: String
)

