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
public class LocateObject extends DBObject {

    public LocateObject() {
        super();
    }

    public int getClassId() {
        return DataDefine.I_LOCATION;
    }
}