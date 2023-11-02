package org.app.parada.utils;

public class HelperHaversine {

    public static double haversine(double latitudPunto1, double longitudPunto1, double latitudPunto2, double longitudPunto2) {
        // Radio de la Tierra en kilómetros
        final double R = 6371.0;

        // Convertir latitud y longitud de grados a radianes
        double latitudPunto1Rad = Math.toRadians(latitudPunto1);
        double longitudPunto1Rad = Math.toRadians(longitudPunto1);
        double latitudPunto2Rad = Math.toRadians(latitudPunto2);
        double longitudPunto2Rad = Math.toRadians(longitudPunto2);

        // Diferencia de latitud y longitud
        double dlat = latitudPunto2Rad - latitudPunto1Rad;
        double dlon = longitudPunto2Rad - longitudPunto1Rad;

        // Fórmula haversine
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(latitudPunto1Rad) * Math.cos(latitudPunto2Rad) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distancia en kilómetros
        double distancia = R * c;

        return distancia;
    }

}
