package sistema;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author mathe
 */
public class Traducao {
    String enderecoArquivo = new File("C:\\Users\\mathe\\Desktop\\Cursos EAD\\2 - Desenvolvimento ágil com Java avançado\\Semana2\\TarefaAvaliadaPorColegaSemana2\\WebTradutor\\src\\java\\sistema\\palavras.txt").getCanonicalPath();
    File arquivo = new File(enderecoArquivo);

    public Traducao() throws IOException {
        super();
    }
    
    public String traduzir(String palavra){
        palavra = palavra.toLowerCase();
        
        try(Scanner scan = new Scanner(arquivo).useDelimiter("-")){
            while(scan.hasNextLine()){
                if(scan.hasNext(palavra)){
                    String traducao = scan.nextLine().split("-")[1];
                    return traducao;
                }else{
                    scan.nextLine();
                }
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }      
        return palavra;
    }
}
