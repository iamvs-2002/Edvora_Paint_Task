package com.example.edvorapaint;

import android.graphics.Path;

public class FingerTouch {
    public int color;
    public int strokeWidth;
    public Path path;

    public FingerTouch(int color, int strokeWidth, Path path) {
        this.color = color;
        this.strokeWidth = strokeWidth;
        this.path = path;
    }
}
