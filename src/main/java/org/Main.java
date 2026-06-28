package org;

public class Main {
    public static void main(String[] args) {
       //Diesen code hat einen ERROR, möchte CI/DC yml datei testen
        int zahl = "Hallo";
        System.out.println(zahl);
       //ende der ci/CD error code


        TicTacToe game = new TicTacToe();
        game.start();
    }
}
