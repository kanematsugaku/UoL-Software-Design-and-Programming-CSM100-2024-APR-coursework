package com.example.game.entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This entity represents a map.
 *
 * For more information see: https://domination.sourceforge.io/makemaps.shtml
 */
public class MapEntity {
    public String name;
    public List<String> files = new ArrayList<>();
    public List<ContinentEntity> continents = new ArrayList<>();
    public List<CountryEntity> countries = new ArrayList<>();
    public List<BorderEntity> borders = new ArrayList<>();

    public void load(File mapFile) throws Exception {
        List<String> mapFileLines = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(mapFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                mapFileLines.add(line);
            }
        }

        MapAttribute processingAttribute = MapAttribute.Name;

        for (String line : mapFileLines) {
            if (line.isEmpty()) {
                continue;
            }

            switch (line) {
                case "[files]" -> {
                    processingAttribute = MapAttribute.Files;
                    continue;
                }
                case "[continents]" -> {
                    processingAttribute = MapAttribute.Continents;
                    continue;
                }
                case "[countries]" -> {
                    processingAttribute = MapAttribute.Countries;
                    continue;
                }
                case "[borders]" -> {
                    processingAttribute = MapAttribute.Borders;
                    continue;
                }
                default -> {
                }
            }

            String[] lineParts = line.split(" ");

            switch (processingAttribute) {
                case MapAttribute.Name -> {
                    if (line.startsWith("name")) {
                        this.name = lineParts[1];
                    }
                }
                case MapAttribute.Files -> {
                    this.files.add(line);
                }
                case MapAttribute.Continents -> {
                    int continentId = continents.size() + 1;
                    ContinentEntity continent = new ContinentEntity(continentId, lineParts[0],
                            Integer.parseInt(lineParts[1]), lineParts[2]);
                    this.continents.add(continent);
                }
                case MapAttribute.Countries -> {
                    CountryEntity country = new CountryEntity(Integer.parseInt(lineParts[0]),
                            lineParts[1], Integer.parseInt(lineParts[2]),
                            Integer.parseInt(lineParts[3]), Integer.parseInt(lineParts[4]));
                    this.countries.add(country);
                }
                case MapAttribute.Borders -> {
                    BorderEntity border = new BorderEntity(Integer.parseInt(lineParts[0]),
                            Arrays.stream(lineParts).skip(1).mapToInt(Integer::parseInt).toArray());
                    this.borders.add(border);
                }
                default -> {
                }
            }
        }
    }

    private enum MapAttribute {
        Name, Files, Continents, Countries, Borders,
    }
}
