package com.example.game.services.interfaces;

public interface MessageService {
    void showWelcomeMessage();

    void showClosingMessage();

    void showExceptionMessage(Exception e);
}
