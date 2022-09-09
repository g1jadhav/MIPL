package mahyco.iqc.nxg.model;

import com.google.gson.annotations.SerializedName;

public class ActionModel {
    public String getUser_Code() {
        return User_Code;
    }

    public void setUser_Code(String user_Code) {
        User_Code = user_Code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getOpMessage() {
        return OpMessage;
    }

    public void setOpMessage(String opMessage) {
        OpMessage = opMessage;
    }

    @SerializedName("User Code")
    String User_Code;

    String Name;
     String Mobile;
    String OpMessage;




}
