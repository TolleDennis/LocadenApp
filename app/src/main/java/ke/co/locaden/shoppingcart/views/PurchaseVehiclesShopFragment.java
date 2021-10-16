package ke.co.locaden.shoppingcart.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import ke.co.locaden.R;
import ke.co.locaden.databinding.FragmentPurchaseVehiclesShopBinding;
import ke.co.locaden.shoppingcart.adapters.ShopListAdapter;
import ke.co.locaden.shoppingcart.models.Product;
import ke.co.locaden.shoppingcart.viewmodels.ShopViewModel;


public class PurchaseVehiclesShopFragment extends Fragment implements ShopListAdapter.ShopInterface {
    private static final String TAG = "PurchaseVehiclesShopFra";
    FragmentPurchaseVehiclesShopBinding fragmentPurchaseVehiclesShopBinding;
    private ShopListAdapter shopListAdapter;
    private ShopViewModel shopViewModel;
    private NavController navController;
    public PurchaseVehiclesShopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)

    {

        fragmentPurchaseVehiclesShopBinding = FragmentPurchaseVehiclesShopBinding.inflate
                (inflater,container,false);
                return fragmentPurchaseVehiclesShopBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopListAdapter = new ShopListAdapter(this);

        fragmentPurchaseVehiclesShopBinding.shopvehiclesrecylerv.setAdapter(shopListAdapter);
        fragmentPurchaseVehiclesShopBinding.shopvehiclesrecylerv.addItemDecoration
                (new DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL));
        fragmentPurchaseVehiclesShopBinding.shopvehiclesrecylerv.addItemDecoration
                (new DividerItemDecoration(requireContext(),DividerItemDecoration.HORIZONTAL));
        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        shopViewModel.getProducts().observe(getViewLifecycleOwner(), products ->
                shopListAdapter.submitList(products));
        navController = Navigation.findNavController(view);
    }

    @Override
    public void addItem(Product product) {
        Log.d(TAG, "addItem: " + product.toString());
        boolean isAdded = shopViewModel.addItemToCart(product);
        Log.d(TAG, "addItem: " + product.getProductName() + isAdded);
        if (isAdded) {
            Snackbar.make(requireView(), product.getProductName() + ", Added to cart.",
                    Snackbar.LENGTH_LONG).setAction("Checkout", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            })
            .show();

        } else {
            Snackbar.make(requireView(), "added max no.of quantities in cart.",
                    Snackbar.LENGTH_LONG).show();

            }
    }

    public void onItemClick(Product product) {
        Log.d(TAG, "onItemClick: "+ product.toString());
        shopViewModel.setProduct(product);
        navController.navigate(R.id.action_purchase_vehicles_shop_fragment_to_productVehiclesDetailsFragment);

    }
}