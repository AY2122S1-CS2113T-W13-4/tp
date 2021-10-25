package cooper.command;

import cooper.exceptions.InvalidAccessException;
import cooper.exceptions.InvalidForumPostIdException;
import cooper.storage.StorageManager;
import cooper.ui.Ui;
import cooper.forum.ForumManager;
import cooper.verification.SignInDetails;
import cooper.verification.UserRole;
import cooper.resources.ResourcesManager;

/**
 * The child class of Command that handles the 'post list' command specifically.
 */
public class PostListCommand extends Command {
    private final int postId;

    public PostListCommand(int postId) {
        super();
        this.postId = postId;
    }

    /**
     * The override function for executing the 'add' command, calls for 'add' and subsequently
     * printing the status to the command line if and only if
     * the command is being accessed by an 'admin' level user.
     * @param signInDetails Sign in details of user to provide correct access
     * @param resourcesManager Provides access to manipulate data in the cOOPer's {@code FinanceManager},
     *                         {@code MeetingsManager} and {@code ForumManager}
     * @param storageManager Stores data which has just been added
     */
    @Override
    public void execute(SignInDetails signInDetails, ResourcesManager resourcesManager,
                        StorageManager storageManager) throws InvalidAccessException {
        UserRole userRole = signInDetails.getUserRole();
        ForumManager forumManager = resourcesManager.getForumManager(userRole);
        if (forumManager != null) {
            if (postId == -1) {
                forumManager.listPosts();
            } else {
                try {
                    forumManager.listPost(postId - 1);
                } catch (InvalidForumPostIdException e) {
                    Ui.printInvalidForumPostIndexError();
                }
            }
        } else {
            Ui.printEmployeeHelp();
            Ui.printGeneralHelp();
            Ui.printAdminHelp();
            throw new InvalidAccessException();
        }
    }
}
