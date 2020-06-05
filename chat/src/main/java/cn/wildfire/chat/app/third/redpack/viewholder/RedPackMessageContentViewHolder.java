package cn.wildfire.chat.app.third.redpack.viewholder;

import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.lqr.emoji.MoonUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.wildfire.chat.app.main.model.MainModel;
import cn.wildfire.chat.app.third.redpack.data.RedPackMsgVO;
import cn.wildfire.chat.kit.ChatManagerHolder;
import cn.wildfire.chat.kit.WfcWebViewActivity;
import cn.wildfire.chat.kit.annotation.EnableContextMenu;
import cn.wildfire.chat.kit.annotation.MessageContentType;
import cn.wildfire.chat.kit.annotation.SendLayoutRes;
import cn.wildfire.chat.kit.conversation.ConversationFragment;
import cn.wildfire.chat.kit.conversation.message.model.UiMessage;
import cn.wildfire.chat.kit.conversation.message.viewholder.NormalMessageContentViewHolder;
import cn.wildfirechat.chat.R;
import cn.wildfirechat.message.RedpackMessageContent;
import cn.wildfirechat.message.TextMessageContent;

/*
@MessageContentType(value = {
        RedpackMessageContent.class
})
*/

@MessageContentType(RedpackMessageContent.class)
@SendLayoutRes(resId = R.layout.conversation_item_redpack_receive)
public class RedPackMessageContentViewHolder extends NormalMessageContentViewHolder {
    @BindView(R.id.textView)
    TextView contentTextView;

    public RedPackMessageContentViewHolder(ConversationFragment fragment, RecyclerView.Adapter adapter, View itemView){
        super(fragment, adapter, itemView);
    }

    private RedPackMsgVO coveRPVO(){
        RedpackMessageContent redpackMessage = (RedpackMessageContent) message.message.content;
        String _con = redpackMessage.getContent();
        Gson gson = new Gson();
        try{
            RedPackMsgVO obj = gson.fromJson(_con, RedPackMsgVO.class);
            return obj;
        } catch (Exception e) {
            //e.printStackTrace();
            Log.d("RedPackMsgVO Error: ", e.toString());
        }
        return null;
    }

    @Override
    public  void onBind(UiMessage message){
        RedpackMessageContent redpackMessage = (RedpackMessageContent) message.message.content;
        RedPackMsgVO obj = coveRPVO();
        contentTextView.setText(obj.desc);
    }

    @OnClick(R.id.imageReadpack)
    public void onClick(View view) {
        RedPackMsgVO obj = coveRPVO();
        //Toast.makeText(fragment.getContext(), "onRedPackMessage click: " + obj.id + "," + obj.desc, Toast.LENGTH_SHORT).show();

        String clientId = ChatManagerHolder.gChatManager.getClientId();
        String userId = ChatManagerHolder.gChatManager.getUserId();

        String url = MainModel.clientConfig.getOpenredpack() + "?cid=" + clientId + "&uid=" + userId + "&rpid="+obj.id;
        WfcWebViewActivity.loadUrl(fragment.getContext(), "拆红包", url);

    }

}
