package srcappsrv;

public class TClassDefine {
 // Предопределенные идентификаторы классов
 // Системные классы
 public static final int I_TDBObject         = 1;
 public static final int I_TObject           = 2;
 public static final int I_TFilter           = 3;
 public static final int I_TFilterElement    = 4;
 public static final int I_TFilterRel        = 5;
 public static final int I_TDocument         = 6;
 public static final int I_TFieldDef         = 7;
 public static final int I_TMethod           = 8;
 public static final int I_TOntoQuery        = 9;
 public static final int I_TOntoTemplate     = 10;
 public static final int I_TOntoResult       = 11;
 public static final int I_TLink             = 12;
 public static final int I_TNode             = 14;
 public static final int I_TGraphModel       = 15;
 public static final int I_TGraphObj         = 16;
 public static final int I_TClass            = 17;
 public static final int I_TEvent            = 18;
 public static final int I_TRelType          = 19;
 public static final int I_TStateType        = 20;
 public static final int I_THistState        = 21;
 public static final int I_TRelation         = 22;
 public static final int I_TFieldVal         = 23;
 public static final int I_TClassDaemon      = 24;
 public static final int I_TFieldDaemon      = 25;
 public static final int I_TObjectInDoc      = 26;
 public static final int I_TFilterOper       = 27;
 public static final int I_TGraphText        = 28;
 public static final int I_TDaemon           = 30;
 public static final int I_TSysReg           = 31;
 public static final int I_TReferenceBook    = 32;
 public static final int I_TReferenceBookValue = 33;
 public static final int I_TGroupNode        = 34;
 public static final int I_TFragment         = 35;
 public static final int I_TUser             = 36;
 public static final int I_TAttribute        = 37;
 public static final int I_TOntology         = 38;
 public static final int I_TApplication      = 39;
 public static final int I_TAction           = 40;
 public static final int I_TMethodParam      = 41;
 public static final int I_TDataProvider     = 42;
 public static final int I_TBinObject        = 43;
 public static final int I_TEventMain        = 44;
 public static final int I_TEventMainQueue   = 45;


 // Зарегистрированные статические классы
 public static final int I_CLASS_OR_ID_NON   = -1;  // Нет объекта
 public static final int I_CLASS_TOBJECT     = 0;   // Объект
 public static final int I_CLASS_TACC_PERS   = 4;   // Лицевой счет
 public static final int I_CLASS_TPLP_OPER   = 9;   // Полупровока операции
 public static final int I_CLASS_TDOCUMENT   = 36;  // Документ
 public static final int I_CLASS_TGROUP      = 37;  // Группировка
 public static final int I_CLASS_TSYSRES     = 55;  // Системный реестр
 public static final int I_CLASS_TGRAPHOBJ   = 121; // Графический объект
 public static final int I_CLASS_TGRAPHMDL   = 122; // Графическая модель
 public static final int I_CLASS_TNODE       = 123; // Графический узел
 public static final int I_CLASS_TLINK       = 124; // Графическая связь
 public static final int I_CLASS_TGRAPHTXT   = 125; // Графический текст
 public static final int I_CLASS_TMETHOD     = 130; // Метод

 // Зарегистрированные типы связей
 public static final int I_TYPE_REL_IN_MODEL     = 1; // Входит в модель
 public static final int I_TYPE_REL_REF_IN_MODEL = 2; // Ссылается на модель
 public static final int I_TYPE_REL_BELON_MODEL  = 3; // Принадлежит модели
 public static final int I_TYPE_REL_CHILD_MODEL  = 4; // Дочерняя модель
 public static final int I_TYPE_REL_K_MAP_QUERY  = 5; // Когнитивная карта запроса
 public static final int I_TYPE_REL_K_MAP_RES    = 6; // Когнитивная карта результата запроса
 public static final int I_TYPE_REL_K_MAP_DOC_QRY= 7; // Когнитивная карта документа по запросу
 public static final int I_TYPE_REL_K_MAP_DOC    = 8; // Когнитивная карта документа
 public static final int I_TYPE_REL_FILTER_NODE  = 9; // Фильтр для графического нода
 public static final int I_TYPE_REL_MODEL_QUERY  = 10;// Фильтр для запроса
 public static final int I_TYPE_REL_FILTER_LINK  = 13;// Фильтр для графической связи

 // Зарегистрированные статусы
 public static final int I_STATE_CREATE          = 1; // Создан
 public static final int I_STATE_DEL             = 2; // Удален
 public static final int I_STATE_ARC             = 3; // В архив
 public static final int I_STATE_ACTIV           = 4; // Активен
 public static final int I_STATE_DOC_INX         = 55;// Документ проиндексирован
 public static final int I_STATE_DOC_RECV        = 56;// Документ принят
 public static final int I_STATE_DOC_POSPO       = 57;// Документ введен в базу ПОСПО
 public static final int I_STATE_QRES_ERR        = 58;// Результат запроса ОШИБКА ЗАГРУЗКИ
 public static final int I_STATE_QRES_LOAD       = 59;// Результат запроса ДОКУМЕНТ ЗАГРУЖЕН
 public static final int I_STATE_QRES_LINK       = 60;// Результат запроса ДОКУМЕНТ ПРИВЯЗАН

