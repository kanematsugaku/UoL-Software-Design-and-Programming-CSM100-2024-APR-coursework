package com.example.game.services;

import org.springframework.stereotype.Service;
import com.example.game.util.PrintUtil;

@Service
public class MessageService {
    public void showWelcomeMessage() {
        PrintUtil.printImportantMessage("Welcome to the game!");
    }

    public void showClosingMessage() {
        PrintUtil.printImportantMessage("Thank you for playing!");
    }

    public void showExceptionMessage(Exception e) {
        PrintUtil.printImportantMessage("An error occurred...");
        PrintUtil.printLine(e.getMessage());
    }
}
