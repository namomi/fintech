package com.zerobase.api.product.response

import org.springframework.http.HttpStatus

enum class ProductResponseCode(
    val status: HttpStatus,
    val code: String,
    val message: String
) {
    SUCCESS(HttpStatus.OK,"00", "success");
}