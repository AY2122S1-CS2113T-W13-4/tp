package cooper.command;

import cooper.exceptions.InvalidAccessException;
import cooper.finance.CashFlow;
import cooper.finance.FinanceCommand;
import cooper.finance.FinanceManager;
import cooper.parser.CommandParser;
import cooper.resources.ResourcesManager;
import cooper.storage.StorageManager;
import cooper.ui.Ui;
import cooper.verification.SignInDetails;
import cooper.verification.UserRole;

public class CfCommand extends Command {

    public CfCommand() {
        super();
    }

    @Override
    public void execute(SignInDetails signInDetails, ResourcesManager resourcesManager,
                        StorageManager storageManager) throws InvalidAccessException {
        CommandParser.financeFlag = FinanceCommand.CF;
        UserRole userRole = signInDetails.getUserRole();
        FinanceManager financeManager = resourcesManager.getFinanceManager(userRole);
        if (financeManager == null) {
            Ui.printAdminHelp();
            Ui.printGeneralHelp();
            throw new InvalidAccessException();
        }
        resetCashFlowStatement(financeManager);
        Ui.initiateCashFlowStatement();
    }

    private void resetCashFlowStatement(FinanceManager financeManager) {
        CashFlow cashFlow = financeManager.cooperCashFlowStatement;
        CashFlow.cashFlowStage = 0;
        cashFlow.getCashFlowStatement().clear();
    }
}