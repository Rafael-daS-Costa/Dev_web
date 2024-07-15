package interfaces;

public interface ValidaDados {
    default boolean nomeEhValido(String nome) {
        return !nome.chars().anyMatch(Character::isDigit);
    }

    default Boolean ufEhValida(String uf) {
        String[] UFsValidas = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA",  "MT", "MS", "MG", "PA","PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};
        boolean ufEncontrada = false;
        for (String ufDaLista : UFsValidas) {
            if (ufDaLista.equals(uf)) {
                ufEncontrada = true;
            }
        }
        return ufEncontrada;
    }
}
