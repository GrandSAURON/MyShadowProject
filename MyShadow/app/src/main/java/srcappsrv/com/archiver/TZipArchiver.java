package srcappsrv.com.archiver;


import java.io.*;
import java.util.zip.*;
import srcappsrv.com.archiver.TArchiverException;


/**
 * НАЗВАНИЕ КЛАССА: TZipArchiver
 * @version 	%I%
 * КРАТКОЕ ОПИСАНИЕ: Архиватор объектов.
 * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
 * <HR>
 * @since	JDK1.3
 */
public class TZipArchiver{
    //Константа типа проверки контролькной суммы по алгоритму Adler.
    //Этот алгоритм более быстрый, но дает не точные результаты.
    public final static int ADLER_CHECKSUMM = 1;

    //Константа типа проверки контролькной суммы по алгоритму CRC32.
    //Этот алгоритм точный, но имеет большее время работы.
    public final static int CRC32_CHECKSUMM = 2;

    // Константа используется для создания буфера распаковываемого объекта.
    // Размер буфера равен константе помноженной на объем упакованного
    // объекта.
    private final static int ARCHIVATION_LEVEL = 10;

    //Значение контрольной суммы полученной в результате упаковки.
    private long checksumm = 0;

    // Флаг определяющий необходимость проверки контрольной
    // суммы при распаковке.
    private boolean makeCheckSummVerification = true;

    // Статический экземпляр алгоритма проверки контрольной суммы
    // типа Adler32.
    private static Adler32 adler;

    // Статический экземпляр алгоритма проверки контрольной суммы
    // типа CRC32.
    private static CRC32 crc32;

    // Тип выбранного алгоритма проверки контрольной суммы:
    // ADLER_CHECKSUMM, или CRC32_CHECKSUMM.
    // По умолчанию используется алгоритм Adler.
    private int checksummAlgorithmType = ADLER_CHECKSUMM;


