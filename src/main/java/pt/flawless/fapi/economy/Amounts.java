package pt.flawless.fapi.economy;

import java.math.BigDecimal;
import java.util.OptionalLong;
import java.util.regex.Pattern;

/** Leitura e formatação de montantes em cêntimos, com sufixos k (mil), M (milhão) e B (bilião). */
public final class Amounts {

    // número com vírgula ou ponto decimal e sufixo opcional; sem sinal nem separador de milhares
    private static final Pattern AMOUNT = Pattern.compile("([0-9]+(?:[.,][0-9]+)?)([kKmMbB]?)");

    private static final long K = 100_000L;          // 1k€ em cêntimos
    private static final long M = 100_000_000L;      // 1M€
    private static final long B = 100_000_000_000L;  // 1B€

    private Amounts() {
    }

    /**
     * Lê um montante ({@code 12}, {@code 12,5}, {@code 1k}, {@code 1,5M}) e devolve-o em cêntimos.
     *
     * @return vazio se for nulo, inválido, tiver frações de cêntimo ou não couber num {@code long}; aceita 0
     */
    public static OptionalLong parse(String text) {
        java.util.regex.Matcher m = text == null ? null : AMOUNT.matcher(text);
        if (m == null || !m.matches()) {
            return OptionalLong.empty();
        }
        long unit = switch (m.group(2).toLowerCase()) {
            case "k" -> 1_000L;
            case "m" -> 1_000_000L;
            case "b" -> 1_000_000_000L;
            default -> 1L;
        };
        try {
            return OptionalLong.of(new BigDecimal(m.group(1).replace(',', '.'))
                    .multiply(BigDecimal.valueOf(unit)).movePointRight(2).longValueExact());
        } catch (ArithmeticException fractionOrOverflow) {
            return OptionalLong.empty();
        }
    }

    /** Formata cêntimos: 1250 -> "12,50€"; a partir de 1000€ abrevia (truncando): 1250000 -> "12,5k€". */
    public static String format(long cents) {
        if (cents < 0) {
            throw new IllegalArgumentException("montante negativo: " + cents);
        }
        if (cents < K) {
            long rest = cents % 100;
            return cents / 100 + "," + (rest < 10 ? "0" : "") + rest + "€";
        }
        long div = cents >= B ? B : cents >= M ? M : K;
        String suffix = div == B ? "B" : div == M ? "M" : "k";
        long frac = (cents % div) * 100 / div;
        String decimals = frac == 0 ? "" : frac % 10 == 0 ? "," + frac / 10 : "," + (frac < 10 ? "0" : "") + frac;
        return cents / div + decimals + suffix + "€";
    }
}
