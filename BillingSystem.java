abstract class Subscriber {
    String subscriberName;
    long subscriberId;
    long subscriberPhoneNumber;
    String subscriberPlanName;
    int subscriberFreeCalls;
    double subscriberPackageCost;
    int subscriberExtraCallsInMinutes;
    double subscriberExtraCallCostPerMinutes;
    double subscriberTaxOnBill = 0.10; // 10%

    // ANSI Formatting Constants
    public static final String RESET   = "\u001B[0m";
    public static final String BOLD    = "\u001B[1m";
    public static final String CYAN    = "\u001B[36m";
    public static final String GREEN   = "\u001B[32m";
    public static final String YELLOW  = "\u001B[33m";
    public static final String BLUE    = "\u001B[34m";
    public static final String PURPLE  = "\u001B[35m";

    public abstract double calculateBill();

    public void getSubscriberDetails() {
        System.out.printf("  %-28s : %s%s%s%n", "Subscriber Name", BOLD, subscriberName, RESET);
        System.out.printf("  %-28s : %d%n", "Subscriber ID", subscriberId);
        System.out.printf("  %-28s : %d%n", "Phone Number", subscriberPhoneNumber);
        System.out.printf("  %-28s : %s%n", "Plan Name", subscriberPlanName);
        System.out.printf("  %-28s : %d mins%n", "Free Calls", subscriberFreeCalls);
        System.out.printf("  %-28s : $%.2f%n", "Package Cost", subscriberPackageCost);
        System.out.printf("  %-28s : %d mins @ $%.2f/min ($%.2f)%n", 
            "Extra Calls", subscriberExtraCallsInMinutes, subscriberExtraCallCostPerMinutes, 
            (subscriberExtraCallsInMinutes * subscriberExtraCallCostPerMinutes));
    }
}

class MobileSubscriber extends Subscriber {
    int roamingNoOfMinutes;
    double roamingCostPerMinute;

    @Override
    public void getSubscriberDetails() {
        System.out.println(CYAN + "┌───────────────────────────────────────────────────────────┐" + RESET);
        System.out.println(CYAN + "│                 MOBILE SUBSCRIBER INVOICE                 │" + RESET);
        System.out.println(CYAN + "├───────────────────────────────────────────────────────────┤" + RESET);
        super.getSubscriberDetails();
        System.out.printf("  %-28s : %d mins @ $%.2f/min ($%.2f)%n", 
            "Roaming Calls", roamingNoOfMinutes, roamingCostPerMinute, 
            (roamingNoOfMinutes * roamingCostPerMinute));
        System.out.printf("  %-28s : %.0f%%%n", "Tax Rate", subscriberTaxOnBill * 100);
        System.out.println(CYAN + "└───────────────────────────────────────────────────────────┘" + RESET);
    }

    @Override
    public double calculateBill() {
        double bill = subscriberPackageCost +
                      (subscriberExtraCallsInMinutes * subscriberExtraCallCostPerMinutes) +
                      (roamingNoOfMinutes * roamingCostPerMinute);
        bill += bill * subscriberTaxOnBill;
        return bill;
    }
}

class LandLineSubscriber extends Subscriber {
    int noOfSTDCallMinutes;
    double costPerEachSTDMinute;

    @Override
    public void getSubscriberDetails() {
        System.out.println(PURPLE + "┌───────────────────────────────────────────────────────────┐" + RESET);
        System.out.println(PURPLE + "│                LANDLINE SUBSCRIBER INVOICE                │" + RESET);
        System.out.println(PURPLE + "├───────────────────────────────────────────────────────────┤" + RESET);
        super.getSubscriberDetails();
        System.out.printf("  %-28s : %d mins @ $%.2f/min ($%.2f)%n", 
            "STD Calls", noOfSTDCallMinutes, costPerEachSTDMinute, 
            (noOfSTDCallMinutes * costPerEachSTDMinute));
        System.out.printf("  %-28s : %.0f%%%n", "Tax Rate", subscriberTaxOnBill * 100);
        System.out.println(PURPLE + "└───────────────────────────────────────────────────────────┘" + RESET);
    }

    @Override
    public double calculateBill() {
        double bill = subscriberPackageCost +
                      (subscriberExtraCallsInMinutes * subscriberExtraCallCostPerMinutes) +
                      (noOfSTDCallMinutes * costPerEachSTDMinute);
        bill += bill * subscriberTaxOnBill;
        return bill;
    }
}

public class BillingSystem {
    public static void main(String[] args) {
        System.out.println(Subscriber.BOLD + Subscriber.BLUE + "=============================================================" + Subscriber.RESET);
        System.out.println(Subscriber.BOLD + Subscriber.BLUE + "             TELECOM BILLING & INVOICING SYSTEM              " + Subscriber.RESET);
        System.out.println(Subscriber.BOLD + Subscriber.BLUE + "=============================================================" + Subscriber.RESET);
        System.out.println();

        MobileSubscriber mobile = new MobileSubscriber();
        mobile.subscriberName = "John Doe";
        mobile.subscriberId = 1001;
        mobile.subscriberPhoneNumber = 9876543210L;
        mobile.subscriberPlanName = "Postpaid Plan";
        mobile.subscriberFreeCalls = 100;
        mobile.subscriberPackageCost = 500.0;
        mobile.subscriberExtraCallsInMinutes = 50;
        mobile.subscriberExtraCallCostPerMinutes = 1.5;
        mobile.roamingNoOfMinutes = 20;
        mobile.roamingCostPerMinute = 2.0;

        mobile.getSubscriberDetails();
        System.out.printf(Subscriber.BOLD + Subscriber.GREEN + "  ➤ Total Mobile Bill (Incl. Tax)   : $%.2f%n" + Subscriber.RESET, mobile.calculateBill());

        System.out.println();

        LandLineSubscriber landline = new LandLineSubscriber();
        landline.subscriberName = "Jane Smith";
        landline.subscriberId = 2001;
        landline.subscriberPhoneNumber = 1234567890L;
        landline.subscriberPlanName = "Landline Plan";
        landline.subscriberFreeCalls = 50;
        landline.subscriberPackageCost = 300.0;
        landline.subscriberExtraCallsInMinutes = 30;
        landline.subscriberExtraCallCostPerMinutes = 1.0;
        landline.noOfSTDCallMinutes = 40;
        landline.costPerEachSTDMinute = 2.5;

        landline.getSubscriberDetails();
        System.out.printf(Subscriber.BOLD + Subscriber.GREEN + "  ➤ Total Landline Bill (Incl. Tax) : $%.2f%n" + Subscriber.RESET, landline.calculateBill());

        System.out.println();
        System.out.println(Subscriber.BOLD + Subscriber.BLUE + "=============================================================" + Subscriber.RESET);
    }
}
