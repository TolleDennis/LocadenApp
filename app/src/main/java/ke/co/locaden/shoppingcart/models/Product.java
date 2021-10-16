package ke.co.locaden.shoppingcart.models;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;

import java.util.Objects;

public class Product {
    private String productId;
    private String productName;
    private String productBriefDes;
    private double price;
    private boolean isAvailable;
    private String imageUrl;

    public Product(String productId, String productName, String productBriefDes, double price, boolean isAvailable, String imageUrl) {
        this.productId = productId;
        this.productName = productName;
        this.productBriefDes = productBriefDes;
        this.price = price;
        this.isAvailable = isAvailable;
        this.imageUrl = imageUrl;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBriefDes() {
        return productBriefDes;
    }

    public void setProductBriefDes(String productBriefDes) {
        this.productBriefDes = productBriefDes;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productBriefDes='" + productBriefDes + '\'' +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.getPrice(), getPrice()) == 0 &&
                isAvailable() == product.isAvailable() &&
                getProductId().equals(product.getProductId()) &&
                getProductName().equals(product.getProductName()) &&
                getProductBriefDes().equals(product.getProductBriefDes()) &&
                getImageUrl().equals(product.getImageUrl());
    }
    public static DiffUtil.ItemCallback<Product> itemCallback = new DiffUtil.ItemCallback<Product>(){
        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.equals(newItem);
        }


        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
        return oldItem.getProductId().equals(newItem.getProductId());
        }
    };

    @BindingAdapter("android:productImage")
    public static void loadImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView)
                .load(imageUrl)
                .fitCenter()
                .into(imageView);
    }

}
