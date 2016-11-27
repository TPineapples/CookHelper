package ad.nerdsqu.cookhelper;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by James on 2016-11-14.
 */





public class Login implements Parcelable {

    private String User_Name;
    private String Password;


    public Login (Parcel p){
        String[] info = new String[2];

        p.readStringArray(info);
        System.out.println("x19191: " + info[0] + ", " + info[1]);
        this.User_Name = info[0];
        this.Password = info[1];
    }
    public Login (String UserName, String Password2){
        User_Name = UserName;
        Password = Password2;
    }

    public Login() {
        User_Name = "A";
        Password = "a";
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

    public void setPassword(String new_Password){
        Password = new_Password;
    }


    public String toString() {
        return ("Login:" + this.getUser_Name() + ", Password:" + this.getPassword());
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{this.User_Name, this.Password});
    }

    public static final Parcelable.Creator<Login> CREATOR = new Parcelable.Creator<Login>(){
        public Login createFromParcel (Parcel p){
            return new Login(p);
        }

        public Login[] newArray(int size) {
            return new Login[size];
        }
    };
}
