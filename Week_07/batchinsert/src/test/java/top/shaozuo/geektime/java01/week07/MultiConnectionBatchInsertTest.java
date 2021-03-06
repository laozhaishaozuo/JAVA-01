package top.shaozuo.geektime.java01.week07;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * 多链接情况下 插入数据
 * 
 * @author shaozuo
 *
 */
class MultiConnectionBatchInsertTest {

    private static final String INSERT_INNODB_SQL = "INSERT INTO shop_order"
            + "(sn, order_status, buyer_id,  total_amount, create_date, update_date)"
            + "VALUES(?, '01', '1', '10.1', now(), now())";

    private static final String INSERT_MYISAM_SQL = "INSERT INTO shop_order_myisam"
            + "(sn, order_status, buyer_id,  total_amount, create_date, update_date)"
            + "VALUES(?, '01', '1', '10.1', now(), now())";

    private static final int TOTAL = 100_0000;

    /**
     * 使用 InnoDB 引擎
     * 
     * 每次插入 10000 条数据 用时 10800ms
     * 
     * 每次插入 20000 条数据 用时 12200ms
     * 
     * 每次插入 50000 条数据 用时 12890ms
     * 
     * @throws SQLException
     */
    @ParameterizedTest
    @ValueSource(ints = { 10000, 20000, 50000 })
    void multiConnectionInnodbBatchInsert(int size) throws SQLException {
        long start = System.currentTimeMillis();
        testBatch(size, INSERT_INNODB_SQL);
        System.out.println("每次插入" + size + ",执行耗时：" + (System.currentTimeMillis() - start) + "ms");
    }

    /**
     * 使用 MyISAM 引擎
     * 
     * 每次插入 10000 条数据 用时 3905ms
     * 
     * 每次插入 20000 条数据 用时 4406ms
     * 
     * 每次插入 50000 条数据 用时 3328ms
     * 
     * @throws SQLException
     */
    @ParameterizedTest
    @ValueSource(ints = { 10000, 20000, 50000 })
    void multiConnectionMyisamBatchInsert(int size) throws SQLException {
        long start = System.currentTimeMillis();
        testBatch(size, INSERT_MYISAM_SQL);
        System.out.println("每次插入" + size + ",执行耗时：" + (System.currentTimeMillis() - start) + "ms");
    }

    private static class InsertWorker implements Runnable {

        private int startSn;
        private int endSn;
        private String sql;

        public InsertWorker(int startSn, int endSn, String sql) {
            super();
            this.startSn = startSn;
            this.endSn = endSn;
            this.sql = sql;
        }

        @Override
        public void run() {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            try {
                connection = JdbcUtils.getConnection();
                preparedStatement = connection.prepareStatement(sql);
                connection.setAutoCommit(false);
                for (int index = startSn; index <= endSn; index++) {
                    preparedStatement.setString(1, "" + index);
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
                connection.commit();
                connection.setAutoCommit(true);
                //System.out.println("插入startSn:" + startSn + ",endSn" + endSn);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                JdbcUtils.closeResource(connection, preparedStatement);
            }
        }

    }

    private void testBatch(int size, String sql) {
        ExecutorService executor = Executors
                .newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
        int times = TOTAL / size;
        for (int time = 0; time < times; time++) {
            int startSn = time * size;
            int endSn = startSn + size - 1;
            executor.execute(new InsertWorker(startSn, endSn, sql));
            System.out.println("提交第" + time + "次" + size + "插入");
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
