/**
 * Copyright © 2020 - 2021 by RAS Technologies  All rights reserved.
 * Classes for working SRCServClient
 * @author  Terminator
 * @version 210515_01
 */
package srcapp.myshadow.service;

import java.util.ArrayList;
import srcapp.myshadow.kbs.DBObject;
import srcapp.myshadow.kbs.DataDefine;
import srcapp.myshadow.kbs.TaskObject;
import srcappsrv.TClientFactory;
import srcappsrv.kbs.TMsg;
import srcappsrv.kbs.knowledge.TEventMain;

/**
 * Class for working with server
 * @author  Terminator
 * @version  210428_01
 * @since    JDK 1.8
 */
public class SRCAppSrvClient {
    static final String servletName =  "SRCAppServer/MainGate";
    private static final Logger sysLog = new Logger(SRCAppSrvClient.class);

    /**
     * Sending data to server
     * @param hostName -- server host
     * @param userName -- server username
     * @param userPass -- server user password
     * @param sendList -- send list to server
     */
    public static void sendData(String hostName, String userName, String userPass, ArrayList sendList) {
        if (sendList != null && sendList.size() > 0) {
            try {
                TClientFactory srcSrv = new TClientFactory(hostName, servletName);
                //TClientFactory srcSrv = new TClientFactory( "http://localhost:8080/", "SRCAppServer/MainGate" );
                srcSrv.createUserSession(userName, userPass);
                TMsg sendMsg = new TMsg(TMsg.OSM_SAVE_OBJECTS);
                ArrayList msgList = new ArrayList();
                for(int i = 0; i < sendList.size(); i++) {
                    DBObject dbObj = (DBObject) sendList.get(i);
                    if (dbObj.getClassId() == DataDefine.I_TASK_OBJ &&
                            (dbObj.getTypeStateId() == DataDefine.I_TYPE_STATE_ACTIVE || dbObj.getTypeStateId() == 1)) {
                        TEventMain event = new TEventMain();
                        TaskObject task = (TaskObject) dbObj;
                        event.setEventType(3063);
                        event.setGID(task.getGuid());
                        event.setName(task.getName());
                        event.setNote(task.getNote());
                        event.setGeoText(task.getLocationName());
                        event.setEventDate(task.getCreateTime());
                        event.setDevModel("Samsung");
                        event.setDevType("Телефон");
                        event.setDevManufNum("46348969540J5959");
                        msgList.add(event);
                        dbObj.setTypeStateId( DataDefine.I_TYPE_STATE_SEND );
                        dbObj.setTypeStateName("Отправлена исполнителю");
                    }
                }
                sendMsg.setObjList(msgList);
                TMsg recvMsg = srcSrv.doMessage(sendMsg);
                if (!recvMsg.isSuccess()) {
                    sysLog.Info(recvMsg.getSysError());
                    sysLog.Info(recvMsg.getUserError());
                } else {
                    sysLog.Info("SRCAppSrv send data OK.");
                }
                srcSrv.removeSession();
            } catch (Exception ex) {
                ex.printStackTrace();
                sysLog.Error(ex.getMessage());
            }
        }else{
            sysLog.Info("No data to send on server.");
        }
    }
    
}
