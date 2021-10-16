package ke.co.locaden.shoppingcart.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ke.co.locaden.R;
import ke.co.locaden.databinding.FragmentProductVehiclesDetailsBinding;
import ke.co.locaden.shoppingcart.viewmodels.ShopViewModel;


public class ProductVehiclesDetailsFragment extends Fragment {

FragmentProductVehiclesDetailsBinding fragmentProductVehiclesDetailsBinding;
    ShopViewModel shopViewModel;
    public ProductVehiclesDetailsFragment() {
        // Required empty public constructor


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentProductVehiclesDetailsBinding = FragmentProductVehiclesDetailsBinding.inflate
                 (inflater,container,false);
                return fragmentProductVehiclesDetailsBinding.getRoot();
    }

    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        fragmentProductVehiclesDetailsBinding.setShopViewModel(shopViewModel);
    }
}