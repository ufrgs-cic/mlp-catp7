package visoes;

import controladores.ControladorPessoa;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import modelos.Pessoa;

public final class VisaoPessoa extends JFrame{
    private JLabel lbNome;
    private JLabel lbDtNasc;
    private JLabel lbSexo;
    private JTextField tfNome;
    private JTextField tfDtNasc;
    private JComboBox cbSexo;
    private JButton btIncluir;
    
    public VisaoPessoa(){
        lbNome = new JLabel("Nome:");
        lbDtNasc = new JLabel("Data de nascimento:");
        lbSexo = new JLabel("Sexo:");
        tfNome = new JTextField("", 30);
        try {            
            tfDtNasc = new JFormattedTextField(new MaskFormatter("##/##/####"));            
            tfDtNasc.setName("tfDtNasc");            
        } catch (ParseException ex) {
            Logger.getLogger(VisaoPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<Pessoa.SEXO> sexos = new ArrayList<Pessoa.SEXO>(Arrays.asList(Pessoa.SEXO.values()));
        sexos.remove(Pessoa.SEXO.values().length-1);
        cbSexo = new JComboBox(sexos.toArray());        
        
        btIncluir = new JButton("Incluir");
        
        this.setLayout(new GridLayout(4,2));
        this.add(lbNome);
        this.add(tfNome);
        this.add(lbDtNasc);
        this.add(tfDtNasc);
        this.add(lbSexo);
        this.add(cbSexo);
        this.add(new JPanel());
        this.add(btIncluir);
        
        this.setTitle("Catálogo de Pessoas");
    
        this.pack();
        
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        this.clear();
    }
    
    public void clear(){
        tfNome.setText("");
        tfDtNasc.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(new Date()));
        cbSexo.setSelectedIndex(0);
        tfNome.requestFocusInWindow();
    }
    
    public void setController(ControladorPessoa cp){
        this.addWindowListener(cp);
        this.btIncluir.addActionListener(cp);  
        this.tfDtNasc.addFocusListener(cp);
    }    
     
    public Pessoa getPessoa(){
        Pessoa p = new Pessoa();
        p.setNome(this.tfNome.getText());
        try {
            p.setDt_nascimento(this.tfDtNasc.getText());
        } catch (ParseException ex) {
            Logger.getLogger(VisaoPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        p.setSexo(this.cbSexo.getSelectedItem().toString());
        return p;
    }
    
}
