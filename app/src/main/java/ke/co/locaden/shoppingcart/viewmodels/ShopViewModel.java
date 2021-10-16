package ke.co.locaden.shoppingcart.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ke.co.locaden.shoppingcart.models.CartItems;
import ke.co.locaden.shoppingcart.models.Product;
import ke.co.locaden.shoppingcart.respository.CartRepo;
import ke.co.locaden.shoppingcart.respository.ShopRepo;

public class ShopViewModel extends ViewModel {
    ShopRepo shopRepo = new ShopRepo();
    CartRepo cartRepo = new CartRepo();
    MutableLiveData<Product> mutableProduct=new MutableLiveData<>();
    public LiveData<List<Product>> getProducts(){
        return shopRepo.getProducts();
    }

public void setProduct(Product product){
        mutableProduct.setValue(product);
}
        public LiveData<Product> getProduct(){
        return mutableProduct;
        }

        public LiveData<List<CartItems>> getCart(){
        return cartRepo.getCart();
        }

        public boolean addItemToCart(Product product){
        return cartRepo.addItemToCart(product);
        }

        public void removeItemFromCart (CartItems cartItems){
        cartRepo.removeItemFromCart(cartItems);}

        public void changeQuantity(CartItems cartItems,int quantity){
        cartRepo.changeQuantity(cartItems,quantity); }

        public LiveData<Double> getTotalPrice(){
        return cartRepo.getTotalPrice(); }

}
