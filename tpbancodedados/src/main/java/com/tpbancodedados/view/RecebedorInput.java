package com.tpbancodedados.view;

import java.time.LocalDate;
import java.util.Scanner;


// Só possui uma função que basicamente lê um input de tipo específico ( no caso só fizemos para int, double 
// e LocalDate )
// Ela não sai do loop enquanto não receber o formato esperado, então evita que um erro de digitação do usuário
// o faça ter que repetir algum passo que ele tinha feito antes

// vide o Conversor para entender melhor, se necessário
public class RecebedorInput {
    private static Scanner scanner = new Scanner(System.in);

    public static <T> T receberInputValidado( Class<T> clazz) {
        String input;
        boolean valido = false;

        while (!valido) {
            System.out.print(": ");
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
                System.out.print("Digite o valor: ");
            }
        }
        return null;
    }
}
