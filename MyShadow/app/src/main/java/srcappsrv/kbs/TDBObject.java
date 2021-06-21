package srcappsrv.kbs;

import java.util.ArrayList;
import java.lang.reflect.Field;
/**
* НАЗВАНИЕ КЛАССА: TObject
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ:
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
public abstract class TDBObject implements java.io.Serializable {
  // Данное хранилище назначается фасадным объектам
  static TFacadeStore dstFacadeReader = new TFacadeStore();
  // Уникальный код объекта
  private int iId;
  // Класс отвечающий за сохранение и востановление (обеспечивает устойчивость)
  transient protected TDateStoreDisp dstReader;
  // Флаг изменения объекта
  private boolean bModify;
  // Наименование родителя объекта
  protected String sParentName = "<NO PARENT>";
  // Флаги графического объекта
  private int iFlags;

  /**
   * Код нового объекта. Данный атрибут класса обеспечивает уникальность
   * кода для всех объектов созданных оператором new без явного указания
   * в контсрукторе кода создаваемого объекта. Каждый последующий новый
   * объект имеет отрицательный код на еденицу меньше предыдущего.
   * @author Novikov V.G.
   */
  transient static private int newObjectId = TClassDefine.I_CLASS_OR_ID_NON;

/**
* НАЗВАНИЕ МЕТОДА: TDBObject
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Конструктор по умолчанию.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK 1.3
*/
  public TDBObject() {
    Init();
    // Уникальное отрицательное значение
    iId = newObjectId--;
  }

/**
* НАЗВАНИЕ МЕТОДА: TDBObject
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Конструктор объекта.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param id  Уникальный код объекта.
* @param dst Хранилище объектов.
* <HR>
* @since	JDK 1.3
*/
  public TDBObject( Integer id, TDateStoreDisp dst ) {
    Init();
    // Установить для объекта класс чтения\записи
    setReaderWriter( dst );
    // Уникальный код объекта
    setId( id.intValue() );
  }

/**
* НАЗВАНИЕ МЕТОДА: Init
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Инициализация атрибутов класса
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK 1.3
*/
  protected void Init(){
     iId = TClassDefine.I_CLASS_OR_ID_NON;
     setModify( false );
     iFlags = 0;
  }

  // Возвращает уникальный идентификатор объекта
  public int getUID(){
    return TClassDefine.I_TDBObject;
  }

  /**
  * НАЗВАНИЕ МЕТОДА: getParentName
  * @author 	Sergey S. Romas
  * @version 	%I%
  * КРАТКОЕ ОПИСАНИЕ: Возвращает наименование родителя для объекта
  * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
  * <HR>
  * @since	JDK 1.3
  */
  public String getParentName(){
    return sParentName;
  }

  public void setParentName( String s){
    setModify();
    sParentName = s;
  }

  public void setId( int i ){
    setModify();
    iId = i;
  }

  public int getId(){
    return iId;
  }

  public void setModify(){
    bModify = true;
  }

  public boolean isModify(){
    return bModify;
  }

  public void setModify( boolean b ){
    bModify = b;
  }

  public TDateStoreDisp getReaderWriter(){
    return dstReader;
  }

  public void setReaderWriter( TDateStoreDisp dsd ){
    dstReader = dsd;
  }

/**
* НАЗВАНИЕ МЕТОДА: getFirstSysRelId
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Метод инициализации запроса связанных объектов. Запрашивает
* объекты связанные в базе при помощи чужих ключей.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param iTypeRel  Уникальный код системноы связи (реализуемой чужыми ключами СУБД),
* которой связаны объекты.
* <HR>
* @since	JDK 1.3
*/
 public int getFirstSysRelId( int iTypeRel ){
   return getReaderWriter().getFirstSysRelId( this.getId(), iTypeRel );
 }

/**
* НАЗВАНИЕ МЕТОДА: getFirstSysRelId
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Метод инициализации запроса связанных объектов. Запрашивает
* объекты связанные в базе при помощи чужих ключей.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param iTypeRel  Уникальный код системноы связи (реализуемой чужыми ключами СУБД),
* которой связаны объекты.
* @param sFilter  Фильтр по которому осуществляется фильтрация связей.
*
* <HR>
* @since	JDK 1.3
*/
 public int getFirstSysRelId( int iTypeRel, String sFilter ){
 int iId;
   getReaderWriter().setFilter( sFilter );
   iId = getReaderWriter().getFirstSysRelId( this.getId(), iTypeRel );
   getReaderWriter().clearFilter();
   return iId;
 }


/**
* НАЗВАНИЕ МЕТОДА: getNextSysRelId
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Получить связанный объект.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK 1.3
*/
 public int getNextSysRelId(){
   return getReaderWriter().getNextSysRelId();
 }
 
/**
* НАЗВАНИЕ МЕТОДА: equals
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Переопределение стандартного метода сравнения объектов
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK 1.3
*/
 public boolean equals( Object obj ){
   //if ( obj instanceof TObject ){
   //   return (this.getId() == ((TObject)obj).getId());
   //}
   if ( obj instanceof TDBObject ){
      return ((this.getId() == ((TDBObject)obj).getId()) && (this.getUID() == ((TDBObject)obj).getUID()));
   }
   return false;
 }

/**
* НАЗВАНИЕ МЕТОДА: Print
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Печать атрибутов в назначенный хранилищу протокол.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK 1.3
*/
 public void Print(){
    getReaderWriter().getErrorLog().WriteLog("*** OBJECT --> "+this.toString()+" ***");
    getReaderWriter().getErrorLog().WriteLog("iId = "+getId());
    getReaderWriter().getErrorLog().WriteLog("sParentName = "+getParentName());
 }


/**
* НАЗВАНИЕ МЕТОДА: Write
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Сохранить объект в хранилище объектов.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK 1.3
*/
  public void Write() throws Exception {
    getReaderWriter().writeData( ( TDBObject ) this );
  }

/**
* НАЗВАНИЕ МЕТОДА: Read
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Прочитать объект из хранилища объектов.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK 1.3
*/
  public void Read() throws Exception{
    getReaderWriter().readData( ( TDBObject ) this );
    // При чтении используются методы set, поэтому необходимо
    // сбросить флаг изменения объекта
    setModify( false );
 }

 /**
 * НАЗВАНИЕ МЕТОДА: getFacadeObj
 * @author 	Sergey S. Romas
 * @version 	%I%
 * КРАТКОЕ ОПИСАНИЕ: Вернуть фасадный объект в виде класса.
 * Фасадный объект предназначен для передачи клиентскому приложению.
 * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
 * <HR>
 * @since	JDK 1.3
 */
 public TDBObject getFacade(){
   this.setReaderWriter( dstFacadeReader );
   return this;
 }

 /**
 * НАЗВАНИЕ МЕТОДА: getXML
 * @author 	Sergey S. Romas
 * @version 	%I%
 * КРАТКОЕ ОПИСАНИЕ: Вернуть фасадный объект в виде XML.
 * Фасадный объект предназначен для передачи клиентскому приложению.
 * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
 * <HR>
 * @since	JDK 1.3
 */
 public TDBObject getXML(){
    return null;
 }

 public void setFlags( int i ){
   iFlags = i;
   setModify();
 }

 public int getFlags(){
   return iFlags;
 }
}