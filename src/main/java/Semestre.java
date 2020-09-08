public class Semestre {
    public Semestre() {

    }

    private int semestre;
    private String periodo;
    private Ciclos ciclos;

    public Ciclos getCiclos() {
        return ciclos;
    }

    public void setCiclos(Ciclos ciclos) {
        this.ciclos = ciclos;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
