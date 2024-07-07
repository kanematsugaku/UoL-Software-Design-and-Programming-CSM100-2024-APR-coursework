package com.example.game.entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The entity represents a map.
 *
 * For more information @see: https://domination.sourceforge.io/makemaps.shtml
 */
public class MapEntity {
    private String name;
    private List<String> files = new ArrayList<>();
    private List<ContinentEntity> continents = new ArrayList<>();
    private List<CountryEntity> countries = new ArrayList<>();
    private List<BorderEntity> borders = new ArrayList<>();

    private enum MapAttribute {
        Name, Files, Continents, Countries, Borders,
    }

    /**
     * @return the name of the map
     */
    public String getName() {
        return name;
    }

    /**
     * @return the files of the map
     */
    public List<String> getFiles() {
        return files;
    }

    /**
     * @return the continents of the map
     */
    public List<ContinentEntity> getContinents() {
        return continents;
    }

    /**
     * @return the countries of the map
     */
    public List<CountryEntity> getCountries() {
        return countries;
    }

    /**
     * @return the borders of the map
     */
    public List<BorderEntity> getBorders() {
        return borders;
    }

    /**
     * Loads the map from a file
     *
     * @param mapFile the map file
     * @throws Exception if an error occurs
     */
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
            }

            String[] lineParts = line.split(" ");

            switch (processingAttribute) {
                case Name:
                    if (line.startsWith("name")) {
                        this.name = lineParts[1];
                    }
                    break;
                case Files:
                    this.files.add(line);
                    break;
                case Continents:
                    Integer continentId = continents.size() + 1;
                    ContinentEntity continent = new ContinentEntity(continentId, lineParts[0],
                            Integer.valueOf(lineParts[1]), lineParts[2]);
                    this.continents.add(continent);
                    break;
                case Countries:
                    CountryEntity country = new CountryEntity(Integer.valueOf(lineParts[0]),
                            lineParts[1], Integer.valueOf(lineParts[2]),
                            Integer.valueOf(lineParts[3]), Integer.valueOf(lineParts[4]));
                    this.countries.add(country);
                    break;
                case Borders:
                    BorderEntity border =
                            new BorderEntity(Integer.valueOf(lineParts[0]), Arrays.stream(lineParts)
                                    .skip(1).map(Integer::valueOf).toArray(Integer[]::new));
                    this.borders.add(border);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Gets the countries of a player
     *
     * @param player the player
     * @return the countries owned by the player
     */
    public List<CountryEntity> getCountriesByPlayer(PlayerEntity player) {
        return countries.stream().filter(country -> country.getPlayerId().equals(player.getId()))
                .toList();
    }

    /**
     * Gets the countries of a continent
     *
     * @param continent the continent
     * @return the countries of the continent
     */
    public List<CountryEntity> getCountriesByContinent(ContinentEntity continent) {
        return countries.stream()
                .filter(country -> country.getContinentId().equals(continent.getId())).toList();
    }

    /**
     * Gets a country by its id
     *
     * @param id the id
     * @return the country
     */
    public CountryEntity getCountryById(Integer id) {
        return countries.stream().filter(country -> country.getId().equals(id)).findFirst()
                .orElse(null);
    }


    /**
     * Gets the countries of a player with two or more armies
     *
     * @param player the player
     * @return the countries of the player with two or more armies
     */
    public List<CountryEntity> getCountriesByPlayerWithTwoOrMoreArmies(PlayerEntity player) {
        return countries.stream().filter(country -> country.getPlayerId().equals(player.getId())
                && country.getArmyCount() >= 2).toList();
    }

    /**
     * Gets the adjacent countries of a country
     *
     * @param country the country
     * @return the adjacent countries
     */
    public List<CountryEntity> getAdjacentCountries(CountryEntity country) {
        return borders.stream()
                .filter(border -> border.getAdjacentCountryIds().contains(country.getId()))
                .map(border -> getCountryById(border.getCountryId())).toList();
    }
}
