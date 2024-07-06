package com.example.game.services;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import org.springframework.stereotype.Service;
import com.example.game.entities.MapEntity;
import com.example.game.util.PrintUtil;

/**
 * The service for loading maps from the text map files.
 */
@Service
public class MapLoaderService {
    private static final String MAPS_FOLDER_PATH = "src/main/resources/static/maps";

    /**
     * Load a map from the text map file then set the loaded value to the map entity
     *
     * @param mapEntity
     * @throws Exception
     */
    public void load(MapEntity mapEntity) throws Exception {
        File folder = new File(MAPS_FOLDER_PATH);
        File[] listOfFiles = folder.listFiles();
        Arrays.sort(listOfFiles, (a, b) -> a.getName().compareTo(b.getName()));

        if (listOfFiles == null || listOfFiles.length == 0) {
            throw new Exception("No map files found.");
        }

        PrintUtil.printLine("Available maps:");
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                PrintUtil.printLine(i + ": " + listOfFiles[i].getName());
            }
        }

        File selectedFile = null;

        try (Scanner scanner = new Scanner(System.in)) {
            PrintUtil.printLine("Enter the number of the map you want to play: ");

            int mapNumber = 0;
            boolean validMapNumber = false;

            while (!validMapNumber) {
                mapNumber = scanner.nextInt();
                if (mapNumber >= 0 && mapNumber < listOfFiles.length) {
                    validMapNumber = true;
                } else {
                    PrintUtil.printLine(
                            "Invalid map number. Please enter the number of the map you want to play: ");
                }
            }

            selectedFile = listOfFiles[mapNumber];
            PrintUtil.printLine("Loading map number " + mapNumber + ": " + selectedFile.getName());
        }

        mapEntity.load(selectedFile);
    }
}