 // ГРАФИЧЕСКИЕ КОМПОНЕНТЫ
 // Константы типов графических моделей
 public static final int I_MDL_SYSTEM           = 0;// Системная модель
 public static final int I_MDL_ONTO             = 1;// ОНТОЛОГИЯ
 public static final int I_MDL_QUERY_KM         = 2;// КОГНИТИВНАЯ КАРТА ЗАПРОСА
 public static final int I_MDL_FILTR            = 3;// ФИЛЬТР
 public static final int I_MDL_FOLDER           = 4;// ПАПКА
 public static final int I_MDL_SYBONTO          = 5;// СУБОНТОЛОГИЯ
 public static final int I_MDL_DOC_KM           = 6;// КОГНИТИВНАЯ КАРТА ДОКУМЕНТА
 public static final int I_MDL_DOC_KMQ          = 7;// КОГНИТИВНАЯ КАРТА ДОКУМЕНТА ПО ЗАПРОСУ
 public static final int I_MDL_SYS_FOLDER       = 8;// СИСТЕМНЫЙ ФОЛДЕР
 public static final int I_MDL_QUERY_INST       = 9;// ЭКЗЕМПЛЯР ЗАПРОСА
 public static final int I_MDL_FILTR_INST       = 10;// ЭКЗЕМПЛЯР ФИЛЬТРА
 public static final int I_MDL_LIBRARY          = 11; // Библиотека

 // Субтипы классов
 public static final int I_OBJ_CONCEPTS         = 1; // Концепты
 public static final int I_OBJ_OBJECTS          = 2; // Объекты

 // Субтипы моделей;
 public static final int I_MDL_ALL = 1; // Все модели;
 public static final int I_MDL_COGNITIVE_MAP = 2; // Все когнитивные карты;

 // Субтипы ПРИЛОЖЕНИЙ
 public static final int I_APP_APP_WITH_INT_CODE = 1; // Все когнитивные карты;

 // Типы запрашиваемой информации для получения связанных объектов онтологии;
 public static final int I_LINKED_OBJECTS_TYPE_ISA_LINKS = 1;
 public static final int I_LINKED_OBJECTS_TYPE_LINKS = 2;
 public static final int I_LINKED_OBJECTS_TYPE_CONCEPTS = 4;
 public static final int I_LINKED_OBJECTS_TYPE_INSTANCES = 8;
 public static final int I_LINKED_OBJECTS_TYPE_ALL = 15;

 // ВЫЗОВ СЕРВИСА НА СЕРВЕРЕ ПРИЛОЖЕНИЙ
 public static final int I_RunMethodOnAppServer = 0;

 // ВЫЗОВ СЕРВИСА ГЕНЕРАЦИИ HTTP КОНТЕНТА
 public static final int I_RunMethodOnDbServer  = 10;

 // ВЫЗОВ МЕТОДА ГЕНЕРАЦИИ СКРИПТОВ ДЛЯ ЗАДАННОГО ОБЪЕКТА
 // ГЕНЕРИТ HTML, JSON, XML
 public static final int I_SCRIPT_GENERATOR_HTML       = 0;
 public static final int I_SCRIPT_GENERATOR_JSON_META  = 1;
 public static final int I_SCRIPT_GENERATOR_JSON_DATA  = 2;

 // Возможные для использования СУБД
 public static final int I_ORACLE_DB  = 1;
 public static final int I_PG_DB      = 2;

 // Назнание фабрики передаваемой WEB серверу
 public static final String WS_FACTORY_NAME = "WebSrvSRCAppSrv";

 // Ключевой тэги передаваемые при присоединению к серверу
 public static final String AGENT_NAME_KEY         = "agent";
 public static final String AGENT_USER_NAME_KEY    = "username";
 // Поддерживаемые АГЕНТЫ Мулитиагентной подсистемы
 public static final String AGENT_NAME_SRC_KMP     = "kmp";
 public static final String AGENT_NAME_SRC_CHAT    = "chat";
 public static final String AGENT_NAME_SRC_DEV_POS = "devpos";
 public static final String AGENT_NAME_SRC_DEV_CAM = "devcam";
 public static final String AGENT_NAME_SRC_NEMO    = "nemo";
 // Поддерживаетмые протоколы обмена по простому протоколу  (соккеты)
 public static final String SOCKRT_FACTORY_PRT_STR      = "string";
 public static final String SOCKRT_FACTORY_PRT_BIN      = "bin";
 public static final String SOCKRT_FACTORY_PRT_DEV_POS  = "dev_pos";

}
