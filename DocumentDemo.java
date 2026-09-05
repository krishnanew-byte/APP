interface Confidential {
}

class Report {
    String name;

    Report(String name) {
        this.name = name;
    }
}

class SalaryReport implements Confidential {
    String name;

    SalaryReport(String name) {
        this.name = name;
    }
}

class PublicDocument {
    String name;

    PublicDocument(String name) {
        this.name = name;
    }
}

public class DocumentDemo {
    public static void main(String[] args) {

        Object d1 = new Report("Annual Report");
        Object d2 = new SalaryReport("Salary Report");
        Object d3 = new PublicDocument("Public Notice");

        if (d1 instanceof Confidential)
            System.out.println("Annual Report is Confidential");
        else
            System.out.println("Annual Report is not Confidential");

        if (d2 instanceof Confidential)
            System.out.println("Salary Report is Confidential");
        else
            System.out.println("Salary Report is not Confidential");

        if (d3 instanceof Confidential)
            System.out.println("Public Notice is Confidential");
        else
            System.out.println("Public Notice is not Confidential");
    }
}