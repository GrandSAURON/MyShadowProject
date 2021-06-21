package srcappsrv.kbs;

/**
* НАЗВАНИЕ КЛАССА: TAbstractControler
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Определение абстрактного контроллера. Абстрактный контроллер
* реализует работу с классом протоколирования ошибок.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/

public abstract class TAbstractControler {
  //Контекст вывода сообщений об ошибках
  protected TErrorLog ErrorLog;

  
/**
* НАЗВАНИЕ МЕТОДА: setErrorLog
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Установить объект протоколирования.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param erl  Oбъект протоколирования ошибок.
* <HR>
* @since	JDK 1.3
*/
  public void setErrorLog( TErrorLog erl ){
     ErrorLog = erl;
  }

/**
* НАЗВАНИЕ МЕТОДА: getErrorLog
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Получить объект протоколирования.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param erl  Oбъект протоколирования ошибок.
* <HR>
* @since	JDK 1.3
*/
  public TErrorLog getErrorLog(){
     return ErrorLog;
  }
}
