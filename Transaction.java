public class Transaction {
    double amount;
    String category;
    String type;
    String date;

    public Transaction(double amount, String category, String type, String date) {
        this.amount = amount;
        this.category = category;
        this.type = type;
        this.date = date;
    }
}