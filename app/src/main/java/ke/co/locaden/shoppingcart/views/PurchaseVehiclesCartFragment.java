package ke.co.locaden.shoppingcart.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import java.util.List;


import ke.co.locaden.R;
import ke.co.locaden.databinding.FragmentPurchaseVehiclesCartBinding;
import ke.co.locaden.shoppingcart.adapters.CartListAdapter;
import ke.co.locaden.shoppingcart.models.CartItems;
import ke.co.locaden.shoppingcart.viewmodels.ShopViewModel;




public class PurchaseVehiclesCartFragment extends Fragment implements CartListAdapter.CartInterface {

    private static final String TAG = "CartFragment";
    ShopViewModel shopViewModel;
    FragmentPurchaseVehiclesCartBinding fragmentPurchaseVehiclesCartBinding;
    NavController navController;
    public PurchaseVehiclesCartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentPurchaseVehiclesCartBinding = FragmentPurchaseVehiclesCartBinding.inflate
                (inflater, container, false);
        return fragmentPurchaseVehiclesCartBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

            navController = Navigation.findNavController(view);

        final CartListAdapter cartListAdapter = new CartListAdapter(this);
        fragmentPurchaseVehiclesCartBinding.cartRecylerView.setAdapter(cartListAdapter);
        fragmentPurchaseVehiclesCartBinding.cartRecylerView.addItemDecoration
                (new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        shopViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItems>>() {
            @Override
            public void onChanged(List<CartItems> cartItems) {
            cartListAdapter.submitList(cartItems);
            fragmentPurchaseVehiclesCartBinding.placeOrder.setEnabled(cartItems.size()>0);

            }
        });

        shopViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                fragmentPurchaseVehiclesCartBinding.orderTotal.setText
                        ("Total: Ksh " + aDouble.toString());
            }
        });
        fragmentPurchaseVehiclesCartBinding.placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            navController.navigate(R.id.action_purchaseVehiclesCartFragment_to_purchaseVehiclesOrderFragment);
            }
        });
    }

    @Override
    public void deleteItem(CartItems cartItems) {
        Log.d(TAG, "deleteItem: "+ cartItems.getProduct().getProductName());
       shopViewModel.removeItemFromCart(cartItems);
    }

    @Override
    public void changeQuantity(CartItems CartItems, int quantity) {
        shopViewModel.changeQuantity(CartItems, quantity);
    }
}