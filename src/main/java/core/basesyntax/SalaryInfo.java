package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        int[] salary = new int[names.length];

        for (String record : data) {
            String[] parts = record.split(" ");
            LocalDate workDate = LocalDate.parse(parts[0], formatter);
            String name = parts[1];
            int hours = Integer.parseInt(parts[2]);
            int income = Integer.parseInt(parts[3]);

            if ((workDate.isAfter(from) || workDate.isEqual(from))
                    && (workDate.isBefore(to) || workDate.isEqual(to))) {
                for (int i = 0; i < names.length; ++i) {
                    if (names[i].equals(name)) {
                        salary[i] += income * hours;
                        break;
                    }
                }
            }
        }

        StringBuilder builder = new StringBuilder("Report for period " + dateFrom
                + " - " + dateTo);
        for (int i = 0; i < names.length; ++i) {
            builder.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salary[i]);
        }
        return builder.toString();
    }
}
