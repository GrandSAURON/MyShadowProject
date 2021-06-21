/**
 * Copyright © 2020 - 2021 by RAS Technologies  All rights reserved.
 * Classes for working with type task table in database
 * @author  Terminator
 * @version 210428_01
 */
package srcapp.myshadow.kbs;

/**
 * Class for working with type task table in database
 * @author  Terminator
 * @version  210428_01
 * @since    JDK 1.8
 */
public class TypeTaskObject extends DBObject{
    private String name;

    public TypeTaskObject() {
        super();
    }

    public int getUID(){
        return DataDefine.I_TYPE_TASK_OBJ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
