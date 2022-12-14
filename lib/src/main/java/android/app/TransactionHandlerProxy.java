package android.app;

import android.app.ActivityThread.ActivityClientRecord;
import android.app.servertransaction.PendingTransactionActions;
import android.app.servertransaction.TransactionExecutor;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Log;
import android.util.MergedConfiguration;
import android.view.DisplayAdjustments;

import com.lody.virtual.client.VClientImpl;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.ipc.VActivityManager;
import com.lody.virtual.helper.utils.ComponentUtils;
import com.lody.virtual.remote.InstalledAppInfo;
import com.lody.virtual.remote.StubActivityRecord;

import java.util.List;
import java.util.Map;

import mirror.android.app.ActivityManagerNative;
import mirror.android.app.IActivityManager;
import mirror.com.android.internal.content.ReferrerIntent;


/**
 * @author weishu
 * @date 2018/8/7.
 */
public class TransactionHandlerProxy extends ClientTransactionHandler {

    private static final String TAG = "TransactionHandlerProxy";

    private ClientTransactionHandler originalHandler;

    public TransactionHandlerProxy(ClientTransactionHandler originalHandler) {
        this.originalHandler = originalHandler;
    }

    @Override
    TransactionExecutor getTransactionExecutor() {
        return originalHandler.getTransactionExecutor();
    }

    @Override
    void sendMessage(int what, Object obj) {
        originalHandler.sendMessage(what, obj);
    }

    @Override
    public void updatePendingConfiguration(Configuration config) {
        originalHandler.updatePendingConfiguration(config);
    }

    @Override
    public void updateProcessState(int processState, boolean fromIpc) {
        originalHandler.updateProcessState(processState, fromIpc);
    }

    @Override
    public void handleDestroyActivity(IBinder token, boolean finishing, int configChanges, boolean getNonConfigInstance, String reason) {
        originalHandler.handleDestroyActivity(token, finishing, configChanges, getNonConfigInstance, reason);
    }

    @Override
    public void handlePauseActivity(IBinder token, boolean finished, boolean userLeaving, int configChanges, PendingTransactionActions pendingActions, String reason) {
        originalHandler.handlePauseActivity(token, finished, userLeaving, configChanges, pendingActions, reason);
    }

    @Override
    public void handleDestroyActivity(ActivityClientRecord r, boolean finishing, int configChanges, boolean getNonConfigInstance, String reason) {
        originalHandler.handleDestroyActivity(r, finishing, configChanges, getNonConfigInstance, reason);
    }

    @Override
    public void handlePauseActivity(ActivityClientRecord r, boolean finished, boolean userLeaving, int configChanges, PendingTransactionActions pendingActions, String reason) {
        originalHandler.handlePauseActivity(r, finished, userLeaving, configChanges, pendingActions, reason);
    }

    @Override
    public void handleResumeActivity(IBinder token, boolean finalStateRequest, boolean isForward, String reason) {
        originalHandler.handleResumeActivity(token, finalStateRequest, isForward, reason);
    }

    @Override
    public void handleResumeActivity(ActivityClientRecord record, boolean finalStateRequest, boolean isForward, String reason) {
        originalHandler.handleResumeActivity(record, finalStateRequest, isForward, reason);
    }

    @Override
    public void handleStopActivity(IBinder token, boolean show, int configChanges, PendingTransactionActions pendingActions, boolean finalStateRequest, String reason) {
        originalHandler.handleStopActivity(token, show, configChanges, pendingActions, finalStateRequest, reason);
    }

    @Override
    public void handleStopActivity(IBinder token, int configChanges, PendingTransactionActions pendingActions, boolean finalStateRequest, String reason) {
        originalHandler.handleStopActivity(token, configChanges, pendingActions, finalStateRequest, reason);
    }

    @Override
    public void handleStopActivity(ActivityClientRecord r, int configChanges, PendingTransactionActions pendingActions, boolean finalStateRequest, String reason) {
        originalHandler.handleStopActivity(r, configChanges, pendingActions, finalStateRequest, reason);
    }

    @Override
    public void reportStop(PendingTransactionActions pendingActions) {
        originalHandler.reportStop(pendingActions);
    }

    @Override
    public void performRestartActivity(IBinder token, boolean start) {
        originalHandler.performRestartActivity(token, start);
    }

    @Override
    public void performRestartActivity(ActivityClientRecord r, boolean start) {
        originalHandler.performRestartActivity(r, start);
    }

