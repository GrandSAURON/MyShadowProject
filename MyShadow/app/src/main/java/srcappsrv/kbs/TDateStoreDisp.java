package srcappsrv.kbs;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

/**
* НАЗВАНИЕ КЛАССА: TDateStoreDisp
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Данный класс описывает абстрактное хранилище объектов.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/

abstract public class TDateStoreDisp extends  TAbstractControler {

  // Фильтр для отбора объектов
  protected String sObjFilter;
  // Список полей по которым сортировать
  protected String sFieldOrder = "";
  // Используемая сортировка объектов
  protected int iOrderBy = 0;
  // Режим повторяемости объектов в выборке
  protected boolean bDistinct = false;
  //
  protected boolean bReadOnly = false;
  // Для доступа к объектам со стартового номера по эндовый номер
  // Начальный объект выборки
  protected int iStartNumber = TClassDefine.I_CLASS_OR_ID_NON;
  // Конечный объект выборки
  protected  int iEndNumber = TClassDefine.I_CLASS_OR_ID_NON;
  // Фабрика использующая данное хранилище данных
  protected TAbstractFactory objFactory = null;


/**
* НАЗВАНИЕ МЕТОДА: Login
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Присоединиться к хранилищу объектов.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param sLogin  Имя пользователя.
* @param sPass   Пароль пользователя.
* <HR>
* @since	JDK1.3
*/
  abstract public void Login( String sLogin, String sPass ) throws Exception;


/**
* НАЗВАНИЕ МЕТОДА: Logout
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Отсоединиться от хранилища объектов.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  abstract public void Logout() throws Exception;
  

/**
* НАЗВАНИЕ МЕТОДА: onLoginSetProp
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Установить дополнительные параметры соединения с базой данных
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/  
  abstract protected void onLoginSetProp( Properties props );


/**
* НАЗВАНИЕ МЕТОДА: GetClassId
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: По уникальному коду объекта получить код его класса.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param iObjId - код объекта класса
* <HR>
* @since	JDK1.3
*/
  abstract public int getClassId( int iObjId );

/**
* НАЗВАНИЕ МЕТОДА: getClassInterface
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: По уникальному коду объекта получить название его интерфейсного
* класса.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param iObjId - код объекта класса
* <HR>
* @since	JDK1.3
*/
  abstract public String getClassInterface( int iObjId );

/**
* НАЗВАНИЕ МЕТОДА: getObjIdForInternalId
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Получить код объекта по его внутреннему коду
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param iInternalId - внутренний код объекта
* <HR>
* @since	JDK1.3
*/
  abstract public int getObjIdForInternalId( int iInternalId );

/**
* НАЗВАНИЕ МЕТОДА: getFirstObjId
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: По наименованию класса объекта получить все уникальные коды
* объектов существующих в базе.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param sInterface - наименование интерфейсного класса
* <HR>
* @since	JDK1.3
*/
  abstract public int getFirstObjId( String  sInterface );

/**
* НАЗВАНИЕ МЕТОДА: getNextObjId
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Получить уникальный код следующего объекта класса запрошенного
* в getClassObjId.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
*  Return  > 0 код объекта, TClassDefine.I_CLASS_OR_ID_NON объектов нет
* <HR>
* @since	JDK1.3
*/
  abstract public int getNextObjId();

/**
* НАЗВАНИЕ МЕТОДА: getObjects
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Получить список объектов.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param iUID       Код класса объекта (TClassDefine). Если TClassDefine.I_CLASS_OR_ID_NON объекты всех классов.
* @param iMasterId  Код родительского объекта. Если TClassDefine.I_CLASS_OR_ID_NON все объекты.
* @param iSubType   Код субтипа для класса.
* <HR>
* @since	JDK 1.3
*/
  abstract public ArrayList getObjects( int iClassId, int iMasterId,  int iSubType );


/**
* НАЗВАНИЕ МЕТОДА: getRelations
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Получить список объектов.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param iUID       Тип связи. Если TClassDefine.I_CLASS_OR_ID_NON все типы связей.
* @param iMasterId  Код родительского объекта. Если TClassDefine.I_CLASS_OR_ID_NON все объекты.
* @param iSubType   Код субтипа для класса.
* <HR>
* @since	JDK 1.3
*/
  abstract public ArrayList getRelations( int iUID, int iMasterId, int iSubType );

/**
* НАЗВАНИЕ МЕТОДА: callUserFunction
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Выполнить пользовательскую функцию
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param iUID       Код функции.
* @param iMasterId  Параметр 1
* @param iSubType   Параметр 2
* <HR>
* @since	JDK 1.3
*/
  abstract public ArrayList callUserFunction( int iUID, int iMasterId, int iSubType );

/**
* НАЗВАНИЕ МЕТОДА: callUserFunction
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Выполнить пользовательскую функцию с переменным числом параметров
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param iUID       Код функции.
* @param oParams
* <HR>
* @since	JDK 1.3
*/
  abstract public ArrayList callUserFunction( int iUID, ArrayList oParams );

