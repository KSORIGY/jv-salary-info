package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_HOURS = 2;
    private static final int INDEX_INCOME_PER_HOUR = 3;
    
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        int[] salary = new int[names.length];

        for (String record : data) {
            String[] dataParts = record.split(" ");
            LocalDate workDate = LocalDate.parse(dataParts[INDEX_DATE], DATE_FORMATTER);
            String employeeName = dataParts[INDEX_NAME];
            int workingHours = Integer.parseInt(dataParts[INDEX_HOURS]);
            int incomePerHour = Integer.parseInt(dataParts[INDEX_INCOME_PER_HOUR]);

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