    @Override
    public void handleActivityConfigurationChanged(IBinder activityToken, Configuration overrideConfig, int displayId) {
        originalHandler.handleActivityConfigurationChanged(activityToken, overrideConfig, displayId);
    }

    @Override
    public void handleActivityConfigurationChanged(ActivityClientRecord r, Configuration overrideConfig, int displayId) {
        originalHandler.handleActivityConfigurationChanged(r, overrideConfig, displayId);
    }

    @Override
    public void handleSendResult(IBinder token, List results, String reason) {
        originalHandler.handleSendResult(token, results, reason);
    }

    @Override
    public void handleSendResult(ActivityClientRecord r, List results, String reason) {
        originalHandler.handleSendResult(r, results, reason);
    }

    @Override
    public void handleMultiWindowModeChanged(IBinder token, boolean isInMultiWindowMode, Configuration overrideConfig) {
        originalHandler.handleMultiWindowModeChanged(token, isInMultiWindowMode, overrideConfig);
    }

    @Override
    public void handleNewIntent(IBinder token, List intents, boolean andPause) {
        originalHandler.handleNewIntent(token, intents, andPause);
    }

    @Override
    public void handlePictureInPictureModeChanged(IBinder token, boolean isInPipMode, Configuration overrideConfig) {
        originalHandler.handlePictureInPictureModeChanged(token, isInPipMode, overrideConfig);
    }

    @Override
    public void handlePictureInPictureRequested(IBinder token) {
        originalHandler.handlePictureInPictureRequested(token);
    }

    @Override
    public void handlePictureInPictureRequested(ActivityClientRecord r) {
        originalHandler.handlePictureInPictureRequested(r);
    }

    @Override
    public void handlePictureInPictureStateChanged(ActivityClientRecord r, Parcelable pipState) {
        originalHandler.handlePictureInPictureStateChanged(r, pipState);
    }

    @Override
    public boolean isHandleSplashScreenExit(IBinder token) {
        return originalHandler.isHandleSplashScreenExit(token);
    }

    @Override
    public void handleAttachSplashScreenView(ActivityClientRecord r, Parcelable parcelable) {
        originalHandler.handleAttachSplashScreenView(r, parcelable);
    }

    @Override
    public void handOverSplashScreenView(ActivityClientRecord r) {
        originalHandler.handOverSplashScreenView(r);
    }

    @Override
    public void handleWindowVisibility(IBinder token, boolean show) {
        originalHandler.handleWindowVisibility(token, show);
    }

    @Override
    public Activity handleLaunchActivity(ActivityClientRecord r, PendingTransactionActions pendingActions, Intent customIntent) {

        Intent stubIntent = mirror.android.app.ActivityThread.ActivityClientRecord.intent.get(r);
        StubActivityRecord saveInstance = new StubActivityRecord(stubIntent);
        if (saveInstance.intent == null) {
            Log.i(TAG, "save instance intent is null, return");
            return null;
        }
        Intent intent = saveInstance.intent;
        ComponentName caller = saveInstance.caller;
        IBinder token = mirror.android.app.ActivityThread.ActivityClientRecord.token.get(r);
        ActivityInfo info = saveInstance.info;
        if (VClientImpl.get().getToken() == null) {
            InstalledAppInfo installedAppInfo = VirtualCore.get().getInstalledAppInfo(info.packageName, 0);
            if (installedAppInfo == null) {
                Log.i(TAG, "install app info is null, return");
                return null;
            }
            VActivityManager.get().processRestarted(info.packageName, info.processName, saveInstance.userId);
            // getH().sendMessageAtFrontOfQueue(Message.obtain(msg));
            Log.i(TAG, "restart process, return");
            return handleLaunchActivity(r, pendingActions, customIntent);
        }
        if (!VClientImpl.get().isBound()) {
            VClientImpl.get().bindApplicationForActivity(info.packageName, info.processName, intent);
            // getH().sendMessageAtFrontOfQueue(Message.obtain(msg));
            Log.i(TAG, "rebound application, return");
            return handleLaunchActivity(r, pendingActions, customIntent);
        }
        int taskId = IActivityManager.getTaskForActivity.call(
                ActivityManagerNative.getDefault.call(),
                token,
                false
        );

        Object packageInfo = mirror.android.app.ActivityThread.ActivityClientRecord.packageInfo.get(r);

        mirror.android.app.ActivityThread.ActivityClientRecord.packageInfo.set(r, null);

        VActivityManager.get().onActivityCreate(ComponentUtils.toComponentName(info), caller, token, info, intent, ComponentUtils.getTaskAffinity(info), taskId, info.launchMode, info.flags);
        ClassLoader appClassLoader = VClientImpl.get().getClassLoader(info.applicationInfo);
        intent.setExtrasClassLoader(appClassLoader);
        mirror.android.app.ActivityThread.ActivityClientRecord.intent.set(r, intent);
        mirror.android.app.ActivityThread.ActivityClientRecord.activityInfo.set(r, info);

        return originalHandler.handleLaunchActivity(r, pendingActions, customIntent);
    }

