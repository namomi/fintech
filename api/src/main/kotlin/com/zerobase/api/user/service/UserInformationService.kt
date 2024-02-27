package com.zerobase.api.user.service

import com.zerobase.api.user.dto.UserInformationDto
import com.zerobase.api.user.dto.UserPrivateInformationDto

interface UserInformationService {
    fun findUserInfo(
        userInfoRequestDto: UserInformationDto.UserInfoRequestDto
    ): UserInformationDto.UserInfoResponseDto

    fun getPrivateInfo(userKey: String)
        : UserPrivateInformationDto.ResponseDto
}