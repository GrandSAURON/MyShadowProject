/**
 * Copyright © 2020 - 2021 by RAS Technologies  All rights reserved.
 * Classes for working with user table in database
 * @author  Terminator
 * @version 210428_01
 */
package srcapp.myshadow.kbs;

/**
 * Class for working with user table in database
 * @author  Terminator
 * @version  210428_01
 * @since    JDK 1.8
 */
public class UserObject extends DBObject {
    private String nickname;
    private String name;
    private String lastname;
    private String surname;

    public UserObject() {
        super();
    }

    public int getUID(){
        return DataDefine.I_USER_OBJ;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