    /**
     * НАЗВАНИЕ МЕТОДА: TZipArchiver
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Создает новый экземляр класса архиватора.
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public TZipArchiver() {
        if ( adler == null ) adler = new Adler32();
        if ( crc32 == null ) crc32 = new CRC32();
    }


    /**
     * НАЗВАНИЕ МЕТОДА: setCheckSummAlgoritm
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Устанавливает тип алгоритма проверки контрольной суммы.
     * Пареметр может быть равен константе класса ADLER_CHECKSUMM (по умолчанию),
     * или CRC32_CHECKSUMM.
     * @param checksummAlgorithm  int
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public void setCheckSummAlgoritm( int checksummAlgorithm ) {
        this.checksummAlgorithmType = checksummAlgorithm;
    }


    /**
     * НАЗВАНИЕ МЕТОДА: getCheckSummAlgoritm
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Возвращает тип алгоритма проверки контрольной суммы при
     * распаковке двоичного массива. Тип равен константе класса CRC32_CHECKSUMM, или
     * ADLER_CHECKSUMM.
     * @return int тип установленного алгоритма проверки контрольной
     * суммы.
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public int getCheckSummAlgoritm() {
        return checksummAlgorithmType;
    }


    /**
     * НАЗВАНИЕ МЕТОДА:
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Установка проверки контрольной суммы.
     * @param makeCheckSummVerification boolean значение флага
     * проверки контрольной суммы. Если флаг установлен в true, то
     * в результате распаковки двоичного потока будет сравниваться полученная
     * контрольная сумма с заданной в методе распаковки.
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public void setCheckSummVerification(boolean makeCheckSummVerification) {
        this.makeCheckSummVerification = makeCheckSummVerification;
    }


    /**
     * НАЗВАНИЕ МЕТОДА:
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Возвращает значение флага проверки контрольной суммы в результате
     * распаковке.
     * @return boolean значение флага проверки контрольной суммы в результате
     * распаковке. Если true, проверка будет выполняться, иначе false.
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public boolean isCheckSummVerificationEnabled() {
        return makeCheckSummVerification;
    }


    /**
     * НАЗВАНИЕ МЕТОДА:
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ:Выполняет упаковку заданного объекта. Обязательным условием является
     * сериализуемость передаваемых в метод объектов. В результате
     * упаковки назначается контрольная сумма исходного объекта, которую
     * можно получить с помощью метода getCheckSumm().
     * @param object Serializable упаковываемый объект.
     * @throws ArchiverException исключение возникающее в результате
     * упаковки объекта.
     * @return byte[] массив байт полученный в результате упаковки,
     * либо null.
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public byte[] archiveObject( Serializable object ) throws TArchiverException{
        return archiveObject( object, null );
    }

    /**
     * НАЗВАНИЕ МЕТОДА:
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Выполняет упаковку заданного объекта с указанным паролем.
     * Обязательным условием является сериализуемость передаваемых
     * в метод объектов. В результате упаковки назначается контрольная сумма
     * исходного объекта, которую можно получить
     * с помощью метода getCheckSumm().
     * @param object Serializable упаковываемый объект.
     * @param password String
     * @throws ArchiverException исключение возникающее в результате
     * упаковки объекта.
     * @return byte[] массив байт полученный в результате упаковки,
     * либо null.
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public byte[] archiveObject(Serializable object, String password) throws  TArchiverException {
        if ( object == null ) return null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.flush();
            oos.close();

            byte[] input = baos.toByteArray();

            if (input == null || input.length == 0){
                return null;
            }
            // Подсчет контрольной суммы массива
            // байтов архивируемого объекта;
            Checksum checkSummAlgorithm = null;

            if (checksummAlgorithmType == ADLER_CHECKSUMM) {
                checkSummAlgorithm = adler;
            }
            else {
                checkSummAlgorithm = crc32;
            }
            checkSummAlgorithm.reset();
            checkSummAlgorithm.update( input, 0, input.length );
            checksumm = checkSummAlgorithm.getValue();

            byte[] compressedBytes = new byte[input.length];

            // Архивация массива байтов объекта.
            Deflater compresser = new Deflater();
            compresser.setInput( input );
            compresser.finish();
            int compressedDataLength = compresser.deflate( compressedBytes );
            byte[] compressedBytesRet = new byte[compressedDataLength];
            if ( password == null ){
                for ( int i = 0; i < compressedDataLength; i++ ) {
                    compressedBytesRet[i] = compressedBytes[i];
                }
            }else{
                byte[] xPassword = password.getBytes("UTF-8");
                int iPassLen = password.length();
                for ( int i = 0; i < compressedDataLength; i++ ) {
                    compressedBytesRet[i] = (byte)(compressedBytes[i] ^ xPassword[i%iPassLen]);
                }
            }
            compressedBytes = null;
            return compressedBytesRet;
        }
        catch ( Exception ex ) {
            throw new TArchiverException(ex.getMessage(), ex);
        }
    }


    /**
     * НАЗВАНИЕ МЕТОДА:
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Распаковывает объект из заданного массива байтов.
     * @param archive byte[]  массив байтов содержащий сериализованный,
     * упакованный объект.
     * @throws ArchiverException исключение возникающее при неудачном
     * выполнении операции распаковки. getMessage() содержит причину
     * неудачи.
     * @return Object объект полученный в результате распаковки.
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public Object extractObject( byte[] archive ) throws TArchiverException {
        boolean oldState = makeCheckSummVerification;
        makeCheckSummVerification = false;
        Object result = extractObject( archive, 0 );
        makeCheckSummVerification = oldState;
        return result;
    }


    /**
     * НАЗВАНИЕ МЕТОДА:
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Распаковывает объект из заданного массива байтов.
     * @param archive byte[]  массив байтов содержащий сериализованный,
     * упакованный объект.
     * @param checksumm long значение контрольной суммы полученное в результате
     * упаковки объекта.
     * @throws ArchiverException исключение возникающее при неудачном
     * выполнении операции распаковки. getMessage() содержит причину
     * неудачи.
     * @return Object объект полученный в результате распаковки.
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public Object extractObject(byte[] archive, long checksumm) throws  TArchiverException {
        return extractObject( archive, checksumm, null );
    }


    /**
     * НАЗВАНИЕ МЕТОДА:
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Распаковывает объект из заданного массива байтов.
     * @param archive byte[] массив байтов содержащий сериализованный,
     * упакованный объект.
     * @param checksumm long значение контрольной суммы полученное в результате
     * упаковки объекта.
     * @param password пароль назначенный при упаковке объекта, либо null
     * в противном случае.
     * @throws ArchiverException исключение возникающее при неудачном
     * выполнении операции распаковки. getMessage() содержит причину
     * неудачи.
     * @return Object объект полученный в результате распаковки.
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public Object extractObject( byte[] archive, long checksumm, String password ) throws  TArchiverException {
        if ( archive == null ) return null;
        try {
            if ( password != null ) {
                byte[] xPassword = password.getBytes("UTF-8");
                int iPassLen = password.length();
                for ( int i = 0; i < archive.length; i++ ) {
                    archive[i] = ( byte )(archive[i] ^ xPassword[i%iPassLen]);
                }
            }

            byte[] result = new byte[archive.length * ARCHIVATION_LEVEL];
            Inflater decompresser = new Inflater();
            decompresser.setInput( archive, 0, archive.length );
            int uncompressedDataSize = decompresser.inflate( result );
            decompresser.end();
            // Подсчет контрольной суммы массива
            // байтов распаковываемого объекта;
            Checksum checkSummAlgorithm = null;
            if ( makeCheckSummVerification ) {
                if (checksummAlgorithmType == ADLER_CHECKSUMM) {
                    checkSummAlgorithm = adler;
                }
                else{
                    checkSummAlgorithm = crc32;
                }

                checkSummAlgorithm.reset();
                checkSummAlgorithm.update(result, 0, uncompressedDataSize);
                long decompressedChecksumm = checkSummAlgorithm.getValue();

                if (decompressedChecksumm != checksumm)
                    throw new TArchiverException( "Контрольная сумма не совпадает." );
            }

            ByteArrayInputStream bais = new ByteArrayInputStream(result);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object object = ois.readObject();

            result = null;

            return object;
        }
        catch (Exception ex) {
            throw new TArchiverException(ex.getMessage(), ex);
        }
    }


    /**
     * НАЗВАНИЕ МЕТОДА:
     * @version 	%I%
     * КРАТКОЕ ОПИСАНИЕ: Возвращает значение контрольной суммы установленное
     * последней операцией архивации объекта.
     * @return long значение контрольной суммы.
     * ДАТА ПОСЛЕДНЕГО ИЗМЕНЕНИЯ: %G%
     * <HR>
     * @since	JDK 1.4
     */
    public long getCheckSumm() {
        return checksumm;
    }


/**
 * Архиватор объектов.
 * <code><pre>
 * // Создание экземпляра архиватора объектов;
 * ZipArchiver zipArchiver = new ZipArchiver();<br>
 *
 * try {
 *     // Подготовка исходных данных для упаковки;
 *     ArrayList objects = new ArrayList(200);<br>
 *
 *     for (int i = 0; i < 200; i++) {
 *         objects.add(new Float(i));
 *     }<br>
 *
 *     // Упаковка контейнера объектов с указанным паролем.
 *     byte[] archive = zipArchiver.archiveObject( objects, "password" );<br>
 *
 *     // Получение контрольной суммы;
 *     long checkSumm = zipArchiver.getCheckSumm();<br>
 *
 *     // Распаковка полученного массива байтов
 *     // содержащих упакованный объект с проверкой контрольной суммы
 *     // и паролем;
 *     ArrayList sourceContainer = (ArrayList)zipArchiver.extractObject(
 *             archive, checkSumm, "password");
 * }<br>
 * catch (ArchiverException ex) {
 *     ex.printStackTrace(System.err);
 * }
 * </pre></code>
 * @version %V%
 */

}
