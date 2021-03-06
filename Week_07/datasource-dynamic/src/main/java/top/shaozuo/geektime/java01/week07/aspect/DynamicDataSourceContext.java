package top.shaozuo.geektime.java01.week07.aspect;

public class DynamicDataSourceContext {

    private static final ThreadLocal<String> container = new ThreadLocal<>();

    public static void setDataSource(String dsName) {
        System.out.println("设置数据源：" + dsName);
        container.set(dsName);
    }

    public static String getDataSource() {
        System.out.println("获取数据源：" + container.get());
        return container.get();
    }

    public static void clearDataSource() {
        container.remove();
    }

}