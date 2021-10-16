package ke.co.locaden.shoppingcart.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import ke.co.locaden.R;
import ke.co.locaden.shoppingcart.models.CartItems;
import ke.co.locaden.shoppingcart.viewmodels.ShopViewModel;

public class ShopActivity extends AppCompatActivity {

    private static final String TAG ="ShopActivity";
    NavController navController;
    ShopViewModel shopViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController);
        shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);
        shopViewModel.getCart().observe(this, new Observer<List<CartItems>>() {


            @Override
            public void onChanged(List<CartItems> cartItems) {
                Log.d(TAG, "onChanged: "+cartItems.size());
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp(){
        navController.navigateUp();
        return super.onSupportNavigateUp();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       return NavigationUI.onNavDestinationSelected(item, navController) ||
               super.onOptionsItemSelected(item);
    }
}