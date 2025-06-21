package org.oauth.enums;

public enum Color {
    BLANCO, NEGRO, AZUL;
    public static Color fromString(String color) {
        return Color.valueOf(color.toUpperCase());
    }


}
