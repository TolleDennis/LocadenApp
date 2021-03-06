package ke.co.locaden.shoppingcart.models;

import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;

public class CartItems {
    private Product product;
    private int quantity;

    public CartItems(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItems{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItems cartItems = (CartItems) o;
        return getQuantity() == cartItems.getQuantity() &&
                getProduct().equals(cartItems.getProduct());
    }
    @BindingAdapter("android:setVal")
    public static void getSelectedSpinnerValue(Spinner spinner, int quantity) {
        spinner.setSelection(quantity - 1, true);

    }

    public static DiffUtil.ItemCallback<CartItems> itemCallback = new DiffUtil.ItemCallback<CartItems>() {
        @Override
        public boolean areItemsTheSame(@NonNull CartItems oldItem, @NonNull CartItems newItem) {

        return  oldItem.getQuantity()==newItem.getQuantity();
        }

        @Override
        public boolean areContentsTheSame(@NonNull CartItems oldItem, @NonNull CartItems newItem) {
            return oldItem.equals(newItem);
        }
    };

}

