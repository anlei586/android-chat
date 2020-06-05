package cn.wildfire.chat.kit.conversation.ext;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import cn.wildfire.chat.app.main.model.MainModel;
import cn.wildfire.chat.kit.ChatManagerHolder;
import cn.wildfire.chat.kit.WfcWebViewActivity;
import cn.wildfire.chat.kit.annotation.ExtContextMenuItem;
import cn.wildfire.chat.kit.conversation.ext.core.ConversationExt;
import cn.wildfire.chat.kit.third.utils.UIUtils;
import cn.wildfirechat.chat.R;
import cn.wildfirechat.model.Conversation;
import cn.wildfirechat.remote.ChatManager;

public class RedpackExt extends ConversationExt {
    @ExtContextMenuItem(title = "红包")
    public void pickRedpack(View containerView, Conversation conversation){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

        String clientId = ChatManagerHolder.gChatManager.getClientId();
        String userId = ChatManagerHolder.gChatManager.getUserId();
        String token = ChatManager.Instance().token;
        Conversation.ConversationType ctype = conversation.type;
        String ctarget = conversation.target;
        int cline = conversation.line;

        String url = MainModel.clientConfig.getSendredpack() + "?cid=" + clientId + "&uid=" + userId + "&ctype=" + ctype + "&ctarget="+ctarget + "&cline="+cline;
        WfcWebViewActivity.loadUrl(containerView.getContext(), "发红包", url);

    }

    @Override
    public int priority() {
        return 100;
    }

    @Override
    public int iconResId() {
        return R.mipmap.redpack;
    }

    @Override
    public String title(Context context) {
        return "红包";
    }
}
