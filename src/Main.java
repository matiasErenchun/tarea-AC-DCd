import java.io.IOException;

public class Main {
    private static Computador miPc;

    public static void main(String[] args) throws IOException {
        //String file=args[1];
        String file ="D:/repo git local 2/tarea-AC-DCd/instrucciones.txt";
        miPc=new Computador(file);

    }
}
