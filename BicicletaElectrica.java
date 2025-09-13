public class BicicletaElectrica extends Bicicleta {
    private int nivelBateria;

    public BicicletaElectrica(int id, boolean disponible, int nivelBateria) {
        super(id, disponible, true); 
        this.nivelBateria = nivelBateria;
    }

    public int getNivelBateria() {
        return nivelBateria;
    }

    public void setNivelBateria(int nivelBateria) {
        this.nivelBateria = nivelBateria;
    }
}
