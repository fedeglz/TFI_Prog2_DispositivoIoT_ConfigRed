package ar.edu.utn.prog2int.Config;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

    // Inicia una transacción
    public static void beginTransaction(Connection con) throws SQLException {
        if (con != null) {
            con.setAutoCommit(false);
            System.out.println("Transacción iniciada.");
        }
    }

    // Confirma los cambios
    public static void commit(Connection con) throws SQLException {
        if (con != null) {
            con.commit();
            con.setAutoCommit(true);
            System.out.println("Transacción confirmada.");
        }
    }

    // Revierte los cambios
    public static void rollback(Connection con) {
        try {
            if (con != null) {
                con.rollback();
                con.setAutoCommit(true);
                System.out.println("Transacción revertida.");
            }
        } catch (SQLException e) {
            System.err.println("Error al hacer rollback: " + e.getMessage());
        }
    }
}

    

