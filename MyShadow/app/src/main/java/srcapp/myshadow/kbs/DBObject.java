/**
 * Copyright © 2020 - 2021 by RAS Technologies  All rights reserved.
 * Classes for working with database
 * @author  Terminator
 * @version 210428_01

 */
package srcapp.myshadow.kbs;
import java.util.UUID;


import srcapp.myshadow.kbs.store.DataStoreSQLite;

/**
 * Class for working with database
 * @author  Terminator
 * @version  210428_01
 * @since    JDK 1.8
 */
public class DBObject {
    private long Id;
    private int classId;
    private int typeStateId;
    private String guid;
    private String name;
    private String typeStateName;
    private boolean isEdit;
    private boolean isNewMode;

    private String makeGUID() {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        return randomUUIDString.replace("-", "");
    }

    protected void Init() {
        this.isNewMode = true;
        this.Id = DataDefine.NO_OBJECT;
        this.typeStateId = 1;
        this.isEdit = false;
        this.guid = makeGUID();
    }

    public DBObject() {
        Init();
    }

    public void Save() {
        DataStoreSQLite.getCurDataStore().saveData(this);
    }

    public void Dalete() {
        DataStoreSQLite.getCurDataStore().delData(this);
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public int getClassId() {
        return DataDefine.I_DB_OBJ;
    }

    public int getTypeStateId() {
        return typeStateId;
    }

    public void setTypeStateId(int typeStateId) {
        setEdit(true);
        this.typeStateId = typeStateId;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        setEdit(true);
        this.name = name;
    }

    public String getTypeStateName() {
        return typeStateName;
    }

    public void setTypeStateName(String typeStateName) {
        setEdit(true);
        this.typeStateName = typeStateName;
    }

    public void setTypeStateNameAndId(String typeStateName) {
        setEdit(true);
        this.typeStateName = typeStateName;
        DBObject tempObj = DataStoreSQLite.getCurDataStore().findObjToName(DataDefine.I_TYPE_STATE_OBJ, typeStateName);
        if (tempObj != null) {
            this.setTypeStateId((int) tempObj.getId());
        }
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public boolean isNewMode() {
        return isNewMode;
    }

    public void setNewMode(boolean newMode) {
        isNewMode = newMode;
    }
}