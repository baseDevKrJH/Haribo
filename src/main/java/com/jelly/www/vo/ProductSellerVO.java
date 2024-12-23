package com.jelly.www.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSellerVO {
    private int productSellerId; // product_seller_id
    private int price;           // price
}