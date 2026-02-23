import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Cafeteria Billing ===");

        PricingCalculator pricing = new PricingCalculator();
        TaxPolicy taxPolicy = new DefTaxPolicy();
        DiscountPolicy discountPolicy = new DefDiscountPolicy();
        Invoicebuilder builder = new Invoicebuilder();

        FileStore store = new FileStore();
        InvoiceRepo repo = new FileStoreRepo(store);


        CafeteriaSystem sys = new CafeteriaSystem(pricing, taxPolicy, discountPolicy, builder, repo);

        sys.addToMenu(new MenuItem("M1", "Veg Thali", 80.00));
        sys.addToMenu(new MenuItem("C1", "Coffee", 30.00));
        sys.addToMenu(new MenuItem("S1", "Sandwich", 60.00));

        List<OrderLine> order = List.of(
                new OrderLine("M1", 2),
                new OrderLine("C1", 1)
        );

        sys.checkout("student", order);
    }
}