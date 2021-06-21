/**
 * Copyright © 2020 - 2021 by RAS Technologies  All rights reserved.
 * Classes for working with database SQLite
 * @author  Terminator
 * @version 210425_01
 */
package srcapp.myshadow.kbs.store;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import srcapp.myshadow.kbs.DBObject;
import srcapp.myshadow.kbs.DataDefine;
import srcapp.myshadow.kbs.DbInfo;
import srcapp.myshadow.kbs.DocumentObject;
import srcapp.myshadow.kbs.LocateObject;
import srcapp.myshadow.kbs.TaskObject;
import srcapp.myshadow.kbs.TypeStateObject;
import srcapp.myshadow.kbs.TypeTaskObject;
import srcapp.myshadow.kbs.UserObject;
import srcapp.myshadow.service.Logger;
import srcapp.myshadow.service.SRCAppSrvClient;
import srcapp.myshadow.ui.R;

/**
 * Class for working with SQLite database
 * @author  Terminator
 * @version  210425_01
 * @since    JDK 1.8
 */
public class DataStoreSQLite extends SQLiteOpenHelper {
    /** object for protocoling*/
    private static final Logger sysLog = new Logger(DataStoreSQLite.class);
    /** constant variable with filename of database*/
    private static final String DATABASE_FILE = "my_shadow.db";
    /** constant variable with name of database */
    private static final String DATABASE_NAME = "my_shadow";
    /** constant variable with version of database*/
    private static final int SCHEMA = 1;
    /** workable data store */
    private static DataStoreSQLite curDataStore = null;
    /** variable with extended context*/
    private Context workCtx;
    /** workable database */
    private SQLiteDatabase workDataBase;
    /** data set */
    private ArrayList dataSet = null;

    /**
     * Get current data store module
     * @author  Terminator
     */
    public static DataStoreSQLite getCurDataStore(){
        return curDataStore;
    }

    /**
     * Reads text from a file, combines it into a single line,
     * and splits it into an array of lines by the ';'character
     * @author  Terminator
     * @param resId -- Special id of scriptfile in res
     * @return String[] -- Array with splitted scriptfile
     */
    private String[] readRawTextFile(int resId) {
        /** Stream for reading script from file */
        InputStream inputStream = workCtx.getResources().openRawResource(resId);
        /** Stream reader */
        InputStreamReader inputreader = new InputStreamReader(inputStream);
        /** Buffer reader */
        BufferedReader buffreader = new BufferedReader(inputreader);
        String lineFromTextWithScript;
        StringBuilder allTextScriptFromFile = new StringBuilder();
        try {
            while (( lineFromTextWithScript = buffreader.readLine()) != null) {
                allTextScriptFromFile.append(lineFromTextWithScript);
                allTextScriptFromFile.append('\n');
            }
        } catch (IOException e) {
            return null;
        }
        return allTextScriptFromFile.toString().split(";");
    }

    /**
     * Checking for the existence of a database by name
     * @author  Terminator
     * @param dbName -- name of my database
     * @return dbtest.exists()
     */
    private boolean doesDatabaseExist(String dbName) {
        File dbExist = workCtx.getApplicationContext().getDatabasePath(dbName);
        return dbExist.exists();
    }

    /**
     * Creating bew Database using script from file
     * @author  Terminator
     */
    private void CreateDataBase(SQLiteDatabase db){
        if(!doesDatabaseExist(DATABASE_NAME)){
            /** String array for strings of script */
            String[] arrayStr;
            arrayStr = readRawTextFile(R.raw.script_create_db);
            for(int i = 0; i < arrayStr.length - 1; i++){
                db.execSQL(arrayStr[i] + ";");
            }
            sysLog.Info("Database was created successful");
        }
    }

    /**
     * Constructor of class DatabaseHelper
     * @author  Terminator
     * @param ctx -- Context var for acces to res
     */
    public DataStoreSQLite(Context ctx) {
        super(ctx, DATABASE_FILE, null, SCHEMA);
        workCtx = ctx;
        curDataStore = this;
        dataSet = new ArrayList(10);
        for (int i = 0; i < 10; i++ ) dataSet.add(null);
    }

    /**
     * Opening SQLite database
     * @author  Terminator
     */
    public void open(){
        //workDataBase = this.getReadableDatabase();
        workDataBase = this.getWritableDatabase();
        sysLog.Info("Database was open successful");
        openAllDataSet();
    }

