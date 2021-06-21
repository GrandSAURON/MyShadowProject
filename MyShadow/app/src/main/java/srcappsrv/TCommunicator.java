package srcappsrv;

import java.io.*;
import java.net.*;

/**
 * НАЗВАНИЕ КЛАССА: TCommunicator
 * @version 	%I%
 * КРАТКОЕ ОПИСАНИЕ: Класс обеспечения приема и передачи данных сервером
 * приложений.
 * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
 * <HR>
 * @since	JDK1.4
 */
public class TCommunicator{
    // URL сервлета
    protected URL urlServlet;
    // Соединение с сервлетом
    protected HttpURLConnection urlConn;
    // Файл с ключом
    protected String sKeyFile;
    // Контрольная сумма файла
    protected long lControlSum;

    /**
     * НАЗВАНИЕ МЕТОДА: TCommunicator
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ:
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public TCommunicator( String sWeb, String sServletName ){
        try {
            // Установить прокси
            //java.util.Properties prop = System.getProperties();
            //if ( sProxyHost != null && !sProxyHost.equals("") ) prop.put( "http.proxyHost", sProxyHost );
            //if ( sProxyPort != null && !sProxyPort.equals("") ) prop.put( "http.proxyPort", sProxyPort );
            //System.setProperties( prop );
            // Создать URL
            urlServlet = new URL( new URL(sWeb), sServletName );
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }


    /**
     * НАЗВАНИЕ МЕТОДА: request
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Установить соединение с сервером приложений
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public synchronized void request() throws Exception {
        try {
            // Установить соединение с сервером
            urlConn = ( HttpURLConnection ) urlServlet.openConnection();
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false);
            urlConn.setAllowUserInteraction(true);
            urlConn.setInstanceFollowRedirects(false);
            urlConn.setRequestMethod("POST");
            urlConn.setRequestProperty("CONTENT_TYPE", "application/x-www-form-urlencoded");
        }catch ( FileNotFoundException fnex ) {
            System.exit( -1 );
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }


    /**
     * НАЗВАНИЕ МЕТОДА: sendData
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Выполнить запрос
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    protected synchronized void sendData( Object obj ){
        ObjectOutputStream oos;
        try{
            oos = new ObjectOutputStream( urlConn.getOutputStream() );
            oos.writeObject( obj );
            oos.flush();
            oos.close();
        }catch ( IOException ex ){
            ex.printStackTrace();
        }
    }

    /**
     * НАЗВАНИЕ МЕТОДА: resvData
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Получить ответ
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public synchronized Object resvData(){
        Object obj = null;
        ObjectInputStream ois;
        try{
            ois = new ObjectInputStream( urlConn.getInputStream() );
            obj = ois.readObject();
            ois.close();
        }catch ( IOException ex ){
            ex.printStackTrace();
        }catch (ClassNotFoundException ex1) {
            ex1.printStackTrace();
        }
        return obj;
    }


    /**
     * НАЗВАНИЕ МЕТОДА: SendResvData
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Выполнить запрос и получить ответ
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public synchronized Object SendResvData( Object objSend ){
        // Запросить сервер
        sendData(objSend);
        // Получить ответ
        return resvData();
    }


    /**
     * НАЗВАНИЕ МЕТОДА: finalize
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Очистить все ресурсы
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    protected void finalize () throws Throwable
    {
        super.finalize();
    }


}

