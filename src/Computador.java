import java.io.*;
import java.util.ArrayList;

public class Computador
{
    private ArrayList<String> rom;
    private ArrayList<String> ram;
    Double A;
    String D;
    int PC;
    private File file;
    private BufferedReader bf;

    public Computador(String file) throws IOException {
        this.file= new File(file);
        this.bf=new BufferedReader(new FileReader(file));
        this.rom = new ArrayList<>();
        this.ram = new ArrayList<>();
        A = new Double(0);
        D = "0";
        this.PC = 0;
        this.cargarRom();
        this.ejecutarPrograma();
    }

    public void cargarRom() throws IOException {
        String linea=this.bf.readLine();

        while(linea!=null)
        {
            this.rom.add(linea);
            linea=this.bf.readLine();

        }
    }

    public void ejecutarPrograma()
    {
        String instruccion = this.rom.get(this.PC);
        this.PC++;
        String tipoDeInstruccion=instruccion.substring(0,3);
        System.out.println(tipoDeInstruccion);
        if(tipoDeInstruccion.equalsIgnoreCase("111"))
        {
            System.out.println("hola");
        }
        else
        {
            this.A=this.binariToDouble(instruccion);
            System.out.println("000 "+A);
        }

    }


    public double binariToDouble(String instruccion)
    {
        double resultado=0;
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

    public void instruccionesTipoC(String instruccion)
    {
        double ficheroA;
        String CodigoInstruccion=instruccion.substring(4,10);
        if(instruccion.charAt(3)=='1')
        {
            //instrucciones del tipo Ram[A]
            int a =Integer.parseInt(this.A.toString());
            ficheroA=Integer.parseInt(this.ram.get(a));
            switch (CodigoInstruccion)
            {
                case "110000"://M
                {
                    break;
                }
                case"110001"://!M
                {
                    break;
                }
                case "110011"://-M
                {
                    break;
                }
                case "110111"://M+1
                {
                    break;
                }
                case "110010" ://M-1
                {
                    break;
                }
                case "000010"://D+M
                {
                    break;
                }
                case "010011"://D-M
                {
                    break;
                }
                case "000111"://M-D
                {
                    break;
                }
                case "000000"://D&M
                {
                    break;
                }
                case "010101"://D|M
                {
                    break;
                }

                default:
                {
                    System.out.println("error");
                }
            }

        }
        else
        {
            ficheroA=this.A;
            switch (CodigoInstruccion)
            {
                case "110000"://A
                {
                    break;
                }
                case "001101"://!D
                {
                    break;
                }
                case"110001"://!A
                {
                    break;
                }
                case "001111" ://-D
                {
                    break;
                }
                case "110011"://-A
                {
                    break;
                }
                case "011111"://D+1
                {
                    break;
                }
                case "110111"://A+1
                {
                    break;
                }
                case "001110"://D-1
                {
                    break;
                }
                case "110010" ://A-1
                {
                    break;
                }
                case "000010"://D+A
                {
                    break;
                }
                case "010011"://D-A
                {
                    break;
                }
                case "000111"://A-D
                {
                    break;
                }
                case "000000"://D&A
                {
                    break;
                }
                case "010101"://D|A
                {
                    break;
                }

                default:
                {
                    System.out.println("error");
                }
            }
        }
    }



}
