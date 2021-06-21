package srcappsrv;

import java.util.ArrayList;

import srcappsrv.com.TChannelMsg;
import srcappsrv.kbs.TMsg;

public class TClientFactory extends TCommunicator{
    public static final int ADM_ERROR  = -1;
    public static final int ADM_OK     = 0;
    public static final int PASS_EXPIRED = -20406;

    // Код текущей сессии коммуникатора
    protected String sUserSession;
    // Открытый ключ претендент
    protected String sLogoImage;
    // Открытый ключ текущей сессии
    protected String sKey;

    /**
     * НАЗВАНИЕ МЕТОДА: TClientFactory
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ:
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public TClientFactory( String web, String sServletName ) {
        super( web, sServletName );
        sUserSession = new String( "" );
        sLogoImage = new String( "Logo1.gif" );
        sKey = new String( "" );
    }

    /**
     * НАЗВАНИЕ МЕТОДА: LoadLogoGif
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Ключик
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public void setLogoGif( String s ){
        sLogoImage = s;
    }

    /**
     * НАЗВАНИЕ МЕТОДА: createUserSession
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Создать пользовательскую сессию клиента с фабрикой классов
     * сервера приложений.
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public boolean createUserSession( String sName, String sPass ) throws Exception {
        TMsg sendMsg = new TMsg( TMsg.OSM_LOGIN );
        ArrayList param = new ArrayList();
        param.add( sName );
        param.add( sPass );
        param.add( sLogoImage );
        sendMsg.setObjList( param );
        TMsg resvMsg = doMessage( sendMsg );
        if ( resvMsg.isSuccess() ){
            sKey = sLogoImage;
            return true;
        }else if ( resvMsg.getError() == PASS_EXPIRED ) {
            throw new Exception( resvMsg.getUserError());
        }else if ( resvMsg.getError() <= ADM_ERROR ) {
            throw new Exception( resvMsg.getUserError() );
        }
        return false;
    }

    /**
     * НАЗВАНИЕ МЕТОДА: createUserSession
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Создать пользовательскую сессию клиента с фабрикой классов
     * сервера приложений, которая контролирует ключевой файл.
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public boolean createUserSession( String sKeyFile, String sName, String sPass ) throws Exception {
        //this.setKeyFile( sKeyFile, sLogoImage );
        return this.createUserSession( sName, sPass );
    }

    /**
     * НАЗВАНИЕ МЕТОДА: changeUserPassword
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Изменить пароль пользователя
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public boolean changeUserPassword( String sName, String sPass ) throws Exception {
        TMsg sendMsg = new TMsg( TMsg.OSM_CHANGE_PASS );
        ArrayList param = new ArrayList();
        param.add(sName);
        param.add(sPass);
        sendMsg.setObjList( param );
        TMsg resvMsg = doMessage( sendMsg );
        if ( !resvMsg.isSuccess() ){
            sKey = sLogoImage;
            throw new Exception( resvMsg.getUserError() );
            //return false;
        }
        return true;
    }

    /**
     * НАЗВАНИЕ МЕТОДА: removeSession
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Удалить пользовательскую сессию в фабрика классов сервера
     * приложений.
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public void removeSession() throws Exception{
        TMsg sendMsg = new TMsg( TMsg.OSM_LOGOUT );
        ArrayList param = new ArrayList();
        sendMsg.setObjList(param);
        doMessage( sendMsg );
    }

    /**
     * НАЗВАНИЕ МЕТОДА: doMessage
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Послать сообщение серверу приложений.
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    synchronized public TMsg doMessage( TMsg msg ) throws Exception {
        Object obj = null;
        TMsg objError = TMsg.genErrorMsg( -1, "Сервер приложений не отвечает!", "" );
        TChannelMsg channelMsg = new TChannelMsg( );
        // Установить код текущей сесиии
        channelMsg.setSession( sUserSession );
        // Если ключ задан
        if (!sKey.equals("")){
            // Установить открытый ключ
            channelMsg.setKey(sKey);
        }else{
            channelMsg.setFlag( 1000 );
        }
        // Сформировать сообщение канального уровня
        channelMsg.setData( (Object) msg );
        // Установить соединение
        this.request();
        // Выполнить запрос и получить ответ
        obj = SendResvData((Object)channelMsg);
        // Если есть результат
        if ( obj != null ){
            channelMsg = (TChannelMsg) obj;
            // Сохранить код сеанса
            // Он потребуется для следующего запроса
            // Может понадобиться при динамической смене кодов сеанса
            sUserSession = channelMsg.getSession();
            // Установить открытый ключ
            channelMsg.setKey( sKey );
            TMsg msgRet = (TMsg) channelMsg.getData();
            if ( msgRet == null ){
                return objError;
            }
            return msgRet;
        }
        return objError;
    }
}
