package dev.trajano.restaurante.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class CpfValidatorTest {
    private CpfValidator validator = new CpfValidator();
    @Test
    void deveAceitarCpfValido(){
        assertTrue(validator.isValid("529.982.247-25",null));
    }
    @Test
    void deveRejeitarCpfNulo() {
        assertFalse(validator.isValid(null, null));
    }

    @Test
    void deveRejeitarCpfComTamanhoErrado() {
        assertFalse(validator.isValid("123", null));
    }

    @Test
    void deveRejeitarCpfComDigitosRepetidos() {
        assertFalse(validator.isValid("111.111.111-11", null));
    }

    @Test
    void deveRejeitarCpfComDigitoVerificadorErrado() {
        assertFalse(validator.isValid("529.982.247-00", null));
    }
}
