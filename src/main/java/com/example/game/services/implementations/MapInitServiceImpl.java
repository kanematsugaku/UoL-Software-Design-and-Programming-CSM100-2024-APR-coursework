package com.example.game.services.implementations;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import org.springframework.stereotype.Service;
import com.example.game.entities.MapEntity;
import com.example.game.services.interfaces.MapInitService;
import com.example.game.util.PrintUtil;

/**
 * The service for loading the map from text map files.
 */
@Service
public class MapInitServiceImpl implements MapInitService {
    private static final String MAPS_FOLDER_PATH = "src/main/resources/static/maps";

    /**
     * Load a map from the text map file then set the loaded value to the map entity
     *
     * @param scanner The scanner.
     * @return The map entity.
     * @throws Exception
     */
    @Override
    public MapEntity init(Scanner scanner) throws Exception {
        File folder = new File(MAPS_FOLDER_PATH);
        File[] listOfFiles = folder.listFiles();
        Arrays.sort(listOfFiles, (a, b) -> a.getName().compareTo(b.getName()));

        if (listOfFiles == null || listOfFiles.length == 0) {
            throw new Exception("No map files found.");
        }

        PrintUtil.printSpace();
        PrintUtil.printLine("Available maps:");
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                PrintUtil.printLine(i + ": " + listOfFiles[i].getName());
            }
        }

        File selectedFile = null;
        Integer mapNumber = 0;
        boolean isMapNumberValid = false;

        while (!isMapNumberValid) {
            PrintUtil.printSpace();
            PrintUtil.printLine("Enter the number of the map you want to play: ");
            mapNumber = scanner.nextInt();
            if (mapNumber >= 0 && mapNumber < listOfFiles.length) {
                isMapNumberValid = true;
            } else {
                PrintUtil.printLine("Invalid map number.");
            }
        }

        selectedFile = listOfFiles[mapNumber];
        PrintUtil.printLine("Loading map number: " + mapNumber);

        MapEntity mapEntity = new MapEntity();
        mapEntity.load(selectedFile);
        PrintUtil.printLine("Map name: " + mapEntity.getName());

        return mapEntity;
    }
}
