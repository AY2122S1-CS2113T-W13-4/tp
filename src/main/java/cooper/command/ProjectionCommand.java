package cooper.command;

import cooper.exceptions.InvalidAccessException;
import cooper.exceptions.LogoutException;
import cooper.finance.FinanceCommand;
import cooper.finance.FinanceManager;
import cooper.finance.Projection;
import cooper.resources.ResourcesManager;
import cooper.ui.Ui;
import cooper.verification.SignInDetails;
import cooper.finance.FinanceManager;
import cooper.verification.UserRole;

public class ProjectionCommand extends Command {

    public int years;
    public int oldIndex = 9;
    public FinanceCommand financeFlag;

    public ProjectionCommand(int years, FinanceCommand financeFlag) {
        super();
        this.years = years;
        this.financeFlag = financeFlag;
    }

    @Override
    public void execute(SignInDetails signInDetails, ResourcesManager resourcesManager)
            throws InvalidAccessException, LogoutException {
        UserRole userRole = signInDetails.getUserRole();
        FinanceManager financeManager = resourcesManager.getFinanceManager(userRole);
        if (financeManager == null || financeFlag == FinanceCommand.IDLE) {
            Ui.printAdminHelp();
            Ui.printGeneralHelp();
            throw new InvalidAccessException();
        }

        if (financeFlag == FinanceCommand.PROJ && financeManager.cooperCashFlowStatement != null) {
            int newFreeCF = financeManager.calculateFreeCashFlow(
                    financeManager.cooperCashFlowStatement.getCashFlowStatement());
            int oldFreeCF = financeManager.cooperCashFlowStatement.cashFlowStatement.get(oldIndex);
            int differenceFreeCF = newFreeCF - oldFreeCF;
            double rateOfGrowth = (differenceFreeCF / (double) oldFreeCF) * 100.0;
            double finalGrowthValue = financeManager.createProjection(newFreeCF, rateOfGrowth, years);
            Ui.printProjections(finalGrowthValue, financeManager.cooperProjection.getProjection());
            Projection.getProjection().clear();
        } else {
            Ui.showListNotFoundException();
        }


    }
}
