package com.br.pollingsystem.utils;

import com.br.pollingsystem.exceptions.InvalidCpfException;
import org.springframework.stereotype.Component;

@Component
public class Validate {

    public static void validarCPF(String cpf) {
        // Remove caracteres não numéricos do CPF
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF possui 11 dígitos
        if (cpf.length() != 11) {
            throw new InvalidCpfException("CPF deve conter 11 dígitos.");
        }

        // Calcula o primeiro dígito verificador
        int primeiroDigito = calcularDigitoVerificador(cpf, 9);

        // Calcula o segundo dígito verificador
        int segundoDigito = calcularDigitoVerificador(cpf, 10);

        // Verifica se os dígitos calculados são iguais aos dígitos fornecidos no CPF
        if (Character.getNumericValue(cpf.charAt(9)) != primeiroDigito
                || Character.getNumericValue(cpf.charAt(10)) != segundoDigito) {
            throw new InvalidCpfException("CPF inválido.");
        }
    }

// Restante do código permanece o mesmo...


    private static int calcularDigitoVerificador(String cpf, int posicao) {
        int soma = 0;
        int multiplicador = posicao + 1; // A multiplicação começa de posicao + 1

        // Soma ponderada dos dígitos
        for (int i = 0; i < posicao; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * multiplicador;
            multiplicador--;
        }

        // Calcula o dígito verificador
        int digito = 11 - (soma % 11);
        return (digito > 9) ? 0 : digito;
    }
}
