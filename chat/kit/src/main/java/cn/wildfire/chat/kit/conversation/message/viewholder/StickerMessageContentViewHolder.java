package cn.wildfire.chat.kit.conversation.message.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import butterknife.BindView;
import cn.wildfire.chat.kit.GlideApp;
import cn.wildfire.chat.kit.annotation.EnableContextMenu;
import cn.wildfire.chat.kit.annotation.MessageContentType;
import cn.wildfire.chat.kit.annotation.ReceiveLayoutRes;
import cn.wildfire.chat.kit.annotation.SendLayoutRes;
import cn.wildfire.chat.kit.conversation.ConversationFragment;
import cn.wildfire.chat.kit.conversation.message.model.UiMessage;
import cn.wildfire.chat.kit.third.utils.UIUtils;
import cn.wildfirechat.chat.R;
import cn.wildfirechat.message.StickerMessageContent;

@MessageContentType(StickerMessageContent.class)
@SendLayoutRes(resId = R.layout.conversation_item_sticker_send)
@ReceiveLayoutRes(resId = R.layout.conversation_item_sticker_receive)
@EnableContextMenu
public class StickerMessageContentViewHolder extends NormalMessageContentViewHolder {
    private String path;
    @BindView(R.id.stickerImageView)
    ImageView imageView;

    public StickerMessageContentViewHolder(ConversationFragment fragment, RecyclerView.Adapter adapter, View itemView) {
        super(fragment, adapter, itemView);
    }

    @Override
    public void onBind(UiMessage message) {
        StickerMessageContent stickerMessage = (StickerMessageContent) message.message.content;
        //imageView.getLayoutParams().width = UIUtils.dip2Px(stickerMessage.width > 150 ? 150 : stickerMessage.width);
        //imageView.getLayoutParams().height = UIUtils.dip2Px(stickerMessage.height > 150 ? 150 : stickerMessage.height);

        int width3 = UIUtils.px2dip(UIUtils.getDisplayWidth());
        int height3= UIUtils.px2dip(UIUtils.getDisplayHeight());

        //float scale = UIUtils.scaleDensity;
        int subpar = UIUtils.px2dip(200);
        float subbs = (float)(subpar)/(float)(width3);
        height3 = (int)(height3 - height3*subbs);
        height3 = UIUtils.dip2Px(height3);
/*
        width3 = stickerMessage.width > width3 ? width3 : stickerMessage.width;
        width3 = UIUtils.dip2Px(width3 - 200);

        height3 = stickerMessage.height > height3 ? height3 : stickerMessage.height;
        height3 = UIUtils.dip2Px(height3 - subb);
*/
        width3 = width3 - subpar;
        //width3 = stickerMessage.width > width3 ? width3 : stickerMessage.width;
        //height3 = stickerMessage.height > height3 ? height3 : UIUtils.dip2Px(stickerMessage.height);
        //imageView.getLayoutParams().width = width3;
        //imageView.getLayoutParams().height = UIUtils.dip2Px(stickerMessage.height);

        int screenWidth = (UIUtils.getDisplayWidth()); // 获取屏幕宽度
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;//UIUtils.dip2Px(width3);
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        imageView.setLayoutParams(lp);

        imageView.setMaxWidth(UIUtils.dip2Px(width3));
        imageView.setMaxHeight(UIUtils.dip2Px(stickerMessage.height));

        imageView.getLayoutParams().width = UIUtils.dip2Px(stickerMessage.width > width3 ? width3 : stickerMessage.width);
//        imageView.getLayoutParams().height = UIUtils.dip2Px(stickerMessage.height > height3 ? height3 : stickerMessage.height);
//        imageView.getLayoutParams().height = stickerMessage.height > height3 ? height3 : UIUtils.dip2Px(stickerMessage.height);

        if (!TextUtils.isEmpty(stickerMessage.localPath)) {
            if (stickerMessage.localPath.equals(path)) {
                return;
            }
            GlideApp.with(fragment).load(stickerMessage.localPath)
                    .into(imageView);
            path = stickerMessage.localPath;
        } else {
            CircularProgressDrawable progressDrawable = new CircularProgressDrawable(fragment.getContext());
            progressDrawable.setStyle(CircularProgressDrawable.DEFAULT);
            progressDrawable.start();
            GlideApp.with(fragment)
                    .load(stickerMessage.remoteUrl)
                    .placeholder(progressDrawable)
                    .into(imageView);
        }
    }

    // 其实也没啥用，有itemView了，可以直接setXXXListener了, 只是简化了，不用调用setXXXListener
    // 更复杂的，比如设置播放进度条的滑动等，在通过itemView设置相关listener吧
    public void onClick(View view) {
        // TODO
    }

}
