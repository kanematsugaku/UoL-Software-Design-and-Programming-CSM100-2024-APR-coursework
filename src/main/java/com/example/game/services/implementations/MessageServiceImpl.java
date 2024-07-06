package com.example.game.services.implementations;

import org.springframework.stereotype.Service;
import com.example.game.services.interfaces.MessageService;
import com.example.game.util.PrintUtil;

/**
 * The service for for showing messages.
 */
@Service
public class MessageServiceImpl implements MessageService {
    /**
     * Show messages for startup
     */
    public void showWelcomeMessage() {
        PrintUtil.printImportantMessage("Welcome to the game!");
    }

    /**
     * Show messages for closing
     */
    public void showClosingMessage() {
        PrintUtil.printImportantMessage("Thank you for playing!");
    }

    /**
     * Show messages for exceptions
     *
     * @param e Exception
     */
    public void showExceptionMessage(Exception e) {
        PrintUtil.printImportantMessage("An error occurred...");
        PrintUtil.printLine(e.getMessage());
    }
}
