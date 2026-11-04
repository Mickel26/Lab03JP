module Lab03JP {
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.slf4j;
    requires java.xml.crypto;

    exports pl.edu.pwr.mf.jp;
    exports pl.edu.pwr.mf.jp.data;
    exports pl.edu.pwr.mf.jp.model;
    exports pl.edu.pwr.mf.jp.dao;
    exports pl.edu.pwr.mf.jp.exception;
}