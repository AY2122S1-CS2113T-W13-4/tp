package cooper.resources;

import cooper.finance.FinanceManager;
import cooper.meetings.MeetingManager;
import cooper.forum.ForumManager;
import cooper.storage.StorageManager;
import cooper.verification.UserRole;
import cooper.verification.Verifier;

public class ResourcesManager {


    private final FinanceManager cooperFinanceManager;
    private final MeetingManager cooperMeetingManager;
    private final ForumManager cooperForumManager;

    public ResourcesManager() {
        cooperFinanceManager = new FinanceManager();
        cooperMeetingManager = new MeetingManager();
        cooperForumManager = new ForumManager();
    }

    public FinanceManager getFinanceManager() {
        return cooperFinanceManager;
    }

    public FinanceManager getFinanceManager(UserRole userRole) {
        if (userRole.equals(UserRole.ADMIN)) {
            return cooperFinanceManager;
        } else {
            return null;
        }
    }

    public MeetingManager getMeetingManager() {
        return cooperMeetingManager;
    }

    public MeetingManager getMeetingManager(UserRole userRole) {
        return cooperMeetingManager;
    }

    public ForumManager getForumManager(UserRole userRole) {
        return cooperForumManager;
    }
}