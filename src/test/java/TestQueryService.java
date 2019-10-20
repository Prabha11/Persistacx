import persistacx.Query.QueryService;

public class TestQueryService {
    public static void main(String[] args) {
        testGetCommaSeparatedStringFromStringArray();
    }

    public static void testGetCommaSeparatedStringFromStringArray(){
        QueryService queryService = new QueryService();
        String[] array = {"aaa","bbb","ccc"};
        System.out.println(queryService.getCommaSeparatedStringFromStringArray(array));
    }
}
