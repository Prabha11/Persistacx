import persistacx.AdvancedDao;
import persistacx.Dao;
import persistacx.DaoService;
import persistacx.DatabaseConnector;

import static persistacx.DatabaseConnector.getInstance;

public class Runner extends AdvancedDao {

    public static void main(String[] args){
//        User user = new User(1,"prabhath","1234");
        Runner runner = new Runner();
//        runner.save(user);
//
        Promotion promotion = new Promotion(1,null,"des", 20, 200);
        try {
            runner.save(promotion);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Item item = new Item("3", 222, promotion, "ItemName");
//
//        runner.save(item);
//
//        promotion = new Promotion();
//        promotion.setPromotionId(1);
//        Promotion opened = (Promotion) runner.open(promotion);
//        System.out.println(opened.getDescription());
//
//        Item getItem = new Item();
//        getItem.setItemId("3");
//        getItem = (Item) runner.open(getItem);
//        Promotion getPromotion = getItem.getPromotion();
//        getPromotion = (Promotion) runner.open(getPromotion);
//        System.out.println(getPromotion.getMaximumAmount());

        try {
            System.out.println(getInstance().setConnection("localhost:3306","simplitpos","root", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }

        DaoService daoService = new DaoService();

        Test2 test2 = new Test2();
        System.out.println(test2.makeDBQuery(Bill.class));
        System.out.println(test2.makeDBQuery(Item.class));
        System.out.println(test2.makeDBQuery(Promotion.class));
        System.out.println(test2.makeDBQuery(Sale.class));
        System.out.println(test2.makeDBQuery(User.class));
        System.out.println(test2.makeDBQuery(UserRole.class));
    }
}
