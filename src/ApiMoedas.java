public class ApiMoedas {
    private String moedaASerConvertida;
    private int valorDaMoedaASerConvertida;
    private String moedaConvertida;
    private Exchangerate exchangerate;

    public ApiMoedas(Exchangerate exchangerate) {
        this.exchangerate = exchangerate;
    }

    public String getMoedaASerConvertida() {
        return moedaASerConvertida;
    }

    public void setMoedaASerConvertida(String moedaASerConvertida) {
        this.moedaASerConvertida = moedaASerConvertida;
    }

    public int getValorDaMoedaASerConvertida() {
        return valorDaMoedaASerConvertida;
    }

    public void setValorDaMoedaASerConvertida(int valorDaMoedaASerConvertida) {
        this.valorDaMoedaASerConvertida = valorDaMoedaASerConvertida;
    }

    public String getMoedaConvertida() {
        return moedaConvertida;
    }

    public void setMoedaConvertida(String moedaConvertida) {
        this.moedaConvertida = moedaConvertida;
    }

    public double calcularValorConvertido() {
        double taxaDeCambio = exchangerate.getTaxaDeCambio(moedaConvertida);
        return valorDaMoedaASerConvertida * taxaDeCambio;
    }

    @Override
    public String toString() {
        return "Você escolheu a moeda " + this.moedaASerConvertida + ", convertendo para a moeda "
                + this.moedaConvertida + ", o resultado ficará: " + this.calcularValorConvertido();
    }
}