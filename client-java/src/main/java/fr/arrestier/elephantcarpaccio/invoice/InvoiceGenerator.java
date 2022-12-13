package fr.arrestier.elephantcarpaccio.invoice;

import fr.arrestier.elephantcarpaccio.cart.Cart;

public class InvoiceGenerator {
    public static String getInvoiceFromCart(Cart cart) {



        double total = 0;
        double special = 0.1;
        for (int i = 0; i < cart.prices.size(); i++) {
            if (cart.reduction.toUpperCase() == "SPECIAL") {
                total += cart.prices.get(i) * cart.quantities.get(i) * (1 - Math.min(0.5, special));
                special += 0.1;
            }else if (cart.reduction.toUpperCase() == "HALF_FIRST" && i == 0) {
                total += cart.prices.get(i) * cart.quantities.get(i) * 0.5;
            } else if (cart.reduction.toUpperCase() == "HALF_LAST" && i == cart.prices.size() - 1) {
                total += cart.prices.get(i) * cart.quantities.get(i) * 0.5;
            } else {
                total += cart.prices.get(i) * cart.quantities.get(i);
            }
        }

        switch (cart.reduction.toUpperCase()) {
            case "HALF":
                total *= 0.5;
                break;
            case "TENTH":
                total *= 0.9;
                break;
            case "HALF_FIRST":
            case "HALF_LAST":
                break;
            default:
                System.out.println(cart.reduction);
                break;
        }

        String currency = "";
        switch (cart.country.toUpperCase()) {
            case "FR":
                currency = "€";
                break;
            case "UK":
                total *= 1.42;
                currency = "£";
                break;
            case "US":
                total *= 1.314;
                currency = "$";
                break;
            default:
                System.out.println(cart.country);
                break;
        }

        return String.format("%.2f %s", total, currency);
    }
}
