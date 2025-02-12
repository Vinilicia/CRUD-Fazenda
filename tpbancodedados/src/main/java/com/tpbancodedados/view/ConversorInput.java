package com.tpbancodedados.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// Uma classe para converter a entrada de String para um tipo desejado, lançando uma exceção caso não seja
// Basicamente só uma forma de garantir que o usuário digite um número quando for pedido um ID, e não "Jorge"
public class ConversorInput {

    public static int converterStringParaInt(String valor) throws NumberFormatException {
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Erro: Não foi possível converter a String para inteiro.");
        }
    }

	public static LocalDate converterStringParaLocalDate(String valor) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        try {
            return LocalDate.parse(valor, formatter);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Erro: A String não está no formato de data válido (yyyy-MM-dd).", valor, e.getErrorIndex());
        }
    }

	public static Double converterStringParaDouble(String valor) throws NumberFormatException {
		try {
			return Double.parseDouble(valor);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Erro: A String não está no formato de número válido.");
		}
	}
	
}