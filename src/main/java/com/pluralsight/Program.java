package com.pluralsight;

import com.pluralsight.ui.UserInterface;


// Creates the UserInterface and starts the app
public class Program {
    public static void main(String[] args) {
        // Create the UI and launch the home screen
        UserInterface ui = new UserInterface();
        ui.display();
    }
}