    /**
     * Opening all task dataset
     * @author  Terminator
     */
    public void openAllDataSet(){
        ArrayList dataList = null;
        dataList = getData(DataDefine.I_TASK_OBJ);
        sysLog.Info("Dataset I_TASK_OBJ was open successful. Load objects "+
                ((dataList == null) ? "no data" : String.valueOf(dataList.size())));
        dataList = getData(DataDefine.I_USER_OBJ);
        sysLog.Info("Dataset I_USER_OBJ was open successful. Load objects "+
                ((dataList == null) ? "no data" : String.valueOf(dataList.size())));
        dataList = getData(DataDefine.I_LOCATION);
        sysLog.Info("Dataset I_LOCATION was open successful. Load objects "+
                ((dataList == null) ? "no data" : String.valueOf(dataList.size())));
        dataList = getData(DataDefine.I_TYPE_STATE_OBJ);
        sysLog.Info("Dataset I_TYPE_STATE_OBJ was open successful. Load objects "+
                ((dataList == null) ? "no data" : String.valueOf(dataList.size())));
        dataList = getData(DataDefine.I_TYPE_TASK_OBJ);
        sysLog.Info("Dataset I_TYPE_TASK_OBJ was open successful. Load objects "+
                ((dataList == null) ? "no data" : String.valueOf(dataList.size())));
        dataList = getData(DataDefine.I_DB_INFO);
        sysLog.Info("Dataset I_DB_INFO was open successful. Load objects "+
                ((dataList == null) ? "no data" : String.valueOf(dataList.size())));

        DBObject tempObj = findObjToId(DataDefine.I_DB_INFO, DataDefine.I_DB_INFO_HOST_NAME);
        if (tempObj != null){
            sysLog.Info(tempObj.getName());
        }
        tempObj = findObjToId(DataDefine.I_DB_INFO, DataDefine.I_DB_INFO_USER_NAME);
        if (tempObj != null){
            sysLog.Info(tempObj.getName());
        }
        tempObj = findObjToId(DataDefine.I_DB_INFO, DataDefine.I_DB_INFO_USER_PASS);
        if (tempObj != null){
            sysLog.Info(tempObj.getName());
        }
        tempObj = findObjToId(DataDefine.I_DB_INFO, DataDefine.I_DB_INFO_AUTO_SEND);
        if (tempObj != null){
            sysLog.Info(tempObj.getName());
        }
        tempObj = findObjToName(DataDefine.I_TYPE_STATE_OBJ, "Отправлена исполнителю");
        if (tempObj != null){
            sysLog.Info(String.valueOf(tempObj.getId()));
        }
    }

    /**
     * Сreating an object in the database
     * @author  Terminator
     * @param iUID
     * @return DBObject
     */
    public DBObject createObject(int iUID){
        DBObject newObj = null;
        switch (iUID){
            case DataDefine.I_TASK_OBJ:
                newObj = new TaskObject();
                break;
            case DataDefine.I_DOCUMENT_OBJ:
                newObj = new DocumentObject();
                break;
            case DataDefine.I_LOCATION:
                newObj = new LocateObject();
                break;
        }
        if (newObj != null && (iUID == DataDefine.I_TASK_OBJ || iUID == DataDefine.I_LOCATION)){
            ArrayList dataList = (ArrayList) dataSet.get(iUID);
            if (dataList == null) {
                dataList = new ArrayList();
                dataList.add(newObj);
                dataSet.set(iUID, dataList);
            } else{
                dataList.add(newObj);
            }
        }
        return newObj;
    }

    /**
     * Getting data from database
     * @author  Terminator
     * @param iUID
     * @return dataList
     */
    public ArrayList getData(int iUID){
        ArrayList dataList = null;
        // get data
        dataList = (ArrayList) dataSet.get(iUID);
        if (dataList == null) {
            sysLog.Info("Reload data from data base ...");
            dataList = getObjectList(iUID);
            dataSet.set(iUID, dataList);
        }
        return dataList;
    }

    /**
     * Finding object using it id
     * @author  Terminator
     * @param iUID -- uid
     * @param iId -- object id
     * @return findObj
     */
    public DBObject findObjToId(int iUID, long iId){
        DBObject findObj = null;
        ArrayList <DBObject> searchList = getData( iUID );
        if (searchList != null) {
            for (DBObject temp : searchList) {
                if (temp.getId() == iId) {
                    findObj = temp;
                    break;
                }
            }
        }
        return findObj;
    }

