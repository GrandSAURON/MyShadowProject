package srcappsrv.kbs;


/**
* НАЗВАНИЕ КЛАССА: TErrorLog
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Класс протоколирования ощибок системы.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
public class TErrorLog {


/**
* НАЗВАНИЕ МЕТОДА: ClearLog
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Очистить протокол.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK 1.3
*/
  public void ClearLog(){
    System.out.flush();
  }

/**
* НАЗВАНИЕ МЕТОДА: WriteLog
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Пишет сообщение об ошибке в протокол. Данный класс выводит в
* стандартный поток.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK 1.3
*/
  public void WriteLog( String s ){
    System.out.println( s );
  }

/**
* НАЗВАНИЕ МЕТОДА: WriteLog
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Пишет в протокол байт.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK 1.3
*/
  public void WriteLog( byte b ){
    System.out.print( b+" " );
  }

/**
* НАЗВАНИЕ МЕТОДА: WriteInfo
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Пишет информационное сообщение об ошибке в протокол.
* Данный класс выводит в стандартный поток.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK 1.3
*/
  public void WriteInfo( String s ){
    System.out.println( s );
  }

/**
* НАЗВАНИЕ МЕТОДА: GetLog
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Вернуть содержимое протокола. Для данного случая
* возвращает null.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK 1.3
*/
  public String GetLog(){
    return null;
  }

}
