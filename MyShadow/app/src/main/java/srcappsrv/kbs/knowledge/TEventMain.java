package srcappsrv.kbs.knowledge;


import srcappsrv.kbs.*;

/**
* НАЗВАНИЕ КЛАССА: 
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Элемент очереди СОБЫТИЙ.
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
public class TEventMain extends TDBObject{
  //Системный идентификатор (уникальный 24 разрядный идентификатор)
  String sGID;
  // Наименование события
  String sName;
  // Дата и время события
  String sEventDate;
  // Тип события
  int    iEventType;
  // Тип географической привязки
  int    iGeoType;
  // Географическая Х-координата
  String sGeoGpsX;
  // Географическая Y-координата
  String sGeoGpsY;
  // Географическая дата (дата с GPS или ГЛОНАС)
  String sGeoGpsDate;
  // Текстовая привязка 
  String sGeoText;
  // Тип устройства фиксации
  String sDevType;
  // Идентификатор устройства
  String sDevCode;
  // Марка и модель устройства
  String sDevModel;
  // Серийный номер производителя
  String sDevManufNum;
  // Номер сертификата устройства
  String sDevSert;
  // Дата сертификата	
  String  sDevSertValidDate;
  // Номер объекта события
  String sDataObjNum;
  // Номер документа
  String sDataDocNum;
  // Дата документа
  String sDataDocDate;
  // Дополнительные данные события
  String sData;
  // Примечание к событию 
  String sNote;   

  public TEventMain() {
     super ();
  }

  public TEventMain( Integer id, TDateStoreDisp disp ) {
     super ( id, disp );
  }

  protected void Init(){
     super.Init();
     sGID = "";     
     sName = "";
     sEventDate = "";
     iEventType = 0;
     iGeoType = 0;
     sGeoGpsX = "";
     sGeoGpsY = "";
     sGeoGpsDate = "";
     sGeoText = "";
     sDevType = "";
     sDevCode = "";
     sDevModel = "";
     sDevManufNum = "";
     sDevSert= "";
     sDevSertValidDate = "";
     sDataObjNum = "";
     sDataDocNum = "";
     sDataDocDate = "";
     sData = "";
     sNote = "";
  }


  public int getUID(){
    return TClassDefine.I_TEventMain;
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
    super.Print();
    getReaderWriter().getErrorLog().WriteLog("sName = "+this.getName());
    getReaderWriter().getErrorLog().WriteLog("sGID = "+this.getGID());     
    getReaderWriter().getErrorLog().WriteLog("sEventDate = "+this.getEventDate());
    getReaderWriter().getErrorLog().WriteLog("iEventType = "+this.getEventType());
    getReaderWriter().getErrorLog().WriteLog("iGeoType = "+this.getGeoType());
    getReaderWriter().getErrorLog().WriteLog("sGeoGpsX = "+this.getGeoGpsX() );
    getReaderWriter().getErrorLog().WriteLog("sGeoGpsY = "+this.getGeoGpsY() );
    getReaderWriter().getErrorLog().WriteLog("sGeoGpsDate = "+this.getGeoGpsDate() );
    getReaderWriter().getErrorLog().WriteLog("sGeoText = "+this.getGeoText() );
    getReaderWriter().getErrorLog().WriteLog("iDevType = "+this.getDevType() );
    getReaderWriter().getErrorLog().WriteLog("sDevCode = "+this.getDevCode() );
    getReaderWriter().getErrorLog().WriteLog("sDevModel = "+this.getDevModel());
    getReaderWriter().getErrorLog().WriteLog("sDevManufNum= "+this.getDevManufNum() );
    getReaderWriter().getErrorLog().WriteLog("sDevSert= "+this.getDevSert() );    
    getReaderWriter().getErrorLog().WriteLog("sDevSertValidDate = "+this.getDevSertValidDate() );    
    getReaderWriter().getErrorLog().WriteLog("sDataObjNum = "+this.getDataObjNum() );    
    getReaderWriter().getErrorLog().WriteLog("sDataDocNum = "+this.getDataDocNum() ); 
    getReaderWriter().getErrorLog().WriteLog("sDataDocDate = "+this.getDataDocDate() );    
    getReaderWriter().getErrorLog().WriteLog("sData = "+this.getData() );
    getReaderWriter().getErrorLog().WriteLog("sNote = "+this.getNote() );
  }


//////////////////////  Start Accessor & Mutator //////////////////////////
/**
* НАЗВАНИЕ МЕТОДА: Accessor & Mutator
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ:
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK 1.3
*/

  public String getGID(){
    return sGID;
  }

  public void setGID( String s ){
    setModify();
    sGID = s;
  }
 
  
  public String getName(){
    return sName;
  }

  public void setName( String s ){
    setModify();
    sName = s;
  }
  
  public String getEventDate(){
    return sEventDate;
  }

  public void setEventDate( String s ){
    setModify();
    sEventDate = s;
  }
  
   public int getEventType(){
    return iEventType;
  }

  public void setEventType( int i ){
    setModify();
    iEventType = i;
  }

  public int getGeoType(){
    return iGeoType;
  }

  public void setGeoType( int i ){
    setModify();
    iGeoType = i;
  }

  public String getGeoGpsX(){
    return sGeoGpsX;
  }

  public void setGeoGpsX( String s ){
    setModify();
    sGeoGpsX = s;
  }

  public String getGeoGpsY(){
    return sGeoGpsY;
  }

  public void setGeoGpsY( String s ){
    setModify();
    sGeoGpsY = s;
  }

  public String getGeoGpsDate(){
    return sGeoGpsDate;
  }

  public void setGeoGpsDate( String s ){
    setModify();
    sGeoGpsDate = s;
  }
  
  public String getGeoText(){
    return sGeoText;
  }

  public void setGeoText( String s ){
    setModify();
    sGeoText = s;
  }

   public void setDevType( String s ){
    setModify();
    sDevType = s;
  }

  public String getDevType(){
    return sDevType;
  }

     
  public String getDevCode(){
    return sDevCode;
  }

  public void setDevCode( String s ){
    setModify();
    sDevCode = s;
  }

  public String getDevModel(){
    return sDevModel;
  }

  public void setDevModel( String s ){
    setModify();
    sDevModel = s;
  }
  
  public String getDevManufNum(){
    return sDevManufNum;
  }

  public void setDevManufNum( String s ){
    setModify();
    sDevManufNum = s;
  }

  public String getDevSert(){
    return sDevSert;
  }

  public void setDevSert( String s ){
    setModify();
    sDevSert = s;
  }
  
  public String getDevSertValidDate(){
    return sDevSertValidDate;
  }

  public void setDevSertValidDate( String s ){
    setModify();
    sDevSertValidDate = s;
  }

   public String getDataObjNum(){
    return sDataObjNum;
  }

  public void setDataObjNum( String s ){
    setModify();
    sDataObjNum = s;
  }
 

  public String getDataDocNum(){
    return sDataDocNum;
  }

  public void setDataDocNum( String s ){
    setModify();
    sDataDocNum = s;
  }
  
  public String getDataDocDate(){
    return sDataDocDate;
  }

  public void setDataDocDate( String s ){
    setModify();
    sDataDocDate = s;
  }
  
  public String getData(){
    return sData;
  }

  public void setData( String s ){
    setModify();
    sData = s;
  }

  public String getNote(){
    return sNote;
  }

  public void setNote( String s ){
    setModify();
    sNote = s;
  }
  
}