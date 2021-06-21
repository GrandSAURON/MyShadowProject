/**
 * Copyright © 22020 - 2021 by RAS Technologies  All rights reserved.
 * Classes for working with document table in database
 * @author  Terminator
 * @version 210428_01
 */
package srcapp.myshadow.kbs;

/**
 * Class for working with document table in database
 * @author  Terminator
 * @version  210428_01
 * @since    JDK 1.8
 */
public class DocumentObject extends DBObject {
    private String fileName;
    private String fileType;
    private int typeDocId;
    private long taskId;

    public DocumentObject() {
        super();
    }

    public int getClassId() {
        return DataDefine.I_DOCUMENT_OBJ;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.setEdit(true);
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public int getTypeDocId() {
        return typeDocId;
    }

    public void setTypeDocId(int typeDocId) {
        this.typeDocId = typeDocId;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.setEdit(true);
        this.taskId = taskId;
    }
}
