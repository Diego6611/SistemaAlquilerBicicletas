public class Bicicleta {
    private int id;
    private boolean disponible;
    private boolean electrica;
    private int carga;

    public Bicicleta(int id, boolean disponible, boolean electrica) {
        this.id = id;
        this.disponible = disponible;
        this.electrica = electrica;
        this.carga = electrica ? 100 : -1;
    }

    public int getId() {
        return id;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public boolean isElectrica() {
        return electrica;
    }

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        this.carga = Math.max(0, Math.min(100, carga));
    }
}
