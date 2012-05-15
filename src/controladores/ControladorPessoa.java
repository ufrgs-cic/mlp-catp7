package controladores;

import java.awt.Component;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import modelos.Pessoa;
import visoes.VisaoPessoa;

public class ControladorPessoa 
       implements WindowListener, ActionListener, FocusListener {

    private List<Pessoa> lp;
    private VisaoPessoa vp;
    
    public ControladorPessoa(List lp, VisaoPessoa vp){
        this.vp = vp;
        this.lp = lp;
    }
    
    @Override
    public void windowOpened(WindowEvent e) {        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        int result = JOptionPane.showConfirmDialog(vp, "Deseja realmente sair?", 
                "Atenção", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION){
            for(Pessoa p:lp)
                System.out.println(p);
            vp.dispose();            
        }        
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.exit(0);    
    }

    @Override
    public void windowIconified(WindowEvent e) { }        
    
    @Override
    public void windowDeiconified(WindowEvent e) { }

    @Override
    public void windowActivated(WindowEvent e) { }

    @Override
    public void windowDeactivated(WindowEvent e) { }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Incluir")){
            lp.add(vp.getPessoa());
            vp.clear();         
        }
    }
      
    @Override
    public void focusGained(FocusEvent e) {      
        Component c = e.getComponent();
        if(c instanceof JFormattedTextField && c.getName().equals("tfDtNasc")){
             ((JFormattedTextField)e.getComponent()).selectAll();
        }        
    }
    
    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource() instanceof JFormattedTextField){
            JFormattedTextField tf = (JFormattedTextField)e.getSource();
            if(tf.getName().equals("tfDtNasc")){
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                dateFormat.setLenient(false);                                
                try {
                    dateFormat.parse(tf.getText());                    
                }
                catch (ParseException pe) {
                    JOptionPane.showMessageDialog(vp, "Data inválida!", "Atenção", JOptionPane.OK_OPTION);                                
                    tf.requestFocusInWindow();
                }           
            }
        }   
    }    
        
}
