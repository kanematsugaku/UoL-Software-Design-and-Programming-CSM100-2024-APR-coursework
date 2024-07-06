package com.example.game.services;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.springframework.stereotype.Service;
import com.example.game.entities.MapEntity;
import com.example.game.util.PrintUtil;

@Service
public class MapLoaderService {
    private static final String MAPS_FOLDER_PATH = "src/main/resources/static/maps";

    public void load(MapEntity mapEntity) throws Exception {
        File folder = new File(MAPS_FOLDER_PATH);
        File[] listOfFiles = folder.listFiles();

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
            int mapNumber = scanner.nextInt();

            if (mapNumber < 0 || mapNumber >= listOfFiles.length) {
                throw new Exception("Invalid map number.");
            }

            selectedFile = listOfFiles[mapNumber];
            PrintUtil.printLine("Loading map number " + mapNumber + ": " + selectedFile.getName());
        } catch (IOException e) {
            PrintUtil.printLine("Error reading the map file.");
            e.printStackTrace();
            throw e;
        }

        mapEntity.load(selectedFile);
    }
}
