package com.zerobase.api.user.service

import com.zerobase.api.loan.GenerateKey
import com.zerobase.api.loan.encrypt.EncryptComponent
import com.zerobase.api.product.response.ProductResponse
import com.zerobase.api.product.response.ProductResponseCode
import com.zerobase.api.user.dto.UserInformationDto
import com.zerobase.api.user.dto.UserPrivateInformationDto
import com.zerobase.domain.repository.UserInfoRepository
import org.springframework.stereotype.Service

@Service
class UserInformationServiceImpl(
    private val userInfoRepository: UserInfoRepository,
    private val generateKey: GenerateKey,
    private val encryptComponent: EncryptComponent
): UserInformationService {
    override fun findUserInfo(
        userInfoRequestDto: UserInformationDto.UserInfoRequestDto
    ): UserInformationDto.UserInfoResponseDto {

        val userKey = generateKey.generateUserKey()

        userInfoRequestDto.userRegistrationNumber =
            encryptComponent.encryptString(userInfoRequestDto.userRegistrationNumber)

        val userInfoDto = userInfoRequestDto.toUserInfoDto(userKey)

        this.userInfoRepository.save(userInfoDto.toEntity())

        val response = ProductResponse(ProductResponseCode.SUCCESS).toResponseDto()

        return UserInformationDto.UserInfoResponseDto(
            UserInformationDto.UserKey(userKey),
            response.responseCode,
            response.responseMessage
        )
    }

    override fun getPrivateInfo(userKey: String)
        : UserPrivateInformationDto.ResponseDto {
        val userInfo = this.userInfoRepository.findByUserKey(userKey)
        val decryptString =
            this.encryptComponent.decryptString(userInfo.userRegistrationNumber)

        val responseDto =
            ProductResponse(ProductResponseCode.SUCCESS).toResponseDto()

        return UserPrivateInformationDto.ResponseDto(
            UserPrivateInformationDto.PrivateData(userKey, decryptString),
            responseDto.responseCode,
            responseDto.responseMessage
        )
    }

}
