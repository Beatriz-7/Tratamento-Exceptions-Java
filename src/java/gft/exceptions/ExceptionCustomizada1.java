package java.gft.exceptions;

import javax.swing.*;
import java.io.*;

public class ExceptionCustomizada1 {
    public static void main(String[] args) {
        String nomeDoArquivo = JOptionPane.showInputDialog("Nome do arquivo a ser exibido: ");

        imprimirArquivoNoConsole(nomeDoArquivo);
        System.out.println("\nCom exception ou não, o programa continua...");
    }

    public static void imprimirArquivoNoConsole(String nomeDoArquivo) {

    }


    {

        try{
            BufferedReader br = lerArquivo(nomeDoArquivo);
            String line = br.readLine();
            BufferedWriter bw = new BufferedWriter(new BufferedWriter(System.out));
            do {
                bw.write(line);
                bw.newLine();
                line = br.readLine();
            } while (line != null);
            bw.flush();
            br.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Ocorreu um erro inesperado, por favor, entre em contato com o suporte." + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static BufferedReader lerArquivo(String nomeDoArquivo) throws ImpossívelAberturaDeAquivoException {
        File file = new File(nomeDoArquivo);
        try {
            return new BufferedReader(new FileReader(nomeDoArquivo));
        } catch (FileNotFoundException e) {
            throw new ImpossívelAberturaDeAquivoException(file.getName(), file.getPath());
        }
    }
}

class ImpossívelAberturaDeAquivoException extends Exception {

    private String nomeDoArquivo;
    private String diretorio;

    public ImpossívelAberturaDeAquivoException(String nomeDoArquivo, String diretorio) {
        super("O arquivo " + nomeDoArquivo + "não foi encontrado no diretório" + diretorio);
        this.nomeDoArquivo = nomeDoArquivo;
        this.diretorio = diretorio;
    }

    @Override
    public String toString() {
        return "ImpossívelAberturaDeAquivoException{" +
                "nomeDoArquivo='" + nomeDoArquivo + '\'' +
                ", diretorio='" + diretorio + '\'' +
                '}';
    }
}
