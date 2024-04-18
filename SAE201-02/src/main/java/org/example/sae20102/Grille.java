public class Grille {
    private int ligne;
    private int colonne;
    private int cellule;
    public Grille(int ligne, int colonne, int cellule){
        this.ligne = ligne;
        this.colonne = colonne;
        this.cellule = cellule;
    }

    public int[][][] creerGrille(){
        int[][][] grille = new int[this.ligne][this.colonne][this.cellule];
        for (int i = 0; i < this.ligne; i++) {
            for (int j = 0; j < this.colonne; j++) {
                for (int k = 0; k < this.cellule; k++){
                    grille[i][j][k] = 0;
                }
            }
        }
        return grille;
    }
}