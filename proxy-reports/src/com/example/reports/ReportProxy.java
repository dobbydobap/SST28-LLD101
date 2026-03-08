package com.example.reports;

public class ReportProxy implements Report {
    private final String reportId;
    private final String title;
    private final String classification;
    private RealReport realReport;

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        if (!isAuthorized(user)) {
            System.out.println(
                    "ACCESS DENIED -> user=" + user.getName() +
                            " role=" + user.getRole() +
                            " cannot open report " + reportId +
                            " (" + classification + ")"
            );
            return;
        }

        if (realReport == null) {
            System.out.println("[proxy] creating RealReport for " + reportId);
            realReport = new RealReport(reportId, title, classification);
        } else {
            System.out.println("[proxy] reusing cached RealReport for " + reportId);
        }

        realReport.display(user);
    }

    private boolean isAuthorized(User user) {
        String role = user.getRole();

        if ("PUBLIC".equals(classification)) {
            return true;
        }
        if ("FACULTY".equals(classification)) {
            return "FACULTY".equals(role) || "ADMIN".equals(role);
        }
        if ("ADMIN".equals(classification)) {
            return "ADMIN".equals(role);
        }
        return false;
    }
}