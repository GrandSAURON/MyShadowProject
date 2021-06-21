package srcappsrv.com.archiver;

/**
 * НАЗВАНИЕ КЛАССА: TArchiverException
 * @version 	%I%
 * КРАТКОЕ ОПИСАНИЕ: Исключение генерируемое в результате неудачной операции
 * упаковки, или распаковки.
 * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
 * <HR>
 * @since	JDK1.3
 */
public class TArchiverException extends Exception{

  public TArchiverException( String message ) {
    super( message );
  }

  public TArchiverException( String message, Throwable cause ) {
    super( message, cause );
  }
}
