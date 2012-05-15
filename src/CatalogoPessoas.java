import controladores.ControladorPessoa;
import java.util.ArrayList;
import java.util.List;
import modelos.Pessoa;
import visoes.VisaoPessoa;

public class CatalogoPessoas {

    public static void main(String[] args) {
        List<Pessoa> lp = new ArrayList<Pessoa>();
        VisaoPessoa vp = new VisaoPessoa();
        ControladorPessoa cp = new ControladorPessoa(lp, vp);
        vp.setController(cp);        
        vp.setVisible(true);        
    }
    
}
