package aranoua.javaweb.aranouajavawebservlet.model;
public class Produto {
    private int numeroDeTombo;
    private String nomeDoProduto;
    private String marca;
    private String modelo;

    public Produto(){
    }

    public Produto(int numeroDeTombo, String nomeDoProduto, String marca, String modelo) {
        this.numeroDeTombo = numeroDeTombo;
        this.nomeDoProduto = nomeDoProduto;
        this.marca = marca;
        this.modelo = modelo;
    }

    public int getNumeroDeTombo() {
        return numeroDeTombo;
    }

    public void setNumeroDeTombo(int numeroDeTombo) {
        this.numeroDeTombo = numeroDeTombo;
    }

    public String getNomeDoProduto() {
        return nomeDoProduto;
    }

    public void setNomeDoProduto(String nomeDoProduto) {
        this.nomeDoProduto = nomeDoProduto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "numeroDeTombo=" + numeroDeTombo +
                ", nomeDoProduto='" + nomeDoProduto + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                '}';
    }
}