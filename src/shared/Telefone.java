import exceptions.TelefoneException;
import java.util.regex.Pattern;

public final class Telefone {
    private final String numero;
    private final boolean isCelular;

    public Telefone(String numero) throws TelefoneException {
        String numeroLimpo = limparNumero(numero);
        validate(numeroLimpo);
        this.numero = numeroLimpo;
        this.isCelular = determinarSeCelular(numeroLimpo);
    }

    private String limparNumero(String numero) {
        if (numero == null) return null;
        return numero.replaceAll("[^0-9]", "");
    }

    private void validate(String numero) throws TelefoneException {
        if (numero == null || numero.isBlank()) {
            throw new TelefoneException("Número de telefone não informado");
        }
        
        if (numero.length() < 10 || numero.length() > 11) {
            throw new TelefoneException("Número de telefone deve ter 10 ou 11 dígitos");
        }
        int ddd = Integer.parseInt(numero.substring(0, 2));
        if (ddd < 11 || ddd > 99) {
            throw new TelefoneException("DDD inválido");
        }

        // Valida primeiro dígito do número (fixo geralmente começa com 2-5, celular com 6-9)
        int primeiroDigitoNumero = Character.getNumericValue(numero.charAt(2));
        if (primeiroDigitoNumero < 2 || primeiroDigitoNumero > 9) {
            throw new TelefoneException("Número de telefone inválido");
        }
    }

    private boolean determinarSeCelular(String numero) {
        // Celulares geralmente têm 11 dígitos e começam com 9 após o DDD
        return numero.length() == 11 && numero.charAt(2) == '9';
    }

    public String getNumero() {
        return numero;
    }

    public boolean isCelular() {
        return isCelular;
    }

    public String getDDD() {
        return numero.substring(0, 2);
    }

    public String getNumeroFormatado() {
        if (isCelular) {
            return String.format("(%s) %s-%s", 
                numero.substring(0, 2),
                numero.substring(2, 7),
                numero.substring(7));
        } else {
            return String.format("(%s) %s-%s", 
                numero.substring(0, 2),
                numero.substring(2, 6),
                numero.substring(6));
        }
    }
    
    @Override
    public String toString() {
        return getNumeroFormatado();
    }
}