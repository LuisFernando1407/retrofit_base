package com.retrofitbase.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.retrofitbase.model.Product;

import java.util.List;

public class ProductsResponse {

    @SerializedName("data")
    @Expose
    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
