package control;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculadoraImc {

    /**
     * Calcula o Índice de Massa Corporal (IMC) e retorna a classificação exata
     * de acordo com as regras da OMS para adultos e idosos.
     *
     * @param peso   O peso da pessoa em quilogramas.
     * @param altura A altura da pessoa em metros.
     * @param idade  A idade da pessoa em anos.
     * @param sexo   O sexo da pessoa ("masculino" ou "feminino").
     * @return Uma String com a classificação precisa do IMC.
     */
    public String calcularImc(double peso, double altura, int idade, String sexo) {

        if (altura <= 0 || peso <= 0) {
            return "Erro: Altura e peso devem ser valores positivos.";
        }

        BigDecimal imc = BigDecimal.valueOf(peso)
                .divide(BigDecimal.valueOf(altura * altura), 2, RoundingMode.HALF_UP);

        if (idade < 20) {
            return "Para menores de 20 anos, o IMC é avaliado por curvas de percentil de idade e sexo. Este cálculo não se aplica.";
        } else if (idade >= 20 && idade <= 65) {
            return classificarAdulto(imc);
        } else { // Idade > 65
            if ("masculino".equalsIgnoreCase(sexo)) {
                return classificarIdosoHomem(imc);
            } else if ("feminino".equalsIgnoreCase(sexo)) {
                return classificarIdosaMulher(imc);
            } else {
                return "Erro: Sexo inválido. Para idosos, o sexo ('masculino' ou 'feminino') é necessário.";
            }
        }
    }

    private String classificarAdulto(BigDecimal imc) {
        if (imc.compareTo(new BigDecimal("16.00")) < 0) {
            return "Baixo peso muito grave";
        } else if (imc.compareTo(new BigDecimal("17.00")) < 0) { // 16.00 a 16.99
            return "Baixo peso grave";
        } else if (imc.compareTo(new BigDecimal("18.50")) < 0) { // 17.00 a 18.49
            return "Baixo peso";
        } else if (imc.compareTo(new BigDecimal("25.00")) < 0) { // 18.50 a 24.99
            return "Peso normal";
        } else if (imc.compareTo(new BigDecimal("30.00")) < 0) { // 25.00 a 29.99
            return "Sobrepeso";
        } else if (imc.compareTo(new BigDecimal("35.00")) < 0) { // 30.00 a 34.99
            return "Obesidade grau I";
        } else if (imc.compareTo(new BigDecimal("40.00")) < 0) { // 35.00 a 39.99
            return "Obesidade grau II";
        } else { // >= 40.00
            return "Obesidade grau III (obesidade mórbida)";
        }
    }

    private String classificarIdosoHomem(BigDecimal imc) {
        if (imc.compareTo(new BigDecimal("22.00")) < 0) { // Abaixo de 21.9
            return "Baixo peso";
        } else if (imc.compareTo(new BigDecimal("27.01")) < 0) { // 22 a 27
            return "Peso normal";
        } else if (imc.compareTo(new BigDecimal("30.01")) < 0) { // 27.1 a 30
            return "Sobrepeso";
        } else if (imc.compareTo(new BigDecimal("35.01")) < 0) { // 30.1 a 35
            return "Obesidade grau I";
        } else if (imc.compareTo(new BigDecimal("40.00")) < 0) { // 35.1 a 39.9
            return "Obesidade grau II";
        } else { // Maior que 40
            return "Obesidade grau III (obesidade mórbida)";
        }
    }

    private String classificarIdosaMulher(BigDecimal imc) {
        if (imc.compareTo(new BigDecimal("22.00")) < 0) { // Abaixo de 21.9
            return "Baixo peso";
        } else if (imc.compareTo(new BigDecimal("27.01")) < 0) { // 22 a 27
            return "Peso normal";
        } else if (imc.compareTo(new BigDecimal("32.01")) < 0) { // 27.1 a 32
            return "Sobrepeso";
        } else if (imc.compareTo(new BigDecimal("37.01")) < 0) { // 32.1 a 37
            return "Obesidade grau I";
        } else if (imc.compareTo(new BigDecimal("42.00")) < 0) { // 37.1 a 41.9
            return "Obesidade grau II";
        } else { // Maior que 42
            return "Obesidade grau III (obesidade mórbida)";
        }
    }
}