package com.zerobase.api.user.dto

class UserPrivateInformationDto {

    data class PrivateData(
        val userKey: String,
        var userRegistrationNumber: String
    )
    data class ResponseDto(
        val data: PrivateData,
        val responseCode: String,
        val responseMessage: String
    )
}