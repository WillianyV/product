package com.products.product.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {
    @NotBlank
    @Size(max = 150)
    private String name;

    @NotBlank
    private BigDecimal amount;

    @NotBlank
    private BigDecimal price;
}