    @Override
    public void handleStartActivity(ActivityClientRecord r, PendingTransactionActions pendingActions, ActivityOptions options) {
        originalHandler.handleStartActivity(r, pendingActions, options);
    }

    @Override
    public void handleStartActivity(ActivityClientRecord r, PendingTransactionActions pendingActions) {
        originalHandler.handleStartActivity(r, pendingActions);
    }

    @Override
    public void handleStartActivity(IBinder binder, PendingTransactionActions pendingActions) {
        originalHandler.handleStartActivity(binder, pendingActions);
    }

    @Override
    public LoadedApk getPackageInfoNoCheck(ApplicationInfo ai, CompatibilityInfo compatInfo) {
        return originalHandler.getPackageInfoNoCheck(ai, compatInfo);
    }

    @Override
    public void handleConfigurationChanged(Configuration config) {
        originalHandler.handleConfigurationChanged(config);
    }

    @Override
    public void handleFixedRotationAdjustments(IBinder token, DisplayAdjustments.FixedRotationAdjustments fixedRotationAdjustments) {
        originalHandler.handleFixedRotationAdjustments(token, fixedRotationAdjustments);
    }

    @Override
    public void addLaunchingActivity(IBinder token, ActivityClientRecord activity) {
        originalHandler.addLaunchingActivity(token, activity);
    }

    @Override
    public ActivityClientRecord getLaunchingActivity(IBinder token) {
        return originalHandler.getLaunchingActivity(token);
    }

    @Override
    public void removeLaunchingActivity(IBinder token) {
        originalHandler.removeLaunchingActivity(token);
    }

    @Override
    public ActivityClientRecord getActivityClient(IBinder token) {
        Log.i(TAG, "getActivityClient : " + token);
        return originalHandler.getActivityClient(token);
    }

    @Override
    public ActivityClientRecord prepareRelaunchActivity(IBinder token, List pendingResults, List pendingNewIntents, int configChanges, MergedConfiguration config, boolean preserveWindow) {
        return originalHandler.prepareRelaunchActivity(token, pendingResults, pendingNewIntents, configChanges, config, preserveWindow);
    }

    @Override
    public void handleRelaunchActivity(ActivityClientRecord r, PendingTransactionActions pendingActions) {
        originalHandler.handleRelaunchActivity(r, pendingActions);
    }

    @Override
    public void reportRelaunch(IBinder token, PendingTransactionActions pendingActions) {
        originalHandler.reportRelaunch(token, pendingActions);
    }

    @Override
    public Map getActivitiesToBeDestroyed() {
        return originalHandler.getActivitiesToBeDestroyed();
    }

    @Override
    public Activity getActivity(IBinder token) {
        return originalHandler.getActivity(token);
    }

    @Override
    public void updatePendingActivityConfiguration(IBinder arg1, Configuration arg2) {
        originalHandler.updatePendingActivityConfiguration(arg1, arg2);
    }

    @Override
    public void handleTopResumedActivityChanged(IBinder arg1, boolean arg2, String arg3) {
        originalHandler.handleTopResumedActivityChanged(arg1, arg2, arg3);
    }

    @Override
    public void handleTopResumedActivityChanged(ActivityClientRecord record, boolean arg2, String arg3) {
        originalHandler.handleTopResumedActivityChanged(record, arg2, arg3);
    }

    @Override
    public void countLaunchingActivities(int num) {
        originalHandler.countLaunchingActivities(num);
    }

    @Override
    public void handleNewIntent(IBinder token, List intents) {
        originalHandler.handleNewIntent(token, intents);
    }

    @Override
    public void handleNewIntent(ActivityClientRecord r, List<ReferrerIntent> intents) {
        originalHandler.handleNewIntent(r, intents);
    }

}
