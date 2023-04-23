package com.capstone.wea.Util;

import com.capstone.wea.model.Coordinate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility functions that may be used by various classes across the project
 */
public class Util {
    private static final DecimalFormat numberFormat = new DecimalFormat("###.##");

    /**
     * Checks if a string is null or blank
     * @param value the string to check
     * @return true if the value is null or blank
     */
    public static boolean isNullOrBlank(String value) {
        return (value == null || value.isBlank());
    }

    /**
     * "Smooths" a polygon by reducing the number of vertices to 100
     * Starting with the 2nd coordinate, the process is as follows:
     * 1. Average the coordinate with the next coordinate
     * 2. Delete the next coordinate
     * 3. Skip the next coordinate
     * 4. Repeat steps 1-3 until the polygon has 100 or fewer coordinates
     * 5. If the current coordinate is the last or second to last coordinate, skip to the 2nd coordinate
     * The first can last coordinate will never be modified because they must remain the same
     * @param polygonString
     * @return
     */
    public static String smoothPolygon(String polygonString) {
        List<Coordinate> coordinates = splitPolygon(polygonString);
        int index = 1;

        while (coordinates.size() > 100) {
            System.out.println(coordinates.size());
            if (index >= coordinates.size() - 2) {
                index = 1;
            }

            double avgLat = (coordinates.get(index).getLatDouble() + coordinates.get(index + 1).getLatDouble()) / 2.0;
            double avgLon = (coordinates.get(index).getLonDouble() + coordinates.get(index + 1).getLonDouble()) / 2.0;

            coordinates.get(index).setLat(numberFormat.format(avgLat));
            coordinates.get(index).setLon(numberFormat.format(avgLon));

            coordinates.remove(index + 1);
            index += 2;
        }

        return joinPolygon(coordinates);
    }

    public static List<Coordinate> splitPolygon(String polygonString) {
        List<Coordinate> coordinates = new ArrayList<>();
        List<String> splitPolygonString = List.of(polygonString.split(" "));

        for (String coordinatePair : splitPolygonString) {
            List<String> latLong = List.of(coordinatePair.split(","));
            coordinates.add(new Coordinate(latLong.get(0), latLong.get(1)));
        }

        return coordinates;
    }

    public static String joinPolygon(List<Coordinate> coordinates) {
        StringBuilder polygonString = new StringBuilder();
        for (Coordinate coordinate : coordinates) {
            polygonString.append(coordinate.getLat()).append(",").append(coordinate.getLon()).append(" ");
        }

        return polygonString.toString().trim();
    }

    /**
     * Wrapper because in addition to IsNullOrEmpty I also miss the null coalescing operator from C#
     * @param input Value to check
     * @param ifNull value to return if input is null
     * @return Returns input if input is not null, otherwise returns ifNull
     * @param <T> Type of input
     */
    public static <T> T nullCoalesce(T input, T ifNull) {
        return (input == null) ? ifNull : input;
    }
}
