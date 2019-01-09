package ssm.codegen.domain.support;

/**
 * @author jinqinghua@gmail.com
 * @version 2013年11月29日 下午9:55:48
 * @see java.sql.DatabaseMetaData#getColumns(String, String, String, String)
 * @see <a href="http://technet.microsoft.com/zh-cn/library/ms379048.aspx">ColumnResultSet</a>
 */
public class ColumnResultSetColumns {
    private String tableCat;
    private String tableSchem;// Schema
    private String tableName;// 表名
    private String columnName;// 列名
    private int dataType;// 来自 java.sql.Types 的 SQL 数据类型
    private String typeName;// 数据类型的名称
    private int columnSize;// 列的精度
    private int bufferLength;
    private int decimalDigits;// 列的小数位数
    private int numPrecRadix;
    private int nullable;// 指示列是否可以为 Null可以是下列值之一：columnNoNulls (0)columnNullable (1)
    private String remarks;// 与列关联的注释,对于此列，SQL Server 始终返回 Null
    private String columnDef;// 列的默认值
    private int sqlDataType;
    private int sqlDatetimeSub;
    private int charOctetLength;
    private int ordinalPosition;
    private String isNullable;// 指示列是否允许 Null 值
    private String scopeCatlog;
    private String scopeSchema;
    private String scopeTable;
    private String sourceDataType;
    private String isAutoincrement;// 如果列是自动递增的，则为“是”如果列不是自动递增的，则为“否”如果驱动程序无法确定列是否为自动递增，则为 ""（空字符串）1

    protected ColumnResultSetColumns() {
        // Do nothing
    }

    public String getTableCat() {
        return tableCat;
    }

    public void setTableCat(String tableCat) {
        this.tableCat = tableCat;
    }

    public String getTableSchem() {
        return tableSchem;
    }

    public void setTableSchem(String tableSchem) {
        this.tableSchem = tableSchem;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public int getBufferLength() {
        return bufferLength;
    }

    public void setBufferLength(int bufferLength) {
        this.bufferLength = bufferLength;
    }

    public int getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    public int getNumPrecRadix() {
        return numPrecRadix;
    }

    public void setNumPrecRadix(int numPrecRadix) {
        this.numPrecRadix = numPrecRadix;
    }

    public int getNullable() {
        return nullable;
    }

    public void setNullable(int nullable) {
        this.nullable = nullable;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getColumnDef() {
        return columnDef;
    }

    public void setColumnDef(String columnDef) {
        this.columnDef = columnDef;
    }

    public int getSqlDataType() {
        return sqlDataType;
    }

    public void setSqlDataType(int sqlDataType) {
        this.sqlDataType = sqlDataType;
    }

    public int getSqlDatetimeSub() {
        return sqlDatetimeSub;
    }

    public void setSqlDatetimeSub(int sqlDatetimeSub) {
        this.sqlDatetimeSub = sqlDatetimeSub;
    }

    public int getCharOctetLength() {
        return charOctetLength;
    }

    public void setCharOctetLength(int charOctetLength) {
        this.charOctetLength = charOctetLength;
    }

    public int getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(int ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public String getScopeCatlog() {
        return scopeCatlog;
    }

    public void setScopeCatlog(String scopeCatlog) {
        this.scopeCatlog = scopeCatlog;
    }

    public String getScopeSchema() {
        return scopeSchema;
    }

    public void setScopeSchema(String scopeSchema) {
        this.scopeSchema = scopeSchema;
    }

    public String getScopeTable() {
        return scopeTable;
    }

    public void setScopeTable(String scopeTable) {
        this.scopeTable = scopeTable;
    }

    public String getSourceDataType() {
        return sourceDataType;
    }

    public void setSourceDataType(String sourceDataType) {
        this.sourceDataType = sourceDataType;
    }

    public String getIsAutoincrement() {
        return isAutoincrement;
    }

    public void setIsAutoincrement(String isAutoincrement) {
        this.isAutoincrement = isAutoincrement;
    }

}
