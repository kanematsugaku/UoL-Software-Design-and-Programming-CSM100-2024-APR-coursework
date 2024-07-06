package com.example.game.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import org.springframework.stereotype.Service;
import com.example.game.util.PrintUtil;

@Service
public class MapLoaderService {
    private static final String MAPS_FOLDER_PATH = "src/main/resources/static/maps";

    public void loadMap() throws Exception {
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

        try (Scanner scanner = new Scanner(System.in)) {
            PrintUtil.printLine("Enter the number of the map you want to play: ");
            int mapNumber = scanner.nextInt();

            if (mapNumber < 0 || mapNumber >= listOfFiles.length) {
                throw new Exception("Invalid map number.");
            }

            File selectedFile = listOfFiles[mapNumber];
            PrintUtil.printLine("Loading map number " + mapNumber + ": " + selectedFile.getName());

            try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    PrintUtil.printLine(line);
                }
            } catch (IOException e) {
                PrintUtil.printLine("Error reading the map file.");
                e.printStackTrace();
                throw e;
            }
        }
    }
}