    /**
     * Finding object using name
     * @author  Terminator
     * @param iUID -- uid
     * @param sName -- name for finding
     * @return
     */
    public DBObject findObjToName(int iUID, String sName){
        DBObject findObj = null;
        ArrayList <DBObject> searchList = getData( iUID );
        if (searchList != null) {
            for (DBObject temp : searchList) {
                if (temp.getName().compareTo(sName) ==  0 ) {
                    findObj = temp;
                    break;
                }
            }
        }
        return findObj;
    }

    /**
     * Save data into database
     * @author  Terminator
     * @param dbObj
     * @return void
     */
    public void saveData(DBObject dbObj){
        if (dbObj == null || !dbObj.isEdit()) return;
        switch (dbObj.getClassId()){
            case DataDefine.I_TASK_OBJ:
                saveTask((TaskObject) dbObj);
                ArrayList docList = ((TaskObject) dbObj).getTaskDocList();
                if (docList != null){
                    for (int i = 0; i < docList.size(); i++){
                        DocumentObject doc = (DocumentObject) docList.get(i);
                        if (doc != null && doc.isEdit()){
                            doc.setTaskId( dbObj.getId());
                            saveDocument(doc);
                        }
                    }
                }
                break;
            case DataDefine.I_DOCUMENT_OBJ:
                saveDocument((DocumentObject) dbObj);
                break;
            case DataDefine.I_LOCATION:
                saveLocate((LocateObject) dbObj);
                break;
            case DataDefine.I_DB_INFO:
                saveDbInfo((DbInfo) dbObj);
                break;
        }
    }

    /**
     * Saving all data to database
     * @author  Terminator
     * @param iUID -- uid
     */
    public void saveAllData(int iUID){
        ArrayList dataList = null;
        dataList = getData(iUID);
        if (dataList != null){
            for(int i = 0; i < dataList.size(); i++){
                saveData((DBObject) dataList.get(i));
            }
        }
    }

    /**
     * Saving all data to database without uid
     * @author  Terminator
     */
    public void saveAllData(){
        saveAllData(DataDefine.I_TASK_OBJ);
        saveAllData(DataDefine.I_DOCUMENT_OBJ);
        saveAllData(DataDefine.I_LOCATION);
    }

    /**
     * Deleting all data for this object
     * @param dbObj -- current object
     */
    public void delData(DBObject dbObj){
        int iUID = dbObj.getClassId();
        if ( iUID == DataDefine.I_TASK_OBJ ||
                iUID == DataDefine.I_DOCUMENT_OBJ ||
                iUID == DataDefine.I_LOCATION) {
            try {
                workDataBase.beginTransaction();
                switch (dbObj.getClassId()){
                    case DataDefine.I_TASK_OBJ:
                        workDataBase.execSQL("delete from task where id="+
                                String.valueOf(dbObj.getId()));
                        break;
                    case DataDefine.I_DOCUMENT_OBJ:
                        workDataBase.execSQL("delete from document where id="+
                                String.valueOf(dbObj.getId()));
                        break;
                    case DataDefine.I_LOCATION:
                        workDataBase.execSQL("delete from location where id="+
                                String.valueOf(dbObj.getId()));
                        break;
                }
                workDataBase.execSQL("delete from mh_object where id="+
                        String.valueOf(dbObj.getId()));
                if (iUID == DataDefine.I_TASK_OBJ || iUID == DataDefine.I_LOCATION){
                    ArrayList dataList = (ArrayList) dataSet.get(iUID);
                    if (dataList != null){
                        dataList.remove(dbObj);
                    }
                }else{
                    ArrayList dataList = (ArrayList) dataSet.get(DataDefine.I_TASK_OBJ);
                    if (dataList != null){
                        for(int i = 0; i < dataList.size(); i++){
                            TaskObject task = (TaskObject) dataList.get(i);
                            if (task != null && task.getId() == ((DocumentObject) dbObj).getTaskId()){
                                ArrayList docList = task.getTaskDocList();
                                if (docList != null){
                                    docList.remove(dbObj);
                                }
                            }
                        }
                    }

                }
                workDataBase.setTransactionSuccessful();
            } finally {
                workDataBase.endTransaction();
            }
        }
    }