/**
* НАЗВАНИЕ МЕТОДА: callScriptGenerator
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Выполнить генерацию скриптов (HTML,Java,JS,...)
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param iUID       Код функции.
* @param oParams
* <HR>
* @since	JDK 1.3
*/
  abstract public ArrayList callScriptGenerator( int iUID, ArrayList oParams,  int iSubType );


/**
* НАЗВАНИЕ МЕТОДА: callDataGenerator
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Выполнить генерацию генерации отчетов, схем, картинок.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param iUID       Код функции.
* @param oParams
* <HR>
* @since	JDK 1.3
*/
  abstract public ArrayList callDataGenerator( int iUID, ArrayList oParams,  int iSubType );


/**
* НАЗВАНИЕ МЕТОДА: getFirstRelId
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Получить коды объектов, связанных с заданным
* определенным видом связи.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param iObjId    Код объекта для которого ищутся связанные.
* @param iTypeRel  Уникальный код типа связи, которой связаны объекты.
* <HR>
* @since	JDK1.3
*/
  abstract public int getFirstRelId( int iObjId, int iTypeRel );

/**
* НАЗВАНИЕ МЕТОДА: getFirstRelId
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Получить коды объектов, связанных с заданным
* определенным видом связи. Выбрать только связанные объекты заданного типа.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param iObjId    Код объекта для которого ищутся связанные.
* @param iTypeRel  Уникальный код типа связи, которой связаны объекты.
* @param sInterface Наименование класса объекты которого отбирать.
* <HR>
* @since	JDK1.3
*/
  abstract public int getFirstRelId( int iObjId, int iTypeRel, String sInterface );


/**
* НАЗВАНИЕ МЕТОДА: getNextRelId
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Получить уникальный код следующего объекта класса запрошенного
* в getFirstRelId.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
*  Return  > 0 код объекта, TClassDefine.I_CLASS_OR_ID_NON объектов нет
* <HR>
* @since	JDK1.3
*/
  abstract public int getNextRelId();

/**
* НАЗВАНИЕ МЕТОДА: getFirstSysRelId
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Метод инициализации запроса связанных объектов. Запрашивает
* объекты связанные в базе при помощи чужих ключей.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param iObjId    Код объекта, для которого определяются все связанные
* @param iTypeRel  Уникальный код системной связи (реализуемой чужыми ключами СУБД),
* которой связаны объекты.
* <HR>
* @since	JDK 1.3
*/
  abstract public int getFirstSysRelId( int iObjId, int iTypeRel );

/**
* НАЗВАНИЕ МЕТОДА: getNextSysRelId
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Получить связанный объект.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK 1.3
*/
  abstract public int getNextSysRelId();

/**
* НАЗВАНИЕ МЕТОДА: setNextRelId
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Связать объект заданным типом связи с текущим объектом
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param iObjId    Код объекта который связывается с текущем.
* @param iTypeRel  Уникальный код типа связи, которой связаны объекты.
* <HR>
* @since	JDK1.3
*/
  abstract public void setNextRelId( int iObjId, int iObjId1, int iTypeRel );


/**
* НАЗВАНИЕ МЕТОДА: WriteData
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Записать объект в хранилище.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param obj     Записываемый объект.
* <HR>
* @since	JDK1.3
*/
  abstract public void writeData( TDBObject obj ) throws Exception;

/**
* НАЗВАНИЕ МЕТОДА: ReadData
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ:
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param obj     Считываемый объект.
* <HR>
* @since	JDK1.3
*/
  abstract public void readData( TDBObject obj ) throws Exception;

/**
* НАЗВАНИЕ МЕТОДА: copyData
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ:
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param sNewName Наименование нового объекта.
* @param obj      Копируемый объект.
* @param iFlag    Флаги копирования
* <HR>
* @since	JDK1.3
*/
  abstract public int copyData( String sNewName, TDBObject obj, int iFlag );

/**
* НАЗВАНИЕ МЕТОДА: writeDataList
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Сохраняет элементы вектора.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param vect  вектор сохраняемых элементов.
* * <HR>
* @since	JDK1.3
*/
  abstract public boolean writeDataList( ArrayList objList );


/**
* НАЗВАНИЕ МЕТОДА: delData
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ:
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param iId  Код удаляемого объекта
* @param iClassId Код класса объекта
* <HR>
* @since	JDK1.3
*/
  abstract public void delData( int iId, int iUID );

/**
* НАЗВАНИЕ МЕТОДА: delObject
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ:
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param obj  Удаляемый объект
* <HR>
* @since	JDK1.3
*/
  abstract public ArrayList delObject( TDBObject obj );

/**
* НАЗВАНИЕ МЕТОДА: FilterData
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Выполнить фильтрацию данных на основе описания фильтра.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param iModelFilter Код модели с описанием фильтра
* @return Код модели с отфильтрованными данными
* <HR>
* @since	JDK 1.3
*/
  abstract public int FilterData( int iModelFilter );

