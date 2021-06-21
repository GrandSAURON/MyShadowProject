package srcappsrv.com;


import java.io.Serializable;

import srcappsrv.com.archiver.TArchiverException;
import srcappsrv.com.archiver.TZipArchiver;


/**
 * НАЗВАНИЕ КЛАССА: TChannelMsg
 * @version 	%I%
 * КРАТКОЕ ОПИСАНИЕ: Сообщение канального уровня. Может кодироваться, подписываться.
 * Является контейнером для сообщения TMsg.
 * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
 * <HR>
 * @since	JDK1.3
 */
public class TChannelMsg implements java.io.Serializable{
    static protected String sXmlStart = "<?xml version=\"1.0\" encoding=\"windows-1251\"?>"+
            "<!-- Copyright (C) 2003-2021 by SRC Solutions Ltd  -->"+
            "<Document>";
    static protected String sXmlEnd =   "</Document>";


    // Код передающего сеанса
    private String sSession;
    // Передаваемые данные (фикция)
    private Object sendData;
    // Передаваемые данные
    private byte[] rss;
    // Контрольная сумма
    protected long   lControlSum;
    // Дополнительные параметры сообщения
    protected int iFlag = 1000;
    // IP адрес сеанса
    private String sIP;

    // Открытый ключ
    transient private String sKey = "";
    // Открытый режим
    transient private boolean bOpenMode = false;


    /**
     * НАЗВАНИЕ МЕТОДА: simpleDecode
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Установить данные для передачи
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public static String simpleDecode( String sObj ){
        byte[] cBuf;
        cBuf = sObj.getBytes();
        if ( (char) cBuf[0] == '@' && (char) cBuf[1] == '@'){
            String sHex = sObj.substring( 2 );
            //cBuf = DatatypeConverter.parseHexBinary( sHex );
            return new String( cBuf );
        }
        return sObj;
    }

    /**
     * НАЗВАНИЕ МЕТОДА: TChannelMsg
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Конструктор
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public TChannelMsg(){
        Init();
    }

    /**
     * НАЗВАНИЕ МЕТОДА: Init
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Инициализация объекта
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    protected void Init(){
        sSession = new String("");
        setFlag( 1000 );
        setOpenMode( false );
    }


    /**
     * НАЗВАНИЕ МЕТОДА: setData
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Установить данные для передачи
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public void setData( Object obj ){
        TZipArchiver zip = new TZipArchiver();
        try{
            if ( bOpenMode == false ){
                if (iFlag == 1000) {
                    rss = zip.archiveObject((Serializable)obj);
                }
                else {
                    rss = zip.archiveObject((Serializable)obj, sKey);
                }
                lControlSum = zip.getCheckSumm();
                sendData = null;
            }else{
                rss = null;
                sendData = obj;
            }
        }catch( TArchiverException ex ){
        }
    }


    /**
     * НАЗВАНИЕ МЕТОДА: getData
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Получить переданные данные
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public Object getData(){
        Object oRet = null;
        TZipArchiver zip = new TZipArchiver();
        try{
            if ( bOpenMode == false ){
                if (rss != null){
                    if (iFlag == 1000) {
                        oRet = zip.extractObject(rss, lControlSum);
                    } else {
                        oRet = zip.extractObject(rss, lControlSum, sKey);
                    }
                    return oRet;
                }else{
                    return null;
                }
            }else{
                return sendData;
            }
        }catch( TArchiverException ex ){
            return null;
        }
    }


    /**
     * НАЗВАНИЕ МЕТОДА: setKey
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Установить код текущей сессии
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public void setKey( String s ){
        sKey = s;
    }

    /**
     * НАЗВАНИЕ МЕТОДА: setSeccionId
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Установить код текущей сессии
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public void setSession( String s ){
        sSession = s;
    }

    /**
     * НАЗВАНИЕ МЕТОДА: getSessionId
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Получить код текущей сессии
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public String getSession(){
        return sSession;
    }


    /**
     * НАЗВАНИЕ МЕТОДА: setIP
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ:
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public void setIP( String s ){
        sIP = s;
    }

    /**
     * НАЗВАНИЕ МЕТОДА: getIP
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ:
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public String getIP(){
        return sIP;
    }

    /**
     * НАЗВАНИЕ МЕТОДА: setOpenMode
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ:
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public void setOpenMode( boolean b ){
        bOpenMode = b;
    }

    /**
     * НАЗВАНИЕ МЕТОДА: getOpenMode
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ:
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public boolean getOpenMode(){
        return bOpenMode;
    }

    /**
     * НАЗВАНИЕ МЕТОДА: setFlag
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ:
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public void setFlag( int i ){
        iFlag = i;
    }


    /**
     * НАЗВАНИЕ МЕТОДА: setFaceDate
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Установить XML представление для сообщения
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public void setFaceData( String sFaceBuf ){
    }

    /**
     * НАЗВАНИЕ МЕТОДА: getFaceDate
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ:
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public String getFaceData(){
        return "<?xml version=\"1.0\" encoding=\"windows-1251\"?>"+
                "<Document>"+
                "</Document>"+
                "<!-- Copyright (C) 2003-2021 by SRC Solutions  -->";

    }

}
