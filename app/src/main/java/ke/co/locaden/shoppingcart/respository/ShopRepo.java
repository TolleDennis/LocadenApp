package ke.co.locaden.shoppingcart.respository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.namespace.QName;

import ke.co.locaden.shoppingcart.models.Product;

public class ShopRepo {
    private MutableLiveData<List<Product>> mutableProductList;

    public LiveData<List<Product>>getProducts(){
    if (mutableProductList==null){
        mutableProductList = new MutableLiveData<>();
        loadProducts ();
    }
    return mutableProductList;
    }
    private  void loadProducts(){
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(UUID.randomUUID().toString(),
                "Range Rover Evoque","Its a brand new 2020",
                98000,true, "https://di-uploads-pod15.dealerinspire.com" +
                "/landroverwilmington/uploads/2019/08/2020-Range-Rover-Evoque.png"));
        productList.add(new Product(UUID.randomUUID().toString(),
                "Range Rover Sport","Its a brand new 2019",
                90000,true,"https://di-uploads-pod9.dealerinspire.com" +
                "/landroverfreeport/uploads/2018/09/2018-Land-Rover-Range-Rover-600x350.png"));
        productList.add(new Product(UUID.randomUUID().toString(),
                "Range Rover Sport","Its a brand new 2018",
                88000,true,"https://i.pinimg.com/originals/de/61/88/" +
                "de6188381ac8dff0499a96eac7cf9388.png"));
        productList.add(new Product(UUID.randomUUID().toString(),
                "Range Rover Sport","Its a brand new 2017",
                84000,true,"https://lh3.googleusercontent.com/proxy/" +
                "csFdBC-P_FY57wQVYEQ-eAQ8bFkva7yyV8N36qND31msMqscyb5nha7mAJ7g2yr-" +
                "1IUwrL0ct4Of4aE735UFBegDNrXXOjmNzIxcWkIGNP5wt4onLODI8nr1cA"));
        productList.add(new Product(UUID.randomUUID().toString(),
                "Range Rover Sport","Its a brand new 2016",
                80000,true,"https://di-uploads-pod6.s3.amazonaws.com/" +
                "landroverwestchester/uploads/2017/01/2017-Land-Rover-Range-Rover-Sport-HSE.png"));
        productList.add(new Product(UUID.randomUUID().toString(),
                "AUDI","Its a brand new 2020",
                60000,true,"https://images.dealer.com/ddc/vehicles/2020" +
                "/Audi/A4/Sedan/perspective/front-left/2020_24.png"));
        productList.add(new Product(UUID.randomUUID().toString(),
                "AUDI","Its a brand new 2019",
                56000,true,"https://pictures.dealer.com/s/" +
                "santamonicaaudiaoa/0109/073111c09c0ffa34117a16a60d6cbaf7x.jpg?impolicy" +
                "=downsize&w=568"));
        productList.add(new Product(UUID.randomUUID().toString(),
                "AUDI","Its a brand new 2018",
                54000,true,"https://carsguide-res.cloudinary.com/image/" +
                "upload/f_auto,fl_lossy,q_auto,t_default/v1/editorial/audi-q7-my20-index-2.png"));
        productList.add(new Product(UUID.randomUUID().toString(),
                "AUDI","Its a brand new 2017",
                53000,true,"https://banner2.cleanpng.com/20180330" +
                "/zje/kisspng-2015-audi-a4-2017-audi-a4-audi-s4-car-a4-5abe66f9a3d918" +
                ".9194822315224276416711.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(),
                "AUDI","Its a brand new 2018 model",
                53000,true,"https://w7.pngwing.com/pngs/511/127/png" +
                "-transparent-2018-audi-q5-2018-audi-q3-car-2017-audi-q7-q-vehicle-metal-rim.png"));
        productList.add(new Product(UUID.randomUUID().toString(),
                "AUDI","Its a brand new 2018",
                53000,true,"https://banner2.cleanpng.com/20180331/qcq" +
                "/kisspng-2018-audi-r8-2017-audi-r8-audi-r8-lms-2016-car-audi-5ac0071b065047" +
                ".3202611315225341710259.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(),
                "AUDI","Its a brand new 2018",
                53000,true,"https://img1.pnghut.com/23/14/24/cj7tdby4bx/" +
                "sports-car-audi-r8-coupe-price-supercar.jpg"));


        mutableProductList.setValue(productList);
    }
}
