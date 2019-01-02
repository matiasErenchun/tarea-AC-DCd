import java.util.ArrayList;

public class ContenedorAux
{
    private ArrayList<String> rom;
    private String[] ram;
    private int A;
    private int D;
    private int PC;

    public ContenedorAux() {
        this.rom = new ArrayList<String>();
        this.ram = new  String[100] ;
        this.A = 0;
        this.D = 0;
        this.PC = 0;
        this.cargarRam();
    }

    public void cargarRam()
    {
        int i=0;
        while(i<this.ram.length)
        {
            this.ram[i]="0";
            i++;
        }
    }



    public void setA(int a) {
        A = a;
    }



    public void setD(int d) {
        D = d;
    }



    public void setPC(int PC) {
        this.PC = PC;
    }

    public String getA()
    {
        Integer intA=this.A;
        return intA.toString();
    }

    public String getD()
    {
        Integer intD=this.D;
        return intD.toString();
    }

    public String getStringPC()
    {
        Integer intPC=this.PC;
        return intPC.toString();
    }

    public int getPC()
    {
        return this.PC;
    }

    public int getRomSize()
    {
        return this.rom.size();
    }

    public ContenedorLinea [] getRam()
    {
        ContenedorLinea [] stringRam = new ContenedorLinea[this.ram.length];
        Integer i=0;
        for (String instruccion:this.ram)
        {
            ContenedorLinea contenedor = new ContenedorLinea(i,instruccion);
            stringRam[i]=contenedor;
            i++;

        }
        return stringRam;
    }

    public void setRamIndex(int index,String contenido)
    {
        this.ram [index] = contenido;
    }

    public void refresh(int a,int d, int pc)
    {
        this.setA(a);
        this.setD(d);
        this.setPC(pc);
    }
    public void resetAll()
    {
        this.A=0;
        this.D=0;
        this.PC=0;
        this.cargarRam();
    }
}
