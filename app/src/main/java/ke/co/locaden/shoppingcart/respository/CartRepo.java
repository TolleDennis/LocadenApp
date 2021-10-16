package ke.co.locaden.shoppingcart.respository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import ke.co.locaden.shoppingcart.models.CartItems;
import ke.co.locaden.shoppingcart.models.Product;

public class CartRepo {

    private MutableLiveData<List<CartItems>> mutableCart = new MutableLiveData<>();
    private MutableLiveData<Double> mutableTotalPrice = new MutableLiveData<>();

    public LiveData<List<CartItems>> getCart() {
        if (mutableCart.getValue() == null) {
            initCart();
        }
        return mutableCart;
    }

    public void initCart() {
        mutableCart.setValue(new ArrayList<CartItems>());
        calculateCartTotal();
    }

    public boolean addItemToCart(Product product) {
        if (mutableCart.getValue() == null) {
            initCart();
        }
        List<CartItems> cartItemsList = new ArrayList<>(mutableCart.getValue());
        for (CartItems cartItems : cartItemsList) {
            if (cartItems.getProduct().getProductId().equals(product.getProductId())) {
                if (cartItems.getQuantity() == 7) {
                    return false;
                }
                int index = cartItemsList.indexOf(cartItems);
                cartItems.setQuantity(cartItems.getQuantity() + 1);
                cartItemsList.set(index, cartItems);

                mutableCart.setValue(cartItemsList);
                calculateCartTotal();
                return true;
            }
        }
        CartItems cartItems = new CartItems(product, 1);
        cartItemsList.add(cartItems);
        mutableCart.setValue(cartItemsList);
        calculateCartTotal();
        return true;
    }

    public void removeItemFromCart(CartItems cartItems) {
        if (mutableCart.getValue() == null) {
            return;
        }
        List<CartItems> cartItemsList = new ArrayList<>(mutableCart.getValue());
        cartItemsList.remove(cartItems);
        mutableCart.setValue(cartItemsList);
        calculateCartTotal();

    }
        public void changeQuantity(CartItems cartItems, int quantity){
        if (mutableCart.getValue()==null)return;

        List<CartItems> cartItemsList = new ArrayList<>(mutableCart.getValue());

        CartItems updateItems = new CartItems(cartItems.getProduct(),quantity);
        cartItemsList.set(cartItemsList.indexOf(cartItems),updateItems);

        mutableCart.setValue(cartItemsList);
        calculateCartTotal();
    }
    private void calculateCartTotal(){
        if (mutableCart.getValue()==null)return;
        double total = 0.0;
        List<CartItems> cartItemsList= mutableCart.getValue();
        for (CartItems cartItems: cartItemsList){
            total += cartItems.getProduct().getPrice() * cartItems.getQuantity();
        }
        mutableTotalPrice.setValue(total);
    }

    public LiveData<Double> getTotalPrice() {
        if (mutableTotalPrice.getValue() == null) {
            mutableTotalPrice.setValue(0.0);
        }
        return mutableTotalPrice;
    }
}