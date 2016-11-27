package ad.nerdsqu.cookhelper;


/**
 * Created by James on 2016-11-14.
 */





public class Login {

    private String User_Name;
    private String Password;



    public Login (String UserName, String Password2){
        User_Name = UserName;
        Password = Password2;
    }

    public Login() {
        User_Name = "Default";
        Password = "Password1";
    }


    public String getUser_Name() {
        return User_Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setUser_Name(String new_User_Name) {
        User_Name = new_User_Name;
    }

    public void setLogin_Name(String new_Password){
        Password = new_Password;
    }


    public String toString() {
        return ("Login:" + this.getUser_Name() + ", Password:" + this.getPassword());
    }



}
