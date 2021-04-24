import java.util.Scanner;

public class server {
    protected String login;
    protected String key;
    protected String imputSignature;

    public void setLogin(String login){
        this.login=login;
    }
    public void setKey(String key){
        this.key=key;

    }
    public void setImputSignature(String imputSignature){
        this.imputSignature=imputSignature;
    }

    public boolean check(){
        return imputSignature.equals(Main.hmacDigest(login,key,"HmacMD5"));
    }

    public server(String login,String key){
        this.login=login;
        this.key=key;
        System.out.println("Introduceti semnatura: ");
        Scanner scanner = new Scanner(System.in);
        this.imputSignature=scanner.nextLine();

    }


}
