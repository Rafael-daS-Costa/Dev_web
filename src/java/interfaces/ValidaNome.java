package interfaces;

public interface ValidaNome {
    default boolean nomeEhValido(String nome) {
        return nome.chars().allMatch(Character::isLetter);
    }
}
