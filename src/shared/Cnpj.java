import exceptions.CnpjException;

public class Cnpj {
    private final String value;
    
    public Cnpj(String value) throws CnpjException {
        this.validate(value);
        this.value = value.replaceAll("[^0-9]", ""); // Remove formatação
    }
    
    private void validate(String value) throws CnpjException {
        if(value == null || value.isBlank()) {
            throw new CnpjException("CNPJ não declarado");
        }
        
        String cnpj = value.replaceAll("[^0-9]", "");
        
        if (cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) {
            throw new CnpjException("CNPJ deve ter 14 dígitos numéricos");
        }
        
        int soma = 0;
        int peso = 5;
        for (int i = 0; i < 12; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * peso;
            peso = (peso == 2) ? 9 : peso - 1;
        }
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito > 9) {
            primeiroDigito = 0;
        }
        
        soma = 0;
        peso = 6;
        for (int i = 0; i < 13; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * peso;
            peso = (peso == 2) ? 9 : peso - 1;
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito > 9) {
            segundoDigito = 0;
        }
        
        if (Character.getNumericValue(cnpj.charAt(12)) != primeiroDigito ||
            Character.getNumericValue(cnpj.charAt(13)) != segundoDigito) {
            throw new CnpjException("Dígitos verificadores do CNPJ inválidos");
        }
    }
    
    public String getValue() {
        return value;
    }
    
    public String getValueFormatado() {
        return value.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
    }
    
    @Override
    public String toString() {
        return getFormatted();
    }
}