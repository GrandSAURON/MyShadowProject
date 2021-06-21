package srcappsrv.kbs;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import srcappsrv.kbs.TDateStoreDisp;
import srcappsrv.kbs.TClassDefine;



/**
* НАЗВАНИЕ КЛАССА: TFacadeStore
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ:
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.3
*/
public class TFacadeStore extends TDateStoreDisp implements java.io.Serializable {

  private TAbstractFactory factory = null;

  public TFacadeStore(){
    setErrorLog( new TErrorLog() );
  }

  public void Login( String sLogin, String sPass ) throws Exception{
    writeNonWorkMsg();
  }

  public void Logout() throws Exception{
    writeNonWorkMsg();
  }
  
/**
* НАЗВАНИЕ МЕТОДА: onLoginSetProp
* @author 	Sergey S. Romas
* @version 	%I%
* КРАТКОЕ ОПИСАНИЕ: Установить дополнительные параметры соединения с базой данных
* ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
* <HR>
* @since	JDK1.7
*/  
 protected void onLoginSetProp( Properties props ){
    writeNonWorkMsg();
 }


  public int getClassId( int iObjId ){
    writeNonWorkMsg();
    return TClassDefine.I_CLASS_OR_ID_NON;
  }

  public String getClassInterface( int iObjId ){
    writeNonWorkMsg();
    return null;
  }

  public int getObjIdForInternalId( int iInternalId ){
    writeNonWorkMsg();
    return TClassDefine.I_CLASS_OR_ID_NON;
  }


  public void setFilter( String s ){
    writeNonWorkMsg();
  }

  public String getFilter( String sBuf ){
    writeNonWorkMsg();
    return null;
  }

  public void setFilterFromObj( Object obj ){
    writeNonWorkMsg();
  }

  public void setFieldOrder( String sFieldList ){
     writeNonWorkMsg();
  }

  public String getFieldOrder(){
     writeNonWorkMsg();
     return null;
  }


  public void setNoOrder(){
    writeNonWorkMsg();
  }

  public void setAscOrder(){
    writeNonWorkMsg();
  }

  public void setDescOrder(){
    writeNonWorkMsg();
  }

  public void  setDistinct( boolean b ){
    writeNonWorkMsg();
  }

  public void clearFilter(){
    writeNonWorkMsg();
  }

  public int getFirstObjId( String  sInterface ){
     writeNonWorkMsg();
     return TClassDefine.I_CLASS_OR_ID_NON;
  }

  public int getNextObjId(){
    writeNonWorkMsg();
    return TClassDefine.I_CLASS_OR_ID_NON;
  }

  public int getFirstRelId( int iObjId, int iTypeRel ){
    writeNonWorkMsg();
    return TClassDefine.I_CLASS_OR_ID_NON;
  }

  public int getFirstRelId( int iObjId, int iTypeRel, String sInterface ){
    writeNonWorkMsg();
    return TClassDefine.I_CLASS_OR_ID_NON;
  }


  public int getNextRelId(){
    writeNonWorkMsg();
    return TClassDefine.I_CLASS_OR_ID_NON;
  }

  public int getFirstSysRelId( int iObjId, int iTypeRel ){
    writeNonWorkMsg();
    return TClassDefine.I_CLASS_OR_ID_NON;
  }

  public int getNextSysRelId(){
    writeNonWorkMsg();
    return TClassDefine.I_CLASS_OR_ID_NON;
  }

  public void setNextRelId( int iObjId, int iObjId1, int iTypeRel ){
    writeNonWorkMsg();
  }

  public void writeData( TDBObject obj ) throws Exception{
    writeNonWorkMsg();
  }

  public void readData( TDBObject obj ) throws Exception{
    writeNonWorkMsg();
  }

  public ArrayList getObjects( int iUID, int iMasterId, int iSubType ){
    writeNonWorkMsg();
    return null;
  }

  public ArrayList getRelations( int iUID, int iMasterId, int iSubType ){
    writeNonWorkMsg();
    return null;
  }

  public ArrayList callUserFunction( int iUID, int iMasterId, int iSubType ){
    writeNonWorkMsg();
    return null;
  }

  public ArrayList callUserFunction( int iUID, ArrayList oParams ){
    writeNonWorkMsg();
    return null;
  }

 public ArrayList callScriptGenerator( int iUID, ArrayList oParams,  int iSubType ){
    writeNonWorkMsg();
    return null;
  }

  public ArrayList callDataGenerator( int iUID, ArrayList oParams,  int iSubType ){
    writeNonWorkMsg();
    return null;
  }

  public int copyData( String sNewName, TDBObject obj, int iFlags ){
    writeNonWorkMsg();
    return TClassDefine.I_CLASS_OR_ID_NON;
  }

  public void delData( int iId, int iUID ){
    writeNonWorkMsg();
  }

  public ArrayList delObject( TDBObject obj ){
     writeNonWorkMsg();
     return null;
  }

  public boolean writeDataList( ArrayList vect ){
     writeNonWorkMsg();
     return false;
  }


  public ArrayList AddNodeInOntology( int iTypeNode, int iOntologyId, String sName, int iParentId ){
     writeNonWorkMsg();
     return null;
  }

  public int FilterData( int iModelFilter ){
     writeNonWorkMsg();
     return -1;
  }

  public String getBlobFormType(int iBlobId) {
    writeNonWorkMsg();
    return null;
  }

  public void setBlobFormType(int iBlobId, String sFormType) {
    writeNonWorkMsg();
  }

  public boolean Flush(){
     writeNonWorkMsg();
     return false;
  }

  public boolean UnFlush(){
     writeNonWorkMsg();
     return false;
  }

  public void setFactory (TAbstractFactory value) {
    if (factory != null) {
      ErrorLog.WriteLog("Factory is not able change!");
    }
    else {
      factory = value;
    }
  }

  public TAbstractFactory getFactory () {
    return factory;
  }

  //////////////////////  End Accessor & Mutator //////////////////////////
}
