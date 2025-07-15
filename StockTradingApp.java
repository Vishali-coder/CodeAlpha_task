import java.util.*;

class Stock {
    String symbol;
    String name;
    double price;

    public Stock(String symbol, String name, double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }
}

class Transaction {
    String type;
    Stock stock;
    int quantity;

    public Transaction(String type, Stock stock, int quantity) {
        this.type = type;
        this.stock = stock;
        this.quantity = quantity;
    }

    public double getTotal() {
        return stock.price * quantity;
    }
}

class Portfolio {
    Map<String, Integer> holdings = new HashMap<>();
    List<Transaction> history = new ArrayList<>();
    double balance = 10000.00;

    public void buyStock(Stock stock, int qty) {
        double totalCost = stock.price * qty;

        if (totalCost > balance) {
            System.out.println("⚠️ Not enough balance to buy!");
            return;
        }

        balance -= totalCost;
        holdings.put(stock.symbol, holdings.getOrDefault(stock.symbol, 0) + qty);
        history.add(new Transaction("BUY", stock, qty));

        System.out.println("✅ Bought " + qty + " shares of " + stock.name);
    }

    public void sellStock(Stock stock, int qty) {
        int currentQty = holdings.getOrDefault(stock.symbol, 0);

        if (qty > currentQty) {
            System.out.println("⚠️ Not enough shares to sell!");
            return;
        }

        balance += stock.price * qty;
        holdings.put(stock.symbol, currentQty - qty);
        history.add(new Transaction("SELL", stock, qty));

        System.out.println("✅ Sold " + qty + " shares of " + stock.name);
    }

    public void viewPortfolio(Map<String, Stock> market) {
        System.out.println("\n📊 Current Portfolio:");
        for (String symbol : holdings.keySet()) {
            int qty = holdings.get(symbol);
            if (qty > 0) {
                Stock s = market.get(symbol);
                System.out.println(s.name + " (" + symbol + ") - Shares: " + qty + ", Current Price: $" + s.price + ", Value: $" + (s.price * qty));
            }
        }
        System.out.printf("💰 Balance: $%.2f\n", balance);
    }

    public void viewHistory() {
        System.out.println("\n🧾 Transaction History:");
        for (Transaction t : history) {
            System.out.println(t.type + " - " + t.stock.name + " - Qty: " + t.quantity + ", Price: $" + t.stock.price + ", Total: $" + t.getTotal());
        }
    }
}

public class StockTradingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Portfolio portfolio = new Portfolio();

        // Simulated market
        Map<String, Stock> market = new HashMap<>();
        market.put("AAPL", new Stock("AAPL", "Apple Inc.", 175.00));
        market.put("GOOGL", new Stock("GOOGL", "Alphabet Inc.", 2800.00));
        market.put("AMZN", new Stock("AMZN", "Amazon.com Inc.", 3300.00));
        market.put("TSLA", new Stock("TSLA", "Tesla Inc.", 700.00));

        System.out.println("📈 Welcome to CodeAlpha Stock Trading Simulator");
        System.out.println("💼 Starting Balance: $10,000\n");

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("\n📊 Market Stocks:");
                    for (Stock s : market.values()) {
                        System.out.println(s.symbol + " - " + s.name + " - Price: $" + s.price);
                    }
                    break;

                case 2:
                    System.out.print("Enter stock symbol to buy: ");
                    String buySymbol = scanner.nextLine().toUpperCase();
                    if (market.containsKey(buySymbol)) {
                        System.out.print("Enter quantity: ");
                        int qty = scanner.nextInt();
                        portfolio.buyStock(market.get(buySymbol), qty);
                    } else {
                        System.out.println("❌ Stock not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter stock symbol to sell: ");
                    String sellSymbol = scanner.nextLine().toUpperCase();
                    if (market.containsKey(sellSymbol)) {
                        System.out.print("Enter quantity: ");
                        int qty = scanner.nextInt();
                        portfolio.sellStock(market.get(sellSymbol), qty);
                    } else {
                        System.out.println("❌ Stock not found!");
                    }
                    break;

                case 4:
                    portfolio.viewPortfolio(market);
                    break;

                case 5:
                    portfolio.viewHistory();
                    break;

                case 6:
                    System.out.println("👋 Thank you for trading with CodeAlpha!");
                    return;

                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }
}
