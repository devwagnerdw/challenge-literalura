package com.challengeliteratura.challengeliteratura.util;


public class CadenasUtil {

    public static String limitarLongitud(String cadena, int longitudMaxima) {
        if (cadena.length() <= longitudMaxima) {
            return cadena;
        } else {
            return cadena.substring(0, longitudMaxima);
        }
    }
}