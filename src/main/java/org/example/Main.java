package org.example;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al conversor de monedas!");

        CurrencyConverter converter = new CurrencyConverter("7ca3898edf2d77e608d856ca");

        System.out.print("Ingrese la cantidad a convertir: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // Consumir la nueva línea después de nextDouble()

        System.out.print("Ingrese la moneda de origen (por ejemplo, USD): ");
        String fromCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Ingrese la moneda de destino (por ejemplo, EUR): ");
        String toCurrency = scanner.nextLine().toUpperCase();

        try {
            double convertedAmount = converter.convertCurrency(amount, fromCurrency, toCurrency);
            System.out.println(amount + " " + fromCurrency + " equivale a " + convertedAmount + " " + toCurrency);
        } catch (IOException e) {
            System.out.println("Error al realizar la conversión: " + e.getMessage());
        }
    }
}