    /**
     * Sending all data to server
     */
    public void sendDataIntoMainServer(){
        ArrayList sendData = this.getData(DataDefine.I_TASK_OBJ);
        new Thread(new Runnable(){
            @Override
            public void run() {
                String hostName = "";
                String userName = "";
                String userPass = "";

                DBObject tempObj = findObjToId(DataDefine.I_DB_INFO,
                        DataDefine.I_DB_INFO_HOST_NAME);
                if (tempObj != null){
                    hostName =  tempObj.getName();
                }
                tempObj = findObjToId(DataDefine.I_DB_INFO, DataDefine.I_DB_INFO_USER_NAME);
                if (tempObj != null){
                    userName = tempObj.getName();
                }
                tempObj = findObjToId(DataDefine.I_DB_INFO, DataDefine.I_DB_INFO_USER_PASS);
                if (tempObj != null){
                    userPass = tempObj.getName();
                }
                try {
                    SRCAppSrvClient.sendData(hostName, userName, userPass, sendData);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Closing data
     * @author  Terminator
     * @param iUID
     */
    public void closeData(int iUID){
        dataSet.set(iUID, null);
    }

    /**
     * Closing whole database
     * @author  Terminator
     */
    public void close(){
        this.saveAllData();
        super.close();
        workDataBase.close();
        sysLog.Info("Database was closed successful");
    }

    /**
     * Getting whole task from database
     * @author  Terminator
     * @return dataList
     */
    protected  ArrayList<DBObject> getTask(){
        ArrayList dataList = new ArrayList();
        Cursor c = workDataBase.rawQuery("SELECT * FROM dpr$_task_obj", null);
        if (c.moveToFirst()){
            do {
                TaskObject task = new TaskObject();
                task.setId(c.getLong(c.getColumnIndex("id")));
                task.setTypeStateId(c.getInt(c.getColumnIndex("type_state_id$")));
                task.setTypeStateName(c.getString(c.getColumnIndex("ts_name")));
                task.setGuid(c.getString(c.getColumnIndex("guid")));
                task.setName(c.getString(c.getColumnIndex("name")));
                task.setSenderId(c.getInt(c.getColumnIndex("sender_id$")));
                task.setReceiverId(c.getInt(c.getColumnIndex("receiver_id$")));
                task.setLocationId(c.getInt(c.getColumnIndex("location_id$")));
                task.setTypeTaskId(c.getInt(c.getColumnIndex("type_task_id$")));
                task.setLocationName(c.getString(c.getColumnIndex("location_name")));
                task.setSenderName(c.getString(c.getColumnIndex("sender_name")));
                task.setReceiverName(c.getString(c.getColumnIndex("receiver_name")));
                task.setTypeTaskName(c.getString(c.getColumnIndex("type_task_name")));
                task.setCreateTime(c.getString(c.getColumnIndex("create_time")));
                task.setStartTime(c.getString(c.getColumnIndex("start_time")));
                task.setEndTime(c.getString(c.getColumnIndex("end_time")));
                task.setLastDate(c.getString(c.getColumnIndex("last_date")));
                task.setPercentCompl(c.getInt(c.getColumnIndex("percent_comp")));
                task.setPrioritet(c.getInt(c.getColumnIndex("prioritet")));
                task.setNote(c.getString(c.getColumnIndex("note")));
                task.setEdit(false);
                dataList.add(task);
            } while(c.moveToNext());
        }
        c.close();
        for (int i = 0; i < dataList.size(); i++) {
            TaskObject task =  (TaskObject) dataList.get(i);
            ArrayList taskDoc = getDocument(task.getId());
            if (taskDoc != null ){
                task.setTaskDocList(taskDoc);
            }
        }
        return dataList;
    }

    /**
     * Getting document from database
     * @author  Terminator
     * @param iTaskId -- current task id
     * @return dataList
     */
    protected  ArrayList<DocumentObject> getDocument(long iTaskId){
        ArrayList dataList = new ArrayList();
        Cursor c = workDataBase.rawQuery("SELECT * FROM dpr$_document WHERE task_id$ = ?",
                new String[] {String.valueOf(iTaskId)});
        if (c.moveToFirst()){
            do {
                DocumentObject doc = new DocumentObject();
                doc.setId(c.getLong(c.getColumnIndex("id")));
                doc.setTypeStateId(c.getInt(c.getColumnIndex("type_state_id$")));
                doc.setTypeStateName(c.getString(c.getColumnIndex("ts_name")));
                doc.setGuid(c.getString(c.getColumnIndex("guid")));
                doc.setName(c.getString(c.getColumnIndex("name")));
                doc.setFileName(c.getString(c.getColumnIndex("file_name")));
                doc.setEdit(false);
                dataList.add(doc);
            } while(c.moveToNext());
        }
        c.close();
        return dataList;
    }

    /**
     * Getting user from database
     * @author  Terminator
     * @return dataList
     */
    protected  ArrayList<DBObject> getUser(){
        ArrayList dataList = new ArrayList();
        Cursor c = workDataBase.rawQuery("SELECT * FROM dpr$_user", null);
        if (c.moveToFirst()){
            do {
                UserObject user = new UserObject();
                user.setId(c.getLong(c.getColumnIndex("id")));
                user.setTypeStateId(c.getInt(c.getColumnIndex("type_state_id$")));
                user.setTypeStateName(c.getString(c.getColumnIndex("ts_name")));
                user.setGuid(c.getString(c.getColumnIndex("guid")));
                user.setName(c.getString(c.getColumnIndex("firstname")));
                user.setEdit(false);
                dataList.add(user);
            } while(c.moveToNext());
        }
        c.close();
        return dataList;
    }

    /**
     * Getting location from database
     * @author  Terminator
     * @return dataList
     */
    protected  ArrayList<DBObject> getLocation(){
        ArrayList dataList = new ArrayList();
        Cursor c = workDataBase.rawQuery("SELECT * FROM dpr$_location", null);
        if (c.moveToFirst()){
            do {
                LocateObject loc = new LocateObject();
                loc.setId(c.getLong(c.getColumnIndex("id")));
                loc.setTypeStateId(c.getInt(c.getColumnIndex("type_state_id$")));
                loc.setTypeStateName(c.getString(c.getColumnIndex("ts_name")));
                loc.setGuid(c.getString(c.getColumnIndex("guid")));
                loc.setName(c.getString(c.getColumnIndex("loc_name")));
                loc.setEdit(false);
                dataList.add(loc);
            } while(c.moveToNext());
        }
        c.close();
        return dataList;
    }

    /**
     * Getting type state from database
     * @author  Terminator
     * @return dataList
     */
    protected  ArrayList<DBObject> getTypeState(){
        ArrayList dataList = new ArrayList();
        Cursor c = workDataBase.rawQuery("SELECT * FROM dpr$_type_state", null);
        if (c.moveToFirst()){
            do {
                TypeStateObject ts = new TypeStateObject();
                ts.setId(c.getLong(c.getColumnIndex("id")));
                ts.setName(c.getString(c.getColumnIndex("name")));
                ts.setEdit(false);
                dataList.add(ts);
            } while(c.moveToNext());
        }
        c.close();
        return dataList;
    }

    /**
     * Getting type of task from database
     * @author  Terminator
     * @return dataList
     */
    protected  ArrayList<DBObject> getTypeTask(){
        ArrayList dataList = new ArrayList();
        Cursor c = workDataBase.rawQuery("SELECT * FROM dpr$_type_task", null);
        if (c.moveToFirst()){
            do {
                TypeTaskObject ts = new TypeTaskObject();
                ts.setId(c.getLong(c.getColumnIndex("id")));
                ts.setName(c.getString(c.getColumnIndex("name")));
                ts.setEdit(false);
                dataList.add(ts);
            } while(c.moveToNext());
        }
        c.close();
        return dataList;
    }

    /**
     * Getting info from database
     * @author  Terminator
     * @return dataList
     */
    protected  ArrayList<DBObject> getDbInfo(){
        ArrayList dataList = new ArrayList();
        Cursor c = workDataBase.rawQuery("SELECT id, value FROM db_info WHERE id >= 1000", null);
        if (c.moveToFirst()){
            do {
                DbInfo di = new DbInfo();
                di.setId(c.getInt(0));
                di.setName(c.getString(1));
                di.setEdit(false);
                dataList.add(di);
            } while(c.moveToNext());
        }
        c.close();
        return dataList;
    }

    /**
     * Getting all object list with all attributes
     * @author  Terminator
     * @return dataList
     */
    protected ArrayList<DBObject> getObjectList(int iUID){
        ArrayList dataList = null;
        switch (iUID){
            case DataDefine.I_TASK_OBJ:
                dataList = getTask();
                break;
            case DataDefine.I_DOCUMENT_OBJ:
                dataList = getDocument(0);
                break;
            case DataDefine.I_USER_OBJ:
                dataList = getUser();
                break;
            case DataDefine.I_LOCATION:
                dataList = getLocation();
                break;
            case DataDefine.I_TYPE_STATE_OBJ:
                dataList = getTypeState();
                break;
            case DataDefine.I_TYPE_TASK_OBJ:
                dataList = getTypeTask();
                break;
            case DataDefine.I_DB_INFO:
                dataList = getDbInfo();
                break;

        }
        return dataList;
    }

    /**
     * Saving current db object
     * @author  Terminator
     * @param obj -- current db object
     */
    void saveDbObject(DBObject obj){
        if(obj.getId() == DataDefine.NO_OBJECT) {
            ContentValues values = new ContentValues();
            values.put("guid", obj.getGuid());
            values.put("name", obj.getName());
            values.put("class_id", String.valueOf(obj.getClassId()));
            long iRet = workDataBase.insert("mh_object", null, values);
            obj.setId(iRet);
            obj.setNewMode( true );
        } else {
            ContentValues values = new ContentValues();
            values.put("name", obj.getName());
            values.put("type_state_id", obj.getTypeStateId());
            long iRet = workDataBase.update("mh_object", values,
                    "id = ?", new String[] {String.valueOf(obj.getId())});
            obj.setNewMode( false );
        }
    }

    /**
     * Saving current task to db
     * @author  Terminator
     * @param task -- current task object
     */
    void saveTask( TaskObject task ){
        if (!task.isEdit()) return;
        try {
            workDataBase.beginTransaction();
            saveDbObject(task);
            ContentValues values = new ContentValues();
            values.put("id", task.getId());
            values.put("sender_id", task.getSenderId());
            values.put("receiver_id", task.getReceiverId());
            values.put("location_id", task.getLocationId());
            values.put("type_task_id", task.getTypeTaskId());
            values.put("start_time", task.getStartTime());
            values.put("end_time", task.getEndTime());
            values.put("prioritet", task.getPrioritet());
            values.put("percent_comp", task.getPercentCompl());
            values.put("note", task.getNote());
            if (task.isNewMode()){
                long iRet = workDataBase.insert("task", null, values);
            }else{
                long iRet = workDataBase.update("task", values,
                        "id = ?", new String[] {String.valueOf(task.getId())});
            }
            workDataBase.setTransactionSuccessful();
        } finally {
            workDataBase.endTransaction();
        }
    }

    /**
     * Saving document to db
     * @author  Terminator
     * @param doc -- current document object
     */
    void saveDocument( DocumentObject doc ){
        if (!doc.isEdit()) return;
        try{
            workDataBase.beginTransaction();
            saveDbObject(doc);
            ContentValues values = new ContentValues();
            values.put("id", doc.getId());
            values.put("file_name", doc.getFileName());
            values.put("task_id", doc.getTaskId());
            values.put("type_doc_id", doc.getTypeDocId());
            if (doc.isNewMode()){
                long iRet = workDataBase.insert("document", null, values);
            }else{
                long iRet = workDataBase.update("document", values,
                        "id = ?", new String[] {String.valueOf(doc.getId())});
            }
            workDataBase.setTransactionSuccessful();
        } finally {
            workDataBase.endTransaction();
        }
    }

    /**
     * Saving location object to db
     * @author  Terminator
     * @param loc -- current location object
     */
    void saveLocate( LocateObject loc ){
        if (!loc.isEdit()) return;
        try{
            workDataBase.beginTransaction();
            saveDbObject(loc);
            ContentValues values = new ContentValues();
            values.put("id", loc.getId());
            values.put("name", loc.getName());
            if (loc.isNewMode()){
                long iRet = workDataBase.insert("location", null, values);
            }else{
                long iRet = workDataBase.update("location", values,
                        "id = ?", new String[] {String.valueOf(loc.getId())});
            }
            workDataBase.setTransactionSuccessful();
        } finally {
            workDataBase.endTransaction();
        }
    }

    /**
     * Saving info object to db
     * @author  Terminator
     * @param di -- current info object
     */
    void saveDbInfo( DbInfo di ){
        if (!di.isEdit()) return;
        try{
            workDataBase.beginTransaction();
            saveDbObject(di);
            ContentValues values = new ContentValues();
            values.put("name", di.getName());
            long iRet = workDataBase.update("db_info", values,
                    "id = ?", new String[] {String.valueOf(di.getId())});
            workDataBase.setTransactionSuccessful();
        } finally {
            workDataBase.endTransaction();
        }
    }

    /********************************************************
     *                       EVENTS
     *******************************************************/

    /**
     * Android method when creating object of this class
     * @author  Terminator
     * @param db -- SQLite database for creating it
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        CreateDataBase(db);
    }

    /**
     * Method when this database was upgraded
     * @author  Terminator
     * @param db -- SQLite database
     * @param oldVersion -- old version of  SQLite database
     * @param newVersion -- new version of  SQLite database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {

    }
}