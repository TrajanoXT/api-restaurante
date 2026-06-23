package dev.trajano.restaurante.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<Cpf,String> {

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context){
        if (cpf==null || cpf.isBlank())return false;
        String cleanCpf = cpf.replaceAll("\\D","");
        if (cleanCpf.length()!=11||cleanCpf.matches("(\\d)\\1{10}"))return false;
        try {
            int[] d = new int[11];
            for (int i = 0; i < 11; i++) d[i] = Character.getNumericValue(cleanCpf.charAt(i));

            int v1 = 0, v2 = 0;
            for (int i = 0; i < 9; i++) v1 += d[i] * (10 - i);
            v1 = (v1 % 11 < 2) ? 0 : 11 - (v1 % 11);

            for (int i = 0; i < 10; i++) v2 += d[i] * (11 - i);
            v2 = (v2 % 11 < 2) ? 0 : 11 - (v2 % 11);

            return (d[9] == v1 && d[10] == v2);
        } catch (Exception e) {
            return false;
        }
    }
}