/**
* НАЗВАНИЕ МЕТОДА: getBlobFormType
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Возвращает тип бинарного объекта (word, exel, pdf)
* @return mime-type бинарного объекта.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  abstract public String getBlobFormType( int iBlobId );

/**
* НАЗВАНИЕ МЕТОДА: getBlobFormType
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Устанавливает mime-type бинарного объекта (word, exel, pdf)
* @param iBlobId код бинарного объекта.
* @param sFormType mime-type бинарного объекта.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  abstract public void setBlobFormType( int iBlobId, String sFormType );

/**
* НАЗВАНИЕ МЕТОДА: setFactory
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Устанавливает фабрику, которая использует данный менеджер данных
* @param value фабрика к которой привязывается DataStore
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  abstract public void setFactory ( TAbstractFactory value );

/**
* НАЗВАНИЕ МЕТОДА: getFactory
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Возвращает фабрику привязанную к DataStore
* @return фабрика к которой привязан DataStore
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  abstract public TAbstractFactory getFactory();

/**
* НАЗВАНИЕ МЕТОДА: Flush
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Сохранить изменения.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  abstract public boolean Flush();

/**
* НАЗВАНИЕ МЕТОДА: UnFlush
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Не сохранять изменения.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  abstract public boolean UnFlush();

/**
* НАЗВАНИЕ МЕТОДА: writeNonWorkMsg
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Выдоет сообщение о не поддерживаемом методе.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public void writeNonWorkMsg(){
      getErrorLog().WriteLog( "In given class this method is not supported." );
  }

/**
* НАЗВАНИЕ МЕТОДА: setObjRange
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Установить диапазон отбора объектов
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public void setObjRange( int iStart, int iEnd ){
    iStartNumber = iStart;
    iEndNumber = iEnd;
  }

/**
* НАЗВАНИЕ МЕТОДА: setFilter
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Установить фильтр для отбора объектов по определенному критерию.
* Фильтр это where выражение select
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param s - значение фильтра, например ( CLASS_ID = 1 )
* <HR>
* @since	JDK1.3
*/
  public void setFilter( String s ){
     sObjFilter = s;
  }

/**
* НАЗВАНИЕ МЕТОДА: getFilter
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Получить фильтр для отбора объектов по определенному критерию.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param sBuf - буфер, куда поместить фильтр
* <HR>
* @since	JDK1.3
*/
  public String getFilter( String sBuf ){
    if ( sObjFilter != null &&  !sObjFilter.equals("") ){
       sBuf = sBuf+" AND "+sObjFilter;
    }
    return sBuf;
  }


/**
* НАЗВАНИЕ МЕТОДА: setFilterFromObj
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Установить фильтр для отбора объектов. Фильтр устанавливаемый
* пользователем транслируется в SQL выражение.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* @param obj - фильтр разработанный пользователем
* <HR>
* @since	JDK1.3
*/
  public void setFilterFromObj( Object obj ){
    if ( obj != null ){
       if (obj instanceof String) {
         setFilter( (String) obj );
       }
    }else{
       setFilter( "" );
    }
  }


/**
* НАЗВАНИЕ МЕТОДА: setFieldOrder
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Установить поля по которым сортировать объекты
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public void setFieldOrder( String sFieldList ){
    sFieldOrder = sFieldList;
  }

/**
* НАЗВАНИЕ МЕТОДА: getFieldOrder
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Получить поля по которым сортировать объекты
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public String getFieldOrder(){
    return sFieldOrder;
  }


/**
* НАЗВАНИЕ МЕТОДА: setNoOrder
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Нет сортировки
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public void setNoOrder(){
    iOrderBy = 0;
  }

/**
* НАЗВАНИЕ МЕТОДА: setAscOrder
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Сортировка по возрастанию
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public void setAscOrder(){
    iOrderBy = 1;
  }

/**
* НАЗВАНИЕ МЕТОДА: setDescOrder
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Сортировка по убыванию
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public void setDescOrder(){
    iOrderBy = 2;
  }


/**
* НАЗВАНИЕ МЕТОДА: DelPreficsInVar
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Получить сортировку
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  protected String DelPreficsInVar( String s ){
    try{
        String sPref = s.substring(0, 1);
        if (sPref.equals("s") || sPref.equals("i") ||
            sPref.equals("n") || sPref.equals("b")) {
            return s.substring(1);
        }
        else {
            sPref = s.substring(0, 2);
            if (sPref.equals("dt")) {
                return s.substring(2);
            }
        }
    }catch (Exception e){
    }
    return s;
  }

/**
* НАЗВАНИЕ МЕТОДА: getOrder
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Получить сортировку
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  protected String getOrder( String sBuf, String sSortFields ){
    // Если пользователь назначил поля для сортировки
    if ( !getFieldOrder().equals("")){
      sSortFields = DelPreficsInVar(getFieldOrder());
    }
    if ( iOrderBy > 0 ){
      sBuf = sBuf + " ORDER BY " + sSortFields;
      if ( iOrderBy == 1) {
          sBuf = sBuf + " ASC";
      }
      if ( iOrderBy == 2) {
          sBuf = sBuf + " DESC";
      }
    }
    return sBuf;
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
* НАЗВАНИЕ МЕТОДА: clearFilter
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Очистить фильтр отбора объектов по определенному критерию.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
  public void clearFilter(){
    sObjFilter = null;
  }
}
