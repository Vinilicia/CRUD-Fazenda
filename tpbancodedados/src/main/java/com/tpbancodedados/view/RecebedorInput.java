package com.tpbancodedados.view;

import java.time.LocalDate;
import java.util.Scanner;
import com.tpbancodedados.controller.ConversorInput;

public class RecebedorInput {
    private static Scanner scanner = new Scanner(System.in);

    public static <T> T receberInputValidado( Class<T> clazz) {
        String input;
        boolean valido = false;

        while (!valido) {
            System.out.print("Digite o valor: ");
            input = scanner.nextLine();

            try {
                if (clazz == Integer.class) {
                    return clazz.cast(ConversorInput.converterStringParaInt(input));
                } else if (clazz == Double.class) {
                    return clazz.cast(ConversorInput.converterStringParaDouble(input));
                } else if (clazz == LocalDate.class) {
                    return clazz.cast(ConversorInput.converterStringParaLocalDate(input));
                } else {
                    System.out.println("Tipo de entrada não reconhecido.");
                    return null;
                }
            } catch (NumberFormatException | java.time.format.DateTimeParseException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}
