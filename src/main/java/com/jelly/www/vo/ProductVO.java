package com.jelly.www.vo;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {
    private int productId;
    private String name;
    private String description;
    private String brand;
    private Date releaseDate;
    private int initialPrice;
    private String modelNumber;
    private int categoryId;
    private String imageUrl;
    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;

    private List<String> sizes;  // 사이즈 목록
    private String size;         // 개별 사이즈
    private int price;           // 개별 가격

    // 필요한 생성자 (size와 price는 필요없음)
    public ProductVO(int productId, String name, String description, String brand, Date releaseDate, int initialPrice,
                     String modelNumber, int categoryId, String imageUrl, boolean isActive, Date createdAt, Date updatedAt) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.releaseDate = releaseDate;
        this.initialPrice = initialPrice;
        this.modelNumber = modelNumber;
        this.categoryId = categoryId;
        this.imageUrl = imageUrl;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}