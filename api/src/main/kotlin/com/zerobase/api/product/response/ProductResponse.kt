package com.zerobase.api.product.response

import org.springframework.http.HttpStatus

class ProductResponse(
    private val productResponseCode: ProductResponseCode
) {
    data class ProductResponseDto(
        val responseStatus: HttpStatus,
        val responseCode: String,
        val responseMessage: String
    )
    fun toResponseDto() = ProductResponseDto(
        productResponseCode.status,
        productResponseCode.code,
        productResponseCode.message
    )
}