package com.example.provaandroid;

import android.graphics.Color;

public class ColorUtility {
    public static int getColorByName(String colorName) {
        switch (colorName) {
            case "VERMELHO":
                return Color.RED;
            case "AZUL":
                return Color.BLUE;
            case "VERDE":
                return Color.GREEN;
            default:
                return Color.BLACK;
        }
    }
}
