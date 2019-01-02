public class ContenedorLinea
{
    private Integer indice;
    private String instruccion;

    public ContenedorLinea(Integer indice, String instruccion) {
        this.indice = indice;
        this.instruccion = instruccion;
    }

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public String getInstruccion() {
        return this.instruccion;
    }

    public void setInstruccion(String instruccion) {
        this.instruccion = instruccion;
    }
}
