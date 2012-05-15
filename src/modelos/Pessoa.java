package modelos;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
public class Pessoa {  
    
    public enum SEXO { MASCULINO, FEMININO, INDEFINIDO };         
    
    private String nome;
    private Date dt_nascimento;
    private SEXO sexo;   
    
    private static int nInstancias = 0;    
    
    public Pessoa(){
        this.nome = "<Indefinido>";
        this.sexo = SEXO.INDEFINIDO;
        this.dt_nascimento = new Date();
        nInstancias++;
    }    
    
    public Pessoa(String nome, Date dt_nascimento, SEXO sexo){
        this();
        this.setNome(nome);
        this.setDt_nascimento(dt_nascimento);
        this.setSexo(sexo);        
    }
    
    public static int getNInstancias(){
        return nInstancias;
    }
           
    @Override
    public String toString(){
        return String.format("%s - %s - %s (%d anos)", this.getNome(),
                this.getSexo().toString().toLowerCase(Locale.getDefault()),
                DateFormat.getDateInstance(DateFormat.SHORT).format(this.dt_nascimento),
                this.getIdade());                
    }
    
    public final void setNome(String nome) {
        boolean found = false;
        for(char c:nome.toCharArray())
            if(Character.isDigit(c)) 
                found = true;        
        if(!nome.isEmpty() && !found)
           this.nome = nome;
    }
    
    public final String getNome() {
        return nome;
    }  

    public final void setDt_nascimento(Date dt_nascimento) {
        this.dt_nascimento = dt_nascimento;
    }
    
    public final void setDt_nascimento(String dt_nascimento) throws ParseException{
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");        
        this.setDt_nascimento(df.parse(dt_nascimento)); // converte a data digitada para um objeto date e passa para a pessoa        
    }
    
    public final void setDt_nascimento(int dd, int mm, int yyyy){
        Calendar c = Calendar.getInstance();
        c.set(yyyy, mm, dd);
        this.dt_nascimento.setTime(c.getTimeInMillis());		
    }
    
    public final Date getDt_nascimento() {
        return (Date)dt_nascimento.clone();
    }  
    
     public int getIdade(){
           Calendar agora = Calendar.getInstance();
           Calendar original = Calendar.getInstance();
                      
           original.setTime(this.dt_nascimento);
           
           int idade = agora.get(Calendar.YEAR) - original.get(Calendar.YEAR);
                                 
           return idade;           
       }
       
    public final void setSexo(String sexo){        
        for(SEXO s:SEXO.values()){
            if(sexo.equalsIgnoreCase(s.toString())){
                this.sexo = s;
                break;
            }
        }
    }
    
    public final void setSexo(SEXO sexo) {
        this.sexo = sexo;
    }
    
    public final SEXO getSexo() {
        return sexo;
    }
}
