
import persistacx.AdvancedDao;
import persistacx.DaoService;

public class Test2 extends AdvancedDao {

    public static void main(String[] args) {
        DaoService daoService = new DaoService();

        Test2 test2 = new Test2();
        System.out.println(test2.makeDBQuery(Bill.class));
        System.out.println(test2.makeDBQuery(Item.class));
        System.out.println(test2.makeDBQuery(Promotion.class));
        System.out.println(test2.makeDBQuery(Sale.class));
        System.out.println(test2.makeDBQuery(User.class));
        System.out.println(test2.makeDBQuery(UserRole.class));

        Item item = new Item(1, (float) 3500.00,"HIK20M","Hikusion cam");
        try {
            test2.save(item);
            item = new Item(2, (float) 5400.00,"HIK40M","Hikusion cam");
            test2.save(item);
            item = new Item(3, (float) 5750.00,"HIK80M","Hikusion cam");
            test2.save(item);
            item = new Item(4, (float) 6900.00,"HIK4CH","Hikusion DVR");
            test2.save(item);
            item = new Item(5, (float) 8800.00,"HIK8CH","Hikusion DVR");
            test2.save(item);
            item = new Item(6, (float) 11200.00,"HIK8CHG","Hikusion DVR");
            test2.save(item);
        } catch (Exception e) {
        e.printStackTrace();
    }

    }
}
