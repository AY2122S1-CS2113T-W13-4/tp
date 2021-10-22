package cooper.command;

import cooper.finance.FinanceManager;
import cooper.meetings.MeetingManager;
import cooper.storage.StorageManager;
import cooper.ui.Ui;
import cooper.verification.SignInDetails;

public class AvailabilityCommand extends Command {

    @Override
    public void execute(SignInDetails signInDetails, FinanceManager financeManager, MeetingManager meetingManager, StorageManager storageManager) {
        Ui.printAvailabilities(meetingManager.getAvailability());
    }
}