module viruses {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.ws;
    requires java.jws;
    requires com.sun.xml.ws;
    requires java.sql;

//    requires org.eclipse.persistence.asm;
    requires org.eclipse.persistence.core;
    requires org.eclipse.persistence.moxy;
//    requires org.eclipse.persistence.sdo;

    opens client to javafx.fxml;
    opens webservice to org.eclipse.persistence.moxy, org.eclipse.persistence.core, java.sql;
    exports webservice;
    exports client;
}