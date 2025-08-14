package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        int[] salary = new int[names.length];

        for (String record : data) {
            String[] dataParts = record.split(" ");
            LocalDate workDate = LocalDate.parse(dataParts[0], DATE_FORMATTER);
            String employeeName = dataParts[1];
            int workingHours = Integer.parseInt(dataParts[2]);
            int incomePerHour = Integer.parseInt(dataParts[3]);

            if ((workDate.isAfter(fromDate) || workDate.isEqual(fromDate))
                    && (workDate.isBefore(toDate) || workDate.isEqual(toDate))) {
                for (int i = 0; i < names.length; ++i) {
                    if (names[i].equals(employeeName)) {
                        salary[i] += incomePerHour * workingHours;
                        break;
                    }
                }
            }
        }

        StringBuilder reportBuilder = new StringBuilder("Report for period " + dateFrom
                + " - " + dateTo);
        for (int i = 0; i < names.length; ++i) {
            reportBuilder.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salary[i]);
        }
        return reportBuilder.toString();
    }
}
