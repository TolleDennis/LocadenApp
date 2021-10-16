package ke.co.locaden.shoppingcart.views;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ke.co.locaden.R;


public class PurchaseVehiclesOrderFragment extends Fragment {

    public PurchaseVehiclesOrderFragment() {

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_purchase_vehicles_order, container, false);
    }
}