package DatabaseConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class DatabaseFashion {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setDataSource (DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void getList() {
//        String sql = "SELECT * FROM Test";
//        List<TestEntity> list = jdbcTemplate.query(sql, new RowMapper<TestEntity>() {
//            public TestEntity mapRow(ResultSet resultSet, int i) throws SQLException {
//                TestEntity test = new TestEntity();
//                test.setTestContent(resultSet.getString("contentTest")) ;
//                test.setDescription(resultSet.getString("description"));
//                return test;
//            }
//        });
//        for (TestEntity item : list) {
//            System.out.println(item.getTestContent()+"---"+item.getDescription());
//        }
    }


}
