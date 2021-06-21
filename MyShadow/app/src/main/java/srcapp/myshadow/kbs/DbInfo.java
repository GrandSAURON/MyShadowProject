/**
 * Copyright © 2020 - 2021 by RAS Technologies  All rights reserved.
 * Classes for working with type state table in database
 * @author  Terminator
 * @version 210428_01
 */
package srcapp.myshadow.kbs;

/**
 * Class for working with app params
 * @author  Terminator
 * @version  210428_01
 * @since    JDK 1.8
 */
public class DbInfo extends DBObject{
    private String name;

    public DbInfo() {
        super();
    }

    public int getUID(){
        return DataDefine.I_DB_INFO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.setEdit(true);
        this.name = name;
    }
}
