package srcappsrv.kbs;

import java.util.Vector;
import java.sql.SQLException;


/**
* НАЗВАНИЕ КЛАССА: TObjectFactory
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Абстрактная фабрика объектов. Предназначенна
* для создания, сохранения и востановления объектов устойчивых классов.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
public abstract class TAbstractFactory extends  TAbstractControler{

  // Абстрактное хранилище объектов
  protected TDateStoreDisp DataStore;

  // Инициализировать хранилище данных для фабрики
  abstract  public void initDateStore();

  // Установить текущее хранилище объектов
  abstract  public void setDateStore( TDateStoreDisp dsd );

    // Получить текущее хранилище объектов
  abstract  public TDateStoreDisp getDateStore();

  // Установить соединение с фабрикой классов
  abstract public boolean Login( String sUser, String sPass ) throws Exception;

  // Разорвать соединение с фабрикой классов
  abstract public boolean Logout() throws Exception;

  // Получить название интерфейсного класса для заданного кода объекта
  abstract String getClassInterface( int iObjId );

  // Получить объект для заданного уникального кода
  abstract public TDBObject getObject( int iId );

  // Получить объект заданного класса из фабрики классов
  abstract public TDBObject getFirstObject( String sClassName );

  // Получить объект заданного в getObject( String sClassName )
  // класса из фабрики классов
  abstract public TDBObject getNextObject();

  //Данный класс описывает хранилище объектов Oracle
  abstract public TDBObject newObject( String sClassName );

  //Удалить объект заданного класса.
  abstract public void delObject( int iId, int iUID ) throws Exception;

  //Выполнить фильтрацию объектов на основе фильтра.
  //Отфильтровать объекты в новую модель.
  abstract public int FilterObjects( int iModelFilter );

}