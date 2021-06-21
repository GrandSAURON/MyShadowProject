
/**
 * Copyright © 2020 - 2021 by RAS Technologies  All rights reserved.
 * Classes for working with task table in database
 * @author  Terminator
 * @version 210428_01

 */
package srcapp.myshadow.kbs;
import java.util.Date;
import java.util.ArrayList;

import srcapp.myshadow.kbs.store.DataStoreSQLite;

/**
 * Class for working with task table in database
 * @author  Terminator
 * @version  210428_01
 * @since    JDK 1.8
 */
public class TaskObject extends DBObject {

    private int senderId;
    private int receiverId;
    private int locationId;
    private int typeTaskId;
    private String locationName;
    private String senderName;
    private String receiverName;
    private String typeTaskName;
    private String createTime;
    private String startTime;
    private String endTime;
    private String lastDate;
    private int percentCompl;
    private int prioritet;
    private String note;
    private ArrayList taskDoc = null;

    protected void Init(){
        super.Init();
        this.percentCompl = 0;
        this.prioritet = 0;
        this.createTime = new Date().toString();
        this.taskDoc = null;
    }

    public TaskObject() {
        super();
    }

    public int getClassId() {
        return DataDefine.I_TASK_OBJ;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        setEdit(true);
        this.senderId = senderId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        setEdit(true);
        this.locationId = locationId;
    }

    public int getTypeTaskId() {
        return typeTaskId;
    }

    public void setTypeTaskId(int typeTaskId) {
        setEdit(true);
        this.typeTaskId = typeTaskId;
    }

    public String getTypeTaskName() {
        return typeTaskName;
    }

    public void setTypeTaskName(String typeTaskName) {
        setEdit(true);
        this.typeTaskName = typeTaskName;
    }

    public void setTypeTaskNameAndId(String typeTaskName) {
        setEdit(true);
        this.typeTaskName = typeTaskName;
        DBObject tempObj = DataStoreSQLite.getCurDataStore().findObjToName(DataDefine.I_TYPE_TASK_OBJ, typeTaskName);
        if (tempObj != null){
            this.setTypeTaskId( (int) tempObj.getId() );
        }
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        setEdit(true);
        this.locationName = locationName;
    }

    public void setLocationNameAndId(String locationName) {
        setEdit(true);
        this.locationName = locationName;
        DBObject tempObj = DataStoreSQLite.getCurDataStore().findObjToName(DataDefine.I_LOCATION, locationName);
        if (tempObj != null){
            this.setLocationId( (int) tempObj.getId() );
        }
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        setEdit(true);
        this.senderName = senderName;
    }

    public void setSenderNameAndId(String senderName) {
        setEdit(true);
        this.senderName = senderName;
        DBObject tempObj = DataStoreSQLite.getCurDataStore().findObjToName(DataDefine.I_USER_OBJ, senderName);
        if (tempObj != null){
            this.setSenderId((int) tempObj.getId());
        }
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        setEdit(true);
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        setEdit(true);
        this.receiverName = receiverName;
    }

    public void setReceiverNameAndId(String receiverName) {
        setEdit(true);
        this.receiverName = receiverName;
        DBObject tempObj = DataStoreSQLite.getCurDataStore().findObjToName(DataDefine.I_USER_OBJ, receiverName);
        if (tempObj != null){
            this.setReceiverId((int) tempObj.getId());
        }
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        setEdit(true);
        this.createTime = createTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        setEdit(true);
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        setEdit(true);
        this.endTime = endTime;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        setEdit(true);
        this.lastDate = lastDate;
    }

    public int getPercentCompl() {
        return percentCompl;
    }

    public void setPercentCompl(int percentCompl) {
        setEdit(true);
        this.percentCompl = percentCompl;
    }

    public int getPrioritet() {
        return prioritet;
    }

    public void setPrioritet(int prioritet) {
        setEdit(true);
        this.prioritet = prioritet;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        setEdit(true);
        this.note = note;
    }

    public ArrayList getTaskDocList() {
        return taskDoc;
    }

    public void setTaskDocList(ArrayList docs ){
        setEdit(true);
        this.taskDoc = docs;
    }

    public void addTaskDoc( DocumentObject doc ) {
        setEdit(true);
        doc.setTaskId( this.getId());
        if (this.taskDoc == null){
            this.taskDoc = new ArrayList(10);
        }
        this.taskDoc.add(doc);
    }
}
