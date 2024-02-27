package com.zerobase.api.product.service


import com.zerobase.api.product.dto.ProductInformationDto
import com.zerobase.api.product.response.ProductResponse
import com.zerobase.api.product.response.ProductResponseCode
import com.zerobase.domain.enum.Organization
import com.zerobase.domain.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductInformationServiceImpl(
    private val productRepository: ProductRepository

): ProductInformationService  {
    override fun saveProduct(productInformationDto: ProductInformationDto.RequestDto)
        : ProductResponse.ProductResponseDto {
        val productInfo = productInformationDto.toEntity()
        this.productRepository.save(productInfo)

        return ProductResponse(ProductResponseCode.SUCCESS).toResponseDto()
    }

    override fun findProduct(organization: Organization)
        : ProductInformationDto.ResponseDto {
        val data: ArrayList<ProductInformationDto>
            = ArrayList()

        this.productRepository
            .findByOrganizationCode(organization)
            .forEach {
                p -> data.add(
                ProductInformationDto(
                        p.organizationCode.organizationCode,
                        p.productCode.productCode,
                        p.productName,
                        p.productMinInterest,
                        p.productMaxInterest
                    )
                )
            }

        val response = ProductResponse(ProductResponseCode.SUCCESS).toResponseDto()

        return ProductInformationDto.ResponseDto(
            data = data,
            responseCode = response.responseCode,
            responseMessage = response.responseMessage
        )
    }
}