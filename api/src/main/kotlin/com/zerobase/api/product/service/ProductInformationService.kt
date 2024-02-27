package com.zerobase.api.product.service

import com.zerobase.api.product.dto.ProductInformationDto
import com.zerobase.api.product.response.ProductResponse
import com.zerobase.domain.enum.Organization

interface ProductInformationService {
    fun saveProduct(productInformationDto: ProductInformationDto.RequestDto)
        : ProductResponse.ProductResponseDto

    fun findProduct(organization: Organization)
        : ProductInformationDto.ResponseDto
}