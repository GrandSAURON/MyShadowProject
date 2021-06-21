package srcappsrv.kbs;


import java.io.Serializable;
import java.util.ArrayList;

/**
* НАЗВАНИЕ КЛАССА: TMsg
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Данный класс прадназначен для предоставления ответа
* сервера клиенту. Класс содержит статус выполнения запроса и контейнер
* объектов возвращаемых сервером приложений.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
public class TMsg implements java.io.Serializable{

  // Типы сообщений
  // Соединиться с сервером приложений
  public static final int OSM_LOGIN                        = -1;
  // Отсоединиться от сервера приложений
  public static final int OSM_LOGOUT                       = -2;
  // Изменить пароль
  public static final int OSM_CHANGE_PASS                  = -3;
  // Системная ошибка
  public static final int OSM_SESSION_KILL                 = -4;
  // OFF-LINE запрос сервиса CallOfflineService
  public static final int OSM_CALL_OFF_LINE_SERVICE        = -5;  
  // Список объектов
  public static final int OSM_LIST_OBJECTS                 = 0;
  // Удалить объекты
  public static final int OSM_DEL_OBJECTS                  = 1;
  // Сохранить объекты
  public static final int OSM_SAVE_OBJECTS                 = 2;
  // Получить объекты по системному ключу объекта
  public static final int OSM_GET_OBJECTS                  = 3;
  // Сохранить и перечитать
  public static final int OSM_SAVE_AND_GET_OBJECTS         = 4;
  // Связать объекты связью
  public static final int OSM_OBJECTS_BY_RELATION          = 5;
  // Получить коды объектов связанных с заданными
  public static final int OSM_CODES_BOUND_OBJECTS          = 6;
  // Получить объектЫ связанные с заданными
  public static final int OSM_BOUND_OBJECTS                = 7;
  // Выполнить пользовательскую функцию
  public static final int OSM_USER_FUNCTION                = 8;
  // Зафиксировать все выполненные изменения (commit)
  public static final int OSM_COMMIT_WORK                  = 9;
  // Отменить все выполненные изменения (rollback)
  public static final int OSM_ROLLBACK_WORK                = 10;
  // Получить объекты по внутреннему ключу объекта
  public static final int OSM_GET_OBJECTS_FOR_INTERNAL_KEY = 11;
  // Выполнить пользовательскую функцию со списком параметров
  public static final int OSM_USER_FUNCTION_WITH_PARAM_LIST = 12;
  // Получить метаданные и данные от провайдера данных в виде скриптов. 
  // Используется для генерации HTML, XML, JS.
  public static final int OSM_SCRIPT_GENERATOR              = 14;
  // Используется для генерации отчетов, получения схем, картинок
  public static final int OSM_DATA_GENERATOR                = 15;
  
  // Специфицирует получателя сообщения
  private int iAppCode;

  // Тип сообщения. Специфицирует номер сообщения
  private int iMsgType;

  // Параметр сообщения
  private int iParamMain;

  // Параметр сообщения
  private int iParamExt;

  // Объектный параметр сообщения (список объектов)
  private ArrayList objParam = null;

  // Фильтр данных (используется с сообщением OSM_GET_OBJECTS)
  private Object objFilter = null;

  // Код возвращаемой ошибки
  private int iError;

  // Сообщение об ошибке возвращаемое сервером для пользователя
  private String sUserError = "";

  // Системное сообщение об ошибке возвращаемое сервером.
  // Содержит стек ошибок сервера
  private String sSysError = "";

  // Режим повторяемости объектов в выборке
  private boolean bDistinct = false;


/**
* НАЗВАНИЕ МЕТОДА: genErrorMsg
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Статическая функция генерации сообщения об ошибке.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  static public TMsg genErrorMsg( int iCode, String sUserError, String sSysError ){
    TMsg errMsg = new TMsg();
      errMsg.setError( iCode );
      errMsg.setUserError( sUserError );
      errMsg.setSysError( sSysError );
      return errMsg;
  }

  public TMsg() {
     super();
     setDistinct( false );
     setAppCode( TClassDefine.I_CLASS_OR_ID_NON );
     setMsgType( TClassDefine.I_CLASS_OR_ID_NON );
     setParamMain( TClassDefine.I_CLASS_OR_ID_NON );
     setParamExt( TClassDefine.I_CLASS_OR_ID_NON  );
  }

  public TMsg( int iType ){
     super();
     setDistinct( false );
     setAppCode( TClassDefine.I_CLASS_OR_ID_NON );
     setMsgType( iType );
     setParamMain( TClassDefine.I_CLASS_OR_ID_NON );
     setParamExt( TClassDefine.I_CLASS_OR_ID_NON  );
  }

  public TMsg( int iType, ArrayList objPar ) {
     super();
     setDistinct( false );
     setAppCode( TClassDefine.I_CLASS_OR_ID_NON );
     setMsgType( iType );
     setParamMain( TClassDefine.I_TObject );
     setParamExt( TClassDefine.I_CLASS_OR_ID_NON  );
     setObjList( objPar );
  }

  public TMsg( int iType, int iMain ) {
     super();
     setDistinct( false );
     setAppCode( TClassDefine.I_CLASS_OR_ID_NON );
     setMsgType( iType );
     setParamMain( iMain );
     setParamExt( TClassDefine.I_CLASS_OR_ID_NON  );
  }

  public TMsg( int iType, int iMain, ArrayList objPar ) {
     super();
     setDistinct( false );
     setAppCode( TClassDefine.I_CLASS_OR_ID_NON );
     setMsgType( iType );
     setParamMain( iMain );
     setParamExt( TClassDefine.I_CLASS_OR_ID_NON  );
     setObjList( objPar );
  }

  public TMsg( int iType, int iMain, int iExt, ArrayList objPar ) {
     super();
     setDistinct( false );
     setAppCode( TClassDefine.I_CLASS_OR_ID_NON );
     setMsgType( iType );
     setParamMain( iMain );
     setParamExt( iExt );
     setObjList( objPar );
  }

//////////////////////  Start Accessor & Mutator //////////////////////////

/**
* НАЗВАНИЕ МЕТОДА: setAppCode
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Установить код компонента которому направленно сообщение
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public void setAppCode( int i ){
     iAppCode = i;
  }

/**
* НАЗВАНИЕ МЕТОДА: getAppCode
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Получить код компонента которому направленно сообщение
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public int getAppCode(){
     return iAppCode;
  }

/**
* НАЗВАНИЕ МЕТОДА: setType
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Установить тип сообщения
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public void setMsgType( int i ){
     iMsgType = i;
  }

/**
* НАЗВАНИЕ МЕТОДА: getType
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Получить тип сообщения
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public int getMsgType(){
     return iMsgType;
  }

/**
* НАЗВАНИЕ МЕТОДА: setParamMain
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Установить простой параметр сообщения
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public void setParamMain( int i ){
     iParamMain = i;
  }

/**
* НАЗВАНИЕ МЕТОДА: getParamMain
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Установить простой параметр сообщения
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public int getParamMain(){
     return iParamMain;
  }

/**
* НАЗВАНИЕ МЕТОДА: setParamExt
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Установить простой параметр сообщения
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public void setParamExt( int i ){
     iParamExt = i;
  }

/**
* НАЗВАНИЕ МЕТОДА: getParamMain
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Установить простой параметр сообщения
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public int getParamExt(){
     return iParamExt;
  }


/**
* НАЗВАНИЕ МЕТОДА: getObjList
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Получить список объектов переданных с сообщением.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public ArrayList getObjList(){
     return objParam;
  }

/**
* НАЗВАНИЕ МЕТОДА: setObjList
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Установить список объектов передаваемыхнных с сообщением.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public void setObjList( ArrayList obj ){
     objParam = obj;
  }

/**
* НАЗВАНИЕ МЕТОДА: getFilter
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Получить список объектов переданных с сообщением.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public Object getFilter(){
     return objFilter;
  }

/**
* НАЗВАНИЕ МЕТОДА: setFilter
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Установить список объектов передаваемыхнных с сообщением.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public void setFilter( Object  obj ){
    objFilter = obj;
  }

/**
* НАЗВАНИЕ МЕТОДА: setDistinct
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Режим повторяемости объектов в выборке
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public void  setDistinct( boolean b ){
    bDistinct = b;
  }

/**
* НАЗВАНИЕ МЕТОДА: isDistinct
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Режим повторяемости объектов в выборке?
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public boolean isDistinct(){
    return bDistinct;
  }


/**
* НАЗВАНИЕ МЕТОДА: getError
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Получить код ошибки от сервера.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public int getError() {
    return iError;
  }

/**
* НАЗВАНИЕ МЕТОДА: isSuccess
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Проверить наличие ошибки при обработке сообщения
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public boolean isSuccess() {
    return (getError() == 0);
  }


/**
* НАЗВАНИЕ МЕТОДА: setError
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Установить код ошибки от сервера.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public void setError( int i ){
    iError = i;
  }

/**
* НАЗВАНИЕ МЕТОДА: getUserError
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Получить сообщение об ошибке для пользователя.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public String getUserError() {
    return sUserError;
  }

/**
* НАЗВАНИЕ МЕТОДА: setUserError
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Установить сообщение об ошибке для пользователя.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param String s  Текст сообщения об ошибке
* <HR>
* @since	JDK1.3
*/
  public void setUserError( String s ) {
    sUserError = s;
  }

/**
* НАЗВАНИЕ МЕТОДА: getSysError
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Получить сообщение об системных ошибках. Сообщения
* содержит стек вызываемых методов и текст ошибки.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public String getSysError() {
    return sSysError;
  }

/**
* НАЗВАНИЕ МЕТОДА: getSysError
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Установить сообщение об системных ошибках. Сообщения
* содержит стек вызываемых методов и текст ошибки.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public void setSysError( String s ) {
    sSysError = s;
  }



/**
* НАЗВАНИЕ МЕТОДА: Print
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Печать объектных параметров сообщения
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK 1.3
*/
 public void Print(){
   System.out.println("TMsg ->>>"  );
   System.out.println("MsgType = " + getMsgType());
   System.out.println("ParamMain = " + getParamMain());
   System.out.println("ParamExt = " + getParamExt());
   System.out.println("Error = " + getError());
   System.out.println("SysError = " + getSysError());
   System.out.println("UserError = " + getUserError());
 }

}
