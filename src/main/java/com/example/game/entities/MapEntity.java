package com.example.game.entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MapEntity {
    public String name;
    public List<String> files = new ArrayList<>();
    public List<String> continents = new ArrayList<>();
    public List<String> countries = new ArrayList<>();
    public List<String> borders = new ArrayList<>();

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
                case "[files]":
                    processingAttribute = MapAttribute.Files;
                    continue;
                case "[continents]":
                    processingAttribute = MapAttribute.Continents;
                    continue;
                case "[countries]":
                    processingAttribute = MapAttribute.Countries;
                    continue;
                case "[borders]":
                    processingAttribute = MapAttribute.Borders;
                    continue;
                default:
                    break;
            }

            switch (processingAttribute) {
                case MapAttribute.Name:
                    if (line.startsWith("name")) {
                        this.name = line.split(" ")[1];
                    }
                    break;
                case MapAttribute.Files:
                    this.files.add(line);
                    break;
                case MapAttribute.Continents:
                    this.continents.add(line);
                    break;
                case MapAttribute.Countries:
                    this.countries.add(line);
                    break;
                case MapAttribute.Borders:
                    this.borders.add(line);
                    break;
                default:
                    break;
            }
        }

    }

    private enum MapAttribute {
        Name, Files, Continents, Countries, Borders,
    }
}
