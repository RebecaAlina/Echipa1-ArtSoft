public class client {
    protected String login;
    protected String key;


    public void setLogin(String login){
        this.login=login;
    }
    public void setKey(String key){
        this.key=key;
    }

    public String getLogin(){
        return login;
    }
    public String getKey(){
        return key;
    }


    public client(String login,String key){
        this.login=login;
        this.key=key;
    }
    public String signature(String sign){
        return sign=Main.hmacDigest(login,key,"HmacMD5");
    }
}
