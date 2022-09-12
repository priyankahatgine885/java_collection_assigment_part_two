package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Software {
    private  String serverName;
    private String softwareType;
    private String softwareTypeName;
    private String versionNumber;
    public String toString(){
        return String.format("%s %s %s %s", serverName, softwareType, softwareTypeName, versionNumber);
    }

}
