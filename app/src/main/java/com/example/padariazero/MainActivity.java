package com.example.padariazero;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.main);

        ViewCompat.setOnApplyWindowInsetsListener(container, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Start with Splash screen
        showScreen(R.layout.activity_splash);
    }

    public void showScreen(@LayoutRes int layoutResId) {
        container.removeAllViews();
        View view = LayoutInflater.from(this).inflate(layoutResId, container, false);
        container.addView(view);

        setupBottomNavigation(view, layoutResId);
        setupScreenListeners(view, layoutResId);
    }

    private void setupBottomNavigation(View view, int currentLayoutId) {
        BottomNavigationView nav = view.findViewById(R.id.bottomNavigation);
        if (nav != null) {
            if (currentLayoutId == R.layout.activity_home) {
                nav.setSelectedItemId(R.id.nav_home);
            } else if (currentLayoutId == R.layout.activity_cart) {
                nav.setSelectedItemId(R.id.nav_cart);
            } else if (currentLayoutId == R.layout.activity_profile) {
                nav.setSelectedItemId(R.id.nav_profile);
            } else if (currentLayoutId == R.layout.activity_order_tracking) {
                nav.setSelectedItemId(R.id.nav_orders);
            }

            nav.setOnItemSelectedListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home && currentLayoutId != R.layout.activity_home) {
                    showScreen(R.layout.activity_home);
                    return true;
                } else if (itemId == R.id.nav_orders && currentLayoutId != R.layout.activity_order_tracking) {
                    showScreen(R.layout.activity_order_tracking);
                    return true;
                } else if (itemId == R.id.nav_cart && currentLayoutId != R.layout.activity_cart) {
                    showScreen(R.layout.activity_cart);
                    return true;
                } else if (itemId == R.id.nav_profile && currentLayoutId != R.layout.activity_profile) {
                    showScreen(R.layout.activity_profile);
                    return true;
                }
                return true;
            });
        }
    }

    private void setupScreenListeners(View view, int layoutResId) {
        if (layoutResId == R.layout.activity_splash) {
            view.setOnClickListener(v -> showScreen(R.layout.activity_onboarding));
            View logo = view.findViewById(R.id.logoContainer);
            if (logo != null) logo.setOnClickListener(v -> showScreen(R.layout.activity_onboarding));

        } else if (layoutResId == R.layout.activity_onboarding) {
            setClick(view, R.id.btnNext, R.layout.activity_login);

        } else if (layoutResId == R.layout.activity_login) {
            setClick(view, R.id.btnLogin, R.layout.activity_home);
            setClick(view, R.id.btnGoogleLogin, R.layout.activity_home);
            setClick(view, R.id.tvRegister, R.layout.activity_home);

        } else if (layoutResId == R.layout.activity_home) {
            setClick(view, R.id.headerLocation, R.layout.activity_permission_location);
            setClick(view, R.id.cardPromoBanner, R.layout.activity_permission_sensors);
            setClick(view, R.id.catPaes, R.layout.activity_product_list);
            setClick(view, R.id.catSalgados, R.layout.activity_product_list);
            setClick(view, R.id.catDoces, R.layout.activity_product_list);
            setClick(view, R.id.catBebidas, R.layout.activity_product_list);
            setClick(view, R.id.btnVerTodos, R.layout.activity_product_list);
            setClick(view, R.id.btnSearchBar, R.layout.activity_product_list);

            setClick(view, R.id.cardPaoFrancesHome, R.layout.activity_product_detail_pao_frances);
            setClick(view, R.id.cardPaoQueijoHome, R.layout.activity_product_detail);
            setClick(view, R.id.cardCroissantHome, R.layout.activity_product_detail_croissant);
            setClick(view, R.id.cardCafeHome, R.layout.activity_product_detail_cafe);

        } else if (layoutResId == R.layout.activity_product_list) {
            setClick(view, R.id.btnBack, R.layout.activity_home);
            setClick(view, R.id.imgPaoFrancesItem, R.layout.activity_product_detail_pao_frances);
            setClick(view, R.id.imgPaoQueijoItem, R.layout.activity_product_detail);
            setClick(view, R.id.imgCroissantItem, R.layout.activity_product_detail_croissant);
            setClick(view, R.id.imgCafeItem, R.layout.activity_product_detail_cafe);

        } else if (layoutResId == R.layout.activity_product_detail ||
                   layoutResId == R.layout.activity_product_detail_pao_frances ||
                   layoutResId == R.layout.activity_product_detail_croissant ||
                   layoutResId == R.layout.activity_product_detail_cafe) {
            setClick(view, R.id.btnDetailBack, R.layout.activity_product_list);
            setClick(view, R.id.btnAddToCart, R.layout.activity_cart);

        } else if (layoutResId == R.layout.activity_cart) {
            setClick(view, R.id.btnCartBack, R.layout.activity_home);
            setClick(view, R.id.btnFinishOrder, R.layout.activity_checkout);

        } else if (layoutResId == R.layout.activity_checkout) {
            setClick(view, R.id.btnCheckoutBack, R.layout.activity_cart);
            setClick(view, R.id.btnConfirmCheckout, R.layout.activity_order_tracking);

        } else if (layoutResId == R.layout.activity_order_tracking) {
            setClick(view, R.id.btnTrackingBack, R.layout.activity_home);

        } else if (layoutResId == R.layout.activity_profile) {
            setClick(view, R.id.btnProfileBack, R.layout.activity_home);
            setClick(view, R.id.iconOrders, R.layout.activity_order_tracking);
            setClick(view, R.id.iconAddress, R.layout.activity_permission_location);

        } else if (layoutResId == R.layout.activity_location) {
            setClick(view, R.id.btnLocationBack, R.layout.activity_home);
            setClick(view, R.id.btnUseLocation, R.layout.activity_home);

        } else if (layoutResId == R.layout.activity_permission_location) {
            setClick(view, R.id.btnAllowWhileUsing, R.layout.activity_location);
            setClick(view, R.id.btnAllowOnlyThisTime, R.layout.activity_location);
            setClick(view, R.id.btnDontAllowLocation, R.layout.activity_error_location_denied);

        } else if (layoutResId == R.layout.activity_error_location_denied) {
            setClick(view, R.id.btnTryAgainLocation, R.layout.activity_permission_location);
            setClick(view, R.id.btnManualAddress, R.layout.activity_location);

        } else if (layoutResId == R.layout.activity_permission_sensors) {
            setClick(view, R.id.btnAllowSensors, R.layout.activity_promo_dialog);
            setClick(view, R.id.btnDontAllowSensors, R.layout.activity_error_sensor_denied);

        } else if (layoutResId == R.layout.activity_error_sensor_denied) {
            setClick(view, R.id.btnEnableSettings, R.layout.activity_permission_sensors);
            setClick(view, R.id.btnBackHome, R.layout.activity_home);

        } else if (layoutResId == R.layout.activity_promo_dialog) {
            setClick(view, R.id.btnViewOffer, R.layout.activity_product_list);
            setClick(view, R.id.tvNotNow, R.layout.activity_home);
        }
    }

    private void setClick(View parent, int viewId, int targetLayoutId) {
        View targetView = parent.findViewById(viewId);
        if (targetView != null) {
            targetView.setOnClickListener(v -> showScreen(targetLayoutId));
        }
    }
}