public class Entrepot {
    private int quantite;
    private TypeM minerai;

    public Entrepot(TypeM minerai) {
        this.quantite = 0;
        this.minerai = minerai;
    }

    public void remplir(int quantite) {
        this.quantite += quantite;
    }

    public int getQuantite() {
        return quantite;
    }

    public TypeM getNature() {
        return minerai;
    }
}