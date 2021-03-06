import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Computador
{
    private ArrayList<String> rom;
    private String[] ram;
    private int A;
    private int D;
    private int PC;
    private File file;
    private BufferedReader bf;
    private ContenedorAux elPc;
    private Pantalla pantalla;

    public Computador(String file, ContenedorAux elPc) throws IOException {
        this.pantalla = null;
        this.elPc =elPc;
        this.file= new File(file);
        this.bf=new BufferedReader(new FileReader(file));
        this.rom = new ArrayList<>();
        this.ram = new String[100];
        this.A = 0;
        this.D = 0;
        this.PC = 0;
        this.cargarRom();
        this.cargarRam();
    }

    public void cargarRom() throws IOException {
        String linea=this.bf.readLine();

        while(linea!=null)
        {
            this.rom.add(linea);
            linea=this.bf.readLine();

        }
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

    public void printRam()
    {
        System.out.println("estado de la Ram");
        for (String s:this.ram)
        {
            System.out.println(s);
        }
    }
    public void printRom()
    {
        for (String s:this.rom)
        {
            System.out.println(s);
        }
    }


    public void ejecutarPrograma()
    {
        while(this.PC<this.rom.size())
        {

            this.ejecutarInstruccion();
        }
    }
    public void ejecutarInstruccion()
    {
        String instruccion = this.rom.get(this.PC);
        String tipoDeInstruccion=instruccion.substring(0,3);
        String guardarADM=instruccion.substring(10,13);
        if(tipoDeInstruccion.equalsIgnoreCase("111"))
        {
            System.out.println("instruccion del tipo Ram[A]");
            String answer=this.instruccionesTipoC(instruccion);
            System.out.println("respuesta de la instruccion "+answer);
            this.guardarResultado(guardarADM,answer);
            this.saltos(instruccion,answer);
        }
        else
        {
            this.A=this.binariToDouble(instruccion);
            this.PC++;
        }
        this.elPc.refresh(this.A,this.D,this.PC);
        System.out.println("valor de A:"+A);
        System.out.println("valor de D:"+D);
        System.out.println("valor de M["+this.A+"] = "+this.ram[A]);

    }


    public int binariToDouble(String instruccion)
    {
        int  resultado=0;
        int i=0;
        while(i<instruccion.length())
        {
            if(instruccion.charAt(i)=='1')
            {
                double a=(instruccion.length()-1)-i;
                resultado+=Math.pow(2,a);
            }
            i++;
        }

        return resultado;
    }

    public String instruccionesTipoC(String instruccion)
    {
        String CodigoInstruccion=instruccion.substring(4,10);
        String answer;
        Integer aux;
        if(instruccion.charAt(3)=='1')
        {
            System.out.println("instrucciones del tipo Ram[A]=>M");
            //instrucciones del tipo Ram[A]=>M
            switch (CodigoInstruccion)
            {
                case "110000"://M
                {
                    answer = this.getRamIndex(this.A);
                    break;
                }
                case"110001"://!M
                {
                    aux =Integer.parseInt(this.getRamIndex(this.A));
                    aux=~aux;
                    answer =aux.toString();
                    break;
                }
                case "110011"://-M
                {
                    aux =Integer.parseInt(this.getRamIndex(this.A));
                    aux=aux*-1;
                    answer =aux.toString();
                    break;
                }
                case "110111"://M+1
                {
                    aux =Integer.parseInt(this.getRamIndex(this.A));
                    aux++;
                    answer =aux.toString();
                    break;
                }
                case "110010" ://M-1
                {
                    aux =Integer.parseInt(this.getRamIndex(this.A));
                    aux--;
                    answer =aux.toString();
                    break;
                }
                case "000010"://D+M
                {
                    aux =Integer.parseInt(this.getRamIndex(this.A));
                    aux=this.D+aux;
                    answer =aux.toString();
                    break;
                }
                case "010011"://D-M
                {
                    aux =Integer.parseInt(this.getRamIndex(this.A));
                    aux=this.D-aux;
                    answer =aux.toString();
                    break;
                }
                case "000111"://M-D
                {
                    aux =Integer.parseInt(this.getRamIndex(this.A));
                    aux=aux-this.D;
                    answer =aux.toString();
                    break;
                }
                case "000000"://D&M
                {
                    aux =Integer.parseInt(this.getRamIndex(this.A));
                    aux=aux&this.D;
                    answer =aux.toString();
                    break;
                }
                case "010101"://D|M
                {
                    aux =Integer.parseInt(this.getRamIndex(this.A));
                    aux=aux|this.D;
                    answer =aux.toString();
                    break;
                }

                default:
                {
                    answer="AAAAAA";
                    System.out.println("error");
                }
            }

        }
        else
        {
            System.out.println("instrucciones del tipo A");
            //instrucciones del tipo A
            switch (CodigoInstruccion)
            {
                case "101010"://0
                {
                    answer="0";
                    break;
                }
                case "111111"://1
                {
                    answer="1";
                    break;
                }
                case "111010"://-1
                {
                    answer="-1";
                    break;
                }
                case "001100"://d
                {
                    aux=this.D;
                    answer=aux.toString();
                    break;
                }
                case "110000"://A
                {
                    aux=this.A;
                    answer=aux.toString();
                    break;
                }
                case "001101"://!D
                {
                    aux=~this.D;
                    answer=aux.toString();
                    break;
                }
                case"110001"://!A
                {
                    aux=~this.A;
                    answer=aux.toString();
                    break;
                }
                case "001111" ://-D
                {
                    aux=this.D*-1;
                    answer=aux.toString();
                    break;
                }
                case "110011"://-A
                {
                    aux=this.A*-1;
                    answer=aux.toString();
                    break;
                }
                case "011111"://D+1
                {
                    aux=this.D+1;
                    answer=aux.toString();
                    break;
                }
                case "110111"://A+1
                {
                    aux=this.A+1;
                    answer=aux.toString();
                    break;
                }
                case "001110"://D-1
                {
                    aux=this.D-1;
                    answer=aux.toString();
                    break;
                }
                case "110010" ://A-1
                {
                    aux=this.A-1;
                    answer=aux.toString();
                    break;
                }
                case "000010"://D+A
                {
                    aux=this.D+this.A;
                    answer=aux.toString();
                    break;
                }
                case "010011"://D-A
                {
                    aux=this.D-this.A;
                    answer=aux.toString();
                    break;
                }
                case "000111"://A-D
                {
                    aux=this.A-this.D;
                    answer=aux.toString();
                    break;
                }
                case "000000"://D&A
                {
                    aux=this.D&this.A;
                    answer=aux.toString();
                    break;
                }
                case "010101"://D|A
                {
                    aux=this.D|this.A;
                    answer=aux.toString();
                    break;
                }

                default:
                {
                    answer="AAAAA";
                    System.out.println("error");
                }
            }
        }

        return answer;
    }

    public void guardarResultado(String ADM,String answer)
    {
        if(ADM.charAt(0)=='1')//guardar en A
        {
            this.A=Integer.parseInt(answer);
        }
        if(ADM.charAt(1)=='1')//guardar en D
        {
            this.D=Integer.parseInt(answer);
        }
        if(ADM.charAt(2)=='1')//guardar en Ram[A]
        {
            this.ram[this.A]=answer;
            this.elPc.setRamIndex(this.A,answer);
        }
}

    public void saltos(String instruccion, String answer) {
        String saltos = instruccion.substring(13, 16);
        int intAnswer = Integer.parseInt(answer);

        //out>0 jump to A
        if (saltos.equals("001") && intAnswer > 0)
        {
            this.PC=this.A;
        }
        else
        {
            //out==0 jump to A
            if(saltos.equals("010") && intAnswer == 0)
            {
                this.PC=this.A;
            }
            else
            {
                //out>=0 jump to A
                if(saltos.equals("011")&&intAnswer>=0)
                {
                    this.PC=this.A;
                }
                else
                {
                    //out<0 jump to A
                    if(saltos.equals("100")&&intAnswer<0)
                    {
                        this.PC=this.A;
                    }
                    else
                    {
                        //out!=0 jump to A
                        if(saltos.equals("101")&& intAnswer!=0)
                        {
                            this.PC=this.A;
                        }
                        else
                        {
                            //out<=0 jump to A
                            if(saltos.equals("110")&& intAnswer<=0)
                            {
                                this.PC=this.A;
                            }
                            else
                            {
                                this.PC++;
                            }
                        }
                    }
                }
            }
        }


    }

    public String getRamIndex(int index)
    {
       return this.ram[index];
    }

    public ContenedorLinea [] getRom()
    {
        ContenedorLinea [] stringRom = new ContenedorLinea[this.rom.size()];
        Integer i=0;
        for (String instruccion:this.rom)
        {
            ContenedorLinea contenedor = new ContenedorLinea(i,instruccion);
            stringRom[i]=contenedor;
            i++;

        }
        return stringRom;
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

    public void esperar(int tiempo)
    {
        try{

            Thread.sleep(tiempo *100);

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void setPantalla(Pantalla pantalla)
    {
        this.pantalla = pantalla;
    }

    public void resetPC()
    {
        this.PC=0;
    }

    public void resetAll()
    {
        this.A=0;
        this.D=0;
        this.PC=0;
        this.cargarRam();
    }


}
