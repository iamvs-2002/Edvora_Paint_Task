package com.example.edvorapaint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private PaintView paintView;

    LinearLayout paint_colors_list;
    CardView card_pencil, card_arrow, card_rect, card_ellipse, card_paint;
    CardView paint_red, paint_blue, paint_green, paint_black;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.white));

        paintView = findViewById(R.id.paintView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);
        PaintView.line = true;

        paint_colors_list = findViewById(R.id.paint_colors_list);

        card_pencil = findViewById(R.id.card_pencil);
        card_arrow = findViewById(R.id.card_arrow);
        card_rect = findViewById(R.id.card_rect);
        card_ellipse = findViewById(R.id.card_ellipse);
        card_paint = findViewById(R.id.card_paint);

        paint_red = findViewById(R.id.color_red);
        paint_blue = findViewById(R.id.color_blue);
        paint_green = findViewById(R.id.color_green);
        paint_black = findViewById(R.id.color_black);

        card_pencil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaintView.line = true;
                PaintView.arrow = false;
                PaintView.ellipse = false;
                PaintView.rect = false;
                card_pencil.setCardBackgroundColor(getResources().getColor(R.color.selected_bg));
                card_paint.setCardBackgroundColor(getResources().getColor(R.color.unselected_bg));
                card_arrow.setCardBackgroundColor(getResources().getColor(R.color.unselected_bg));
                card_ellipse.setCardBackgroundColor(getResources().getColor(R.color.unselected_bg));
                card_rect.setCardBackgroundColor(getResources().getColor(R.color.unselected_bg));
                paint_colors_list.setVisibility(View.GONE);
            }
        });
        card_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaintView.line = false;
                PaintView.arrow = true;
                PaintView.ellipse = false;
                PaintView.rect = false;
                card_arrow.setCardBackgroundColor(getResources().getColor(R.color.selected_bg));
                card_paint.setCardBackgroundColor(getResources().getColor(R.color.unselected_bg));
                card_pencil.setCardBackgroundColor(getResources().getColor(R.color.unselected_bg));
                card_ellipse.setCardBackgroundColor(getResources().getColor(R.color.unselected_bg));
                card_rect.setCardBackgroundColor(getResources().getColor(R.color.unselected_bg));
                paint_colors_list.setVisibility(View.GONE);
            }
        });
        card_rect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaintView.line = false;
                PaintView.arrow = false;
                PaintView.ellipse = false;
                PaintView.rect = true;
                card_rect.setCardBackgroundColor(getResources().getColor(R.color.selected_bg));
                card_paint.setCardBackgroundColor(getResources().getColor(R.color.unselected_bg));
                card_arrow.setCardBackgroundColor(getResources().getColor(R.color.unselected_bg));
                card_ellipse.setCardBackgroundColor(getResources().getColor(R.color.unselected_bg));
                card_pencil.setCardBackgroundColor(getResources().getColor(R.color.unselected_bg));
                paint_colors_list.setVisibility(View.GONE);
            }
        });
        card_ellipse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaintView.line = false;
                PaintView.arrow = false;
                PaintView.ellipse = true;
                PaintView.rect = false;
                card_ellipse.setCardBackgroundColor(getResources().getColor(R.color.selected_bg));
                card_paint.setCardBackgroundColor(getResources().getColor(R.color.unselected_bg));
                card_arrow.setCardBackgroundColor(getResources().getColor(R.color.unselected_bg));
                card_pencil.setCardBackgroundColor(getResources().getColor(R.color.unselected_bg));
                card_rect.setCardBackgroundColor(getResources().getColor(R.color.unselected_bg));
                paint_colors_list.setVisibility(View.GONE);
            }
        });


        card_paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                card_paint.setCardBackgroundColor(getResources().getColor(R.color.selected_bg));
                card_pencil.setCardBackgroundColor(getResources().getColor(R.color.unselected_bg));
                card_arrow.setCardBackgroundColor(getResources().getColor(R.color.unselected_bg));
                card_ellipse.setCardBackgroundColor(getResources().getColor(R.color.unselected_bg));
                card_rect.setCardBackgroundColor(getResources().getColor(R.color.unselected_bg));
                paint_colors_list.setVisibility(View.VISIBLE);
            }
        });
        paint_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaintView.currentColor = getResources().getColor(R.color.red);
                paint_colors_list.setVisibility(View.GONE);
            }
        });
        paint_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaintView.currentColor = getResources().getColor(R.color.blue);
                paint_colors_list.setVisibility(View.GONE);
            }
        });
        paint_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaintView.currentColor = getResources().getColor(R.color.green);
                paint_colors_list.setVisibility(View.GONE);
            }
        });
        paint_black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaintView.currentColor = getResources().getColor(R.color.black);
                paint_colors_list.setVisibility(View.GONE);
            }
        });
    }
}