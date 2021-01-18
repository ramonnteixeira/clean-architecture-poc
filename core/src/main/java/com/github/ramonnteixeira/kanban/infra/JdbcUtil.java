package com.github.ramonnteixeira.kanban.infra;

import org.sql2o.Sql2o;

public class JdbcUtil {

	private Sql2o connection;
	public static final JdbcUtil INSTANCE = new JdbcUtil();

	private JdbcUtil(){
	}

	public Sql2o getConnection() {
        if (connection == null) {
            String dbpath = "./resources/app.db";
            String url = "jdbc:h2:" + dbpath;
            String user = "sa";
            connection = new Sql2o(url, user, "");

            initSchema();
        }
        return connection;
    }

    private void initSchema() {
	    try (var conn = connection.open()) {
            var is = getClass().getResourceAsStream("/create.sql");
            var sqls = new String(is.readAllBytes(), "UTF-8").split(";");

            for (var sql: sqls) {
                conn.getJdbcConnection().prepareStatement(sql).execute();
            }

        } catch (Exception e) {
	        e.printStackTrace();
        }
    }
}
