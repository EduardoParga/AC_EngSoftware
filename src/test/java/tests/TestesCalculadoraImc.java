package tests;

import control.CalculadoraImc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestesCalculadoraImc {

    private CalculadoraImc calculadora;

    private final double ALTURA_PADRAO_H = 1.80;
    private final double ALTURA_PADRAO_M = 1.60;

    @BeforeEach
    void setUp() {
        calculadora = new CalculadoraImc();
    }

    @Nested
    @DisplayName("Testes de Limite para Adultos (20-65 anos)")
    class TestesLimiteAdulto {

        @Test
        @DisplayName("Limite: Baixo peso muito grave -> Baixo peso grave")
        void testeLimiteBaixoPesoMuitoGraveGrave() {

            assertEquals("Baixo peso muito grave", calculadora.calcularImc(51.81, ALTURA_PADRAO_H, 40, "masculino")); // IMC: 15.99
            assertEquals("Baixo peso grave", calculadora.calcularImc(51.84, ALTURA_PADRAO_H, 40, "masculino"));      // IMC: 16.00
        }

        @Test
        @DisplayName("Limite: Baixo peso grave -> Baixo peso")
        void testeLimiteBaixoPesoGraveNormal() {
            assertEquals("Baixo peso grave", calculadora.calcularImc(55.05, ALTURA_PADRAO_H, 40, "masculino")); // IMC: 16.99
            assertEquals("Baixo peso", calculadora.calcularImc(55.08, ALTURA_PADRAO_H, 40, "masculino"));      // IMC: 17.00
        }

        @Test
        @DisplayName("Limite: Baixo peso -> Peso normal")
        void testeLimiteBaixoPesoE䊂Normal() {
            assertEquals("Baixo peso", calculadora.calcularImc(59.91, ALTURA_PADRAO_H, 40, "masculino"));    // IMC: 18.49
            assertEquals("Peso normal", calculadora.calcularImc(59.94, ALTURA_PADRAO_H, 40, "masculino")); // IMC: 18.50
        }

        @Test
        @DisplayName("Limite: Peso normal -> Sobrepeso")
        void testeLimitePesoNormalSobrepeso() {
            assertEquals("Peso normal", calculadora.calcularImc(80.97, ALTURA_PADRAO_H, 40, "masculino"));  // IMC: 24.99
            assertEquals("Sobrepeso", calculadora.calcularImc(81.00, ALTURA_PADRAO_H, 40, "masculino"));    // IMC: 25.00
        }

        @Test
        @DisplayName("Limite: Sobrepeso -> Obesidade Grau I")
        void testeLimiteSobrepesoObesidadeI() {
            assertEquals("Sobrepeso", calculadora.calcularImc(97.17, ALTURA_PADRAO_H, 40, "masculino"));       // IMC: 29.99
            assertEquals("Obesidade grau I", calculadora.calcularImc(97.20, ALTURA_PADRAO_H, 40, "masculino"));// IMC: 30.00
        }

        @Test
        @DisplayName("Limite: Obesidade Grau I -> Obesidade Grau II")
        void testeLimiteObesidadeIeII() {
            // Limite é 35.00
            assertEquals("Obesidade grau I", calculadora.calcularImc(113.37, ALTURA_PADRAO_H, 40, "masculino")); // IMC: 34.99
            assertEquals("Obesidade grau II", calculadora.calcularImc(113.40, ALTURA_PADRAO_H, 40, "masculino"));// IMC: 35.00
        }

        @Test
        @DisplayName("Limite: Obesidade Grau II -> Obesidade Grau III")
        void testeLimiteObesidadeIIeIII() {
            // Limite é 40.00
            assertEquals("Obesidade grau II", calculadora.calcularImc(129.57, ALTURA_PADRAO_H, 40, "masculino"));                    // IMC: 39.99
            assertEquals("Obesidade grau III (obesidade mórbida)", calculadora.calcularImc(129.60, ALTURA_PADRAO_H, 40, "masculino")); // IMC: 40.00
        }
    }

    @Nested
    @DisplayName("Testes de Limite para Idosos Homens (> 65 anos)")
    class TestesLimiteIdosoHomem {

        @Test
        @DisplayName("Limite: Baixo peso -> Peso normal")
        void testeLimiteBaixoPesoNormal() {
            // Limite é 22.00
            assertEquals("Baixo peso", calculadora.calcularImc(71.25, ALTURA_PADRAO_H, 70, "masculino"));  // IMC: 21.99
            assertEquals("Peso normal", calculadora.calcularImc(71.28, ALTURA_PADRAO_H, 70, "masculino")); // IMC: 22.00
        }

        @Test
        @DisplayName("Limite: Peso normal -> Sobrepeso")
        void testeLimiteNormalSobrepeso() {
            // Limite é 27.01
            assertEquals("Peso normal", calculadora.calcularImc(87.48, ALTURA_PADRAO_H, 70, "masculino")); // IMC: 27.00
            assertEquals("Sobrepeso", calculadora.calcularImc(87.51, ALTURA_PADRAO_H, 70, "masculino"));   // IMC: 27.01
        }

        @Test
        @DisplayName("Limite: Sobrepeso -> Obesidade Grau I")
        void testeLimiteSobrepesoObesidadeI() {
            // Limite é 30.01
            assertEquals("Sobrepeso", calculadora.calcularImc(97.20, ALTURA_PADRAO_H, 70, "masculino"));       // IMC: 30.00
            assertEquals("Obesidade grau I", calculadora.calcularImc(97.23, ALTURA_PADRAO_H, 70, "masculino")); // IMC: 30.01
        }
    }

    @Nested
    @DisplayName("Testes de Limite para Idosas Mulheres (> 65 anos)")
    class TestesLimiteIdosaMulher {

        @Test
        @DisplayName("Limite: Baixo peso -> Peso normal")
        void testeLimiteBaixoPesoNormal() {
            // Limite é 22.00
            assertEquals("Baixo peso", calculadora.calcularImc(56.29, ALTURA_PADRAO_M, 70, "feminino"));  // IMC: 21.99
            assertEquals("Peso normal", calculadora.calcularImc(56.32, ALTURA_PADRAO_M, 70, "feminino")); // IMC: 22.00
        }

        @Test
        @DisplayName("Limite: Peso normal -> Sobrepeso")
        void testeLimiteNormalSobrepeso() {
            // Limite é 27.01
            assertEquals("Peso normal", calculadora.calcularImc(69.12, ALTURA_PADRAO_M, 70, "feminino")); // IMC: 27.00
            assertEquals("Sobrepeso", calculadora.calcularImc(69.15, ALTURA_PADRAO_M, 70, "feminino"));   // IMC: 27.01
        }

        @Test
        @DisplayName("Limite: Sobrepeso -> Obesidade Grau I")
        void testeLimiteSobrepesoObesidadeI() {
            // Limite é 32.01
            assertEquals("Sobrepeso", calculadora.calcularImc(81.92, ALTURA_PADRAO_M, 70, "feminino"));       // IMC: 32.00
            assertEquals("Obesidade grau I", calculadora.calcularImc(81.95, ALTURA_PADRAO_M, 70, "feminino")); // IMC: 32.01
        }

        @Test
        @DisplayName("Limite: Obesidade Grau I -> Obesidade Grau II")
        void testeLimiteObesidadeIeII() {
            // Limite é 37.01
            assertEquals("Obesidade grau I", calculadora.calcularImc(94.72, ALTURA_PADRAO_M, 70, "feminino"));   // IMC: 37.00
            assertEquals("Obesidade grau II", calculadora.calcularImc(94.75, ALTURA_PADRAO_M, 70, "feminino")); // IMC: 37.01
        }

        @Test
        @DisplayName("Limite: Obesidade Grau II -> Obesidade Grau III")
        void testeLimiteObesidadeIIeIII() {
            // Limite é 42.00
            assertEquals("Obesidade grau II", calculadora.calcularImc(107.49, ALTURA_PADRAO_M, 70, "feminino"));                  // IMC: 41.99
            assertEquals("Obesidade grau III (obesidade mórbida)", calculadora.calcularImc(107.52, ALTURA_PADRAO_M, 70, "feminino")); // IMC: 42.00
        }
    }

    @Nested
    @DisplayName("Testes de Validação e Entradas Inválidas")
    class TestesDeValidacao {
        // Estes testes já validam limites de dados e permanecem os mesmos.
        @Test
        void deveRetornarMensagemCorretaParaMenorDe20Anos() {
            String esperado = "Para menores de 20 anos, o IMC é avaliado por curvas de percentil de idade e sexo. Este cálculo não se aplica.";
            assertEquals(esperado, calculadora.calcularImc(40, 1.50, 19, "feminino"));
        }

        @Test
        void deveRetornarErroParaEntradasInvalidas() {
            String esperado = "Erro: Altura e peso devem ser valores positivos.";
            assertEquals(esperado, calculadora.calcularImc(0, 1.75, 30, "masculino"));
            assertEquals(esperado, calculadora.calcularImc(70, -1.75, 30, "masculino"));
        }

        @Test
        void deveRetornarErroParaSexoInvalidoEmIdosos() {
            String esperado = "Erro: Sexo inválido. Para idosos, o sexo ('masculino' ou 'feminino') é necessário.";
            assertEquals(esperado, calculadora.calcularImc(80, 1.75, 70, "nao_informado"));
        }
    }
}